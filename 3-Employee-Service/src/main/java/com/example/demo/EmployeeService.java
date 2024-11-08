package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	public Employee addEmployee(Employee employee) {
		return repo.save(employee);
	}
	
	public List<Employee> findAll(){
		return repo.findAll();
	}
	
	public Employee findById(Long id) {
		return repo.findById(id)
			   .orElseThrow(() -> new RuntimeException("Employee Not Found"));	
	}
	
	public List<Employee> findByDepartmentId(Long departmentId){
		return repo.findByDepartmentId(departmentId);
	}
}
