package com.avinash.book_network.dto;

import com.avinash.book_network.entity.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponseDTO {

    private Long enrollmentId;
    private Long courseId;
    private String studentEmail;
    private String courseTitle;
    private EnrollmentStatus status;
    private LocalDateTime enrolledAt;
}
