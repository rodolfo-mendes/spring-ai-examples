package ai.rodolfomendes.consolechat;

import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ConsoleChatApplication {
	private static final @Nullable String DEFAULT_MODEL = "gemma3";

	public static void main(String[] args) {
		SpringApplication.run(ConsoleChatApplication.class, args);
	}

	@Bean
	public ApplicationRunner init(ChatClient.Builder chatBuilder) {
		return args -> {
			ChatClient chatClient = chatBuilder
					.defaultOptions(ChatOptions.builder().model(DEFAULT_MODEL))
					.build();

			IO.println("*** Console Chat ***");
			IO.println("model: " + DEFAULT_MODEL);
			IO.println("Type /exit to quit the application.");

			while(true) {
				var prompt = IO.readln("> ");
				if(prompt == null || prompt.isBlank()) {
					continue;
				}

				if(prompt.equals("/exit")) {
					break;
				}

				var response = chatClient
						.prompt(prompt)
						.call()
						.content();

				IO.println("- " + response + System.lineSeparator());
			}
		};
	}
}
