package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFound;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeesRepo;
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

    public List<Employee> getEmployeesList(){
        return employeesRepo.findAll();
    }

    public Employee getById(Integer employeeId){
        return employeesRepo.findById(employeeId).orElseThrow(() -> new EmployeeNotFound("Employee Not Found"));
    }

    public List<Employee> getByGender(String gender){
        return employeesRepo.findAll()
                .stream()
                .filter(employees -> employees.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public List<Employee> getByPagination(Integer index, Integer size){
        return employeesRepo.findAll(PageRequest.of(index-1,size)).getContent();
    }

    public void addEmployee(Employee newEmployee){
        employeesRepo.save(newEmployee);
    }

    public Employee updateById(Integer employeeId, Employee newEmployeeInfo){
        return employeesRepo.save(updateEmployeesInfo(employeesRepo.getOne(employeeId), newEmployeeInfo));
    }


    private Employee updateEmployeesInfo(Employee employee, Employee newEmployeeInfo) {
        if (newEmployeeInfo.getId() != null)
            employee.setId(newEmployeeInfo.getId());
        if (newEmployeeInfo.getName() != null)
            employee.setName(newEmployeeInfo.getName());
        if (newEmployeeInfo.getAge() != null)
            employee.setAge(newEmployeeInfo.getAge());
        if (newEmployeeInfo.getGender() != null)
            employee.setGender(newEmployeeInfo.getGender());
        if (newEmployeeInfo.getSalary() != null)
            employee.setSalary(newEmployeeInfo.getSalary());
        return employee;
    }

    public void deleteById(Integer employeeId){
        employeesRepo.deleteById(employeeId);
    }


}
