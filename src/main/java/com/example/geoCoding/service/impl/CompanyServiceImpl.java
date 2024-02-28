package com.example.geoCoding.service.impl;


import com.example.geoCoding.auth.JwtService;
import com.example.geoCoding.dTO.AddCompanyDto;
import com.example.geoCoding.dTO.LoginDto;
import com.example.geoCoding.dTO.PayloadAssignDto;
import com.example.geoCoding.exceptionHandling.BadRequestException;
import com.example.geoCoding.model.Company;
import com.example.geoCoding.model.Subscriptions;
import com.example.geoCoding.repository.CompanyRepository;
import com.example.geoCoding.repository.Service.CompanyRepoService;
import com.example.geoCoding.repository.Service.SubscriptionRepoService;
import com.example.geoCoding.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {


    @Autowired
    ModelMapper modelMapper;
    @Autowired
    JwtService jwtService;
    @Autowired
    private SubscriptionRepoService subscriptionRepoService;
    @Autowired
    private CompanyRepoService companyRepoService;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> addCompany(AddCompanyDto addCompanyDto) {
        Company company = new Company();
        modelMapper.map(addCompanyDto, company);
        companyRepository.save(company);
        return companyRepository.findAll();
    }

    @Override
    public UserDetails loadByCompanyName(String companyName) {
        Optional<Company> company = companyRepoService.findByCompanyName(companyName);

        return modelMapper.map(company, UserDetails.class);
    }

    @Override
    public String login(LoginDto loginDto) {

        Optional<Company> company = companyRepoService.findByCompanyName(loginDto.getCompanyName());

        if (company.isPresent() && company.get().getPassword().equals(loginDto.getPassword()))
            return login(loginDto, company.get());

        throw new BadRequestException("company mot found");
    }

    public String login(LoginDto loginDto, Company company) {
        Optional<Subscriptions> subscriptions = subscriptionRepoService.findByCompanyIdAndExpiredAtGreaterThan(company.getCompanyId(), new Date());


        if (subscriptions.isEmpty())
            throw new BadRequestException("subscription not present");
        PayloadAssignDto payloadAssignDto = new PayloadAssignDto(company.getCompanyId(), subscriptions.get().getSubscriptionId(), company.getCompanyName());
        return jwtService.generateToken(payloadAssignDto);
    }
}
