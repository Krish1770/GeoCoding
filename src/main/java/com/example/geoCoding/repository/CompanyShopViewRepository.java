package com.example.geoCoding.repository;

import com.example.geoCoding.view.CompanyShopView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyShopViewRepository extends JpaRepository<CompanyShopView,Integer> {
    @Query("SELECT a from CompanyShopView a where a.companyId=(?4) AND (acos(cos(RADIANS(?2)-RADIANS(a.latitude))* cos(RADIANS(a.longitude)-RADIANS(?1)))*6371)<(?3)")
    public List<CompanyShopView> getWithInKms(Double longit, Double latit, Integer Distance, String company);

}
