package be.avidoo.sandbox.springcloudstreamconsumer;

import be.avidoo.sandbox.springcloudstreamconsumer.person.events.PersonCreatedEvent;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.UUID;

public class PersonMother {

    public static PersonCreatedEvent person() {
        return PersonCreatedEvent.builder()
                .id(UUID.fromString("5013e15a-e3f1-493a-bbb5-236e723392ff"))
                .name("John")
                .when(LocalDateTime.of(2019, Month.APRIL, 22, 0, 0).toInstant(ZoneOffset.UTC))
                .build();
    }
}
