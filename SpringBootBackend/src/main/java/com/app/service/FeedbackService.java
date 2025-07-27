package com.app.service;

import java.util.List;

import com.app.entities.Feedback;

public interface FeedbackService {

	void saveFeedback(Feedback feedback);

	List<Feedback> findAllFeedbacks();

}
