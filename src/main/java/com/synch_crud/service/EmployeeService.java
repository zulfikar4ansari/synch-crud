package com.synch_crud.service;


import com.synch_crud.repository.EmployeeRepository;
import com.synch_crud.dto.EmployeeRequest;
import com.synch_crud.entity.Employee;
import com.synch_crud.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service

public class EmployeeService
{

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository repo)
    {
        this.employeeRepository = repo;
    }

    public Employee createEmployee(EmployeeRequest request)
    {
        System.out.println("Hello");
        Employee employee = Employee.builder()
                .name(request.name())
                .department(request.department())
                .salary(request.salary()).build();
        return employeeRepository.save(employee);
    }




    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));
    }

   /* public Employee create(EmployeeRequest req) {
        Employee emp = new Employee(req.name(), req.department(), req.salary());
        return employeeRepository.save(emp);
    }*/

    public Employee update(Long id, EmployeeRequest req) {
        Employee emp = findById(id);
        emp.setName(req.name());
        emp.setDepartment(req.department());
        emp.setSalary(req.salary());
        return employeeRepository.save(emp);
    }

    public void delete(Long id) {
        Employee emp = findById(id);
        employeeRepository.delete(emp);
    }
}
