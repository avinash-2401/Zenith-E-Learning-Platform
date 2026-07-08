package com.avinash.book_network.dto;

import com.avinash.book_network.entity.QuizLevel;
import lombok.Data;

@Data
public class QuizRequestDTO {

    private String title;
    private String description;
    private QuizLevel level;
}
