package com.example.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Person;

@RestController
@RequestMapping("/person")
public class PersonController {

	@RequestMapping
	public Person name() {
		
		return new Person("Marcelo", 32); 
	}
	
	@RequestMapping("/me")
	public String loggedUser(){
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
