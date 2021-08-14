package com.thoughtworks.springbootemployee.controller;


import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.mapper.CompanyMapper;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.repository.CompaniesRepository;
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
    private static List<Company> companyList = new ArrayList<>();

    @Autowired
    private CompaniesRepository companiesRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public CompaniesController(CompaniesService companiesService){
    }

    @GetMapping()
    public List<CompanyResponse> getAllCompanies(){
        return companyMapper.toResponse(companiesService.getCompaniesList());
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getCompanyById(@PathVariable Integer companyId){
        return companyMapper.toResponse(companiesService.getById(companyId));
    }

    @GetMapping("/{companyId}/employees")
    public List<EmployeeResponse> getAllEmployeesByCompany(@PathVariable Integer companyId){
        return employeeMapper.toResponse(companiesService.getById(companyId).getEmployees());
    }


    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getCompaniesByPagination(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return companyList
                .stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addCompany(@RequestBody Company newCompany) {
        Company companyToBeAdded = new Company(
                newCompany.getCompanyName(),
                newCompany.getEmployeesNumber(),
                newCompany.getEmployees(),
                companyList.size()+1);
        companyList.add(companyToBeAdded);
    }

    @PutMapping(path = "/{companyId}")
    public Company updateCompanyInformation(@PathVariable Integer companyId, @RequestBody Company companyToBeUpdated) {
        return companyList
                .stream()
                .filter(company -> company.getCompanyID().equals(companyId))
                .map(company -> updateCompanyInformation(company,companyToBeUpdated))
                .findFirst()
                .get();
    }

    private Company updateCompanyInformation(Company company, Company companyToBeUpdated) {

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
        companyList.remove(getCompanyById(companyId));
    }
}
