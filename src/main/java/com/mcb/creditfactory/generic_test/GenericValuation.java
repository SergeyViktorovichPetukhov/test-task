package com.mcb.creditfactory.generic_test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericValuation {
    private Long id;
    private BigDecimal value;
    private LocalDate date;
}
