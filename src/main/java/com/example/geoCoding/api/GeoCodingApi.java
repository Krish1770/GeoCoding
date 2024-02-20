package com.example.geoCoding.api;


import com.example.geoCoding.DTO.AddShopDto;
import com.example.geoCoding.DTO.ShopDto;
import com.example.geoCoding.view.CompanyShopView;
import com.example.geoCoding.model.Shop;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "${shop}")
public interface GeoCodingApi {


    @PostMapping("${add}")
    public ResponseEntity<List<Shop>> addShops(@Valid @RequestBody AddShopDto shop);
    @GetMapping("${find}")
    public ResponseEntity<List<CompanyShopView>> getNearestShops(@Valid @RequestBody ShopDto shopDto);

    @GetMapping("${ApiKey}")
    public ResponseEntity<String> getApiKey(HttpServletRequest request);

}
