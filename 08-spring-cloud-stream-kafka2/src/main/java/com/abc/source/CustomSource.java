package com.abc.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomSource {
    String CHANNEL_NAME = "xxx";

    @Output(CustomSource.CHANNEL_NAME)
    MessageChannel output();
}
