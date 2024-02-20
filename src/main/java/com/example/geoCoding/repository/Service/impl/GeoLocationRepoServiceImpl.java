package com.example.geoCoding.repository.Service.impl;


import com.example.geoCoding.repository.Service.GeoLocationRepoService;
import com.example.geoCoding.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeoLocationRepoServiceImpl implements GeoLocationRepoService {


    @Autowired
    private ShopRepository shopRepository;
//    @Override
//    public List<Shop> getWithInKms(Double longit, Double latit, Integer Distance, Long company) {
//        return shopRepository.getWithInKms(longit,latit, Distance,company);
//    }


}
