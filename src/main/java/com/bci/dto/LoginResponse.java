package com.bci.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse implements Serializable{
	
	private static final long serialVersionUID = 9106044126769552983L;
	private String username;
	private String token;

}
