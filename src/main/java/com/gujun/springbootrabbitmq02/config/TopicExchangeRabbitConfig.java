package com.gujun.springbootrabbitmq02.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicExchangeRabbitConfig {

    public final static String MAN="topic.man";

    public final static String WOMAN="topic.woman";

    @Bean
    public Queue manQueue(){
        return new Queue(TopicExchangeRabbitConfig.MAN,true);
    }

    @Bean
    public Queue womanQueue(){
        return new Queue(TopicExchangeRabbitConfig.WOMAN,true);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("testTopicExchange");
    }

    /*
        将manQueue和topicExchange绑定，且绑定键位topic.man，
        这样只要消息携带该路由键才会分发到该队列；
     */
    @Bean
    public Binding bindingTopic1(){
        return BindingBuilder.bind(manQueue()).to(topicExchange()).with(TopicExchangeRabbitConfig.MAN);
    }

    /*
        将womanQueue和topicExchange绑定，且绑定键位topic.# 用上了路由通配键，
        这样只要消息携带的路由键是topic.开头就分发到该队列；
     */
    @Bean
    public Binding bindingTopic2(){
        return BindingBuilder.bind(womanQueue()).to(topicExchange()).with("topic.#");
    }

}
