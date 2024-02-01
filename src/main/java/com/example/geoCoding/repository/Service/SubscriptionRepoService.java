package com.example.geoCoding.repository.Service;


import com.example.geoCoding.model.Subscriptions;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public interface SubscriptionRepoService {
    Optional<Subscriptions> findByCompanyIdAndExpiredAtGreaterThan(String company, Date date);

}
