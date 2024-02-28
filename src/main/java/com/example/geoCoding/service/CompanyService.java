package com.example.geoCoding.service;

import com.example.geoCoding.dTO.AddCompanyDto;
import com.example.geoCoding.dTO.LoginDto;
import com.example.geoCoding.model.Company;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface CompanyService {
    List<Company> addCompany(AddCompanyDto addCompanyDto);


    UserDetails loadByCompanyName(String companyName);

    String login(LoginDto loginDto);
}


