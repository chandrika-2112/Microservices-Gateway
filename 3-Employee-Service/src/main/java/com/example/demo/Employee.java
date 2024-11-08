package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {

    @Id
    private Long id;
    @Column(name = "department_id", insertable = false, updatable = false)
    private Long departmentId;
    private String name;
    private int age;
    private String position;

    // Constructors, getters, setters, toString...
    public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(Long id, Long departmentId, String name, int age, String position) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.name = name;
		this.age = age;
		this.position = position;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", departmentId=" + departmentId + ", name=" + name + ", age=" + age
				+ ", position=" + position + "]";
	}
    
    
}