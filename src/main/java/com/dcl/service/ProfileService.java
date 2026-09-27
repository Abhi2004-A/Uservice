package com.dcl.service;

import org.springframework.web.multipart.MultipartFile;

import com.dcl.dto.ProfileDto;
import com.dcl.entity.Profile;
import com.dcl.request.ProfileRequest;

public interface ProfileService {
	
	Profile addProfile(Profile profile);
	
	ProfileDto updateProfile(Integer profileId,ProfileRequest request,MultipartFile image);
	
	void deletePRofile(Integer profileId);
	
	ProfileDto getByProfileId(Integer profileId);
	
	ProfileDto getProfileByUserId(Integer userId);

}
