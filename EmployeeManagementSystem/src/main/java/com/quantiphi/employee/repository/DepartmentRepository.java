package com.quantiphi.employee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quantiphi.employee.model.Department;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer>{

}
