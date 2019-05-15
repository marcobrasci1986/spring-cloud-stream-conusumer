package be.avidoo.sandbox.springcloudstreamconsumer;

import be.avidoo.sandbox.springcloudstreamconsumer.person.events.PersonCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

@Slf4j
@EnableBinding(Sink.class)
public class EventConsumer {

    @StreamListener(Sink.INPUT)
    public void consumeMessage(PersonCreatedEvent personCreatedEvent) {
        log.info("Received : " + personCreatedEvent);

        // TODO store in EventStore
        // Add EventHandler to update Database
    }

    /**
     * All errors are routed to this channel
     *
     * @param message
     */
    @StreamListener("errorChannel")
    public void error(Message<?> message) {
        System.out.println("Handling ERROR: " + message);
    }
}
