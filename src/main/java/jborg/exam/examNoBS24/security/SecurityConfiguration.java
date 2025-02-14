package jborg.exam.examNoBS24.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jborg.exam.examNoBS24.security.jwt.JWTAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration
{
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests((authorize)->
				{
					
					authorize.requestMatchers("/login").permitAll();
					
					//These must be always possible
					authorize.requestMatchers("/createuser").permitAll();
					authorize.requestMatchers("/products").permitAll();
					authorize.requestMatchers(HttpMethod.GET, "/product/{Id}").permitAll();
					authorize.requestMatchers("/pSearch/{Id}").permitAll();
					authorize.requestMatchers("/pSort/{Id}").permitAll();

					authorize.anyRequest().authenticated();
				})
				.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
				.build();
			
	}
	
	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter()
	{
		return new JWTAuthenticationFilter();
	}
}
