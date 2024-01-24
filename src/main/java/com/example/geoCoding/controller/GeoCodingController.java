package com.example.geoCoding.controller;


import com.example.geoCoding.DTO.ResponseDto;
import com.example.geoCoding.DTO.ShopDto;
import com.example.geoCoding.api.GeoCodingApi;
import com.example.geoCoding.service.GeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeoCodingController implements GeoCodingApi {


    @Autowired
    private GeoCodingService geoCodingService;
    @Override
    public ResponseEntity<ResponseDto> getNearestShops(ShopDto shopDto) {
        return geoCodingService.getNearestShops(shopDto);
    }
}
