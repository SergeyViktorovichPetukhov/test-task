package com.mcb.creditfactory.service.airplane;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.Valuation;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.AirplaneValuation;
import com.mcb.creditfactory.repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService {
    @Autowired
    private ExternalApproveService approveService;
    @Autowired
    private AirplaneRepository repository;

    @Override
    public boolean approve(AirplaneDto dto) {
        return approveService.approve(new AirplaneAdapter(dto)) == 0;
    }

    @Override
    public Airplane save(Airplane airplane) {
        return repository.save(airplane);
    }

    @Override
    public Optional<Airplane> load(Long id) {
        return repository.findById(id);
    }

    @Override
    public Airplane fromDto(AirplaneDto dto) {
        Airplane airplane = new Airplane(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getManufacturer(),
                dto.getYear(),
                dto.getFuelCapacity(),
                dto.getSeats()
        );
        airplane.setValuations(convertValuation(dto,airplane));
        return airplane;
    }

    @Override
    public AirplaneDto toDTO(Airplane airplane) {
        AirplaneValuation valuation = airplane.getValuations()
            .stream()
            .max(Comparator.comparing(AirplaneValuation::getDate))
            .get();
        return new AirplaneDto(
                airplane.getId(),
                airplane.getBrand(),
                airplane.getModel(),
                airplane.getManufacturer(),
                airplane.getYear(),
                airplane.getFuelCapacity(),
                airplane.getSeats(),
                new Valuation(
                        valuation.getValue(),
                        valuation.getDate()
                )
        );
    }

    @Override
    public Airplane addValuation(AirplaneDto dto) {
        Optional<Airplane> optional = repository.findById(dto.getId());
        if (optional.isPresent()){
            Airplane airplane = optional.get();
            List<AirplaneValuation> valuations = airplane.getValuations();
            AirplaneValuation newValuation = new AirplaneValuation(
                    dto.getValuation().getValue(),
                    dto.getValuation().getDate()
            );
            valuations.add(newValuation);
            newValuation.setAirplane(airplane);
            return airplane;
        }
        return null;
    }

    @Override
    public Long getId(Airplane airplane) {
        return airplane.getId();
    }

    private List<AirplaneValuation> convertValuation(AirplaneDto dto, Airplane airplane){
        List<AirplaneValuation> valuations = new ArrayList<>();
        AirplaneValuation valuation = new AirplaneValuation(
                dto.getValuation().getValue(),
                dto.getValuation().getDate()
        );
        valuation.setAirplane(airplane);
        valuations.add(valuation);
        return valuations;
    }
}
