package com.avinash.book_network.controller;

import com.avinash.book_network.dto.LessonRequestDTO;
import com.avinash.book_network.dto.LessonResponseDTO;
import com.avinash.book_network.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LessonResponseDTO> createLesson(@Valid @RequestBody LessonRequestDTO requestDTO) {
        return ResponseEntity.ok(lessonService.createLesson(requestDTO));
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<LessonResponseDTO>> getLessonsByCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(lessonService.getLessonsByCourse(courseId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLessonById(@PathVariable Long id){
        return ResponseEntity.ok(lessonService.deleteLesson(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{lessonId}")
    public ResponseEntity<LessonResponseDTO> updateLesson
            (@PathVariable Long lessonId, @RequestBody LessonRequestDTO requestDTO){

        return ResponseEntity.ok(lessonService.updateLesson(lessonId, requestDTO));
    }
}
