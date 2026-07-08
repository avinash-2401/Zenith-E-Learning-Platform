package com.avinash.book_network.repository;

import com.avinash.book_network.entity.Course;
import com.avinash.book_network.entity.Enrollment;
import com.avinash.book_network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment ,Long> {
    boolean existsByUserAndCourse(
            User user,
            Course course
    );
    List<Enrollment> findByUser(User user);

}
