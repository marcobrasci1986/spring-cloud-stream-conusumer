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
public class PersonNameChangedEvent implements DomainEvent {
    private UUID id;
    private String firstname;
    private Instant when;

    @JsonCreator
    public PersonNameChangedEvent(
            @JsonProperty("id") UUID id,
            @JsonProperty("firstname") String firstname,
            @JsonProperty("when") Instant when
    ) {
        this.id = id;
        this.firstname = firstname;
    }

    @Override
    public Instant occuredAt() {
        return when;
    }
}
