package com.quantiphi.employee.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.quantiphi.employee.model.Function;
import com.quantiphi.employee.model.Role;
import com.quantiphi.employee.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private Role role;
	
	private Set<Function> functions = new HashSet<>();
	
	
	public Role addRoles(String roleName, String[] functionList) {
		
		role.setRoleName(roleName);
		
		for(String functionName : functionList) {
			Function function = new Function();
			function.setFunctionName(functionName);
			functions.add(function);
		}
		
		role.setFunctions(functions);
		roleRepository.save(role);
		Optional<Role> persistedRole = roleRepository.findById(role.getRoleId());
		if(!persistedRole.isPresent())
			return null;
		return persistedRole.get();
		
	 }
	
	public List<Role> fetchRoles(){
		
		List<Role> allRoles = (List<Role>) roleRepository.findAll();
		   if(!allRoles.isEmpty())
				 return allRoles;
		   
		   return null;
		   
	}

}
