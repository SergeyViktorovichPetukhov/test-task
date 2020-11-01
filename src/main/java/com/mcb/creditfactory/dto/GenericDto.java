package com.mcb.creditfactory.dto;

import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class GenericDto {
    protected Valuation valuation;
    protected Short year;
    protected LocalDate date;
    protected CollateralType type;
}
