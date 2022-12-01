package com.ideas2It.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
         return authConfig.getAuthenticationManager();
     }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.httpBasic().and()
		        .csrf().disable().authorizeRequests()
				.antMatchers("/getTwoWheelers", "/getTwoWheeler/{code}", "/searchTwoWheelers/{letters}",
						"/range/{start}/{end}", "/In", "/createDealer", "/deteleDealer/{id}", "/getDealers",
						"/getDealer/{id}", "/updateDealer/{id}")
				.hasAuthority("dealer")
				.antMatchers("/createTwoWheeler", "/getTwoWheelers", "/deleteTwoWheeler/{code}", "/getTwoWheeler/{code}",
						"/searchTwoWheelers/{letters}", "/range/{start}/{end}", "/In", "/updateTwoWheeler/{code}",
						"/createManufacturer", "/getManufacturers", "/getManufacturer/{id}", "/deleteManufacturer/{id}",
						"/updateManufacturer/{id}")
				.hasAuthority("manufacturer")
				.anyRequest().permitAll()

				// login
				.and().formLogin().defaultSuccessUrl("/home", true)

				// logout
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		return http.build();
	}
}
