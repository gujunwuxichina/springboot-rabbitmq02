package com.gujun.springbootrabbitmq02;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@SpringBootApplication
public class SpringbootRabbitmq02Application extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootRabbitmq02Application.class, args);
    }

    //配置fastjson
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4、将convert添加到converters中
        converters.add(fastConverter);
        //5、追加默认转换器
        super.addDefaultHttpMessageConverters(converters);
    }

    /*
        消费者消息确认机制：
        1.自动确认：也是默认的消息确认情况，RabbitMQ成功将消息发出(即消息成功写入TCP Socket中)，立即认为本次投递，
        不管消费者端是否成功处理本次投递；该情况，如果消费端消费逻辑抛出异常，即消费端没有成功处理该条消息，就相当于丢失消息；
        可以使用try...catch...捕获异常后，再做后续处理；
        2.手动确认：多数选择的模式；
        消费者收到消息后，手动调用basic.ack/basic.nack/basic.reject后，RabbitMQ收到这些消息后，才认为本次投递成功；
        basic.ack用于肯定确认
        basic.nack用于否定确认
        basic.reject用于否定确认，但与basic.nack相比有一个限制:一次只能拒绝单条消息
        消费者端以上的3个方法都表示消息已经被正确投递，但是basic.ack表示消息已经被正确处理，
        但是basic.nack,basic.reject表示没有被正确处理，但是RabbitMQ中仍然需要删除这条消息；
     */

}
