package com.quantiphi.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quantiphi.employee.model.Role;
import com.quantiphi.employee.service.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
    private Environment environment;
	
	@PostMapping("/addRoles")
	public ResponseEntity<?> addRoles(@RequestParam(name="roleName",required=true) String roleName, @RequestParam(name="functionList",required=true) String[] functionList) {
		
		Role addedRole = roleService.addRoles(roleName,functionList);
		if(addedRole==null)
			return new ResponseEntity<>(environment.getProperty("AddRoleService.FAILURE"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(environment.getProperty("AddRoleService.SUCCESS"), HttpStatus.OK);

	}
	
	@GetMapping("/fetchRoles")
	public ResponseEntity<?> fetchRoles() {
		
		List<Role> availableRoles = roleService.fetchRoles();
		if(null != availableRoles)
			return new ResponseEntity<>(availableRoles, HttpStatus.OK);
		
		return new ResponseEntity<>(environment.getProperty("FetchAllRolesService.NIL"), HttpStatus.BAD_REQUEST);
	}

}
