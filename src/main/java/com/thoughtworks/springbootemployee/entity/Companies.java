package com.thoughtworks.springbootemployee.entity;

import java.util.List;

public class Companies {
    private String companyName;
    private Integer employeesNumber;
    private List<Employee> employees;



    private Integer companyID;

    public Companies(String companyName, Integer employeesNumber, List<Employee> employees, Integer companyID) {
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

    public void setEmployees(List<Employee> employees) {
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

    public List<Employee> getEmployees() {
        return employees;
    }

}
