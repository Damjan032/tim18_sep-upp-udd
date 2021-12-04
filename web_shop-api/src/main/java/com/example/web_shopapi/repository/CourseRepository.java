package com.example.web_shopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.web_shopapi.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long>{

}
