package com.ideas2It.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(AuthenticationManagerBuilder authentication) throws Exception {
		authentication.inMemoryAuthentication().withUser("manufacturer").password("{noop}manu0411").authorities("manufacturer");
		authentication.inMemoryAuthentication().withUser("dealer").password("{noop}deal0411").authorities("dealer");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
	     http
	    .httpBasic()
	    .and()
	    .csrf().disable()
		.authorizeRequests()	
		.antMatchers("/getTwoWheelers", "/getTwoWheeler/{code}", "/searchTwoWheelers/{letters}", 
				"/range/{start}/{end}", "/In", "/createDealer", "/deteleDealer/{id}",
				"/getDealers", "/getDealer/{id}", "/updateDealer/{id}")
		.hasAuthority("dealer")
		.antMatchers("/createTwoWheeler", "/getTwoWheelers", "/deleteTwoWheeler/{code}",
				"/getTwoWheeler/{code}", "/searchTwoWheelers/{letters}", "/range/{start}/{end}", 
				"/In", "/updateTwoWheeler/{code}", "/createManufacturer", "/getManufacturers",
			    "/getManufacturer/{id}", "/deleteManufacturer/{id}", "/updateManufacturer/{id}")
		.hasAuthority("manufacturer")
		
		//login
		.and()
		.formLogin()
		.defaultSuccessUrl("/home", true)
		
		//logout
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        ;
	}
}
