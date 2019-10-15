package com.gujun.springbootrabbitmq02.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FanoutReceiver {

    @RabbitListener(queues = {"fanout.A","fanout.B","fanout.C"})
    public void process(JSONObject jsonObject) {
        System.out.println("fanout收到信息:" + jsonObject.toString());
    }

}
