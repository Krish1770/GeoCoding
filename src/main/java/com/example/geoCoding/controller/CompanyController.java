package com.example.geoCoding.controller;

import com.example.geoCoding.DTO.AddCompanyDto;
import com.example.geoCoding.DTO.LoginDto;
import com.example.geoCoding.DTO.ResponseDto;
import com.example.geoCoding.api.CompanyApi;
import com.example.geoCoding.model.Company;
import com.example.geoCoding.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController implements CompanyApi {

    @Autowired
    private CompanyService companyService;
    @Override
    public ResponseEntity<List<Company>>  addCompany(AddCompanyDto addCompanyDto) {
        return  ResponseEntity.status(HttpStatus.OK).body(companyService.addCompany(addCompanyDto));
    }

    @Override
    public ResponseEntity<String> login(LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.login(loginDto));
    }
}
