package com.bci.mapper;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bci.dto.PhoneDTO;
import com.bci.dto.UserRequestDTO;
import com.bci.dto.UserResponseDTO;
import com.bci.entity.Phone;
import com.bci.entity.User;



public class UserMapper{
	
	public static User converToEntity(UserRequestDTO dto, String token) {
		
		List<Phone> lista = new ArrayList<>();
		lista = dto.getPhones().stream().map(phone -> PhoneMapper.converToEntity(phone))
				.collect(Collectors.toList());
		
		User entity = User.builder()
				.name(dto.getName())
				.email(dto.getEmail())
				.password(dto.getPassword())
				.phones(lista)
				.token(token).build();
		return entity;
		
	}
	
	public static UserResponseDTO converToDto(User user) {
		List<PhoneDTO> lista = new ArrayList<>();
		lista = user.getPhones().stream().map(phone -> PhoneMapper.convertToDto(phone))
				.collect(Collectors.toList());
		UserResponseDTO dto = UserResponseDTO.builder()
				.name(user.getName())
				.email(user.getEmail())
		        .password(user.getPassword())
		        .phone(lista)
				.id(user.getId())
				.created(user.getCreated())
				.modified(user.getModified())
				.lastLogin(user.getLastLogin())
				.token(user.getToken())
				.isActive(user.getIsActive()).build();
		return dto;

	}

}
