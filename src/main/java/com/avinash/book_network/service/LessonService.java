package com.avinash.book_network.service;

import com.avinash.book_network.dto.LessonRequestDTO;
import com.avinash.book_network.dto.LessonResponseDTO;
import com.avinash.book_network.entity.Course;
import com.avinash.book_network.entity.Lesson;
import com.avinash.book_network.repository.CourseRepository;
import com.avinash.book_network.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonResponseDTO createLesson(LessonRequestDTO requestDTO){
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(()-> new RuntimeException("Course not exits.. for this id"));

        Lesson lesson = new Lesson();

        lesson.setTitle(requestDTO.getTitle());
        lesson.setVideoUrl(requestDTO.getVideoUrl());
        lesson.setDuration(requestDTO.getDuration());
        lesson.setCourse(course);

        Lesson savedLesson = lessonRepository.save(lesson);

        return LessonResponseDTO.builder()
                .id(savedLesson.getId())
                .title(savedLesson.getTitle())
                .videoUrl(savedLesson.getVideoUrl())
                .duration(savedLesson.getDuration())
                .courseId(savedLesson.getCourse().getId())
                .courseTitle(savedLesson.getCourse().getTitle())
                .createdAt(savedLesson.getCreatedAt())
                .build();
    }

    public List<LessonResponseDTO> getLessonsByCourse(Long courseId){
        List<Lesson> lessons = lessonRepository.findByCourseId(courseId);

        return lessons.stream()
                .map(lesson ->
                        LessonResponseDTO.builder()
                                .id(lesson.getId())
                                .title(lesson.getTitle())
                                .videoUrl(lesson.getVideoUrl())
                                .duration(lesson.getDuration())
                                .courseId(lesson.getCourse().getId())
                                .courseTitle(lesson.getCourse().getTitle())
                                .createdAt(lesson.getCreatedAt())
                                .build())
                .toList();
    }

    public  String deleteLesson(Long lessonId){
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(()->new RuntimeException("Lesson Not exits.."));
        lessonRepository.deleteById(lessonId);

         return "Lesson Deleted Successful";
    }

    public LessonResponseDTO updateLesson(Long lessonId , LessonRequestDTO requestDTO){
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found with this id"));

        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found with this id"));

        lesson.setTitle(requestDTO.getTitle());
        lesson.setVideoUrl(requestDTO.getVideoUrl());
        lesson.setDuration(requestDTO.getDuration());
        lesson.setCourse(course);

        Lesson updatedLesson = lessonRepository.save(lesson);

        return LessonResponseDTO.builder()
                .id(updatedLesson.getId())
                .title(updatedLesson.getTitle())
                .videoUrl(updatedLesson.getVideoUrl())
                .duration(updatedLesson.getDuration())
                .courseId(updatedLesson.getCourse().getId())
                .courseTitle(updatedLesson.getCourse().getTitle())
                .createdAt(updatedLesson.getCreatedAt())
                .build();
    }

    }






