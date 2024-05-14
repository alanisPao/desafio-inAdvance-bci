package com.bci.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordPatternValidator.class)
public @interface ValidPassPattern {
	String message() default "Formato de contraseña no válido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
