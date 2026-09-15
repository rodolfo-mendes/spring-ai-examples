package ai.rodolfomendes.consolechat;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsoleChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsoleChatApplication.class, args);
	}

	@Bean
	public ApplicationRunner init() {
		return args -> {
			IO.println("Welcome to the Console Chat Application!");
		};
	}
}
