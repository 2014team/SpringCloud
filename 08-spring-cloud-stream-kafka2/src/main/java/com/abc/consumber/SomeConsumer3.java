package com.abc.consumber;

import com.abc.source.InputxxxSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@EnableBinding({ InputxxxSource.class,Sink.class})
public class SomeConsumer3 {

    @StreamListener(Sink.INPUT)
    public void pringMSG(Object message) {
        System.out.println(message);
    }


    @StreamListener(InputxxxSource.INPUT)
    public void pringMSGInputxxx(Object message) {
        System.out.println("-----------"+message);
    }
}
