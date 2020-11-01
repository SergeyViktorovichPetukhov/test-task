package com.mcb.creditfactory.service.car;

import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.dto.Valuation;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.CarValuation;
//import com.mcb.creditfactory.model.Valuation;
import com.mcb.creditfactory.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private ExternalApproveService approveService;

    @Autowired
    private CarRepository repository;

    @Override
    public boolean approve(CarDto dto) {
        return approveService.approve(new CarAdapter(dto)) == 0;
    }

    @Override
    public Car save(Car car) {
        return repository.save(car);
    }

    @Override
    public Optional<Car> load(Long id) {
        return repository.findById(id);
    }

    @Override
    public Car fromDto(CarDto dto) {
        Car car = new Car(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getYear(),
                dto.getPower()
        );
        car.setValuations(convertValuation(dto,car));
        return car;
    }


    @Override
    public CarDto toDTO(Car car) {
        CarValuation valuation = car.getValuations()
                .stream()
                .max(Comparator.comparing(CarValuation::getDate))
                .get();
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getPower(),
                car.getYear(),
                new Valuation(
                        valuation.getValue(),
                        valuation.getDate()
                )
        );
    }

    @Override
    public Car addValuation(CarDto dto) {
        Optional<Car> optional = repository.findById(dto.getId());
        if (optional.isPresent()){
            Car car = optional.get();
            List<CarValuation> valuations = car.getValuations();
            CarValuation newValuation = new CarValuation(
                    dto.getValuation().getValue(),
                    dto.getValuation().getDate()
            );
            valuations.add(newValuation);
            newValuation.setCar(car);
            return car;
        }
        return null;
    }

    @Override
    public Long getId(Car car) {
        return car.getId();
    }

    private List<CarValuation> convertValuation(CarDto dto, Car car){
        List<CarValuation> valuations = new ArrayList<>();
        CarValuation valuation = new CarValuation(
                        dto.getValuation().getValue(),
                        dto.getValuation().getDate()
        );
        valuation.setCar(car);
        valuations.add(valuation);
        return valuations;
    }

}



