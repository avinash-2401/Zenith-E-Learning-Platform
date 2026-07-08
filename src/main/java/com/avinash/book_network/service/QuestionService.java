package com.avinash.book_network.service;

import com.avinash.book_network.dto.QuestionRequestDTO;
import com.avinash.book_network.dto.QuestionResponseDTO;
import com.avinash.book_network.dto.QuizResponseDTO;
import com.avinash.book_network.entity.Question;
import com.avinash.book_network.entity.Quiz;
import com.avinash.book_network.repository.QuestionRepository;
import com.avinash.book_network.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {


    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public QuestionResponseDTO createQuestion(QuestionRequestDTO requestDTO){
        Quiz quiz = quizRepository.findById(requestDTO.getQuizId())
                .orElseThrow(()->new RuntimeException("Quiz Not found..."));

        Question question = new Question();
        question.setQuestionText(requestDTO.getQuestionText());
        question.setOptionA(requestDTO.getOptionA());
        question.setOptionB(requestDTO.getOptionB());
        question.setOptionC(requestDTO.getOptionC());
        question.setOptionD(requestDTO.getOptionD());
        question.setCorrectAnswer(requestDTO.getCorrectAnswer());
        question.setQuiz(quiz);

        Question savedQuestion = questionRepository.save(question);

        return QuestionResponseDTO.builder()
                .id(savedQuestion.getId())
                .questionText(savedQuestion.getQuestionText())
                .optionA(savedQuestion.getOptionA())
                .optionB(savedQuestion.getOptionB())
                .optionC(savedQuestion.getOptionC())
                .optionD(savedQuestion.getOptionD())
                .quizId(savedQuestion.getQuiz().getId())
                .build();
    }
    public List<QuestionResponseDTO> getQuestionsByQuiz(Long quizId) {

        List<Question> questions = questionRepository.findByQuizId(quizId);

        return questions.stream()
                .map(question ->
                        QuestionResponseDTO.builder()
                                .id(question.getId())
                                .questionText(question.getQuestionText())
                                .optionA(question.getOptionA())
                                .optionB(question.getOptionB())
                                .optionC(question.getOptionC())
                                .optionD(question.getOptionD())
                                .quizId(question.getQuiz().getId())
                                .build()
                )
                .toList();
    }

    public QuestionResponseDTO updateQuestion(Long questionId, QuestionRequestDTO requestDTO) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Quiz quiz = quizRepository.findById(requestDTO.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        question.setQuestionText(requestDTO.getQuestionText());
        question.setOptionA(requestDTO.getOptionA());
        question.setOptionB(requestDTO.getOptionB());
        question.setOptionC(requestDTO.getOptionC());
        question.setOptionD(requestDTO.getOptionD());
        question.setCorrectAnswer(requestDTO.getCorrectAnswer());
        question.setQuiz(quiz);

        Question updatedQuestion =
                questionRepository.save(question);

        return QuestionResponseDTO.builder()
                .id(updatedQuestion.getId())
                .questionText(updatedQuestion.getQuestionText())
                .optionA(updatedQuestion.getOptionA())
                .optionB(updatedQuestion.getOptionB())
                .optionC(updatedQuestion.getOptionC())
                .optionD(updatedQuestion.getOptionD())
                .quizId(updatedQuestion.getQuiz().getId())
                .build();
    }

    public String deleteQuestion(Long questionId) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        questionRepository.delete(question);

        return "Question Deleted Successfully";
    }
    public String createQuestionsBulk(List<QuestionRequestDTO> requestDTOs) {

        List<Question> questions = requestDTOs.stream()
                .map(dto -> {
                    Quiz quiz = quizRepository.findById(dto.getQuizId()).orElseThrow(() ->
                                    new RuntimeException("Quiz not found"));

                    Question question = new Question();

                    question.setQuestionText(dto.getQuestionText());
                    question.setOptionA(dto.getOptionA());
                    question.setOptionB(dto.getOptionB());
                    question.setOptionC(dto.getOptionC());
                    question.setOptionD(dto.getOptionD());
                    question.setCorrectAnswer(dto.getCorrectAnswer());
                    question.setQuiz(quiz);

                    return question;
                }
                )
                .toList();

        questionRepository.saveAll(questions);

        return "Questions Added Successfully";
    }


}

