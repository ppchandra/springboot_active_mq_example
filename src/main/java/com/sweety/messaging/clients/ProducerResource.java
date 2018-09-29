package com.sweety.messaging.clients;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producemessage")
public class ProducerResource {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Queue queue;

	@GetMapping("/{message}")
	private String publish(@PathVariable("message") String message) {
		jmsTemplate.convertAndSend(queue, message);
		return "Published Succesfully";
	}

}
