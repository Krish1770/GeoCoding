package com.example.geoCoding.controller;

import com.example.geoCoding.DTO.AddSubscriptionDto;
import com.example.geoCoding.api.SubscriptionApi;
import com.example.geoCoding.model.Subscriptions;
import com.example.geoCoding.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubscriptionController implements SubscriptionApi {

    @Autowired
    private SubscriptionService subscriptionService;
    @Override
    public Subscriptions add(AddSubscriptionDto addSubscriptionDto) {
        return subscriptionService.add(addSubscriptionDto);
    }
}
