package com.quantiphi.employee.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quantiphi.employee.model.Address;
import com.quantiphi.employee.model.Department;
import com.quantiphi.employee.model.Employee;
import com.quantiphi.employee.repository.AddressRepository;
import com.quantiphi.employee.repository.DepartmentRepository;
import com.quantiphi.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	private ArrayList<Employee> employees;
	private ArrayList<Employee> updatedEmployees; 
	
	public Employee addEmployee(Employee employee) {
		
		addressRepository.save(employee.getAddress());
		employeeRepository.save(employee);
		addDepartment(employee);
		Optional<Employee> persistedEmployee = employeeRepository.findById(employee.getEmployeeId());
		if(!persistedEmployee.isPresent())
			return null;
		return persistedEmployee.get();
		 
	 }
   
	public void addDepartment(Employee employee) {
		employees = new ArrayList<>();
		List<Department> departments = (List<Department>) departmentRepository.findAll();
		if(!departments.isEmpty()) {
			List<Department> existingDepartment = departments.stream().filter(d->d.getDepartmentName().toLowerCase().equals(employee.getDepartment().toLowerCase())).collect(Collectors.toList());
			List<Employee> existingEmployees;
			if(!existingDepartment.isEmpty()) {
				for(Department department : existingDepartment) {
					existingEmployees = department.getEmployees();
					existingEmployees.add(employee);
					departmentRepository.save(department);
			}
		}
		   else {
			    Department department = new Department();
			    department.setDepartmentName(employee.getDepartment());
				department.setCity(employee.getAddress().getCity());
				employees.add(employee);
				department.setEmployees(employees);
				departmentRepository.save(department);
				}
		}
		
		else {
			Department department = new Department(); 
			department.setDepartmentName(employee.getDepartment());
			department.setCity(employee.getAddress().getCity());
			employees.add(employee);
			department.setEmployees(employees);
			departmentRepository.save(department);
			}
	}
	
    
   public List<Employee> updateEmployees(List<Employee> employeesToBeUpdated) {
	   updatedEmployees= new ArrayList<>();
	   for(Employee employee:employeesToBeUpdated) {
		   Optional<Employee> existingEmployee = employeeRepository.findById(employee.getEmployeeId());
		   if(existingEmployee.isPresent()) {
			   if(!existingEmployee.get().getDepartment().toLowerCase().equals(employee.getDepartment().toLowerCase()))
				   updateDepartment(employee);
			   existingEmployee.get().setFirstName(employee.getFirstName());
			   existingEmployee.get().setLastName(employee.getLastName());
			   existingEmployee.get().setSalary(employee.getSalary());
			   existingEmployee.get().setDepartment(employee.getDepartment());
			   existingEmployee.get().getAddress().setBuildingNumber(employee.getAddress().getBuildingNumber());
			   existingEmployee.get().getAddress().setStreet(employee.getAddress().getStreet());
			   existingEmployee.get().getAddress().setCity(employee.getAddress().getCity());
			   existingEmployee.get().getAddress().setZipCode(employee.getAddress().getZipCode());
			   addressRepository.save(existingEmployee.get().getAddress());
			   existingEmployee.get().setAddress(existingEmployee.get().getAddress());
			   employeeRepository.save(existingEmployee.get());
			   Optional<Employee> updatedEmployee = employeeRepository.findById(employee.getEmployeeId());
			   if(updatedEmployee.isPresent()) {  
				   if(updatedEmployee.get().getFirstName().equals(employee.getFirstName()) && updatedEmployee.get().getLastName().equals(employee.getLastName()) && updatedEmployee.get().getSalary() == employee.getSalary() && updatedEmployee.get().getDepartment().equals(employee.getDepartment()) && updatedEmployee.get().getAddress().getBuildingNumber() == employee.getAddress().getBuildingNumber() && updatedEmployee.get().getAddress().getStreet().equals(employee.getAddress().getStreet()) && updatedEmployee.get().getAddress().getCity().equals(employee.getAddress().getCity()) && updatedEmployee.get().getAddress().getZipCode() == employee.getAddress().getZipCode())   
					   updatedEmployees.add(updatedEmployee.get());
			   
				   else
					   return null;
			   }
			   else
				   return null;
		   
		   }
		   else
			   return null;
	   }
	   if(updatedEmployees.size() == employeesToBeUpdated.size())
		   return updatedEmployees;
	   
	   return null;
		   
	 }
   
