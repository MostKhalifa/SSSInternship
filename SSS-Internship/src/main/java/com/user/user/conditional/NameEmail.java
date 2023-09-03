package com.user.user.conditional;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NameEmailCondition.class)
@Retention(RUNTIME)
@Target({TYPE, ANNOTATION_TYPE})
public @interface NameEmail {
	
    String fieldName ();
    String dependFieldName ();
	
    String message() default "Have your username in your email";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    
    //String first();
    //String second();
    
}
