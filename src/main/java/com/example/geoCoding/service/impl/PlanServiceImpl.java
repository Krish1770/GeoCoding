package com.example.geoCoding.service.impl;

import com.example.geoCoding.DTO.AddPlanDto;
import com.example.geoCoding.model.Plan;
import com.example.geoCoding.repository.PlanRepository;
import com.example.geoCoding.service.PlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Plan add(AddPlanDto addPlanDto) {

        Plan plan=modelMapper.map(addPlanDto, Plan.class);
        return planRepository.save(plan);
    }
}
