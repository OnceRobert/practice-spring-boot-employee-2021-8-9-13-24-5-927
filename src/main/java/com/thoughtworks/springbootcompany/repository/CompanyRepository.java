package com.thoughtworks.springbootcompany.repository;


import com.thoughtworks.springbootcompany.model.Companies;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Companies> companies = new ArrayList<>();

    public CompanyRepository(){

    }

    public List<Companies> getAllCompanies(){
        return companies;
    }

    public Companies add(Companies newCompany){
        companies.add(newCompany);
        return newCompany;
    }
}