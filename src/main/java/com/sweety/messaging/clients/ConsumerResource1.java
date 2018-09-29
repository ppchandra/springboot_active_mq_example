package com.sweety.messaging.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerResource1 {
	
	@Value("spring.active.queue")
	private String queue;

	@JmsListener(destination = "queue")
	public void listener(String message) {
		System.out.println("Received message in 1 : " + message);
	}

}
