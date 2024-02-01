package com.example.geoCoding.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Shop extends Address{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Length(max = 50,message = "size of shopName must be less than 50")
    @NotBlank(message = "shopName must not be empty")
    private String shopName;

    @Size(max = 10,min = 10,message = "length of phoneNumber must be 10")
    private String phoneNumber;

    @NotBlank(message = "longitude must not be empty")
    private Double longitude;

    @NotBlank(message = "latitude must not be empty")
    private Double latitude;

    @NotBlank(message = "latitude must not be empty")
    private String company;


}
