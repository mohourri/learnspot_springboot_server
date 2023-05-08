package com.learnspot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString @Getter @Setter
public class Chapter {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long ID_chapter;
	String title;
	@Column(length = 20000)
	String content;
	
	
	public Chapter(String title, String content, Course c) {
		this.title = title;
		this.content = content;
	}
	
}
