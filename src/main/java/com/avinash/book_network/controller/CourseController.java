package com.avinash.book_network.controller;

import com.avinash.book_network.dto.CourseRequestDTO;
import com.avinash.book_network.dto.CourseResponseDTO;
import com.avinash.book_network.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("createcourse")
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO request) {

        return ResponseEntity.ok(courseService.createCourse(request));
    }

    @GetMapping("getAllCourses")
    public ResponseEntity<List<CourseResponseDTO>> getAllCourse(){
        return ResponseEntity.ok(courseService.getAllCourse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> findCourseById(@PathVariable Long id ){
      return ResponseEntity.ok(courseService.findCourseById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id , @RequestBody CourseRequestDTO requestDTO){
        return ResponseEntity.ok(courseService.updateCourseById(id , requestDTO));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> courseDeleteById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.deleteCourseById(id));
    }
}
