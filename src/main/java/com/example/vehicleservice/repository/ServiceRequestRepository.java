package com.example.vehicleservice.repository;

import com.example.vehicleservice.dto.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,Long> {
}
