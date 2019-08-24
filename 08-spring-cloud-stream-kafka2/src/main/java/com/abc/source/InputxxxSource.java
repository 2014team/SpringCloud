package com.abc.source;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface InputxxxSource {
    String INPUT = "inputxxx";

    @Input(InputxxxSource.INPUT)
    MessageChannel output();
}
