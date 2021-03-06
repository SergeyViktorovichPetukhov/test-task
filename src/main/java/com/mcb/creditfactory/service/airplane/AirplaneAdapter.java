package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.Valuation;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class AirplaneAdapter implements CollateralObject {
    private AirplaneDto airplane;

    public AirplaneAdapter(AirplaneDto dto,LocalDate date){
        this.airplane = dto;
        if (date != null){
            Valuation lastValuation = airplane.getValuation();
            lastValuation.setDate(date);
            airplane.setValuation(lastValuation);
        }
    }

    @Override
    public BigDecimal getValue() {
        return airplane.getValuation().getValue();
    }

    @Override
    public Short getYear() {
        return airplane.getYear();
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.now();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.AIRPLANE;
    }
}
