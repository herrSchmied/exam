package jborg.exam.examNoBS24.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jborg.exam.examNoBS24.security.jwt.JWTUtil;


@RestController
public class LoginController
{
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private AuthenticationManager manager;

	public LoginController(AuthenticationManager manager)
	{
		this.manager = manager;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody CustomUser user)
	{
		
		logger.info("Login user: " + user.getUsername());
		
		//This is not a jsonwebtoken!!!
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken
				(user.getUsername(), user.getPassword());
		
		
		//This will fault if the Credentials are not valide!!
		Authentication authentication = manager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwtToken = JWTUtil.generateToken((User)authentication.getPrincipal());
		
		return ResponseEntity.ok(jwtToken);
	}
}