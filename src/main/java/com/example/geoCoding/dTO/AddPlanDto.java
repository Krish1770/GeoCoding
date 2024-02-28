package com.example.geoCoding.dTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPlanDto {

    private String description;

    private String planType;

    private Long validity;

    private Long requestCount;

    private Long price;
}
