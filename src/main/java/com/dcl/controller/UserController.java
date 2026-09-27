package com.dcl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.dto.UserDto;
import com.dcl.exception.AppException;
import com.dcl.request.LoginRequest;
import com.dcl.request.RegisterRequest;
import com.dcl.response.ApiResponse;
import com.dcl.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService uservice;
	
	@PostMapping("/userreg")
	public ResponseEntity<?> userregister(@RequestBody RegisterRequest requset){
		UserDto dto=uservice.registeruser(requset);
		return ResponseEntity.ok(new ApiResponse<>("Account Created Successfully!", dto,HttpStatus.OK));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody LoginRequest request){
		UserDto dto=uservice.login(request);
		return ResponseEntity.ok(new ApiResponse<>("Login Successfull!", dto, HttpStatus.OK));
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteuser(@PathVariable Integer userId){
		uservice.deleteUserById(userId);
		return ResponseEntity.ok(new ApiResponse<>("User Deleted Successfully!", null, HttpStatus.OK));
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getallusers(){
		List<UserDto> ulist=uservice.getAllUser();
		return ResponseEntity.ok(ulist);
	}
	
	@GetMapping("/get/{userId}")
	public ResponseEntity<?> getbyid(@PathVariable Integer userId){
		UserDto dto=uservice.findByUserId(userId);
		return ResponseEntity.ok(dto);
	}

}
