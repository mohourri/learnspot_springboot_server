package com.learnspot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnspot.entities.Course;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	
}
