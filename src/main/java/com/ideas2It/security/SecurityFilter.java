package com.ideas2It.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ideas2It.util.jwtUtil.JwtUtil;

import io.jsonwebtoken.Claims;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	UserDetailsService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = request.getHeader("Authorization");

		if (null != token) {
			Claims claims = JwtUtil.getClaims(token);
			String userName = claims.getSubject();
			SecurityContext securityContext = SecurityContextHolder.getContext();
			
			if (null != userName && null == securityContext.getAuthentication()
					&& !claims.getExpiration().before(new Date(System.currentTimeMillis()))) {
				UserDetails user = userService.loadUserByUsername(userName);
				UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(
						user.getUsername(), user.getPassword(), user.getAuthorities());
				userNamePassword.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				securityContext.setAuthentication(userNamePassword);
			}
		}
		filterChain.doFilter(request, response);
	}
}
