package com.abc.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

// 生产者类
@Component
// 开启绑定器，将当前应用与指定的管道绑定
@EnableBinding(Source.class)
public class SomeProducer {
    @Autowired
    @Qualifier(Source.OUTPUT)   // 指定要绑定的输出管道名称
    private MessageChannel channel;

    public void sendMsg(String message) {
        channel.send(MessageBuilder.withPayload(message).build());
    }
}


