package com.example.geoCoding.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDto {



    private String method;

    private String requestURI;

    private String requestBody;

    private int status;

    private String responseBody;
}

