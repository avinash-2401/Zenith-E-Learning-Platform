package com.avinash.book_network.controller;

import com.avinash.book_network.dto.QuestionRequestDTO;
import com.avinash.book_network.dto.QuestionResponseDTO;
import com.avinash.book_network.dto.QuizResponseDTO;
import com.avinash.book_network.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<QuestionResponseDTO> createQuestion(@Valid @RequestBody QuestionRequestDTO requestDTO) {
        return ResponseEntity.ok(questionService.createQuestion(requestDTO));
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuestionResponseDTO>> getQuestionsByQuiz(@PathVariable Long quizId) {
        return ResponseEntity.ok(questionService.getQuestionsByQuiz(quizId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionResponseDTO> updateQuestion
            (@PathVariable Long questionId, @RequestBody QuestionRequestDTO requestDTO) {

        return ResponseEntity.ok(questionService.updateQuestion(questionId, requestDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {

        return ResponseEntity.ok(questionService.deleteQuestion(questionId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/bulk")
    public ResponseEntity<String> createQuestionsBulk(@RequestBody List<QuestionRequestDTO> requestDTOs){

        return ResponseEntity.ok(questionService.createQuestionsBulk(requestDTOs));
    }


}