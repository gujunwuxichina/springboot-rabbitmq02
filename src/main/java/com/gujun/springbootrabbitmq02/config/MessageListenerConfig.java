package com.gujun.springbootrabbitmq02.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private DirectReceiver directReceiver;

    @Autowired
    private DirectExchangeRabbitConfig directExchangeRabbitConfig;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container=new SimpleMessageListenerContainer(cachingConnectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);   //RabbitMQ默认是自动即NONE，此处改为手动
        /*
            如果需要有些消息接收类设置自动确认，有些消息接收类设置手动确认的话，
            那只需要将需要设置手动确认的相关队列加到之前的MessageListenerConfig的SimpleMessageListenerContainer里面就行
         */
        container.setQueues(directExchangeRabbitConfig.testDirectQueue());
        container.setMessageListener(directReceiver);
        return container;
    }

}
