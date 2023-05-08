package com.learnspot.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Course {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long ID_course;
	String description;
	String title;
	String category;
	Integer duration;
	String author;
	Integer views;

	@OneToMany
	private List<Chapter> chapters;
	
	public Course(String title , String description, String category, Integer duration, String author, Integer views) {
		this.title = title;
		this.description = description;
		this.category = category;
		this.duration = duration;
		this.author = author;
		this.views = views;
		
	}

}
