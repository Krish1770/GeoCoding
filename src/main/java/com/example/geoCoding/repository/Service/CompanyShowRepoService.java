package com.example.geoCoding.repository.Service;

import com.example.geoCoding.view.CompanyShopView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyShowRepoService {
    List<CompanyShopView> getWithInKms(Double longitude, Double latitude, Integer distance, String companyId);
}
