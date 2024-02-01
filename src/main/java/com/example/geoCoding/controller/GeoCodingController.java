package com.example.geoCoding.controller;


import com.example.geoCoding.DTO.AddShopDto;
import com.example.geoCoding.DTO.ShopDto;
import com.example.geoCoding.api.GeoCodingApi;
import com.example.geoCoding.view.CompanyShopView;
import com.example.geoCoding.model.Shop;
import com.example.geoCoding.service.GeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
}
