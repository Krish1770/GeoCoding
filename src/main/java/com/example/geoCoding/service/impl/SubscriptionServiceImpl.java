package com.example.geoCoding.service.impl;

import com.example.geoCoding.dTO.AddSubscriptionDto;
import com.example.geoCoding.exceptionHandling.BadRequestException;
import com.example.geoCoding.model.Company;
import com.example.geoCoding.model.Plan;
import com.example.geoCoding.model.Subscriptions;
import com.example.geoCoding.repository.CompanyRepository;
import com.example.geoCoding.repository.PlanRepository;
import com.example.geoCoding.repository.SubscriptionRepository;
import com.example.geoCoding.service.SubscriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscriptions add(AddSubscriptionDto addSubscriptionDto) {

        Optional<Plan> plan = planRepository.findById(addSubscriptionDto.getPlanId());
        Optional<Company> company = companyRepository.findById(addSubscriptionDto.getCompanyId());

        if (plan.isEmpty() || company.isEmpty()) {
            if (plan.isPresent())
                throw new BadRequestException("company id not found");
            if (company.isPresent())
                throw new BadRequestException("plan id not found");

            throw new BadRequestException("company id and plan id not found");
        }
        Subscriptions subscriptions = new Subscriptions();
        subscriptions.setPlanId(addSubscriptionDto.getPlanId());
        subscriptions.setCompanyId(addSubscriptionDto.getCompanyId());
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, Integer.parseInt(plan.get().getValidity().toString()));
        subscriptions.setCreatedAt(date);
        subscriptions.setExpiredAt(calendar.getTime());
        return subscriptionRepository.save(subscriptions);
    }

    @Override
    public Boolean isSubscriptionValid(String subscriptionId) {
        return null;
    }
}
