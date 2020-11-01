package com.mcb.creditfactory.service;

import com.mcb.creditfactory.dto.GenericDto;
import com.mcb.creditfactory.external.CollateralObject;
import com.mcb.creditfactory.external.ExternalApproveService;
import com.mcb.creditfactory.repository.IGenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Optional;

@Service
class GenericServiceImpl<Entity extends Serializable, Dto> implements GenericService{

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
    public Object save(Object o) {
        return dao.create((Entity)o);
    }

    @Override
    public Optional load(Long id) {
        return Optional.empty();
    }

    @Override
    public Object fromDto(Object o) {
        return null;
    }

    @Override
    public Object toDTO(Object car) {
        return null;
    }

    @Override
    public Long getId(Object car) {
        return null;
    }

    @Override
    public Object addValuation(Object o) {
        return null;
    }
}
