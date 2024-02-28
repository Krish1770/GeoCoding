package com.example.geoCoding.service;


import com.example.geoCoding.dTO.AddShopDto;
import com.example.geoCoding.dTO.ShopDto;
import com.example.geoCoding.view.CompanyShopView;
import com.example.geoCoding.model.Shop;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GeoCodingService {
    List<CompanyShopView> getNearestShops(ShopDto shopDto);

   List<Shop> addShops(AddShopDto shop);

    byte[] getApiKey(HttpServletRequest request) ;
}
