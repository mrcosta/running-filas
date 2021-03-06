package com.running.filas;

import com.running.filas.model.LogMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyLoggerServiceApplication.class)
@DirtiesContext
public class MyLoggerServiceApplicationTest {

    @Autowired
    private Processor pipe;

    @Autowired
    private MessageCollector messageCollector;

    @Test
    public void whenSendMessage_thenResponseShouldUpdateText() {
        pipe.input()
                .send(MessageBuilder.withPayload(new LogMessage("This is my message"))
                        .build());

        Object payload = messageCollector.forChannel(pipe.output())
                .poll()
                .getPayload();

        assertEquals("[1]: This is my message", payload.toString());
    }

}