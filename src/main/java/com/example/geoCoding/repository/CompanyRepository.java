package com.example.geoCoding.repository;

import com.example.geoCoding.model.Company;
import com.example.geoCoding.model.Shop;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CompanyRepository extends JpaRepository<Company,String> {


    Optional<Company> findByCompanyName(String companyName);
}
