package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.entity.Companies;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    @Autowired
    private CompaniesService companiesService;
    private static List<Companies> companiesList = new ArrayList<>();

    public CompaniesController(CompaniesService companiesService){
        List<Employee> newEmployees = new ArrayList<>();
        newEmployees.add(new Employee(1,"Park Jinyoung",49,"male",40000));
        newEmployees.add(new Employee(2,"Hirai Momo",24,"female",4200));
        newEmployees.add(new Employee(3,"Myoui Mina",24,"female",4000));
        newEmployees.add(new Employee(4,"Park Jisoo",24,"female",4700));
        newEmployees.add(new Employee(5,"Im Nayeon",25,"female",4600));
        newEmployees.add(new Employee(6,"Hwang Yeji",21,"female",3000));
        newEmployees.add(new Employee(7,"Lee Chaeryeong",20,"female",3400));
        newEmployees.add(new Employee(8,"Lee Yong Bok",20,"male",4000));
        newEmployees.add(new Employee(9,"Kim Seung Min",20,"male",4100));
        newEmployees.add(new Employee(10,"Jung Ji-hoon",39,"male",30000));
        companiesList.add(new Companies("JYP Entertainment",20,newEmployees, 1));
        companiesList.add(new Companies("TheBlackLabel",20,newEmployees, 2));
        companiesList.add(new Companies("SM Entertainment",20,newEmployees, 3));
        companiesList.add(new Companies("Pledis",20,newEmployees, 4));
        companiesList.add(new Companies("YG Entertainment",20,newEmployees, 5));
        companiesList.add(new Companies("Starship",20,newEmployees, 6));
        companiesList.add(new Companies("FNC",20,newEmployees, 7));

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

    @GetMapping("/{companyId}/employees")
    public List<Employee> getAllEmployeesByCompany(@PathVariable Integer companyId){
        return companiesList.stream()
                .filter(company -> company.getCompanyID().equals(companyId))
                .findFirst()
                .orElse(null)
                .getEmployees();
    }


    @GetMapping(params = {"page", "pageSize"})
    public List<Companies> getCompaniesByPagination(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companiesList
                .stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addCompany(@RequestBody Companies newCompany) {
        Companies companyToBeAdded = new Companies(
                newCompany.getCompanyName(),
                newCompany.getEmployeesNumber(),
                newCompany.getEmployees(),
                companiesList.size()+1);
        companiesList.add(companyToBeAdded);
    }

    @PutMapping(path = "/{companyId}")
    public Companies updateCompanyInformation(@PathVariable Integer companyId, @RequestBody Companies companyToBeUpdated) {
        return companiesList
                .stream()
                .filter(company -> company.getCompanyID().equals(companyId))
                .map(company -> updateCompanyInformation(company,companyToBeUpdated))
                .findFirst()
                .get();
    }

    private Companies updateCompanyInformation(Companies company, Companies companyToBeUpdated) {

        if (companyToBeUpdated.getCompanyID()!= null)
            company.setCompanyID(companyToBeUpdated.getCompanyID());
        if (companyToBeUpdated.getCompanyName()!=null)
            company.setCompanyName(companyToBeUpdated.getCompanyName());
        if (companyToBeUpdated.getEmployees()!=null)
            company.setEmployees(companyToBeUpdated.getEmployees());
        if (companyToBeUpdated.getEmployeesNumber()!=null)
            company.setEmployeesNumber(companyToBeUpdated.getEmployeesNumber());
        return company;
    }


    @DeleteMapping(path = "/{companyId}")
    public void deleteCompany(@PathVariable Integer companyId){
        companiesList.remove(getCompanyById(companyId));
    }
}
