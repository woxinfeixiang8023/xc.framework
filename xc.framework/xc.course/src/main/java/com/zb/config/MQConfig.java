package com.zb.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    public static final String xcExchange = "xcOrder-exchange";
    public static final String xcQueue = "xcOrder-queue";

    @Bean(xcExchange)
    public Exchange createExchange() {
        return ExchangeBuilder.topicExchange(xcExchange).durable(true).build();
    }

    @Bean(xcQueue)
    public Queue createQueue() {
        Queue queue = new Queue(xcQueue);
        return queue;
    }

    @Bean
    public Binding bindingOrder(@Qualifier(xcExchange) Exchange exchange, @Qualifier(xcQueue) Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("inform.#.order.#").noargs();
    }
}
