package com.example.geoCoding.service;

import com.example.geoCoding.DTO.LogDto;
import com.example.geoCoding.model.ResponseLog;
import org.springframework.stereotype.Service;

@Service
public interface ResponseLogService {

     ResponseLog add(LogDto logDto,String subscriptionId);

     Boolean isSubscriptionValid(String subscription);


}
