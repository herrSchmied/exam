package jborg.exam.examNoBS24.security;


import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService
{
	
	private CustomUserRepository customUserRepository;
	
	

	public CustomUserDetailsService(CustomUserRepository customUserRepository)
	{
		this.customUserRepository = customUserRepository;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		
		Optional<CustomUser> opUser = customUserRepository.findById(username);
		
		if(opUser.isPresent())
		{
			
			CustomUser user = opUser.get();
			
			String role = user.getRole().getRolename();
			
			//Here would be the place for Roles and authorities. Maybe
			//thru a Database.
			
			
			return User
					.withUsername(user.getUsername())
					.password(user.getPassword())
					.roles(role)
					.build();
		}

		//should not throw new UserNotFoundException();
		
		return null;
	}
}