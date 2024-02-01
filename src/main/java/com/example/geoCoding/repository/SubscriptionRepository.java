package com.example.geoCoding.repository;

import com.example.geoCoding.model.Subscriptions;
import jdk.jshell.JShell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface SubscriptionRepository extends JpaRepository<Subscriptions,String> {
    Optional<Subscriptions> findByCompanyIdAndExpiredAtGreaterThan(String company, Date date);
}
