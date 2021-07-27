package com.chaitanya.config;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.chaitanya.service.FileHandlingService;

import lombok.AllArgsConstructor;

@Lazy(false)
@Component
@AllArgsConstructor
public class ApplicationEventshandler {
	
	private FileHandlingService fileHandlingService;

	@EventListener(classes = { ContextRefreshedEvent.class })
	public void handleMultipleEvents() throws Exception {
		fileHandlingService.cleanup();
	}
}
