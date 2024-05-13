package com.bci.controller;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bci.dto.LoginRequest;
import com.bci.dto.LoginResponse;
import com.bci.dto.UserRequestDTO;
import com.bci.dto.UserResponseDTO;
import com.bci.service.UserService;
import com.bci.utils.JwtUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@RequestMapping("/api/v1/login")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService service;

	
	
	@PostMapping()
	@ApiOperation(value="Authenticate user and generate jwt")
	@ApiResponses(value = { 
	        @ApiResponse(code = 200, message = "authenticated user", response = LoginResponse.class),
	        @ApiResponse(code = 403 ,message = "Unauthorized user")}) 
	public ResponseEntity<?> login(@Valid LoginRequest authenticationRequest) throws Exception {

		log.info("Login");
		String username = authenticationRequest.getUsername();

		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPassword()));
        log.info("paso la autenticacion");

        
       // UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getUsername());

		final String token = JwtUtil.generateJWTToken(username);
		
		
         UserResponseDTO user = service.findByEmail(username);
         log.info("usuario encontrado "+user);
         user.setToken(token);
         log.info("ahora actualizar "+user);
         UserRequestDTO rq = UserRequestDTO.builder()
        		 .name(user.getName())
                 .email(user.getEmail())
                 .password(user.getPassword())
                 .phones(user.getPhone())
                 .token(token)
                 .build();

         service.update(rq, user.getId());
         
		return ResponseEntity.ok(new LoginResponse(username, token));


	}
	


}
