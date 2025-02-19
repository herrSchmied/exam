package jborg.exam.examNoBS24.security;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUserController
{

	private PasswordEncoder encoder;
	
	private CustomUserRepository customUserRepository;

	public CreateUserController(PasswordEncoder encoder, CustomUserRepository customUserRepository)
	{

		this.encoder = encoder;
		this.customUserRepository = customUserRepository;
	}
	
	@PostMapping("/createuser")
	public ResponseEntity<String> createUser(@RequestBody CustomUser user)
	{
		Optional<CustomUser> opUser = customUserRepository.findById(user.getUsername());
		
		if(!opUser.isPresent())
		{
			String name = user.getUsername();
			String pw = encoder.encode(user.getPassword());
			Roles role = user.getRole();
			
			//Default Constructor ist nur da wenn man entweder:
			// 1.) gar keinen Constructor angibt.
			// 2.) eine Constructor explicit ohne parameter.
			//Das scheint eine Regel für Entities zu sein!!!!!
			CustomUser toBeSaved = new CustomUser(name, pw, role);
			
			customUserRepository.save(toBeSaved);
			
			return ResponseEntity.ok("Success");
		}
		
		return ResponseEntity.badRequest().body("Failed");
	}
}
