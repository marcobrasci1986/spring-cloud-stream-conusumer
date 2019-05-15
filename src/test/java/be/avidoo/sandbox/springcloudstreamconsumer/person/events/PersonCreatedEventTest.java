package be.avidoo.sandbox.springcloudstreamconsumer.person.events;

import be.avidoo.sandbox.springcloudstreamconsumer.PersonMother;
import be.avidoo.sandbox.springcloudstreamconsumer.config.ObjectMapperConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JsonTest
@Import(ObjectMapperConfiguration.class)
public class PersonCreatedEventTest {

    private static final PersonCreatedEvent PERSON_CREATED_EVENT = PersonMother.person();
    @Autowired
    protected JacksonTester<PersonCreatedEvent> jacksonTester;

    @Test
    public void shouldSerialize() throws Exception {
        final JsonContent<PersonCreatedEvent> actual = jacksonTester.write(PERSON_CREATED_EVENT);
        Assertions.assertThat(actual).isEqualToJson("person-created.json", JSONCompareMode.STRICT);
    }

    @Test
    public void shouldDeserialize() throws Exception {
        final PersonCreatedEvent actual = jacksonTester.read("person-created.json").getObject();
        Assertions.assertThat(actual).isEqualTo(PERSON_CREATED_EVENT);
    }
}
