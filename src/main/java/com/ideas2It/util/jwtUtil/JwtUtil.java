package com.ideas2It.util.jwtUtil;

import java.util.Date;

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
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis()))
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
