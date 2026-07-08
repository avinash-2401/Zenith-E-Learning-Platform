package com.avinash.book_network.service;

import com.avinash.book_network.GlobalExceptionHandling.ResourceNotFoundException;
import com.avinash.book_network.dto.CourseRequestDTO;
import com.avinash.book_network.dto.CourseResponseDTO;
import com.avinash.book_network.entity.Course;
import com.avinash.book_network.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public CourseResponseDTO createCourse(CourseRequestDTO request) {

        Course course = new Course();

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setInstructorName(request.getInstructorName());
        course.setPrice(request.getPrice());
        course.setCategory(request.getCategory());
        course.setThumbnailUrl(request.getThumbnailUrl());

        Course savedCourse = courseRepository.save(course);

        return CourseResponseDTO.builder()
                .id(savedCourse.getId())
                .title(savedCourse.getTitle())
                .description(savedCourse.getDescription())
                .instructorName(savedCourse.getInstructorName())
                .price(savedCourse.getPrice())
                .category(savedCourse.getCategory())
                .thumbnailUrl(savedCourse.getThumbnailUrl())
                .createdAt(savedCourse.getCreatedAt())
                .build();
    }

    public List<CourseResponseDTO> getAllCourse() {

        List<Course> courses = courseRepository.findAll();

        return courses.stream()
                .map(course -> CourseResponseDTO.builder()
                        .id(course.getId())
                        .title(course.getTitle())
                        .description(course.getDescription())
                        .instructorName(course.getInstructorName())
                        .price(course.getPrice())
                        .category(course.getCategory())
                        .thumbnailUrl(course.getThumbnailUrl())
                        .createdAt(course.getCreatedAt())
                        .build())
                .toList();
    }

    public CourseResponseDTO findCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with this id: " + id));

        return CourseResponseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .instructorName(course.getInstructorName())
                .price(course.getPrice())
                .category(course.getCategory())
                .thumbnailUrl(course.getThumbnailUrl())
                .createdAt(course.getCreatedAt())
                .build();
    }

    public CourseResponseDTO updateCourseById(Long id , CourseRequestDTO request ){
        Course course = courseRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Course Not Found With This Id So We can not Update"));

        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setInstructorName(request.getInstructorName());
        course.setPrice(request.getPrice());
        course.setCategory(request.getCategory());
        course.setThumbnailUrl(request.getThumbnailUrl());

        Course updatedCourse = courseRepository.save(course);

        return CourseResponseDTO.builder()
                .id(updatedCourse.getId())
                .price(updatedCourse.getPrice())
                .title(updatedCourse.getTitle())
                .category(updatedCourse.getCategory())
                .description(updatedCourse.getDescription())
                .thumbnailUrl(updatedCourse.getThumbnailUrl())
                .createdAt(updatedCourse.getCreatedAt())
                .instructorName(updatedCourse.getInstructorName())
                .build();
    }

    public String deleteCourseById(Long id){
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        courseRepository.deleteById(id);
        return "Course Deleted Successfully";

    }
}