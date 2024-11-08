package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository repo;
	
	public Department addDepartment(Department department) {
		return repo.save(department);
	}
	
	public List<Department> findAll(){
		return repo.findAll();
	}
	
	public Department findById(Long id) {
		return repo.findById(id)
			   .orElseThrow(() -> new RuntimeException("Department Not Found"));	
	}
	

}
