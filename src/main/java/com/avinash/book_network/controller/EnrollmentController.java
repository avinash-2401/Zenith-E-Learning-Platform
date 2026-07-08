package com.avinash.book_network.controller;

import com.avinash.book_network.dto.EnrollmentResponseDTO;
import com.avinash.book_network.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/course/{courseId}")
    public ResponseEntity<String> enrollmentInCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(
                enrollmentService.enrollmentCourse(courseId)
        );

    }
    @GetMapping("/my-courses")
    public ResponseEntity<List<EnrollmentResponseDTO>> myCourses(){
        return ResponseEntity.ok(
                enrollmentService.myCourse());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments(){
        return ResponseEntity.ok(
                enrollmentService.getAllEnrollments());
    }
}
