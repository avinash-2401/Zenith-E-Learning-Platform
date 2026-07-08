package com.avinash.book_network.controller;

import com.avinash.book_network.dto.QuizResultDTO;
import com.avinash.book_network.dto.QuizResultResponseDTO;
import com.avinash.book_network.dto.QuizSubmissionDTO;
import com.avinash.book_network.service.QuizResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/quiz-results")
@RequiredArgsConstructor
public class QuizResultController {

    private final QuizResultService quizResultService;

    @PostMapping("/submit")
    public ResponseEntity<QuizResultDTO> submitQuiz(@RequestBody QuizSubmissionDTO request) {

        return ResponseEntity.ok(quizResultService.submitQuiz(request));
    }

    @GetMapping("/my-results")
    public ResponseEntity<List<QuizResultResponseDTO>> myResults() {

        return ResponseEntity.ok(quizResultService.myResults());
    }
}