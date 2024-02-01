package com.example.geoCoding.service.impl;


import com.example.geoCoding.DTO.AddShopDto;
import com.example.geoCoding.DTO.ShopDto;
import com.example.geoCoding.exceptionHandling.BadRequestException;
import com.example.geoCoding.model.Company;
import com.example.geoCoding.view.CompanyShopView;
import com.example.geoCoding.model.Shop;
import com.example.geoCoding.repository.CompanyRepository;
import com.example.geoCoding.repository.Service.CompanyShowRepoService;
import com.example.geoCoding.repository.Service.GeoLocationRepoService;
import com.example.geoCoding.repository.ShopRepository;
import com.example.geoCoding.service.GeoCodingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeoCodingServiceImpl implements GeoCodingService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GeoLocationRepoService geoLocationRepoService;

    @Autowired
    private CompanyShowRepoService companyShowRepoService;

    @Autowired
    private ShopRepository shopRepository;


    @Autowired
    private CompanyRepository companyRepository;
    @Override
    public List<CompanyShopView> getNearestShops(ShopDto shopDto) {
        List<CompanyShopView> shopList=companyShowRepoService.getWithInKms(shopDto.getLongitude(), shopDto.getLatitude(),shopDto.getDistance(), shopDto.getCompanyId());
        return shopList;
    }

    @Override
    public List<Shop> addShops(AddShopDto addShopDto) {



            Optional<Company>company=companyRepository.findById(addShopDto.getCompanyId());
            Shop shop;
            if(company.isPresent())
            {
                 shop=modelMapper.map(addShopDto, Shop.class);
                 shop.setCompany(addShopDto.getCompanyId());
                shopRepository.save(shop);
                return  shopRepository.findAll();
            }
            throw new BadRequestException("company mot found");
    }
}
