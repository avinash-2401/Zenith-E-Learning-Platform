package com.avinash.book_network.dto;

import lombok.Data;

import java.util.Map;

@Data
public class QuizSubmissionDTO {

    private Long quizId;
    private Map<Long, String> answers;
}