package com.bci.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bci.entity.User;
import com.bci.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User userDetail = repository.findByEmail(username);

		
		if(!Objects.isNull(userDetail)) {

			log.info("user "+userDetail.getEmail());
			log.info("user pass"+userDetail.getPassword());
			return org.springframework.security.core.userdetails.User
		            .withUsername(userDetail.getEmail())
		            .password(userDetail.getPassword())
		            .authorities("ROLE_USER")
		            .build();
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}

}
