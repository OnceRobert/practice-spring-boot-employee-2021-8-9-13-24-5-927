package com.thoughtworks.springbootemployee.model;
import com.thoughtworks.springbootemployee.model.Employees;

import java.util.ArrayList;
import java.util.List;

public class Companies {
    private String companyName;
    private Integer employeesNumber;
    private List<Employees> employees;



    private Integer companyID;

    public Companies(String companyName, Integer employeesNumber, List<Employees> employees, Integer companyID) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
        this.companyID = companyID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setEmployeesNumber(Integer employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public void setEmployees(List<Employees> employees) {
        this.employees = employees;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public Integer getCompanyID() {
        return companyID;
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

}
