package com.example.geoCoding.dTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {
    private String message;


    private Object data;

    private HttpStatus httpStatus;
}
