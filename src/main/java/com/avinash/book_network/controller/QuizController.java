package com.avinash.book_network.controller;

import com.avinash.book_network.dto.QuizRequestDTO;
import com.avinash.book_network.dto.QuizResponseDTO;
import com.avinash.book_network.dto.QuizResultDTO;
import com.avinash.book_network.dto.QuizSubmissionDTO;
import com.avinash.book_network.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("create")
    public ResponseEntity<QuizResponseDTO> createQuiz(@RequestBody QuizRequestDTO requestDTO){
        return ResponseEntity.ok(quizService.createQuiz(requestDTO));
    }
    @GetMapping
    public ResponseEntity<List<QuizResponseDTO>> getAllQuiz() {
        return ResponseEntity.ok(quizService.getAllQuiz()
        );
    }

}
