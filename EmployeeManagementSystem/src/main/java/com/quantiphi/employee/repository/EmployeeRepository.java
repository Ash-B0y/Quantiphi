package com.quantiphi.employee.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.quantiphi.employee.model.Employee;



@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>,JpaSpecificationExecutor<Employee>{
	
}
