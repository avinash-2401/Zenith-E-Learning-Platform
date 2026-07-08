package com.avinash.book_network.service;

import com.avinash.book_network.GlobalExceptionHandling.ResourceNotFoundException;
import com.avinash.book_network.dto.EnrollmentResponseDTO;
import com.avinash.book_network.entity.Course;
import com.avinash.book_network.entity.Enrollment;
import com.avinash.book_network.entity.EnrollmentStatus;
import com.avinash.book_network.entity.User;
import com.avinash.book_network.repository.CourseRepository;
import com.avinash.book_network.repository.EnrollmentRepository;
import com.avinash.book_network.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public String enrollmentCourse(Long courseId){

        // JWT token se logged-in user ka email nikl raha h .
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("user not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with is id"));

        if(enrollmentRepository.existsByUserAndCourse(user, course)){
            throw new RuntimeException(
                    "You are already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());
        enrollment.setStatus(EnrollmentStatus.ACTIVE);

        enrollmentRepository.save(enrollment);

        return "Course Enrolled Successfully";
    }

public List<EnrollmentResponseDTO> myCourse(){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user= userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found.."));

       List<Enrollment> enrollments = enrollmentRepository.findByUser(user);

            return enrollments.stream()
                    .map(enrollment -> EnrollmentResponseDTO.builder()
                            .enrollmentId(enrollment.getId())
                            .courseId(enrollment.getCourse().getId())
                            .courseTitle(enrollment.getCourse().getTitle())
                            .status(enrollment.getStatus())
                            .enrolledAt(enrollment.getEnrolledAt())
                            .build())
                    .toList();
}
    public List<EnrollmentResponseDTO> getAllEnrollments(){
        List<Enrollment> enrollments =
                enrollmentRepository.findAll();

        return enrollments.stream()
                .map(enrollment ->
                        EnrollmentResponseDTO.builder()
                                .enrollmentId(enrollment.getId())
                                .courseId(enrollment.getCourse().getId())
                                .courseTitle(enrollment.getCourse().getTitle())
                                .status(enrollment.getStatus())
                                .enrolledAt(enrollment.getEnrolledAt())
                                .studentEmail(
                                        enrollment.getUser().getEmail()
                                )
                                .build())
                .toList();
    }


}












