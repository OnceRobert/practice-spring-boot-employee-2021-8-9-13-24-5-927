package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeMapper employeeMapper;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
//        employeesList.add(new Employees(1, "Angelo", 23, "male", 1000));
//        employeesList.add(new Employees(2, "Angela", 26, "female", 900));
    }


    @GetMapping()
    public List<EmployeeResponse> getAllEmployees() {
        return employeeMapper.toResponse(employeeService.getEmployeesList());
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer employeeId) {
        return employeeMapper.toResponse(employeeService.getById(employeeId));
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getEmployeesByGender(@RequestParam("gender") String givenGender) {
        return employeeService.getByGender(givenGender);
    }

    @GetMapping(params = {"index", "size"})
    public List<Employee> getEmployeesByPagination(@RequestParam int index, @RequestParam int size) {
        return employeeService.getByPagination(index,size);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void addEmployee(@RequestBody Employee newEmployee) {
        employeeService.addEmployee(newEmployee);
    }

    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployee(@PathVariable Integer employeeId, @RequestBody Employee employeeToBeUpdated) {
        return employeeService.updateById(employeeId, employeeToBeUpdated);
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteById(employeeId);
    }
}
