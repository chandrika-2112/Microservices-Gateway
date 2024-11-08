package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeRepository repo;
	
	@PostMapping
	public Employee create(@RequestBody Employee employee) {
		LOGGER.info("Employee add: {}", employee);
		return repo.save(employee);
	}
	
	@GetMapping
	public List<Employee> findAll(){
		LOGGER.info("Employee find");
		return repo.findAll();
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<Employee> employee = repo.findById(id);
        if (employee.isPresent()) {
            LOGGER.info("Employee found: id {}", id);
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            LOGGER.warn("Employee not found: id {}", id);
            return new ResponseEntity<>("Employee Not Found", HttpStatus.NOT_FOUND);
        }
    }
	
	@GetMapping("/department/{departmentId}")
	public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId){
		LOGGER.info("Employee find: departmentId{}", departmentId);
		return repo.findByDepartmentId(departmentId);
	}
	
}
