package be.avidoo.sandbox.springcloudstreamconsumer.person.events;

import java.time.Instant;

public interface DomainEvent {
    Instant occuredAt();
}
