package com.gujun.springbootrabbitmq02.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//相比DirectReceiver的写法，该写法也行
@Component
public class TopicReceiver {

    @RabbitListener(queues = {"topic.man","topic.woman"})
    public void process(JSONObject jsonObject){
        System.out.println("topic收到消息:"+jsonObject.toString());
    }

}
