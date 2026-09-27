package com.dcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.dto.RoleDto;
import com.dcl.entity.Role;
import com.dcl.enums.RoleType;
import com.dcl.request.AddRoleRequest;
import com.dcl.response.ApiResponse;
import com.dcl.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService rservice;
	

	@PostMapping("/addrole")
	public ResponseEntity<?> addroles(@RequestBody AddRoleRequest request){
		RoleDto dto=rservice.addrole(request.getRoleName());
		return ResponseEntity.ok(new ApiResponse<>("Role Added Successfully!",dto,HttpStatus.OK));
	}
}
