package com.thoughtworks.springbootcompany.controller;


import com.thoughtworks.springbootcompany.model.Companies;
import com.thoughtworks.springbootcompany.service.CompaniesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
    @Autowired
    private CompaniesService companiesService;
    private static List<Companies> companiesList new ArrayList<>();



}
