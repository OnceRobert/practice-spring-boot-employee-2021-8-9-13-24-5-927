package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Companies;
import com.thoughtworks.springbootemployee.repository.CompaniesRepository;
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