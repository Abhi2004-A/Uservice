package com.dcl.request;

import java.time.LocalDate;

import com.dcl.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	private String email;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private LocalDate dob;
	
	private String phone;
	
	private RoleType roleName;

}
