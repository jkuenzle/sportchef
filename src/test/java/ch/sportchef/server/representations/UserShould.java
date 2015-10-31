package ch.sportchef.server.representations;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

public class UserShould {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializeToJSON() throws IOException {
        final User user = new User(1L, "John", "Doe", "+41 79 123 45 67", "john.doe@sportchef.ch");
        assertThat(MAPPER.writeValueAsString(user)).isEqualTo(fixture("fixtures/user.json"));
    }

    @Test
    public void deserializeFromJSON() throws IOException {
        final User user = MAPPER.readValue(fixture("fixtures/user.json"), User.class);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getFirstName()).isEqualTo("John");
        assertThat(user.getLastName()).isEqualTo("Doe");
        assertThat(user.getPhone()).isEqualTo("+41 79 123 45 67");
        assertThat(user.getEmail()).isEqualTo("john.doe@sportchef.ch");
    }

    @Test
    public void haveUsefulToStringImplementation() {
        final User user = new User(1L, "John", "Doe", "+41 79 123 45 67", "john.doe@sportchef.ch");
        final String toString = user.toString();
        assertThat(toString).contains("ch.sportchef.server.representations.User");
        assertThat(toString).contains("id=1");
        assertThat(toString).contains("firstName=John");
        assertThat(toString).contains("lastName=Doe");
        assertThat(toString).contains("phone=+41 79 123 45 67");
        assertThat(toString).contains("email=john.doe@sportchef.ch");
    }
}
