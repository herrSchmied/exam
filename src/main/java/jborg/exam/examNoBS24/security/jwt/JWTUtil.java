package jborg.exam.examNoBS24.security.jwt;


import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JWTUtil
{
	public static String generateToken(User userDetails)
	{
		String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // Extract role(s)
		
		return Jwts
				.builder()
				.claim("role", role)
				.subject(userDetails.getUsername())
				.expiration(new Date(System.currentTimeMillis()+3000000))
				.signWith(getSigningKey())
				.compact();
		
	}

	public static Claims getClaims(String token)
	{
		return Jwts
				.parser()
				.verifyWith(getSigningKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	public static boolean isTokenValide(String token)
	{
		return !isExpired(token);
	}
	
	public static boolean isExpired(String token)
	{
		
		return getClaims(token)
				.getExpiration()
				.before(new Date(System.currentTimeMillis()));
	}

	private static SecretKey getSigningKey()
	{
		byte keyBytes[] = Decoders.BASE64.decode("OnceThereWasABoyAndThisMustBeAStoryLongEnoughSoItEndsHere");
		
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
