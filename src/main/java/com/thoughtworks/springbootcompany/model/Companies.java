package com.thoughtworks.springbootcompany.model;
import com.thoughtworks.springbootemployee.model.Employees;

import java.util.ArrayList;
import java.util.List;

public class Companies {
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployeesNumber(Integer employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getEmployeesNumber() {
        return employeesNumber;
    }

    public List<Employees> getEmployees() {
        return employees;
    }

    private String companyName;
    private Integer employeesNumber;
    private List<Employees> employees;

    public Companies(String companyName, Integer employeesNumber, List<Employees> employees) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }

}
