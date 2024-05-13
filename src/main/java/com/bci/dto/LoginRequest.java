package com.bci.dto;

import java.io.Serializable;

import lombok.Data;


@Data
public class LoginRequest implements Serializable{

	private static final long serialVersionUID = -1443411407069952684L;
	
	private String username;
	private String password;

}
