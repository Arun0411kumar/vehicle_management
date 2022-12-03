package com.ideas2It.util.jwtUtil;


import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	private static String key = "Arun";
	
	public static String generateToken(String userName) {
		return Jwts.builder()
		.setSubject(userName)
		.setIssuer("arun0411")
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30)))
		.signWith(SignatureAlgorithm.HS256, key.getBytes())
		.compact()
		;
	}
	
	public static Claims getClaims(String token) {
		return Jwts.parser()
				.setSigningKey(key.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
}
