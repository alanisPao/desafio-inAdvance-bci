package com.bci.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bci.dto.LoginResponse;
import com.bci.dto.UserRequestDTO;
import com.bci.dto.UserResponseDTO;
import com.bci.exception.ResourceAlreadyExistException;
import com.bci.exception.UserNotFoundException;
import com.bci.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping(value = "/", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Creacion de usuario")
	@ApiResponses(value = { 
	        @ApiResponse(code = 201, message = "Usuario creado", response = LoginResponse.class),
	        @ApiResponse(code = 403 ,message = "No tienes acceso a este recurso")}) 
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO rqDto,
			@RequestHeader("Authorization") String bearerToken) throws ResourceAlreadyExistException {

		log.info("Create user "+rqDto);
		UserResponseDTO response = service.create(rqDto);

		return ResponseEntity.ok(response);

	}
	
	@GetMapping("/all")
	@ApiOperation(value="Listar usuarios")
	public ResponseEntity<List<UserResponseDTO>> findAllUsers(@RequestHeader("Authorization") String bearerToken){	
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/profile")
	@ApiOperation(value="Listar usuario por email")
	public ResponseEntity<?> findUserByEmail(@RequestParam(name = "email") String email, @RequestHeader("Authorization") String bearerToken) throws UserNotFoundException{
		
		UserResponseDTO user = service.findByEmail(email);
		
		return ResponseEntity.ok(user);
	       
	
	}
	
	
	@DeleteMapping("/delete/{id}")
	@ApiOperation(value="Eliminar usuario por id")
    public void deleteUser(@PathVariable String id) throws UserNotFoundException{
                service.delete(id);
    }

}
