package com.user.user.conditional;

import javax.validation.ConstraintValidator;

import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Properties;

import com.user.user.entity.User;
 

public class NameEmailCondition implements ConstraintValidator<NameEmail,Object>{
	
    private String fieldName ;
    private String dependFieldName ;
	private String message;

    
	@Override
	public void initialize(NameEmail constraintAnnotation) {
		fieldName  = constraintAnnotation.fieldName ();
		dependFieldName  = constraintAnnotation.dependFieldName ();
		message = constraintAnnotation.message();
 }
	
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(fieldName);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(dependFieldName);

        if (fieldValue != null) {
            return ((String) fieldMatchValue).contains((String)fieldValue);
        } else {
            return fieldMatchValue == null;
        }
    }
	
	
}
