package com.example.geoCoding.DTO;


import jakarta.validation.constraints.NotBlank;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShopDto {

//    @NotBlank(message = "longitude must not be null")
    private Double longitude;

    private Double latitude;

    private Integer distance;

    private String companyId;

}
