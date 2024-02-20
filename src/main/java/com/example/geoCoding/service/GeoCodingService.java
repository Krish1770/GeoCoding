package com.example.geoCoding.service;


import com.example.geoCoding.DTO.AddShopDto;
import com.example.geoCoding.DTO.ShopDto;
import com.example.geoCoding.view.CompanyShopView;
import com.example.geoCoding.model.Shop;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@Service
public interface GeoCodingService {
    List<CompanyShopView> getNearestShops(ShopDto shopDto);

   List<Shop> addShops(AddShopDto shop);

    byte[] getApiKey(HttpServletRequest request) ;
}
