package com.disha.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.disha.dto.empDTO;
import com.disha.entity.Employee;
import com.disha.service.EmpService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/emp") //  base URL, map HTTP requests to specific controller classes or methods.
public class EmpController {

	EmpService service;
    public EmpController(EmpService service){
		this.service = service;
	}
    
    @PostMapping("/add")
    public String addEmp(@RequestBody @Valid Employee empToAdd) {
        return service.addEmp(empToAdd);
    }

    @GetMapping("/{id}") //{id} is a dynamic placeholder, Extracts the value of {id} from the 
    //URL and assigns it to the method parameter id.
    public Employee getEmp(@PathVariable("id") Integer id) { 
    	 return service.searchEmp(id);
    }
    
    @PutMapping("/update/{id}")
    public String updateEmp(@RequestBody @Valid  empDTO employee, @PathVariable("id") Integer id) {
    	return service.UpdateEmp(employee, id);
    }
   
    //since the controller is annotated with @RestController , then Spring implicitly applies
    //@ResponseBody to all methods, including exception handlers inside it.
    
	/*@ExceptionHandler(value = NoSuchEmployeeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND) --- for the postman 
	@ResponseBody
	public ErrorResponse handleNoSuchEmpExistsException(NoSuchEmployeeException ex) {
	     return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	@ExceptionHandler(value = EmployeeAlreadyExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public ErrorResponse handleEmployeeAlreadyExistsException(EmployeeAlreadyExistException ex) {
	     return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
	}*/
	
}
