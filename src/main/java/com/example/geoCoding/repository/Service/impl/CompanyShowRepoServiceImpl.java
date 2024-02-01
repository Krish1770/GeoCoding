package com.example.geoCoding.repository.Service.impl;

import com.example.geoCoding.view.CompanyShopView;
import com.example.geoCoding.repository.CompanyShopViewRepository;
import com.example.geoCoding.repository.Service.CompanyShowRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyShowRepoServiceImpl implements CompanyShowRepoService {

    @Autowired
    private CompanyShopViewRepository companyShopViewRepository;
    @Override
    public List<CompanyShopView> getWithInKms(Double longitude, Double latitude, Integer distance, String company) {
        return companyShopViewRepository.getWithInKms( longitude,  latitude,  distance, company);

    }
}
