package com.example.geoCoding.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan
{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String planId;

    private String description;

    private String planType;

    private Integer validity;

    private Long requestCount;

    private Long price;

}
