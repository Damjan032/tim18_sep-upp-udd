package com.example.web_shopapi.service;

import com.example.web_shopapi.model.Course;
import com.example.web_shopapi.model.Product;
import com.example.web_shopapi.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> findAll(){
        return courseRepository.findAll();
    }
}
