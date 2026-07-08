package com.avinash.book_network.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardResponseDTO {

    private Long totalUsers;
    private Long totalCourses;
    private Long totalEnrollments;
    private Long totalQuizzes;
}
