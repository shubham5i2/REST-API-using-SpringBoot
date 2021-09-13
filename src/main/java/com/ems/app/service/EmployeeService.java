package com.ems.app.service;

import java.util.List;

import com.ems.app.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

	List<Employee> getAllEmployees();

	Employee getEmployeeById(long id);

	Employee updateEmployee(Employee employee, long id);
	
	void deleteEmployee(long id);
}
