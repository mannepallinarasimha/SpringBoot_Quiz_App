package com.nara.QuestionQuizApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "QUIZ API", version = "1.0", description = "Questions Quiz Information"))
public class QuestionQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestionQuizApplication.class, args);
	}

}
