package com.synch_crud.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


public record EmployeeRequest(

        @NotBlank(message = "Employee name should not be empty")
        String name,
        String department,

        @Min(value = 0,message = "salary should not be negative")
        double salary) {
}
