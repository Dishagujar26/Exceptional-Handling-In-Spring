package com.disha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disha.dto.empDTO;
import com.disha.entity.Employee;
import com.disha.exception.EmployeeAlreadyExistException;
import com.disha.exception.NoSuchEmployeeException;
import com.disha.repository.EmpRepoo;

@Service
public class EmpService {
     private EmpRepoo repo; 
     @Autowired
     public EmpService(EmpRepoo repo) {
    	 this.repo=repo;
     }
     
     public String addEmp(Employee emp) {
    	 Employee e = repo.findByName(emp.getName()).orElse(null);
    	 if( e != null) {
    		 throw new EmployeeAlreadyExistException("Employee with name "+ emp.getName()+" already exist!");
    	 }
    	 else {
    		 repo.save(emp);
        	 return "Employee saved sucessfully !";
    	 } 
     }
     
     public String UpdateEmp(empDTO emp, Integer id){
    	 Employee e = repo.findById(id).orElse(null);
    	 if( e == null) {
    		 throw new NoSuchEmployeeException("Employee with ID "+ id +" does not exist!");
    	 }
    	 
    	 // so that when no data is given, user will get the right message and not record saved successfully.
    	 if(emp.getName()==null && emp.getSal()==null) {
    		 throw new RuntimeException("Empty Object Not Allowed!");
    	 }
    	 
    	 if(emp.getName() !=null && emp.getName().isBlank()){
    		 throw new RuntimeException("Name Can not be just spaces");
    	 }
    	 if(emp.getName() != null) {
    		 e.setName(emp.getName());
    	 }
    	 if(emp.getSal() != null) {
    		 e.setSal(emp.getSal());
    	 }
    	 repo.save(e);
    	 return "Employee Upated sucessfully";
     }
     
     public Employee searchEmp(Integer id){
    	 Employee e = repo.findById(id).orElse(null);
    	 if( e == null) {
    		 throw new NoSuchEmployeeException("Employee with ID "+ id +" does not exist!");
    	 }
    	 else {	 
        	 return e;
    	 } 
     }
     
}
