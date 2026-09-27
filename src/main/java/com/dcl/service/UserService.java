package com.dcl.service;

import java.util.List;

import com.dcl.dto.UserDto;
import com.dcl.request.LoginRequest;
import com.dcl.request.RegisterRequest;

public interface UserService {
	
	UserDto registeruser(RegisterRequest requset);
	
	UserDto login(LoginRequest request);
	
	void deleteUserById(Integer userId);
	
	UserDto findByUserId(Integer userId);
	
	List<UserDto> getAllUser();

}
