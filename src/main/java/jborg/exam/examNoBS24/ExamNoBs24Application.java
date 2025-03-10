package jborg.exam.examNoBS24;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ExamNoBs24Application {

	public static void main(String[] args) {
		SpringApplication.run(ExamNoBs24Application.class, args);
	}

}
