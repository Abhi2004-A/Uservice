package com.dcl.service;

import java.util.List;

import com.dcl.dto.RoleDto;
import com.dcl.entity.Role;
import com.dcl.enums.RoleType;
import com.dcl.request.AddRoleRequest;


public interface RoleService {
	
	RoleDto addrole(RoleType roleName);
	
	RoleDto updaterole(Integer roleId, AddRoleRequest request);
	
	void deleteRole(Integer roleId);
	
	List<RoleDto> getAllRoles();
	
	RoleDto getRoleByName(RoleType roleName);

}
