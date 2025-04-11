package com.disha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.disha.entity.Employee;

@Repository
public interface EmpRepoo extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByName(String name);  
}
