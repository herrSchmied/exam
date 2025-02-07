package jborg.exam.examNoBS24.profanityFilter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfanityData
{

	  private String original;
	  private String censored;
	  private boolean has_profanity;
	  
	  public String toString()
	  {
		  String s = "original: " + original + "\n"
				  	+"censord: " + censored + "\n"
				  	+"has profanity: " + has_profanity;
		  
		  
		  return s;
	  }
}
