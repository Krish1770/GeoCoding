package com.example.geoCoding.service.impl;


import com.example.geoCoding.DTO.AddShopDto;
import com.example.geoCoding.DTO.ShopDto;
import com.example.geoCoding.auth.JwtService;
import com.example.geoCoding.exceptionHandling.BadRequestException;
import com.example.geoCoding.model.Company;
import com.example.geoCoding.model.Shop;
import com.example.geoCoding.repository.CompanyRepository;
import com.example.geoCoding.repository.Service.CompanyShowRepoService;
import com.example.geoCoding.repository.Service.GeoLocationRepoService;
import com.example.geoCoding.repository.ShopRepository;
import com.example.geoCoding.service.GeoCodingService;
import com.example.geoCoding.view.CompanyShopView;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GeoCodingServiceImpl implements GeoCodingService {



    @Autowired
    private JwtService jwtService;

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
//        System.out.println("password"+pass);
        List<CompanyShopView> shopList = companyShowRepoService.getWithInKms(shopDto.getLongitude(), shopDto.getLatitude(), shopDto.getDistance(), shopDto.getCompanyId());
        return shopList;
    }

    @Override
    public List<Shop> addShops(AddShopDto addShopDto) {


        Optional<Company> company = companyRepository.findById(addShopDto.getCompanyId());
        Shop shop;
        if (company.isPresent()) {
            shop = modelMapper.map(addShopDto, Shop.class);
            shop.setCompany(addShopDto.getCompanyId());
            shopRepository.save(shop);
            return shopRepository.findAll();

        }
        throw new BadRequestException("company mot found");
    }

    @Override
    public byte[] getApiKey(HttpServletRequest request) {

        String jwt = request.getHeader("Authorization");
        jwt = jwt.substring(7);
        Claims claims = jwtService.extractAllClaims(jwt);
        String subscriptionId = claims.get("subscriptionId").toString();
        Date expiration = claims.getExpiration();
        byte[] message = (subscriptionId +"_"+ expiration).getBytes();
        System.out.println(subscriptionId +"_"+ expiration+ "  12345");
        KeyFactory keyFactory = null;

        try {

            keyFactory = KeyFactory.getInstance("RSA");
            String publicString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqlkcWd7j9LvGya/WI/4+G2TzDuZiYcMERayQWbf0wK+IifNd8ci5igN/aIJ1xxjNZiZ69iWEoAZdrPSFvpPWVAXZ8whqnCV1nssmpje6UOFFSlkORz/qcNTuG2XZqcvmXFQGLmhk2gCZndURtmqsMw+D6SzToG4ECogL6Eh1UewIDAQAB";
            byte[] publicData = Base64.getDecoder().decode((publicString.getBytes()));
            X509EncodedKeySpec spec1 = new X509EncodedKeySpec(publicData);

            PublicKey publicKey = null;
            publicKey = keyFactory.generatePublic(spec1);
            Cipher cipher = null;
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        cipher.update(message);
            byte[] cipherText = null;
            return cipherText;
        }
        catch (InvalidKeySpecException |NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e)
        {
            throw new BadRequestException(e.toString());
        }
    }
}
