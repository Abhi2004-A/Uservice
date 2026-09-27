package com.dcl.service.imp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.dcl.dto.ProfileDto;
import com.dcl.entity.Profile;
import com.dcl.exception.AppException;
import com.dcl.repo.ProfileRepo;
import com.dcl.request.ProfileRequest;
import com.dcl.response.CloudinaryResponse;
import com.dcl.service.CloudinaryService;
import com.dcl.service.ProfileService;

@Service
public class ProfileServiceImp implements ProfileService{
	
	@Autowired
	private ProfileRepo prepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CloudinaryService cservice;

	@Override
	public Profile addProfile(Profile profile) {
		return prepo.save(profile);
	}

	@Override
	public ProfileDto updateProfile(Integer profileId, ProfileRequest request, MultipartFile image) {
		Profile p=prepo.findById(profileId).orElseThrow(()->new AppException("User Not Found!", HttpStatus.NOT_FOUND));
		mapper.map(request, p);
		if(image!=null && !image.isEmpty()) {
			if(p.getImagePath()!=null && p.getPublicId()!=null) {
				cservice.deleteImage(p.getPublicId());
			}
			CloudinaryResponse response=cservice.uploadImage(image);
			p.setImagePath(response.getImageUrl());
			p.setPublicId(response.getPublucId());
		}
		p=prepo.save(p);
		return mapper.map(p, ProfileDto.class);
	}

	@Override
	public void deletePRofile(Integer profileId) {
		Profile p=prepo.findById(profileId).orElseThrow(()->new AppException("Profile Not Found!", HttpStatus.NOT_FOUND));
		prepo.deleteById(profileId);
		
	}

	@Override
	public ProfileDto getByProfileId(Integer profileId) {
		Profile p=prepo.findById(profileId).orElseThrow(()->new AppException("Profile Not Found!", HttpStatus.NOT_FOUND));
		return mapper.map(p, ProfileDto.class);
	}

	@Override
	public ProfileDto getProfileByUserId(Integer userId) {
		Profile p=prepo.findByUserUserId(userId).orElseThrow(()->new AppException("Profile Not Found!",HttpStatus.NOT_FOUND));
		return mapper.map(p, ProfileDto.class);
	}

}
