package com.example.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootbackend.entity.Employee;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
