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
        return employeesRepo.findAll(PageRequest.of(index-1,size)).getContent();
    }

    public void addEmployee(Employees newEmployee){
        employeesRepo.save(newEmployee);
    }

    public Employees updateById(Integer employeeId, Employees newEmployeeInfo){
        return employeesRepo.save(updateEmployeesInfo(employeesRepo.getOne(employeeId), newEmployeeInfo));
    }


    private Employees updateEmployeesInfo(Employees employees, Employees newEmployeeInfo) {
        if (newEmployeeInfo.getId() != null)
            employees.setId(newEmployeeInfo.getId());
        if (newEmployeeInfo.getName() != null)
            employees.setName(newEmployeeInfo.getName());
        if (newEmployeeInfo.getAge() != null)
            employees.setAge(newEmployeeInfo.getAge());
        if (newEmployeeInfo.getGender() != null)
            employees.setGender(newEmployeeInfo.getGender());
        if (newEmployeeInfo.getSalary() != null)
            employees.setSalary(newEmployeeInfo.getSalary());
        return employees;
    }

    public void deleteById(Integer employeeId){
        employeesRepo.deleteById(employeeId);
    }


}
