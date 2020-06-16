package com.zb.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayRabbitConfig {
    //死信的信息
    public static final String ORDER_DELAY_QUEUE = "user.order.delay.queue";
    public static final String ORDER_DELAY_EXCHANGE = "user.order.delay.exchange";
    public static final String ORDER_DELAY_ROUTING_KEY = "order_delay";

    //非死信的信息
    public static final String ORDER_QUEUE_NAME = "user.order.queue";
    public static final String ORDER_EXCHANGE_NAME = "user.order.exchange";
    public static final String ORDER_ROUTING_KEY = "order";


    //死信的信息
    public static final String ORDER_DELAY_QUEUE1 = "user.order.delay.queue1";
    public static final String ORDER_DELAY_EXCHANGE1 = "user.order.delay.exchange1";
    public static final String ORDER_DELAY_ROUTING_KEY1 = "order_delay1";

    //非死信的信息
    public static final String ORDER_QUEUE_NAME1 = "user.order.queue1";
    public static final String ORDER_EXCHANGE_NAME1 = "user.order.exchange1";
    public static final String ORDER_ROUTING_KEY1 = "order1";


    //在普通队列上添加死信配置
    @Bean
    public Queue createDirectQueue() {
        Map<String, Object> param = new HashMap<String, Object>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        param.put("x-dead-letter-exchange", ORDER_DELAY_EXCHANGE);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        param.put("x-dead-letter-routing-key", ORDER_DELAY_ROUTING_KEY);
        return new Queue(ORDER_QUEUE_NAME, true, false, false, param);
    }

    @Bean
    public DirectExchange createDirectExchange() {
        return new DirectExchange(ORDER_EXCHANGE_NAME);
    }

    @Bean
    public Binding dlxBind() {
        return BindingBuilder.bind(createDirectQueue()).to(createDirectExchange()).with(ORDER_ROUTING_KEY);
    }

    @Bean
    public Queue createQueue() {
        return new Queue(ORDER_DELAY_QUEUE, true, false, false, null);
    }

    @Bean
    public TopicExchange createTopicExchange() {
        return new TopicExchange(ORDER_DELAY_EXCHANGE);
    }

    @Bean
    public Binding orderBind() {
        return BindingBuilder.bind(createQueue()).to(createTopicExchange()).with(ORDER_DELAY_ROUTING_KEY);
    }


    //在普通队列上添加死信配置
    @Bean
    public Queue createDirectQueue1() {
        Map<String, Object> param = new HashMap<String, Object>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        param.put("x-dead-letter-exchange", ORDER_DELAY_EXCHANGE1);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        param.put("x-dead-letter-routing-key", ORDER_DELAY_ROUTING_KEY1);
        return new Queue(ORDER_QUEUE_NAME1, true, false, false, param);
    }

    @Bean
    public DirectExchange createDirectExchange1() {
        return new DirectExchange(ORDER_EXCHANGE_NAME1);
    }

    @Bean
    public Binding dlxBind1() {
        return BindingBuilder.bind(createDirectQueue1()).to(createDirectExchange1()).with(ORDER_ROUTING_KEY1);
    }

    @Bean
    public Queue createQueue1() {
        return new Queue(ORDER_DELAY_QUEUE1, true, false, false, null);
    }

    @Bean
    public TopicExchange createTopicExchange1() {
        return new TopicExchange(ORDER_DELAY_EXCHANGE1);
    }

    @Bean
    public Binding orderBind1() {
        return BindingBuilder.bind(createQueue1()).to(createTopicExchange1()).with(ORDER_DELAY_ROUTING_KEY1);
    }


}
