package com.avinash.book_network.repository;

import com.avinash.book_network.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course , Long> {

}
