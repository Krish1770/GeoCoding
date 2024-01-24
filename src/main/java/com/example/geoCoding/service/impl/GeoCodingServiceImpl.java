package com.example.geoCoding.service.impl;


import com.example.geoCoding.DTO.ResponseDto;
import com.example.geoCoding.DTO.ShopDto;
import com.example.geoCoding.model.Shop;
import com.example.geoCoding.repository.Service.GeoLocationRepoService;
import com.example.geoCoding.service.GeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeoCodingServiceImpl implements GeoCodingService {

    @Autowired
    private GeoLocationRepoService geoLocationRepoService;
    @Override
    public ResponseEntity<ResponseDto> getNearestShops(ShopDto shopDto) {

        List<Shop> shopList=geoLocationRepoService.getWithInKms(shopDto.getLongitude(), shopDto.getLatitude(),shopDto.getDistance());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("list of near shops within "+shopDto.getDistance(),shopList,HttpStatus.OK));
    }
}
