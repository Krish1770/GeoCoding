package com.example.geoCoding.api;


import com.example.geoCoding.DTO.ResponseDto;
import com.example.geoCoding.DTO.ShopDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "geocoding")
public interface GeoCodingApi {

    @GetMapping()
    public ResponseEntity<ResponseDto> getNearestShops(@RequestBody ShopDto shopDto);
}
