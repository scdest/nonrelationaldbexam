package com.exam.relationaldbexam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class CarShowroom {
    @Id
    private UUID id;
    private String name;
    private String location;
    private int capacity;
    private int numberOfCars;
    private String contactNumber;
    private String email;
    private boolean hasParking;
    private double averageRating;
    private List<String> availableBrands;
}
