package be.avidoo.sandbox.springcloudstreamconsumer.person.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Builder
@ToString
@Getter
public class PersonDeactivatedEvent implements DomainEvent {
    private UUID id;
    private Instant when;


    @JsonCreator
    public PersonDeactivatedEvent(
            @JsonProperty("id") UUID id,
            @JsonProperty("when") Instant when
    ) {
        this.id = id;
        this.when = when;
    }

    @Override
    public Instant occuredAt() {
        return when;
    }


}
