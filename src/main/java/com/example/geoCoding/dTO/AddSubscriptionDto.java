package com.example.geoCoding.dTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSubscriptionDto {

   @NotNull(message = "companyId must not be null")
    private String companyId;
    @NotNull(message = "planId must not be null")
    private String planId;
}
