package com.thoughtworks.springbootcompany.service;

import com.thoughtworks.springbootcompany.model.Companies;
import com.thoughtworks.springbootcompany.repository.CompaniesRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompaniesService {
    @Resource
    private CompaniesRepository companiesRepository;

    public  CompaniesService(CompaniesRepository companiesRepository){
        this.companiesRepository = companiesRepository;
    }

    public List<Companies> getCompaniesList(){
        return companiesRepository.getAllCompanies();
    }
}