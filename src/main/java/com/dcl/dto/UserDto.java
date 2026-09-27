package com.dcl.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {
	
	private Integer userId;
	
	private String email;
	
	private LocalDate createdAt;
	
	private ProfileDto profiledto;
	
	private RoleDto roledto;

}
