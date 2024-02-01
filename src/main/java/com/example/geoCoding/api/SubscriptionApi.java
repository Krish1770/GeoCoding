package com.example.geoCoding.api;


import com.example.geoCoding.DTO.AddSubscriptionDto;
import com.example.geoCoding.model.Subscriptions;
import jdk.jshell.JShell;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("${subscription}")
public interface SubscriptionApi {

    @PostMapping("${add}")
    public Subscriptions add(@RequestBody AddSubscriptionDto addSubscriptionDto);
}
