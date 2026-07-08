package com.avinash.book_network.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LessonRequestDTO {

    @NotBlank(message = "Lesson title is required")
    private String title;

    @NotBlank(message = "Video URL is required")
    private String videoUrl;

    @NotNull(message = "Duration is required")
    @Min(value = 1,
            message = "Duration must be greater than 0")
    private Integer duration;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}
