package com.bci.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailPatternValidator.class)
public @interface ValidEmailPattern {
	String message() default "Formato de correo electrónico no válido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
