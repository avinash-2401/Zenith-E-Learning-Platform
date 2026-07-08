package com.avinash.book_network.repository;

import com.avinash.book_network.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson , Long> {

    List<Lesson> findByCourseId(Long courseId);
}
