package com.example.geoCoding.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subscriptions {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String subscriptionId;

    private String companyId;

    private Date createdAt;

    private Date expiredAt;


    private String planId;
}
