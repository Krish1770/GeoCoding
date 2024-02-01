package com.example.geoCoding.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @NotNull(message = "city must not be empty")
    private String city;
    @NotNull(message = "state must not be empty")
    private String state;
    @NotNull(message = " country must not be empty")
    private String country;
    @NotNull(message = "door must not be empty")
    private String door;
    @NotNull(message = "street must not be empty")
    private String street;
}
