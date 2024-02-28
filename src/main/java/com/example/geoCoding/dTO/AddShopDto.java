package com.example.geoCoding.dTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddShopDto extends AddressDto {

    @Range(min =-180,max = 180,message = "longitude must be between -180 and 180 both inclusive")
    @NotNull(message = "longitude must not be null")
    private Double longitude;
    @Range(min =-90,max = 90,message = "longitude must be between -90 and 90 both inclusive")
    @NotNull(message = "latitude must not be null")
    private Double latitude;
    @NotNull(message = "companyId must not be null")
    private String companyId;
    @NotNull(message = "shopName must not be null")
    private String shopName;

}
