package com.bci.mapper;

import com.bci.dto.PhoneDTO;
import com.bci.entity.Phone;


public interface PhoneMapper {
	
	public static Phone converToEntity(PhoneDTO dto) {
		Phone phone = Phone.builder()
				.number(dto.getNumber())
				.cityCode(dto.getCityCode())
				.countryCode(dto.getCountryCode())
				.build();
		return phone;
	}
	
     public static PhoneDTO convertToDto(Phone phone) {
    	 PhoneDTO dto = PhoneDTO.builder()
    			 .number(phone.getNumber())
    			 .cityCode(phone.getCityCode())
    			 .countryCode(phone.getCountryCode())
    			 .build();
		return dto;
     }

}
