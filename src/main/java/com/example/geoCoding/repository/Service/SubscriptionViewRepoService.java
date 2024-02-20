package com.example.geoCoding.repository.Service;

import com.example.geoCoding.view.SubscriptionView;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface SubscriptionViewRepoService {
    Optional<SubscriptionView> findBySubscriptionId(String subscription);
}
