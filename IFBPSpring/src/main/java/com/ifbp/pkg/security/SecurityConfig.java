package com.ifbp.pkg.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home", "/user", "/movie").permitAll()
				.anyRequest().permitAll()
				.and()
			.logout()
				.permitAll()
			.and()
			.csrf().disable();
	}
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = 
				User.withDefaultPasswordEncoder()
					.username("admin")
					.password("password")
					.roles("USER")
					.build();
		
		return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
