package com.sweety.messaging.config;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class JMSConfiguration {

	@Value("${activemq.broker-url}")
	private String brokerUrl;

	@Value("${activemq.pub-sub-domain}")
	private boolean pub_sub_domain;

	@Value("${activemq.concurrency}")
	private String concurrency;
	
	@Value("spring.active.queue")
	private String queue;

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("queue");
	}

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(brokerUrl);
		return factory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(activeMQConnectionFactory());
		jmsTemplate.setPubSubDomain(true);
		return new JmsTemplate(activeMQConnectionFactory());
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerConnectionListenerFactory() {
		DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
		defaultJmsListenerContainerFactory.setConnectionFactory(activeMQConnectionFactory());
		defaultJmsListenerContainerFactory.setConcurrency("2");
		defaultJmsListenerContainerFactory.setPubSubDomain(true);
		return defaultJmsListenerContainerFactory;
	}
}
