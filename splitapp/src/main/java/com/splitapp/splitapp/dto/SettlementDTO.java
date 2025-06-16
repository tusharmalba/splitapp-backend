
package com.splitapp.splitapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SettlementDTO {
    private String from;
    private String to;
    private double amount;
}
