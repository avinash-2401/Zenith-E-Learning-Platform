package com.avinash.book_network.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
    public class Lesson {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String title;

        private String videoUrl;

        private Integer duration;

        @ManyToOne
        @JoinColumn(name = "course_id")
        private Course course;

        private LocalDateTime createdAt;

        @PrePersist
        public void prePersist() {
            this.createdAt = LocalDateTime.now();
        }

}
