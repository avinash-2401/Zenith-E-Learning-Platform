package com.avinash.book_network.dto;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CourseResponseDTO {

    private Long id;

    private String title;

    private String description;

    private String instructorName;

    private Double price;

    private String category;

    private String thumbnailUrl;

    private LocalDateTime createdAt;



}
