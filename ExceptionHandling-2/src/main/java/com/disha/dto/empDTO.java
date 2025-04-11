package com.disha.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class empDTO {
	//fields that are likely to be updated are specified in the DTO class that is
	//instead of returning an entire entity object we will be returning an DTo class object 
	 @Size(min = 1,message = "Name must contain atleast atleast 1 character !")
     private String name;
	 @Min(value = 10000,message = "Salary must be atleast 10000")
     private Double sal;
}
