package com.example.geoCoding.api;


import com.example.geoCoding.dTO.AddSubscriptionDto;
import com.example.geoCoding.model.Subscriptions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${subscription}")
public interface SubscriptionApi {

    @PostMapping("${add}")
    public Subscriptions add(@RequestBody AddSubscriptionDto addSubscriptionDto);
}
