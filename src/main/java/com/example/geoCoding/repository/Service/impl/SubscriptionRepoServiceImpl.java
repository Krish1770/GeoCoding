package com.example.geoCoding.repository.Service.impl;


import com.example.geoCoding.model.Subscriptions;
import com.example.geoCoding.repository.Service.SubscriptionRepoService;
import com.example.geoCoding.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SubscriptionRepoServiceImpl implements SubscriptionRepoService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Override
    public Optional<Subscriptions> findByCompanyIdAndExpiredAtGreaterThan(String company, Date date) {
        return subscriptionRepository.findByCompanyIdAndExpiredAtGreaterThan(company,date);
    }
}
