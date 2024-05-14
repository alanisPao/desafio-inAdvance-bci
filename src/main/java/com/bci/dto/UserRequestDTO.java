package com.bci.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bci.utils.ValidEmailPattern;
import com.bci.utils.ValidPassPattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO implements Serializable{
	
	
	private static final long serialVersionUID = 7226107429008591357L;

	@ApiModelProperty(name = "name", value = "Nombre del usuario")
	@NotNull(message = "name no puede ser nulo")
	@NotEmpty(message = "name es requerido.")
	private String name;
	
	@ApiModelProperty(name = "Email", value = "Email del usuario")
	@NotNull(message = "Email no puede ser nulo")
	@NotEmpty(message = "Email es requerido.")
	@ValidEmailPattern
	private String email;
	
	@ApiModelProperty(name = "Password", value = "password del usuario")
	@NotNull(message = "Password no puede ser nulo")
	@NotEmpty(message = "Password es requerido.")
	@ValidPassPattern
	private String password;
	
	@ApiModelProperty(name = "Token", value = "Token generado")
	private String token;
	
	private List<PhoneDTO> phones;

}
