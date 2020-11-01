package com.mcb.creditfactory.service;

import java.util.Optional;

public interface GenericService<Entity,Dto> {
    void setEntityAndDtoClazz(Class entityClass, Class dtoClass);
    boolean approve(Dto dto);
    Entity save(Entity entity);
    Optional<Entity> load(Long id);
    Entity fromDto(Dto dto);
    Dto toDTO(Entity car);
    Long getId(Entity car);
    Entity addValuation(Dto dto);
}
