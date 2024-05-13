package com.bci.service;

import java.util.List;
import java.util.Optional;

import com.bci.dto.UserRequestDTO;
import com.bci.dto.UserResponseDTO;
import com.bci.entity.User;
import com.bci.exception.ResourceAlreadyExistException;
import com.bci.exception.UserNotFoundException;

public interface UserService {
	
	UserResponseDTO create(UserRequestDTO user) throws ResourceAlreadyExistException;
	
	List<UserResponseDTO> findAll();
	
	UserResponseDTO findByEmail(String email) throws UserNotFoundException;
	
	UserResponseDTO findById(String id) throws UserNotFoundException;
	
	UserResponseDTO update(UserRequestDTO user, String id) throws UserNotFoundException;
	
	void delete(String id) throws UserNotFoundException;
	
	

}
