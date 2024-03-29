package com.example.geoCoding.repository;

import com.example.geoCoding.model.Company;
import com.example.geoCoding.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {


}
