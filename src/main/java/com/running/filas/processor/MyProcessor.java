package com.running.filas.processor;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyProcessor {

    String INPUT = "myInput";

    @Input
    SubscribableChannel myInput();

    // to set the name of the output channel
    @Output("myOutput")
    MessageChannel anOutput();

    @Output
    MessageChannel anotherOutput();

}
