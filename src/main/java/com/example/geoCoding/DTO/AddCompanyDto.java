package com.example.geoCoding.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCompanyDto extends AddressDto {


    @Size(min = 15, max = 15, message = "length of gst must be 15")
    private String gstNumber;

    @Size(max = 10, min = 10, message = "length of phoneNumber must be 10")
    private String phoneNumber;

    @NotNull(message = "companyName must not be null")
    @Size(max = 50, message = "length of CompanyName must be less than 50")
    private String companyName;


}
