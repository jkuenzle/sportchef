package ch.sportchef.server;

import ch.sportchef.server.configuration.SportChefConfiguration;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SportChefConfigurationShould {

    @Test
    public void beInstancable() {
        assertThat(new SportChefConfiguration()).isNotNull();
    }

    @Test
    public void beValidForTesting() throws Exception {
        App.main(new String[]{"check", "config-test.yaml"});
    }

    @Test
    public void beValidForProduction() throws Exception {
        App.main(new String[]{"check", "config.yaml"});
    }
}
