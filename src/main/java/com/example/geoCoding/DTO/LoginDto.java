package com.example.geoCoding.DTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull(message = "company name must not be empty")
    private String companyName;

    @NotNull(message = "password must not be empty")
    private String password;
}
