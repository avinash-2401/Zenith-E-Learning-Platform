package com.avinash.book_network.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResultResponseDTO {

    private String quizTitle;

    private Integer score;

    private Integer totalQuestions;

    private LocalDateTime submittedAt;
}