public Employee updateEmployee(Employee employee) {
	   
	   Optional<Employee> existingEmployee = employeeRepository.findById(employee.getEmployeeId());
	   if(existingEmployee.isPresent()) {
		   if(!existingEmployee.get().getDepartment().toLowerCase().equals(employee.getDepartment().toLowerCase()))
			   updateDepartment(employee);
		   existingEmployee.get().setFirstName(employee.getFirstName());
		   existingEmployee.get().setLastName(employee.getLastName());
		   existingEmployee.get().setSalary(employee.getSalary());
		   existingEmployee.get().setDepartment(employee.getDepartment());
		   existingEmployee.get().getAddress().setBuildingNumber(employee.getAddress().getBuildingNumber());
		   existingEmployee.get().getAddress().setStreet(employee.getAddress().getStreet());
		   existingEmployee.get().getAddress().setCity(employee.getAddress().getCity());
		   existingEmployee.get().getAddress().setZipCode(employee.getAddress().getZipCode());
		   addressRepository.save(existingEmployee.get().getAddress());
		   existingEmployee.get().setAddress(existingEmployee.get().getAddress());
		   employeeRepository.save(existingEmployee.get());
		   Optional<Employee> updatedEmployee = employeeRepository.findById(employee.getEmployeeId());
		   if(updatedEmployee.isPresent()) {
			   if(updatedEmployee.get().getFirstName().equals(employee.getFirstName()) && updatedEmployee.get().getLastName().equals(employee.getLastName()) && updatedEmployee.get().getSalary() == employee.getSalary() && updatedEmployee.get().getDepartment().equals(employee.getDepartment()) && updatedEmployee.get().getAddress().getBuildingNumber() == employee.getAddress().getBuildingNumber() && updatedEmployee.get().getAddress().getStreet().equals(employee.getAddress().getStreet()) && updatedEmployee.get().getAddress().getCity().equals(employee.getAddress().getCity()) && updatedEmployee.get().getAddress().getZipCode() == employee.getAddress().getZipCode())
				   return updatedEmployee.get();
			   return null;
		   }
		   return null;
	   
	   }
	   return null;
		 
	 }
   
   public void updateDepartment(Employee employee) {
	   
	   Employee employeeRemoved = null;
	   List<Department> departments = (List<Department>) departmentRepository.findAll();
	   List<Department> employeeDepartments = departments.stream().filter(e->e.getEmployees().stream().anyMatch(id->id.getEmployeeId() == employee.getEmployeeId())).collect(Collectors.toList());
	   List<Department> departmentUpdatedExists = departments.stream().filter(dep->dep.getDepartmentName().toLowerCase().equals(employee.getDepartment().toLowerCase())).collect(Collectors.toList()); 
	   if(!employeeDepartments.isEmpty()) {
		   if(employeeDepartments.get(0).getEmployees().size() == 1 && employeeDepartments.get(0).getEmployees().get(0).getEmployeeId() == employee.getEmployeeId()) {
			   if(departmentUpdatedExists.isEmpty()) {
				   employeeDepartments.get(0).setDepartmentName(employee.getDepartment());
				   employeeDepartments.get(0).setCity(employee.getAddress().getCity());
				   departmentRepository.save(employeeDepartments.get(0));
			   }
			   else{
				   departmentRepository.deleteById(employeeDepartments.get(0).getDepartmentId());
				   departmentUpdatedExists.get(0).getEmployees().add(employee);
				   departmentRepository.save(departmentUpdatedExists.get(0));
			   }
			   
		   }
			   
			   else {
				   for(Employee employeeToBeRemoved : employeeDepartments.get(0).getEmployees()) {
					   if(employeeToBeRemoved.getEmployeeId() == employee.getEmployeeId()) {
						   employeeRemoved = employeeToBeRemoved;
						   break;
					   }
				   }
				   if(!departmentUpdatedExists.isEmpty()) {
					   employeeDepartments.get(0).getEmployees().remove(employeeRemoved);
					   departmentRepository.save(employeeDepartments.get(0));
					   departmentUpdatedExists.get(0).getEmployees().add(employee);
					   departmentRepository.save(departmentUpdatedExists.get(0));
				   }
				   else {
					   employees = new ArrayList<>();
					   employeeDepartments.get(0).getEmployees().remove(employeeRemoved);
				       Department newDepartment = new Department();
				       newDepartment.setCity(employee.getAddress().getCity());
				       newDepartment.setDepartmentName(employee.getDepartment());
				       employees.add(employee);
				       departmentRepository.save(employeeDepartments.get(0));
				       newDepartment.setEmployees(employees);
				       departmentRepository.save(newDepartment);
			   }
		   }
	   }
   }
   
   public Employee deleteEmployee(Integer id) {
	   
	   int idToBeDeleted = deleteDepartment(id);
	   addressRepository.deleteById(idToBeDeleted); 
	   Optional<Address> deletedEmployee = addressRepository.findById(idToBeDeleted); 
	   if(!deletedEmployee.isPresent()) 
		   return new Employee();
		 
	   return null;
	 }
   
   public int deleteDepartment(Integer id) {
	   List<Department> departments = (List<Department>) departmentRepository.findAll();
	   Optional<Employee> employee = employeeRepository.findById(id);
	   List<Department> employeeDepartments = departments.stream().filter(d->d.getDepartmentName().equals(employee.get().getDepartment())).collect(Collectors.toList());
	   for(Department department : employeeDepartments) {
		   if(department.getEmployees().size() == 1 && department.getEmployees().get(0).getEmployeeId() == employee.get().getEmployeeId())
			   departmentRepository.deleteById(department.getDepartmentId());
		   }
	   return employee.get().getAddress().getAddressId();
   }
   
   public List<Employee> fetchAllEmployees() {
	   
	   List<Employee> allEmployees = (List<Employee>) employeeRepository.findAll();
	   
	   if(!allEmployees.isEmpty())
			 return allEmployees.stream().sorted(Comparator.comparing(Employee::getFirstName)).collect(Collectors.toList());
	   
	   return null;
	 }
   
   public List<Employee> filterEmployees(String[] filterList) {
	   List<Employee> allEmployees = (List<Employee>) employeeRepository.findAll();
	   List<Employee> filteredEmployees = null;
	   for(String filters :filterList) {
		   if(filters.split(":")[0].toLowerCase().equals("firstname"))
			   filteredEmployees = allEmployees.stream().filter(name->name.getFirstName().toLowerCase().equals(filters.split(":")[1].toLowerCase())).collect(Collectors.toList());
		   else if(filters.split(":")[0].toLowerCase().equals("lastname"))
			   filteredEmployees = allEmployees.stream().filter(name->name.getLastName().toLowerCase().equals(filters.split(":")[1].toLowerCase())).collect(Collectors.toList());
		   else if(filters.split(":")[0].toLowerCase().equals("department"))
			   filteredEmployees = allEmployees.stream().filter(dep->dep.getDepartment().toLowerCase().equals(filters.split(":")[1].toLowerCase())).collect(Collectors.toList());
		   else if(filters.split(":")[0].toLowerCase().equals("salary")) {
			   if(filters.split(":")[1].charAt(0)=='<')
				   filteredEmployees = allEmployees.stream().filter(sal->sal.getSalary() < Integer.parseInt(filters.split(":")[1].substring(1))).collect(Collectors.toList());
			   else if(filters.split(":")[1].charAt(0)=='>')
				   filteredEmployees = allEmployees.stream().filter(sal->sal.getSalary() > Integer.parseInt(filters.split(":")[1].substring(1))).collect(Collectors.toList());
			   else if(filters.split(":")[1].charAt(0)=='=')
				   filteredEmployees = allEmployees.stream().filter(sal->sal.getSalary() == Integer.parseInt(filters.split(":")[1].substring(1))).collect(Collectors.toList());
		   }
		   
		  allEmployees = filteredEmployees;
	   }
	   
	   return allEmployees.stream().sorted(Comparator.comparing(Employee::getFirstName)).collect(Collectors.toList());
	 }

   
   
}


