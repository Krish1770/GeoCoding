package com.example.geoCoding.repository.Service;

import com.example.geoCoding.model.Company;
import com.example.geoCoding.model.Subscriptions;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public interface CompanyRepoService {




    Optional<Company>findByCompanyName(String companyName);
}
