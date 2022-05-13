package com.quantiphi.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quantiphi.employee.model.Employee;
import com.quantiphi.employee.model.UpdateEmployeeDetails;
import com.quantiphi.employee.service.EmployeeService;

@RestController
@PropertySource("classpath:configuration.properties")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService; 
	@Autowired
    private Environment environment;
	
	@PostMapping("/add")
	public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {
		Employee addedEmployee = employeeService.addEmployee(employee);
		if(addedEmployee==null)
			return new ResponseEntity<>(environment.getProperty("AddEmployeeService.FAILURE"), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(environment.getProperty("AddEmployeeService.SUCCESS"), HttpStatus.OK);
		
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		Employee updatedEmployee = employeeService.updateEmployee(employee);
		if(updatedEmployee==null) 
			return new ResponseEntity<>(environment.getProperty("UpdateEmployeeService.FAILURE"),HttpStatus.BAD_REQUEST);
		 
		return new ResponseEntity<>(environment.getProperty("UpdateEmployeeService.SUCCESS"), HttpStatus.OK);
		
	}

	@PutMapping("/updateEmployees")
	public ResponseEntity<?> updateEmployees(@RequestBody UpdateEmployeeDetails updateEmployeeDetails) {
		
		List<Employee> updatedEmployees = employeeService.updateEmployees(updateEmployeeDetails.getEmployees());
		
		if(updatedEmployees==null) 
			return new ResponseEntity<>(environment.getProperty("UpdateEmployeeService.FAILURE"),HttpStatus.BAD_REQUEST);
		  
		return new ResponseEntity<>(environment.getProperty("UpdateEmployeeService.SUCCESS"), HttpStatus.OK);
		
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteEmployee(@RequestParam(name="employeeId",required=true) Integer id) {
		Employee deletedEmployee = employeeService.deleteEmployee(id);
		if(deletedEmployee == null) 
			return new ResponseEntity<>(environment.getProperty("DeleteEmployeeService.FAILURE"),HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(environment.getProperty("DeleteEmployeeService.SUCCESS"), HttpStatus.OK);
		
	}
	
	
	@GetMapping("/fetchAll")
	public ResponseEntity<?> fetchAllEmployees() {
		
		List<Employee> allEmployees = employeeService.fetchAllEmployees();  
		  if(allEmployees == null) 
			  return new ResponseEntity<>(environment.getProperty("FetchAllEmployeesService.NIL"),HttpStatus.BAD_REQUEST);
		 
		return new ResponseEntity<>(allEmployees, HttpStatus.OK);
		
	}
	
	@GetMapping("/searchFilter")
	public ResponseEntity<?> filterEmployees(@RequestParam(name="filterList",required=true) String[] filterList) {
		
		List<Employee> filteredEmployees = employeeService.filterEmployees(filterList);
		
		if(filteredEmployees.isEmpty())
			return new ResponseEntity<>(environment.getProperty("FilterEmployeesService.NIL"),HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<>(filteredEmployees, HttpStatus.OK);
		
	}

	

}
