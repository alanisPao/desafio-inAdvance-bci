package com.bci.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bci.dto.PhoneDTO;
import com.bci.dto.UserRequestDTO;
import com.bci.dto.UserResponseDTO;
import com.bci.entity.Phone;
import com.bci.entity.User;
import com.bci.exception.ResourceAlreadyExistException;
import com.bci.exception.UserNotFoundException;
import com.bci.mapper.UserMapper;
import com.bci.repository.UserRepository;
import com.bci.utils.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	public UserResponseDTO create(UserRequestDTO user) throws ResourceAlreadyExistException {
		Optional<User> userEmail = repository.findUserByEmail(user.getEmail());
		if(userEmail.isPresent()) {
			throw new ResourceAlreadyExistException("El correo ya esta registrado");
		}
			
		User entity;
		try {
			String token = JwtUtil.generateJWTToken(user.getEmail());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			entity = UserMapper.converToEntity(user, token);
			repository.save(entity);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		User newUser = repository.findByEmail(user.getEmail());
		log.info("user "+newUser.getPhones());
		UserResponseDTO response = UserMapper.converToDto(newUser);
		
		return response;
	}

	@Override
	@Transactional(readOnly = true)
	public UserResponseDTO findByEmail(String email) throws UserNotFoundException {
		User findUser = repository.findByEmail(email);
		if(findUser == null) {
			throw new UserNotFoundException("Usuario no encontrado");

		}

		UserResponseDTO response = UserMapper.converToDto(findUser);
		return response ;
	}

	@Override
	public UserResponseDTO findById(String id) throws UserNotFoundException {
		
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			log.info("User found with id: {} ", id);
            return UserMapper.converToDto(user.get());

		}
		throw new UserNotFoundException("Usuario no encontrado");

	}

	@Override
	public UserResponseDTO update(UserRequestDTO user, String id) throws UserNotFoundException {
		
		Optional<User> optionalUser = repository.findById(id);

		// Verifica si el usuario existe
		if(optionalUser.isPresent()) {
			User existingUser = optionalUser.get();

			existingUser.setName(user.getName());
			existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
    

			User savedUser = repository.save(existingUser);
			
			UserResponseDTO response = UserMapper.converToDto(savedUser);

			return response;
		}
		throw new UserNotFoundException("Usuario no encontrado");

	}

	@Override
	public void delete(String id) {

		Optional<User> optionalUser = repository.findById(id);
		
		if(optionalUser.isPresent()){
			repository.delete(optionalUser.get());
        }

		
	}

	@Override
	public List<UserResponseDTO> findAll() {
		List<User> lista = repository.findAll();
		
		List<UserResponseDTO> users = new ArrayList<>();
		users = lista.stream().map(user -> UserMapper.converToDto(user))
				.collect(Collectors.toList());
		return users;
	}

	


	@Override
	public void updateUser(UserRequestDTO user) {
		
		repository.updateUser(user.getEmail(), user.getToken(), LocalDateTime.now(), LocalDateTime.now());
		
	}

}
