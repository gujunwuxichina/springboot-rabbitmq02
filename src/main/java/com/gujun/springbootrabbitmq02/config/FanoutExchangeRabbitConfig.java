package com.gujun.springbootrabbitmq02.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutExchangeRabbitConfig {

    @Bean
    public Queue queueA(){
        return new Queue("fanout.A");
    }

    @Bean
    public Queue queueB(){
        return new Queue("fanout.B");
    }

    @Bean
    public Queue queueC(){
        return new Queue("fanout.C");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("textFanoutExchange");
    }

    //扇形交换机,无需绑定键，即便绑定了键也被无视；

    @Bean
    public Binding bindingExchangeA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingExchangeB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingExchangeC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }


}
