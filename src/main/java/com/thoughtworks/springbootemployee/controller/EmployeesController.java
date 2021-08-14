package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employees;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;
    private static List<Employees> employeesList = new ArrayList<>();

    public EmployeesController(EmployeeService employeeService) {
        employeesList.add(new Employees(1, "Angelo", 23, "male", 1000));
        employeesList.add(new Employees(2, "Angela", 26, "female", 900));
    }


    @GetMapping()
    public List<Employees> getAllEmployees() {
        return employeeService.getEmployeesList();
    }

    @GetMapping("/{employeeId}")
    public Employees getEmployeeById(@PathVariable Integer employeeId) {
        return employeeService.getById(employeeId);
    }

    @GetMapping(params = {"gender"})
    public List<Employees> getEmployeesByGender(@RequestParam("gender") String givenGender) {
        return employeeService.getByGender(givenGender);
    }

    @GetMapping(params = {"index", "size"})
    public List<Employees> getEmployeesByPagination(@RequestParam int index, @RequestParam int size) {
        return employeeService.getByPagination(index,size);
    }

    @PostMapping
    public void addEmployee(@RequestBody Employees newEmployee) {
        Employees employeesToBeAdded = new Employees(employeesList.size() + 1,
                newEmployee.getName(),
                newEmployee.getAge(),
                newEmployee.getGender(),
                newEmployee.getSalary());
        employeeService.addEmployee(employeesToBeAdded);

    }

    @PutMapping(path = "/{employeeId}")
    public Employees updateEmployee(@PathVariable Integer employeeId, @RequestBody Employees employeesToBeUpdated) {
        return employeeService.updateById(employeeId, employeesToBeUpdated);
    }

    @DeleteMapping(path = "/{employeeId}")
    public void deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteById(employeeId);
    }
}
