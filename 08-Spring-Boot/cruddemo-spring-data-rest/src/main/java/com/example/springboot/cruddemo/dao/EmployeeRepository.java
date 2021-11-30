package com.example.springboot.cruddemo.dao;

import com.example.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "emps")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
