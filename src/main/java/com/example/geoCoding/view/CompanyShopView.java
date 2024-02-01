package com.example.geoCoding.view;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;


@Entity
@Immutable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "company_shop_view")
public class CompanyShopView {
    @Id
    private Integer id;
    private String shopName;
    private Double longitude;
    private Double latitude;
    private String companyId;
    private String companyName;
    private String city;
    private String state;
    private String country;
    private String door;
    private String street;


}
