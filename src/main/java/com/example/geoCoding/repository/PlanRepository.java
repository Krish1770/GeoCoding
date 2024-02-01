package com.example.geoCoding.repository;


import com.example.geoCoding.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan,String> {


}
