package com.example.geoCoding.api;

import com.example.geoCoding.dTO.AddPlanDto;
import com.example.geoCoding.model.Plan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "${plan}")
public interface PlanApi {

    @PostMapping(value = "${add}")
    public Plan add(@RequestBody AddPlanDto addPlanDto);
}
