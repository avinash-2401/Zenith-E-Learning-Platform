package com.avinash.book_network.service;

import com.avinash.book_network.GlobalExceptionHandling.ResourceNotFoundException;
import com.avinash.book_network.dto.QuizResultDTO;
import com.avinash.book_network.dto.QuizResultResponseDTO;
import com.avinash.book_network.dto.QuizSubmissionDTO;
import com.avinash.book_network.entity.Question;
import com.avinash.book_network.entity.Quiz;
import com.avinash.book_network.entity.QuizResult;
import com.avinash.book_network.entity.User;
import com.avinash.book_network.repository.QuestionRepository;
import com.avinash.book_network.repository.QuizRepository;
import com.avinash.book_network.repository.QuizResultRepository;
import com.avinash.book_network.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizResultService {

    private final QuizResultRepository quizResultRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuizResultDTO submitQuiz(QuizSubmissionDTO request) {

        List<Question> questions = questionRepository.findByQuizId(request.getQuizId());

        int score = 0;

        for (Question question : questions) {
            String studentAnswer = request.getAnswers().get(question.getId());

            if (studentAnswer != null && studentAnswer.equalsIgnoreCase(question.getCorrectAnswer())) {
                score++;
            }
        }

        String email = SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        QuizResult result = new QuizResult();

        result.setUser(user);
        result.setQuiz(quiz);
        result.setScore(score);
        result.setTotalQuestions(
                questions.size());

        quizResultRepository.save(result);
        return new QuizResultDTO(score, questions.size());
    }

    public List<QuizResultResponseDTO> myResults() {

        String email = SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        List<QuizResult> results = quizResultRepository.findByUser(user);

        return results.stream()
                .map(result ->
                        QuizResultResponseDTO.builder()
                                .quizTitle(
                                        result.getQuiz().getTitle())
                                .score(result.getScore())
                                .totalQuestions(result.getTotalQuestions())
                                .submittedAt(result.getSubmittedAt())
                                .build())
                .toList();
    }
}