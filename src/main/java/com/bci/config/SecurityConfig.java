package com.bci.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bci.service.CustomUserDetailsService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	public static final String[] WHITE_LIST = {
			"/api/v1/login",
			"/api-docs/**",
			"/v2/api-docs",
			"/v3/api-docs/**",
            "/swagger-resources/**", 
            "/configuration/ui",
            "/configuration/security", 
            "/swagger-ui.html",
            "/webjars/**"
	};
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity){
		

       try {
    	   log.info("filterChain ");
		return  httpSecurity.csrf(csrf -> csrf.disable())
				   .addFilterAfter(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
				   
		            .authorizeHttpRequests(requests -> requests
		                    .antMatchers(WHITE_LIST) // Permitir acceso sin autenticación a estas rutas específicas
		                    .permitAll()
		                    // Permitir acceso sin autenticación a las rutas de inicio de sesión
		                    // Resto de las rutas requieren autenticación
		                    .anyRequest().authenticated())
		            .authenticationProvider(authenticationProvider())
		            .build();
	} catch (Exception e) {
		e.printStackTrace(System.out);
		return null;
		
	}
  

	}
	

	
	@Bean
     AuthenticationProvider authenticationProvider() {
		 log.info("authenticationProvider ");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        log.info("prov "+authenticationProvider);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    	log.info("conf "+config.getAuthenticationManager());
        return config.getAuthenticationManager();
    }


	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
