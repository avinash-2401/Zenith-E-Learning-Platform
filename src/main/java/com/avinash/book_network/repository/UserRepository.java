package com.avinash.book_network.repository;

import com.avinash.book_network.entity.Enrollment;
import com.avinash.book_network.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
