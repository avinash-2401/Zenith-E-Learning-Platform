package com.avinash.book_network.repository;

import com.avinash.book_network.entity.QuizResult;
import com.avinash.book_network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizResultRepository extends JpaRepository<QuizResult , Long> {

    List<QuizResult> findByUser(User user);
}
