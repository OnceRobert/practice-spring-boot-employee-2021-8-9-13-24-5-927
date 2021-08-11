package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class EmployeesRepo {

    private List<Employees> employees = new ArrayList<>();

    public EmployeesRepo() {
        employees.add(new Employees(1, "Angelo", 23, "male", 1000));
        employees.add(new Employees(2, "Angela", 26, "female", 900));
    }

    public List<Employees> getAllEmployees() {
        return employees;
    }


    public Employees add(Employees employee){
        employees.add(employee);
        return employee;
    }

}
