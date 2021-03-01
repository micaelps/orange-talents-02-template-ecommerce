package com.micaelps.ecommerce.validators;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Object> {

    @PersistenceContext
    private EntityManager manager;

    private String domainAttribute;
    private Class<?> klass;

    @Override
    public void initialize(ExistsId params){
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value==null) return  true;
        Query query = manager.createQuery("select 1 from "+klass.getName() +" where id=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        return !(list.isEmpty());
    }
}
