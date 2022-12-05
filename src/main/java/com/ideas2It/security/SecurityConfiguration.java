package com.ideas2It.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Autowired
	UserDetailsService userService;

	@Autowired
	SecurityFilter securityFilter;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
		return authenticationManagerBuilder.build();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic().disable();
		http.csrf().disable().authorizeRequests().antMatchers("/loginUser").permitAll()
				.antMatchers("/dealer/create-dealer", "/dealer/{id}", "/dealer").hasAuthority("dealer")
				.antMatchers("/twoWheeler/create-twoWheeler", "/manufacturer/create-manufacturer", "manufacturers",
						"manufacturer/{id}")
				.hasAuthority("manufacturer")

				.antMatchers("twoWheeler", "/twoWheeler/{code}", "/twoWheeler/search/{letters}",
						"/twoWheeler/range/{start}/{end}", "/twoWheeler-in-codes")
				.hasAnyAuthority("manufacturer", "dealer")

				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
