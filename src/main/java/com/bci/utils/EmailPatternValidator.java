package com.bci.utils;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailPatternValidator implements ConstraintValidator<ValidEmailPattern, String> {


	@Value("${regex.email}")
	private String emailRegex;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// Aquí aplicas la lógica de validación utilizando el patrón leído desde el
		// archivo de propiedades

		return value != null && value.matches(emailRegex);
	}
}