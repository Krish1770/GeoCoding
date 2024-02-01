package com.example.geoCoding.repository;

import com.example.geoCoding.model.ResponseLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseLogRepository extends MongoRepository<ResponseLog,String> {
    Optional<ResponseLog> findBySubscriptionId(String subscriptionId);
}
