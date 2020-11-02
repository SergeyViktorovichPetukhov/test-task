package com.mcb.creditfactory.generic_test;

import com.mcb.creditfactory.dto.AirplaneDto;
import com.mcb.creditfactory.dto.CarDto;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.model.Airplane;
import com.mcb.creditfactory.model.AirplaneValuation;
import com.mcb.creditfactory.model.Car;
import com.mcb.creditfactory.model.CarValuation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class GenericServiceImpl<Entity extends Serializable, Dto> implements GenericService<Entity,Dto>{

    @Autowired
    private IGenericDao<Entity> dao;

    @Autowired
    ExternalApproveService approveService;

    private Class<Entity> entityClass;

    private Class<Dto> dtoClass;

    @Override
    public void setEntityAndDtoClazz(Class entityClass, Class dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public boolean approve(Object o) {
        return approveService.approve(new GenericAdapter<>((GenericDto)o)) == 0;
    }

    @Override
    public Entity save(Entity entity) {
      //  return dao.create(entity);
        return null;
    }

    @Override
    public Optional<Entity> load(Long id) {
        return Optional.of(dao.findOne(id));
    }

    @Override
    public Entity fromDto(Dto o) {
        return null;
    }

    @Override
    public Dto toDTO(Entity entity) {
        return null;
    }

    @Override
    public Long getId(Entity entity) {
        if (entity instanceof Car){
            return ((Car) entity).getId();
        }
        if (entity instanceof Airplane){
            return ((Airplane) entity).getId();
        }
        return null;
    }

    @Override
    public Entity addValuation(Object o) {
        return null;
    }

    private List<CarValuation> convertValuation(Dto dto, Entity entity){
        if (entity instanceof Car){
            Car car = (Car) entity;
            CarDto carDto = (CarDto) dto;
            List<CarValuation> valuations = new ArrayList<>();
            CarValuation valuation = new CarValuation(
                    carDto.getValuation().getValue(),
                    carDto.getValuation().getDate()
            );
            valuation.setCar(car);
            valuations.add(valuation);
            return valuations;
        }
        if (entity instanceof Airplane){
            Airplane airplane = (Airplane) entity;
            AirplaneDto airplaneDto = (AirplaneDto) dto;
            List<AirplaneValuation> valuations = new ArrayList<>();
            AirplaneValuation valuation = new AirplaneValuation(
                    airplaneDto.getValuation().getValue(),
                    airplaneDto.getValuation().getDate()
            );
            valuation.setAirplane(airplane);
            valuations.add(valuation);
         //   return valuations;
        }
        return null;
    }
}
