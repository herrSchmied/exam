package jborg.exam.examNoBS24.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.Filter;
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
					
					//It must be always possible to create new User....
					authorize.requestMatchers("/createuser").permitAll();

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
