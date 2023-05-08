package com.learnspot.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learnspot.entities.Chapter;
import com.learnspot.entities.Course;
import com.learnspot.entities.User;
import com.learnspot.repositories.ChapterRepository;
import com.learnspot.repositories.CourseRepository;
import com.learnspot.repositories.UserRepository;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class CourseController {
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ChapterRepository chapterRepo;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
		
		try {
			System.out.println(email);

			User u = null;
			u = userRepo.findByEmail(email.toLowerCase().trim());
			if(u != null) {
				if( u.getPassword().equals(password)) {
					System.out.println("OK");
					return ResponseEntity.ok(u);
				}else {
					System.out.println("Invalide");
		            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");

				}
			}else {			
				System.out.println("No user !!");
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");

			}
		}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong !");
		}

	}

	@PostMapping("/users")
	public void AjouterClasse(@RequestBody JsonNode cls) {    
		ObjectMapper mapper = new ObjectMapper();
		try {
			User usr = mapper.readValue(cls.toString(), User.class);
			usr.setEmail(usr.getEmail().toLowerCase().trim());
			userRepo.save(usr);		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
	}
	
	@GetMapping("/course/{id}")
	public ResponseEntity<?> getCourseByID(@PathVariable  String id) {
		try {
			System.out.println(id +" : this actually the ID");
			Course c = courseRepo.findById(Long.parseLong(id)).orElse(null);
			ResponseEntity<?> re = null;
			if(c != null) {
				re =  ResponseEntity.status(HttpStatus.OK).body(c);
				return re;
			}else {
				re = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Can not find the course that you are looking for !");
				return re;
			}

			
		}catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong !");
		}
	}
	
	@GetMapping("/courses")
	public List<Course> getAllCourses(){
		return courseRepo.findAll();
	}
	
	@PostMapping("/courses")
	public ResponseEntity<?> addCourse(@RequestBody JsonNode course){
		try {
			System.out.println("try statement !");
			ObjectMapper mapper = new ObjectMapper();
			Course c = mapper.readValue(course.toString(), Course.class);
			courseRepo.save(c);
			for(int i =0; i<c.getChapters().size(); i++) {
				String title = c.getChapters().get(i).getTitle();
				String content = c.getChapters().get(i).getContent();
				Chapter ch = new Chapter(title,content,c);
				chapterRepo.save(ch);
			}			
			return ResponseEntity.ok("The course has been added successfully !");

		} catch (Exception e) {
			System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong !");
		}
	}

}
