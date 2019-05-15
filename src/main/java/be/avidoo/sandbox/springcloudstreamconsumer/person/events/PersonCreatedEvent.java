package be.avidoo.sandbox.springcloudstreamconsumer.person.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class PersonCreatedEvent implements DomainEvent {
    private UUID id;
    private String name;
    private Instant when;

    @JsonCreator
    public PersonCreatedEvent(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("when") Instant when

    ) {
        this.id = id;
        this.name = name;
        this.when = when;
    }


    @Override
    public Instant occuredAt() {
        return when;
    }
}
