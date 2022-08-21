package com.example.springbootbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootbackend.entity.Employee;
import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="http://localhost:4200/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository repo;
	
	// get All employees
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return repo.findAll();
	}
	
	//create employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee emp)
	{
		return repo.save(emp);
	}
	
	//get employee with id
	@GetMapping("/employees/{id}")
	/*public ResponseEntity<Employee> getEmployee(@PathVariable Long id){
		Employee e=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee is not present with id: "+id));
		return ResponseEntity.ok(e);
	}*/
	public Employee getEmployee(@PathVariable Long id){
		Employee e=repo.findById(id).get();
		return e;
		}
	
	//updateEmployee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee e){
		Employee emp=repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee is not present with id: "+id));
	    emp.setFirstName(e.getFirstName());
	    emp.setLastName(e.getLastName());
	    emp.setEmailId(e.getEmailId());
	    Employee e1=repo.save(emp);
	    return ResponseEntity.ok(e1);
	}
	
	//delete Employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		Employee e=repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee is not present with id: "+id));
		repo.delete(e);
		Map<String,Boolean>resp=new HashMap<>();
		resp.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(resp);
	}
}