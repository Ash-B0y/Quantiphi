package com.quantiphi.employee.model;

import java.util.ArrayList;

public class UpdateEmployeeDetails {
	
	private ArrayList<Employee> employees;

	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "UpdateEmployeeDetails [employees=" + employees + "]";
	}
	
	

}
