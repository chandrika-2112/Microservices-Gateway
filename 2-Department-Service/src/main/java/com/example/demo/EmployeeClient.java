package com.example.demo;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "3-Employee-Service")
public interface EmployeeClient {
	
	@GetMapping("/employee/department/{departmentId}")
	public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId);

}
