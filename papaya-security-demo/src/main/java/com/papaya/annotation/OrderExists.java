package com.papaya.annotation;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * @Description: java类作用描述
 * @Author: kevin.chen
 * @CreateDate: 2018/2/26 16:32
 * @Version: 1.0
 */
@Target({METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { })
public @interface OrderExists {

    String message();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
