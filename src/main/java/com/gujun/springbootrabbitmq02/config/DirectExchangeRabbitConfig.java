package com.gujun.springbootrabbitmq02.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//直连交换机
@Configuration
public class DirectExchangeRabbitConfig {

    @Value("${spring.rabbitmq.queue.testDirectQueue}")
    private String testDirectQueue;

    @Bean
    public Queue testDirectQueue(){
        return new Queue(testDirectQueue,true); //第二个boolean参数，表示是否持久化；
    }

    //direct交换机，并取名
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("testDirectExchange");
    }

    //绑定    将队列和交换机绑定，并设置匹配键  testDirectRouting
    @Bean
    public Binding bindingDirect(){
        return BindingBuilder.bind(testDirectQueue()).to(directExchange()).with("testDirectRouting");
    }

}
