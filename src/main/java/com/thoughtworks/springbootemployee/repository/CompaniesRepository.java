package com.thoughtworks.springbootemployee.repository;


import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, Integer> {

}