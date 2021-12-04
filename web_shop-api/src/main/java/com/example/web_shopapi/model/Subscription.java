package com.example.web_shopapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subscription_table")
public class Subscription {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private Date date;
	
	private int durationMonths;
	
	@ManyToOne
	@JoinColumn(name="course_id", referencedColumnName = "id")
	private Course course;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private User subscribed;


	public Subscription() {
		super();
	}
	
	
}
