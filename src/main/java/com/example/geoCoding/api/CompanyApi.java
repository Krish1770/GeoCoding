package com.example.geoCoding.api;


import com.example.geoCoding.dTO.AddCompanyDto;
import com.example.geoCoding.dTO.LoginDto;
import com.example.geoCoding.model.Company;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "${company}")
public interface CompanyApi {

    @PostMapping("${add}")
    public ResponseEntity<List<Company>> addCompany(@Valid @RequestBody AddCompanyDto addCompanyDto);

    @PostMapping("${login}")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto);
}
