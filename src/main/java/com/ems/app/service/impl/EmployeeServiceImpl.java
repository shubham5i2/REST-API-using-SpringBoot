package com.ems.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.app.dao.EmployeeDao;
import com.ems.app.exception.ResourceNotFoundException;
import com.ems.app.model.Employee;
import com.ems.app.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao dao;

	public EmployeeServiceImpl(EmployeeDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		Employee emp = dao.save(employee);
		return emp;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = dao.findAll();
		return employees;
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = dao.findById(id);

		if(employee.isPresent()) {
			return employee.get();
		}
		else {
			throw new ResourceNotFoundException("Employee", "Id", id);
		}
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {

		//check if employee is present in DB or not
		Optional<Employee> existingEmployee = dao.findById(id);

		if(existingEmployee.isPresent()) {
			Employee emp = existingEmployee.get();

			emp.setFirstName(employee.getFirstName());
			emp.setLastName(employee.getLastName());
			emp.setEmail(employee.getEmail());

			Employee updatedEmployee = dao.save(emp);

			return updatedEmployee;
		}
		else {
			throw new ResourceNotFoundException("Employee", "Id", id);
		}
	}

	@Override
	public void deleteEmployee(long id) {
		Optional<Employee> employee = dao.findById(id);

		if(employee.isPresent()) {
			dao.deleteById(id);
		}
		else {
			throw new ResourceNotFoundException("Employee", "Id", id);
		}
	}

}
