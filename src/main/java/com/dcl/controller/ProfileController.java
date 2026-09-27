package com.dcl.controller;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dcl.dto.ProfileDto;
import com.dcl.entity.Profile;
import com.dcl.request.ProfileRequest;
import com.dcl.response.ApiResponse;
import com.dcl.service.ProfileService;

@RestController
public class ProfileController {
	
	@Autowired
	private ProfileService pservice;
	
	@Autowired
	private ModelMapper mapper;
	
	@PutMapping(path="/getprofile/{profileId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateprofile(@PathVariable Integer profileId,
			                                @RequestPart(value="image", required=false) MultipartFile image,
			                                @RequestParam String firstName, @RequestParam String lastName, @RequestParam LocalDate dob, @RequestParam String phone){
		ProfileRequest p=new ProfileRequest();
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setDob(dob);
		p.setPhone(phone);
		ProfileDto pdto=pservice.updateProfile(profileId, p, image);
		return ResponseEntity.ok(new ApiResponse<>("Profile Updated Successfully!",pdto,HttpStatus.OK));
	}

}
