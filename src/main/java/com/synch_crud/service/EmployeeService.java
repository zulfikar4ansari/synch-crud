package com.synch_crud.service;


import com.synch_crud.repository.EmployeeRepository;
import com.synch_crud.dto.EmployeeRequest;
import com.synch_crud.entity.Employee;
import com.synch_crud.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> findAll() {
        return repo.findAll();
    }

    public Employee findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));
    }

    public Employee create(EmployeeRequest req) {
        Employee emp = new Employee(req.name(), req.department(), req.salary());
        return repo.save(emp);
    }

    public Employee update(Long id, EmployeeRequest req) {
        Employee emp = findById(id);
        emp.setName(req.name());
        emp.setDepartment(req.department());
        emp.setSalary(req.salary());
        return repo.save(emp);
    }

    public void delete(Long id) {
        Employee emp = findById(id);
        repo.delete(emp);
    }
}
