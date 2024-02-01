package com.example.geoCoding.repository.Service;

import com.example.geoCoding.model.ResponseLog;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ResponseLogRepoService {
    Optional<ResponseLog> findBySubscriptionId(String subscriptionId);
}
