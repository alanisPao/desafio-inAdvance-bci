package com.bci.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bci.utils.ValidEmailPattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class LoginRequest implements Serializable{

	private static final long serialVersionUID = -1443411407069952684L;
	
	
	@ApiModelProperty(name = "Email", value = "Email del usuario")
	@NotNull(message = "Email no puede ser nulo")
	@NotEmpty(message = "Email es requerido.")
	private String username;
	
	@ApiModelProperty(name = "Password", value = "password del usuario")
	@NotNull(message = "Password no puede ser nulo")
	@NotEmpty(message = "Password es requerido.")
	private String password;

}
