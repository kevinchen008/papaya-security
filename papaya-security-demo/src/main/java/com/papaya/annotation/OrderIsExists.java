package com.papaya.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: java类作用描述
 * @Author: kevin.chen
 * @CreateDate: 2018/2/26 16:38
 * @Version: 1.0
 */
public class OrderIsExists implements ConstraintValidator<OrderExists,String> {
    @Override
    public void initialize(OrderExists constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
