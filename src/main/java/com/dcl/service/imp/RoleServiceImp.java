package com.dcl.service.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dcl.dto.RoleDto;
import com.dcl.entity.Role;
import com.dcl.enums.RoleType;
import com.dcl.exception.AppException;
import com.dcl.repo.RoleRepo;
import com.dcl.request.AddRoleRequest;
import com.dcl.service.RoleService;

import ch.qos.logback.core.joran.sanity.AppenderWithinAppenderSanityChecker;

@Service
public class RoleServiceImp implements RoleService{
	
	@Autowired
	private RoleRepo rrepo;
	
	@Autowired
	private ModelMapper mapper;

	public RoleDto addrole(RoleType roleName) {
		Role r=rrepo.findByRoleName(roleName).orElse(null);
		if(r!=null) {
			throw new AppException("Role Already Exists!", HttpStatus.CONFLICT);
		}
		r=new Role();
		r.setRoleName(roleName);
		r=rrepo.save(r);
		return mapper.map(r, RoleDto.class);
	}

	@Override
	public RoleDto updaterole(Integer roleId, AddRoleRequest request) {
		Role r=rrepo.findById(roleId).orElseThrow(()->new AppException("Role Not Found!", HttpStatus.NOT_FOUND));
		mapper.map(request, r);
		r=rrepo.save(r);
		return mapper.map(r, RoleDto.class);
	}

	public void deleteRole(Integer roleId) {
		Role r=rrepo.findById(roleId).orElseThrow(()->new AppException("Role Not Found!", HttpStatus.NOT_FOUND));
		rrepo.deleteById(roleId);
		
	}

	public List<RoleDto> getAllRoles() {
		return rrepo.findAll().stream().map((r)->mapper.map(r, RoleDto.class)).collect(Collectors.toList());
	}

	@Override
	public RoleDto getRoleByName(RoleType roleName) {
		Role r=rrepo.findByRoleName(roleName).orElseThrow(()->new AppException("Role Not Found!", HttpStatus.NOT_FOUND));
		return mapper.map(r, RoleDto.class);
	}

}
