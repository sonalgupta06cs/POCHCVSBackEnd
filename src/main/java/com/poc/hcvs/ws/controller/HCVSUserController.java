package com.poc.hcvs.ws.controller;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.hcvs.ui.model.request.UserDetailsRequestModel;
import com.poc.hcvs.ui.model.response.UserResponse;
import com.poc.hcvs.ws.model.UserEntity;
import com.poc.hcvs.ws.service.HCVSUserService;
import com.poc.hcvs.ws.shared.dto.UserDto;

@CrossOrigin(origins = "http://localhost:4300")
@RestController
@RequestMapping("/api/users")
public class HCVSUserController {
	
	@Autowired
	HCVSUserService hcvsUserService;
	
	@GetMapping("/all")
	public List<UserEntity> getAllUsers() {

		System.out.println("Get all Users...");

		List<UserEntity> users = hcvsUserService.findAll();
		return users;
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<?> createUser(@RequestBody UserDetailsRequestModel userDetails) throws IOException {

		try 
		{
			System.out.println("Creating the User...");
			
			UserResponse userResponse = new UserResponse();
	
			ModelMapper modelMapper = new ModelMapper();
			UserDto userDto = modelMapper.map(userDetails, UserDto.class);
			
			UserDto createdCustomer = hcvsUserService.createUser(userDto);
			userResponse = modelMapper.map(createdCustomer, UserResponse.class);
			
			return new ResponseEntity<>(userResponse, HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
	}
	
	@GetMapping("/usersNames")
	public List<UserDto> getAllUsersName() {
		return hcvsUserService.getAllUsersName();
	}


}
