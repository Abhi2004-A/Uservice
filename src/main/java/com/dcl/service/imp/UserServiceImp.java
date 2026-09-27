package com.dcl.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dcl.dto.ProfileDto;
import com.dcl.dto.RoleDto;
import com.dcl.dto.UserDto;
import com.dcl.entity.Profile;
import com.dcl.entity.Role;
import com.dcl.entity.User;
import com.dcl.exception.AppException;
import com.dcl.repo.UserRepo;
import com.dcl.request.LoginRequest;
import com.dcl.request.RegisterRequest;
import com.dcl.response.ApiResponse;
import com.dcl.service.ProfileService;
import com.dcl.service.RoleService;
import com.dcl.service.UserService;

@Service
public class UserServiceImp implements UserService{
	
	@Autowired
	private UserRepo urepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ProfileService pservice;
	
	@Autowired
	private RoleService rservice;

	@Transactional
	@Override
	public UserDto registeruser(RegisterRequest requset) {
		
		RoleDto rdto=rservice.getRoleByName(requset.getRoleName());
		if(rdto==null) {
			throw new AppException("Role Not Found!", HttpStatus.NOT_FOUND);
		}
		
		User alreadyexists=urepo.findByEmail(requset.getEmail()).orElse(null);
		if(alreadyexists!=null) {
			throw new AppException("User already exists!", HttpStatus.BAD_REQUEST);
		}
		User u=mapper.map(requset, User.class);
		Role r=mapper.map(rdto, Role.class);
		u.setRole(r);
		Profile p=mapper.map(requset, Profile.class);
		u.setProfile(p);
		u=urepo.save(u);
		return mapper.map(u, UserDto.class);
	}

	@Override
	public UserDto login(LoginRequest request) {
		User alreadyexists=urepo.findByEmail(request.getEmail()).orElse(null);
		if(alreadyexists==null) {
			throw new AppException("User Not Found!", HttpStatus.NOT_FOUND);
		}
		if(!alreadyexists.getPassword().equals(request.getPassword())) {
			throw new AppException("Inavalid Username or Password!", HttpStatus.BAD_REQUEST);
		}
		UserDto dto=mapper.map(alreadyexists, UserDto.class);
		ProfileDto pdto=pservice.getProfileByUserId(dto.getUserId());
		dto.setProfiledto(pdto);
		return dto;
	}

	@Override
	public void deleteUserById(Integer userId) {
		User u=urepo.findById(userId).orElseThrow(()->new AppException("User Not Found!", HttpStatus.NOT_FOUND));
		pservice.getProfileByUserId(userId);
		urepo.deleteById(userId);
		
	}

	@Override
	public UserDto findByUserId(Integer userId) {
		User u=urepo.findById(userId).orElseThrow(()->new AppException("User Not Found!", HttpStatus.NOT_FOUND));
		ProfileDto pdto=pservice.getProfileByUserId(userId);
		UserDto udto=mapper.map(u, UserDto.class);
		udto.setProfiledto(pdto);
		return udto;
	}

	@Override
	public List<UserDto> getAllUser() {
		return urepo.findAll()
				    .stream()
				    .map(u->mapper.map(u, UserDto.class))
				    .collect(Collectors.toList());
	}

}
