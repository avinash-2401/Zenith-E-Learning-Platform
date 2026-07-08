package com.avinash.book_network.service;

import com.avinash.book_network.dto.QuizRequestDTO;
import com.avinash.book_network.dto.QuizResponseDTO;
import com.avinash.book_network.entity.Quiz;
import com.avinash.book_network.repository.QuestionRepository;
import com.avinash.book_network.repository.QuizRepository;
import com.avinash.book_network.repository.QuizResultRepository;
import com.avinash.book_network.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

     private final QuizRepository quizRepository;
     private final QuestionRepository questionRepository;
    private final QuizResultRepository quizResultRepository;
    private final UserRepository userRepository;

    public QuizResponseDTO createQuiz(QuizRequestDTO requestDTO){

        Quiz quiz = new Quiz();

        quiz.setTitle(requestDTO.getTitle());
        quiz.setDescription(requestDTO.getDescription());
        quiz.setLevel(requestDTO.getLevel());

        Quiz savedQuiz = quizRepository.save(quiz);

        return QuizResponseDTO.builder()
                .id(savedQuiz.getId())
                .title(savedQuiz.getTitle())
                .description(savedQuiz.getDescription())
                .level(savedQuiz.getLevel())
                .createdAt(savedQuiz.getCreatedAt())
                .build();
    }

    public List<QuizResponseDTO> getAllQuiz() {

        List<Quiz> quizzes = quizRepository.findAll();

        return quizzes.stream()
                .map(quiz ->
                        QuizResponseDTO.builder()
                                .id(quiz.getId())
                                .title(quiz.getTitle())
                                .description(quiz.getDescription())
                                .level(quiz.getLevel())
                                .createdAt(quiz.getCreatedAt())
                                .build())
                .toList();
    }


}
