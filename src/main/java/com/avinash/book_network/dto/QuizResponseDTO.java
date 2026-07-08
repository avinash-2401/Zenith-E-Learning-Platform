package com.avinash.book_network.dto;

import com.avinash.book_network.entity.QuizLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponseDTO {

    private Long id;
    private String title;
    private String description;
    private QuizLevel level;
    private LocalDateTime createdAt;
}
