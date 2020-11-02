package com.mcb.creditfactory.generic_test;

import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class GenericAdapter<Dto extends GenericDto> implements CollateralObject {

    private Dto dto;

    @Override
    public BigDecimal getValue() {
        return dto.getValuation().getValue();
    }

    @Override
    public Short getYear() {
        return dto.getYear();
    }

    @Override
    public LocalDate getDate() {
        return dto.getValuation().getDate();
    }

    @Override
    public CollateralType getType() {
        return dto.getType();
    }
}
