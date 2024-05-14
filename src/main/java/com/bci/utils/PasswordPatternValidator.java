package com.bci.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PasswordPatternValidator implements ConstraintValidator<ValidPassPattern, String> {

	@Value("${regex.password}")
	private String regexPassword;

	@Override
	public void initialize(ValidPassPattern constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// Aquí aplicas la lógica de validación utilizando el patrón leído desde el
		// archivo de propiedades
		log.info("reg pass "+regexPassword);
		
		log.info("valida "+value.matches(regexPassword));
		return value != null && value.matches(regexPassword);
	}
}
