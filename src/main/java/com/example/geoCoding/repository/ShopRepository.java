package com.example.geoCoding.repository;

import com.example.geoCoding.model.Shop;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShopRepository extends JpaRepository<Shop,Integer> {

    @Query( "SELECT a from Shop a where (acos(cos(RADIANS(?2)-RADIANS(a.latitude))* cos(RADIANS(a.longitude)-RADIANS(?1)))*6371)<(?3)" )
    public List<Shop> getWithInKms(Double longit,Double latit,Integer Distance);
}
