package com.example.geoCoding.repository.Service.impl;


import com.example.geoCoding.repository.Service.SubscriptionRepoService;
import com.example.geoCoding.repository.Service.SubscriptionViewRepoService;
import com.example.geoCoding.repository.SubscriptionViewRepository;
import com.example.geoCoding.service.impl.SubscriptionServiceImpl;
import com.example.geoCoding.view.SubscriptionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionViewRepoServiceImpl implements SubscriptionViewRepoService {

    @Autowired
    private SubscriptionViewRepository subscriptionViewRepository;
    @Override
    public Optional<SubscriptionView> findBySubscriptionId(String subscription) {
        return subscriptionViewRepository.findById(subscription);
    }
}
