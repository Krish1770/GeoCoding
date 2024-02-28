package com.example.geoCoding.dTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

