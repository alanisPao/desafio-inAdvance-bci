package com.bci.utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtil {

	private static Integer JWT_EXPIRATION;

	public static Integer getJwt_expiration() {
		return JWT_EXPIRATION;
	}

	@Value("${jwt.expiration}")
	public void setJwt_expiration(Integer jwt_expiration) {
		JWT_EXPIRATION = jwt_expiration;
	}

	public static String getSecretKey() {
		return SECRET_KEY;
	}

	@Value("${jwt.secret}")
	public void setSecretKey(String secret) {
		SECRET_KEY = secret;
	}

	private static String SECRET_KEY;


	public static String generateJWTToken(String username) {

		// Duration d = Duration.parse(JWT_EXPIRATION);
		log.info("dURACION", SECRET_KEY);
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
				.signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes())).compact();

		return "Bearer " + token;
	}
	
	

}
