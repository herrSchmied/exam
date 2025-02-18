package jborg.exam.examNoBS24.security.jwt;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;

public class JWTAuthenticationFilter extends OncePerRequestFilter
{

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		
		System.out.println("InsideJWTAuthenticationFilter");
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		System.out.println("authHeader: " + authHeader);
		
		if(authHeader != null)token = authHeader;//&& authHeader.startsWith("Bearer "))

		
		if(token != null && JWTUtil.isTokenValide(token))
		{
			
			System.out.println("Hi inside setting authentication to SecurityContextHolder");

			String username = JWTUtil.getClaims(token).getSubject();
			String role = "ROLE_" + JWTUtil.getClaims(token).get("role", String.class);
			System.out.println("Hi again role: " + role);
			List<GrantedAuthority> list = new ArrayList<>();
			list.add(new SimpleGrantedAuthority(role));
			
			Authentication authentication = new UsernamePasswordAuthenticationToken
												(username,null, list);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

}
