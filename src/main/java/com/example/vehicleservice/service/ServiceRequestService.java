package com.example.vehicleservice.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ServiceRequestService {

    private static final Map<String, Double> prices = Map.of(
            "battery", 500.0,
            "tyres", 200.0,
            "lights", 100.0,
            "engine", 1000.0
    );

    /**
     * @author Namin Saranga
     * @param serviceRequirements
     * @return
     */
    public Double calculatePrice(List<String> serviceRequirements) {
        return serviceRequirements.stream()
                .mapToDouble(prices::get)
                .sum();
    }
}
