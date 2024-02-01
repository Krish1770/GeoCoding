package com.example.geoCoding.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayloadDto {

    private Long exp;
    private Long iat;
    private String sub;
    private String companyId;
    private String subscriptionId;
}
