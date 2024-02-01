package com.example.geoCoding.repository.Service.impl;

import com.example.geoCoding.model.Company;
import com.example.geoCoding.model.Subscriptions;
import com.example.geoCoding.repository.CompanyRepository;
import com.example.geoCoding.repository.Service.CompanyRepoService;
import com.example.geoCoding.repository.SubscriptionRepository;
import com.example.geoCoding.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CompanyRepoServiceImpl implements CompanyRepoService {

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public Optional<Company> findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }
}
