package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.repository.EmployeesRepo;
import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Resource
    private EmployeesRepo employeesRepo;

    public EmployeeService(EmployeesRepo employeesRepo) { this.employeesRepo = employeesRepo; }

    public List<Employees> getEmployeesList(){
        return employeesRepo.findAll();
    }

    public Employees getById(Integer employeeId){
        return employeesRepo.getOne(employeeId);
    }

    public List<Employees> getByGender(String gender){
        return employeesRepo.findAll()
                .stream()
                .filter(employees -> employees.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employees> getByPagination(Integer index, Integer size){
        employeesRepo.findAll(PageRequest.of(index-1,size)).getContent();
    }
}
