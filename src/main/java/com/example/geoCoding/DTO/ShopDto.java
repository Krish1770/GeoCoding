package com.example.geoCoding.DTO;


import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopDto {
    private Double longitude;

    private Double latitude;

    private Integer distance;
}
