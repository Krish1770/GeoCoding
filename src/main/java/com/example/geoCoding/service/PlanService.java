package com.example.geoCoding.service;

import com.example.geoCoding.DTO.AddPlanDto;
import com.example.geoCoding.model.Plan;
import org.springframework.stereotype.Service;

@Service
public interface PlanService {
    Plan add(AddPlanDto addPlanDto);
}
