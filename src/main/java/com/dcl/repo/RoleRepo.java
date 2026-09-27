package com.dcl.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dcl.entity.Role;
import com.dcl.enums.RoleType;
import java.util.List;


@Repository
public interface RoleRepo extends JpaRepository<Role, Integer>{

	Optional<Role> findByRoleName(RoleType roleName);
}
