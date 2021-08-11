package com.thoughtworks.springbootcompany.controller;


import com.thoughtworks.springbootcompany.model.Companies;
import com.thoughtworks.springbootcompany.service.CompaniesService;
import com.thoughtworks.springbootemployee.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    @Autowired
    private CompaniesService companiesService;
    private static List<Companies> companiesList = new ArrayList<>();

    public CompaniesController(CompaniesService companiesService){
        List<Employees> newEmployees = new ArrayList<>();
        newEmployees.add(new Employees(1,"Park Jinyoung",49,"male",40000));
        newEmployees.add(new Employees(2,"Hirai Momo",24,"female",4200));
        newEmployees.add(new Employees(3,"Myoui Mina",24,"female",4000));
        newEmployees.add(new Employees(4,"Park Jisoo",24,"female",4700));
        newEmployees.add(new Employees(5,"Im Nayeon",25,"female",4600));
        newEmployees.add(new Employees(6,"Hwang Yeji",21,"female",3000));
        newEmployees.add(new Employees(7,"Lee Chaeryeong",20,"female",3400));
        newEmployees.add(new Employees(8,"Lee Yong Bok",20,"male",4000));
        newEmployees.add(new Employees(9,"Kim Seung Min",20,"male",4100));
        newEmployees.add(new Employees(10,"Jung Ji-hoon",39,"male",30000));
        companiesList.add(new Companies("JYPE",20,newEmployees, 1));
    }

    @GetMapping()
    public List<Companies> getAllCompanies(){
        return companiesList;
    }

    @GetMapping("/{companyId}")
    public Companies getCompanyById(@PathVariable Integer companyId){
        return companiesList.stream()
                .filter(company -> company.getCompanyID().equals(companyId))
                .findFirst()
                .orElse(null);
    }



}
