package com.abc.controller;

import com.abc.producer.SomeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeController {
    // 将生产者注入
    @Autowired
    private SomeProducer producer;

    @PostMapping("/msg/send")
    public String sendMsg(@RequestParam("message") String message) {
        // 生产者发送消息
        producer.sendMsg(message);
        return "send success";
    }
}
