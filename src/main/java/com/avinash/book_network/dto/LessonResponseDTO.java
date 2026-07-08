package com.avinash.book_network.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonResponseDTO {

    private Long id;
    private String title;
    private String videoUrl;
    private Integer duration;
    private Long courseId;
    private String courseTitle;
    private LocalDateTime createdAt;
}
