package com.mcb.creditfactory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Valuation {
    private BigDecimal value;
    private LocalDate date;
}
