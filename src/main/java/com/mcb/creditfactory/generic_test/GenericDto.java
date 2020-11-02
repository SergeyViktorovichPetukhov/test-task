package com.mcb.creditfactory.generic_test;

import com.mcb.creditfactory.dto.Valuation;
import com.mcb.creditfactory.external.CollateralType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericDto {
    private Valuation valuation;
    private String model;
    private String brand;
    private Short year;
    private CollateralType type;
}
