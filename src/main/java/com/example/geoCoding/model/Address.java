package com.example.geoCoding.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.http.HttpResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Address  {

    private String city;
    private String state;
    private String country;
    private String door;
    private String street;
}
