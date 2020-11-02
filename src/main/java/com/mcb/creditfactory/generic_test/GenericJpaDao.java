package com.mcb.creditfactory.generic_test;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
public class GenericJpaDao< T extends Serializable>
        extends AbstractJpaDao< T > implements IGenericDao< T >{
    @Override
    public void setClazz(Class<T> clazzToSet) {
        super.setClazz(clazzToSet);
    }

    @Override
    public T findOne(Long id) {
        return super.findOne(id);
    }

    @Override
    public List<T> findAll() {
        return super.findAll();
    }

    @Override
    public void save(T entity) {
        super.save(entity);
    }

    @Override
    public T update(T entity) {
        return super.update(entity);
    }

    @Override
    public void delete(T entity) {
        super.delete(entity);
    }

    @Override
    public void deleteById(Long entityId) {
        super.deleteById(entityId);
    }


}