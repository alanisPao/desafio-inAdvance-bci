package com.bci.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO implements Serializable{

	private static final long serialVersionUID = 8145153816260993653L;

	private String number;
	
	@JsonProperty(value = "citycode")
	private String cityCode;
	
	@JsonProperty(value = "countrycode")
	private String countryCode;

}
