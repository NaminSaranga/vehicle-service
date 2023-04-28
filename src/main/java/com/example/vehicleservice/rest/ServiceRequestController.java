package com.example.vehicleservice.rest;

import com.example.vehicleservice.dto.ServiceRequest;
import com.example.vehicleservice.repository.ServiceRequestRepository;
import com.example.vehicleservice.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/service-requests")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private ServiceRequestService serviceRequestService;

    /**
     * @author Namin Saranga
     * @param serviceRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        serviceRequest.setPrice(serviceRequestService.calculatePrice(serviceRequest.getServiceRequirement()));
        serviceRequestRepository.save(serviceRequest);
        return ResponseEntity.ok("Service request has been successfully created!");
    }

    /**
     * @author Namin Saranga
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceRequest> getServiceRequestById(@PathVariable Long id) {
        Optional<ServiceRequest> optionalServiceRequest = serviceRequestRepository.findById(id);
        if (optionalServiceRequest.isPresent()) {
            ServiceRequest serviceRequest = optionalServiceRequest.get();
            return ResponseEntity.ok(serviceRequest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @author Namin Saranga
     * @return
     */
    @GetMapping
    public ResponseEntity<List<ServiceRequest>> getAllServiceRequests() {
        List<ServiceRequest> serviceRequests = serviceRequestRepository.findAll();
        return ResponseEntity.ok(serviceRequests);
    }

    /**
     * @author Namin Saranga
     * @param id
     * @param updatedServiceRequest
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateServiceRequest(@PathVariable Long id, @RequestBody ServiceRequest updatedServiceRequest) {
        Optional<ServiceRequest> optionalServiceRequest = serviceRequestRepository.findById(id);
        if (optionalServiceRequest.isPresent()) {
            ServiceRequest serviceRequest = optionalServiceRequest.get();
            serviceRequest.setServiceRequirement(updatedServiceRequest.getServiceRequirement());
            serviceRequest.setVehicleType(updatedServiceRequest.getVehicleType());
            serviceRequest.setExpectedDeliveryDate(updatedServiceRequest.getExpectedDeliveryDate());
            serviceRequest.setName(updatedServiceRequest.getName());
            serviceRequest.setEmail(updatedServiceRequest.getEmail());
            serviceRequest.setPhone(updatedServiceRequest.getPhone());
            serviceRequest.setPrice(serviceRequestService.calculatePrice(updatedServiceRequest.getServiceRequirement()));
            serviceRequestRepository.save(serviceRequest);
            return ResponseEntity.ok("Service request with ID " + id + " has been successfully updated!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @author Namin Saranga
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceRequest(@PathVariable Long id) {
        Optional<ServiceRequest> optionalServiceRequest = serviceRequestRepository.findById(id);
        if (optionalServiceRequest.isPresent()) {
            serviceRequestRepository.deleteById(id);
            return ResponseEntity.ok("Service request with ID " + id + " has been successfully deleted!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
