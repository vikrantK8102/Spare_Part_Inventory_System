package com.app.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.app.entities.Customer;
import com.app.entities.Feedback;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDTO {

	private int feedbackId;
	
	private String email;
	
	private String message;
	
	private int rating;
	
	private int customerId;
	
	private String name;

	public Feedback toEntity(FeedbackDTO dto) {
		Feedback feedback=new Feedback();
		BeanUtils.copyProperties(dto, feedback);		
		return feedback;
	}
}
