package ai.rodolfomendes.consolechat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
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
	public ApplicationRunner init(ChatClient.Builder chatBuilder) {
		return args -> {
			ChatClient chatClient = chatBuilder
					.defaultOptions(ChatOptions.builder().model("gemma3"))
					.build();

			var prompt = "Hello AI buddy, greetings from humanity!";

			var response = chatClient
					.prompt(prompt)
					.call()
					.content();

			IO.println("> " + prompt);
			IO.println("> " + response);
		};
	}
}
