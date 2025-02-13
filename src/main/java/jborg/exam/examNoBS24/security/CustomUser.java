package jborg.exam.examNoBS24.security;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomUser
{
	
	@Id
	@Column(name="username")
	private String username;
	

	@Column(name="password")
	private String password;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rolename")
    private Roles role;

}
