package jborg.exam.examNoBS24.security.jwt;


import java.io.IOException;
import java.util.Collections;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class JWTAuthenticationFilter extends OncePerRequestFilter
{

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer "))
		{
			token = authHeader.substring(7);
		}
		
		if(token != null && JWTUtil.isTokenValide(token))
		{
			Authentication authentication = new UsernamePasswordAuthenticationToken
												(JWTUtil.getClaims(token).getSubject(),
														null,
														Collections.emptyList()
												);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}

}
