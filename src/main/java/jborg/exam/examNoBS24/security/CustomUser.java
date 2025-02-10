package jborg.exam.examNoBS24.security;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="custom_user")
@Data
@AllArgsConstructor
public class CustomUser
{

	public CustomUser()
	{
		
	}
	
	@Id
	@Column(name="username")
	private String username;
	

	@Column(name="password")
	private String password;
}
