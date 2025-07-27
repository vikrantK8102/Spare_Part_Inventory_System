package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
