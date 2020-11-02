package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Valuation;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.CollateralType;
import com.mcb.creditfactory.model.CarValuation;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class CarAdapter implements CollateralObject {

    private CarDto car;

    public CarAdapter(CarDto dto,LocalDate date){
        this.car = dto;
        if (date != null){
            Valuation lastValuation = car.getValuation();
            lastValuation.setDate(date);
            car.setValuation(lastValuation);
        }
    }

    @Override
    public BigDecimal getValue() {
        return car.getValuation().getValue();
    }

    @Override
    public Short getYear() {
        return car.getYear();
    }


    @Override
    public LocalDate getDate() {
        return car.getValuation().getDate();
    }

    @Override
    public CollateralType getType() {
        return CollateralType.CAR;
    }
}
