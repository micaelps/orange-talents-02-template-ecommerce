package com.micaelps.ecommerce.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistsIdValidator.class)
@Target({ElementType.FIELD})
@Documented
public @interface ExistsId {

    String message() default "{referece.not.exists}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> domainClass();

}
