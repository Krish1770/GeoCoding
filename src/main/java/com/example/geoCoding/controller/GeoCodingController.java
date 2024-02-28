package com.example.geoCoding.controller;


import com.example.geoCoding.dTO.AddShopDto;
import com.example.geoCoding.dTO.ShopDto;
import com.example.geoCoding.api.GeoCodingApi;
import com.example.geoCoding.view.CompanyShopView;
import com.example.geoCoding.model.Shop;
import com.example.geoCoding.service.GeoCodingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;

@RestController
public class GeoCodingController implements GeoCodingApi {


    @Autowired
    private GeoCodingService geoCodingService;

    @Override
    public ResponseEntity<List<Shop>> addShops(AddShopDto addShopDto) {
        return ResponseEntity.status(HttpStatus.OK).body(geoCodingService.addShops(addShopDto));
    }

    @Override
    public ResponseEntity<List<CompanyShopView>> getNearestShops(ShopDto shopDto) {
        return ResponseEntity.status(HttpStatus.OK).body(geoCodingService.getNearestShops(shopDto));
    }

    @Override
    public ResponseEntity<String> getApiKey(HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(Base64.getEncoder().encodeToString(geoCodingService.getApiKey(request)));

    }
}
