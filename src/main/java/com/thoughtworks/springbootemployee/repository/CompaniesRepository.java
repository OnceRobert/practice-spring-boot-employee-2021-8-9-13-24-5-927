package com.thoughtworks.springbootemployee.repository;


import com.thoughtworks.springbootemployee.model.Companies;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompaniesRepository {
    private List<Companies> companies = new ArrayList<>();

    public CompaniesRepository(){

    }

    public List<Companies> getAllCompanies(){
        return companies;
    }

    public Companies add(Companies newCompany){
        companies.add(newCompany);
        return newCompany;
    }
}