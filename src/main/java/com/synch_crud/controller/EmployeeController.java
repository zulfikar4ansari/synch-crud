package com.synch_crud.controller;


import com.synch_crud.dto.EmployeeRequest;
import com.synch_crud.dto.EmployeeResponse;
import com.synch_crud.entity.Employee;
import com.synch_crud.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request)
    {
        Employee emp = service.createEmployee(request);
        EmployeeResponse response = mapToDTO(emp);
        System.out.println("In controller");

        return ResponseEntity.created(URI.create("/employees/"+emp.getId())).body(response) ;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> findAllEmployee()
    {
        List<Employee> emp = service.findAllEmployee();
        List<EmployeeResponse> responses = emp.stream()
                .map(this::mapToDTO)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findEmployeeById(@PathVariable Long id) {
            EmployeeResponse response = mapToDTO(service.findEmployeeById(id));
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable Long id,
                           @Valid @RequestBody EmployeeRequest req) {
        EmployeeResponse response = mapToDTO(service.update(id, req));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    public EmployeeResponse mapToDTO(Employee emp)
    {
        return new EmployeeResponse(
                emp.getId(),
                emp.getName(),
                emp.getDepartment(),
                emp.getSalary());
    }
}
