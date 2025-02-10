package jborg.exam.examNoBS24.profanityFilter.service;


import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jborg.exam.examNoBS24.product.services.Query;
import jborg.exam.examNoBS24.profanityFilter.ProfanityData;


@Service
public class ProfanityService implements Query<Boolean, String>
{
	
    // Define the URL of the API
    String urlStr = "https://api.api-ninjas.com/v1/profanityfilter?text=";
	private String text = "text";
	
	private RestTemplate restTemplate;
	
	public ProfanityService(RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}
	
	@Override
	public ResponseEntity<Boolean> execute(String text) 
	{
		
		URI uri = UriComponentsBuilder
				.fromHttpUrl(urlStr)
				.queryParam(this.text, text)
				.build()
				.toUri();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
        headers.set("X-API-Key", "9IAG7Vst1QZXB8ZEZTO5VA==uV25rqYmsMAWY9gi");

		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		
		try
		{
			ResponseEntity<ProfanityData> response = restTemplate.exchange(uri, HttpMethod.GET, entity, ProfanityData.class);
			boolean profane = response.getBody().isHas_profanity();
			if(profane)System.out.println(text + " is Profane");
			else System.out.println(text + " is not Profane");
			
			
			//CatFactResponse response = restTemplate.getForObject("https://catfact.ninja/fact?max_length="+i, CatFactResponse.class);
			
			return ResponseEntity.ok(response.getBody().isHas_profanity());

		}
		catch(Exception exception)
		{
			throw new RuntimeException("Profanity Rest API is down");
		}
	}
}
