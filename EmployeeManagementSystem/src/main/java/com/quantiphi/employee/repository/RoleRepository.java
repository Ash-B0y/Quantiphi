package com.quantiphi.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.quantiphi.employee.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

	@Query("Select roleName from Role")
	public List<String> findRoles();
}
