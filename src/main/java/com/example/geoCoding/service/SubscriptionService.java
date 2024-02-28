package com.example.geoCoding.service;

import com.example.geoCoding.dTO.AddSubscriptionDto;
import com.example.geoCoding.model.Subscriptions;
import org.springframework.stereotype.Service;

@Service
public interface SubscriptionService {
    Subscriptions add(AddSubscriptionDto addSubscriptionDto);

    Boolean isSubscriptionValid(String subscriptionId);
}
