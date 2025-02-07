package jborg.exam.examNoBS24.profanityFilter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config
{
	@Bean
	//Spring Container injection.
	//Gives access to the RestTemplate throughout the Application
	public RestTemplate restTemplate()
	{		
		//NO RestTemplate configuration yet.
		return new RestTemplate();
	}
}
