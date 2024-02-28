package com.example.geoCoding.controller;

import com.example.geoCoding.dTO.AddPlanDto;
import com.example.geoCoding.api.PlanApi;
import com.example.geoCoding.model.Plan;
import com.example.geoCoding.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController implements PlanApi {

    @Autowired
    private PlanService planService;
    @Override
    public Plan add(AddPlanDto addPlanDto) {
        return planService.add(addPlanDto);
    }
}
