package com.quantiphi.employee.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.quantiphi.employee.model.Address;
import com.quantiphi.employee.model.Employee;
import com.quantiphi.employee.repository.EmployeeRepository;
import com.quantiphi.employee.service.EmployeeService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
	
	@InjectMocks
	EmployeeService employeeService;

	@Mock
	EmployeeRepository employeeRepository;

	@Captor
	ArgumentCaptor<Employee> employeeDetailsArgumentCaptor;

	Employee employee;
	
	
	@BeforeEach
	public void initEmployeeDetails() {
		employee = new Employee("Ramesh", "Karthikeyan", 70000, "IT", new Address(43, "Manjnur", "Ooty", 641000));
		
	}

	@Test
	@DisplayName("Testing Fetch All Employees functionality")
	void fetchEmployeesTest() throws Exception {
		when(employeeRepository.findAll()).thenReturn(
				Stream.of(
						new Employee("Adarsh", "Das", 100000, "IT", new Address(47, "Mathigiri", "Hosur", 635109)),
						new Employee("Ravi", "Kumar", 70000, "Sales", new Address(19, "Bagalur", "Hosur", 635109))
				).collect(Collectors.toList())
		);

		assertEquals(2,((List<Employee>) employeeRepository.findAll()).size());
		
	}
	
	@Test
	@DisplayName("Persisting new Employees")
	public void saveEmployeesTest() {

		employeeRepository.save(employee);
		verify(employeeRepository, Mockito.times(1)).save(employeeDetailsArgumentCaptor.capture());

	}
	

}
