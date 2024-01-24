package com.example.geoCoding.service;


import com.example.geoCoding.DTO.ResponseDto;
import com.example.geoCoding.DTO.ShopDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface GeoCodingService {
    ResponseEntity<ResponseDto> getNearestShops(ShopDto shopDto);
}
