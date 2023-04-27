package com.example.vehicleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "service_request")
public class ServiceRequest {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<String> serviceRequirement;

    private String vehicleType;

    private LocalDate expectedDeliveryDate;

    private String name;

    private String email;

    private String phone;

    private Double price;
}


