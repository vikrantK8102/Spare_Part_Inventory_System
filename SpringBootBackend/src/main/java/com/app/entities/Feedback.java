package com.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int feedbackId;
	@Column(name = "name")
	private String name;
	
	@Column(name="email")
	@NotBlank(message = "Email is required")
	@Length(min = 5,max=50,message = "Invalid Email length")
	@Email(message = "Invalid email format")
	private String email;
	
	@Column(length = 60, name="message")
	private String message;
	
	@Min(value=1)
	@Max(value=5)
	@Column(name="rating")
	private int rating;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	
}
	