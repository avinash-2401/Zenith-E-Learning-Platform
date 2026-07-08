package com.avinash.book_network.service;

import com.avinash.book_network.dto.DashboardResponseDTO;
import com.avinash.book_network.repository.CourseRepository;
import com.avinash.book_network.repository.EnrollmentRepository;
import com.avinash.book_network.repository.QuizRepository;
import com.avinash.book_network.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final QuizRepository quizRepository;

    public DashboardResponseDTO getDashboard() {

        Long totalUsers = userRepository.count();

        Long totalCourses = courseRepository.count();

        Long totalEnrollments = enrollmentRepository.count();

        Long totalQuizzes = quizRepository.count();

        return new DashboardResponseDTO(
                totalUsers,
                totalCourses,
                totalEnrollments,
                totalQuizzes
        );
    }
}