package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartController {
	
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DepartController.class);
	
	@Autowired
	private DepartmentRepository repo;
	
	@Autowired
	private EmployeeClient employeeClient;
	
	@PostMapping
	public Department create(@RequestBody Department department) {
		LOGGER.info("Department add: {} ", department);
		return repo.save(department);
	}
	
	@GetMapping
	public List<Department> findAll(){
		LOGGER.info("Department find");
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Department> department = repo.findById(id);
        if (department.isPresent()) {
            LOGGER.info("Department found: id {}", id);
            return new ResponseEntity<>(department.get(), HttpStatus.OK);
        } else {
            LOGGER.warn("Department not found: id {}", id);
            return new ResponseEntity<>("Department Not Found", HttpStatus.NOT_FOUND);
        }
    }
	
	
	@GetMapping("/with-employees")
	public List<Department> findAllWithEmployees() {
	    LOGGER.info("Department find all with employees");
	    List<Department> departments = repo.findAll();
	    departments.forEach(department -> 
	        department.setEmployees(employeeClient.findByDepartment(department.getId()))
	    );
	    return departments;
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        Optional<Department> department = repo.findById(id);
        if (department.isPresent()) {
            repo.deleteById(id);
            LOGGER.info("Department deleted: id {}", id);
            return new ResponseEntity<>("Department was deleted successfully", HttpStatus.OK);
        } else {
            LOGGER.warn("Department not found: id {}", id);
            return new ResponseEntity<>("Department Not Found", HttpStatus.NOT_FOUND);
        }
    }


}
