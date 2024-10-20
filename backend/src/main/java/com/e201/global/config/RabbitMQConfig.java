package com.e201.global.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String EXCHANGE = "payment.exchange";
	public static final String SALES_QUEUE = "sales.created.queue";
	public static final String MONTHLY_SUM_QUEUE = "payment.monthly.sum.queue";
	public static final String ROUTING_SALES = "SALES_CREATED";
	public static final String ROUTING_MONTHLY_SUM = "PAYMENT_MONTHLY_SUM_UPDATE";

	@Bean
	public TopicExchange paymentExchange() {
		return new TopicExchange(EXCHANGE);
	}

	@Bean
	public Queue salesQueue() {
		return new Queue(SALES_QUEUE, true);
	}

	@Bean
	public Queue paymentMonthlySumQueue() {
		return new Queue(MONTHLY_SUM_QUEUE, true);
	}

	@Bean
	public Binding salesBinding(Queue salesQueue, TopicExchange paymentExchange) {
		return BindingBuilder.bind(salesQueue).to(paymentExchange).with(ROUTING_SALES);
	}

	@Bean
	public Binding monthlySumBinding(Queue paymentMonthlySumQueue, TopicExchange paymentExchange) {
		return BindingBuilder.bind(paymentMonthlySumQueue).to(paymentExchange).with(ROUTING_MONTHLY_SUM);
	}

	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate template = new RabbitTemplate(connectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}
}
