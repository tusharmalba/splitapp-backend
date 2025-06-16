package com.splitapp.splitapp.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive(message = "Amount must be positive")
    private double amount;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "PaidBy is required")
    private String paidBy;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, Double> splits; // e.g. {"Shantanu": 20.0, "Sanket": 40.0}
}
