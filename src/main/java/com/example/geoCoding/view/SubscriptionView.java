package com.example.geoCoding.view;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.util.Date;

@Immutable
@Entity(name = "subscription_view")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionView {

    @Id
    private String subscriptionId;

    private String companyName;

    private String companyId;

    private String planId;

    private String description;

    private String planType;

    private Long requestCount;

    private Date createdAt;

    private Date expiredAt;
}
