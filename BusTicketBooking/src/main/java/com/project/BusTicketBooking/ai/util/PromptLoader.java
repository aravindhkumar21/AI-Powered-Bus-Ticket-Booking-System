package com.project.BusTicketBooking.ai.util;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class PromptLoader {
	public String loadSystemPrompt() {
		try {
			ClassPathResource resource = new ClassPathResource("prompts/system-prompt.txt");

			return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
