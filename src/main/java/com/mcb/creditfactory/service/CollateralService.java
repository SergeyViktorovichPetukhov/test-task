package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Collateral;
import com.mcb.creditfactory.service.airplane.AirplaneService;
import com.mcb.creditfactory.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: reimplement this
@Service
public class CollateralService {
    @Autowired
    private CarService carService;
    @Autowired
    private AirplaneService airplaneService;


    public Long saveCollateral(Collateral object) {

        if (object instanceof CarDto) {
            CarDto car = (CarDto) object;
            boolean approved = carService.approve(car);
            if (!approved) {
                return null;
            }

            return Optional.of(car)
                    .map(carService::fromDto)
                    .map(carService::save)
                    .map(carService::getId)
                    .orElse(null);

        }
        if (object instanceof AirplaneDto) {
            AirplaneDto airplane = (AirplaneDto) object;
            boolean approved = airplaneService.approve(airplane);
            if (!approved) {
                return null;
            }

            return Optional.of(airplane)
                    .map(airplaneService::fromDto)
                    .map(airplaneService::save)
                    .map(airplaneService::getId)
                    .orElse(null);

        }
        throw new IllegalArgumentException();
    }

    public Collateral getInfo(Collateral object) {
        if (object instanceof CarDto) {
            return Optional.of((CarDto) object)
                    .map(carService::fromDto)
                    .map(carService::getId)
                    .flatMap(carService::load)
                    .map(carService::toDTO)
                    .orElse(null);

        }
        if (object instanceof AirplaneDto) {
            return Optional.of((AirplaneDto) object)
                    .map(airplaneService::fromDto)
                    .map(airplaneService::getId)
                    .flatMap(airplaneService::load)
                    .map(airplaneService::toDTO)
                    .orElse(null);

        }
        throw new IllegalArgumentException();
    }

    public Collateral addValuation(Collateral object){
        if (object instanceof CarDto) {
            CarDto dto = (CarDto) object;
            boolean approved = carService.approve(dto);
            if (!approved) {
                return null;
            }

            return Optional.of(dto)
                    .map(carService::addValuation)
                    .map(carService::save)
                    .map(carService::toDTO)
                    .orElse(null);

        }
        if (object instanceof AirplaneDto) {
            AirplaneDto dto = (AirplaneDto) object;
            boolean approved = airplaneService.approve(dto);
            if (!approved) {
                return null;
            }

            return Optional.of(dto)
                    .map(airplaneService::addValuation)
                    .map(airplaneService::save)
                    .map(airplaneService::toDTO)
                    .orElse(null);

        }
        throw new IllegalArgumentException();
    }
}
