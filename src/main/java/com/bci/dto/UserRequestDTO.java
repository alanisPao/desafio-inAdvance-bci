package com.bci.dto;

import java.io.Serializable;
import java.util.List;

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

	private String name;
	
	private String email;
	
	private String password;
	
	private String token;
	
	private List<PhoneDTO> phones;

}
