package com.example.web_shopapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.web_shopapi.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

	
}
