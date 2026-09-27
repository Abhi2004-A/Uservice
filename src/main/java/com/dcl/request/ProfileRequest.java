package com.dcl.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileRequest {
	
	private String firstName;
	
	private String lastName;
	
	private String phone;
	
	private LocalDate dob;
	

}
