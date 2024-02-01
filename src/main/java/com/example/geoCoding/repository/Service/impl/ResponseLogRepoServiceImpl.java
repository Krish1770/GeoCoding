package com.example.geoCoding.repository.Service.impl;

import com.example.geoCoding.model.ResponseLog;
import com.example.geoCoding.repository.ResponseLogRepository;
import com.example.geoCoding.repository.Service.ResponseLogRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ResponseLogRepoServiceImpl implements ResponseLogRepoService {

    @Autowired
    private ResponseLogRepository responseLogRepository;
    @Override
    public Optional<ResponseLog> findBySubscriptionId(String subscriptionId) {

        return responseLogRepository.findBySubscriptionId(subscriptionId);

    }
}
