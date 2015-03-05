package ch.sportchef.server;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.sportchef.server.dao.UserDAO;
import ch.sportchef.server.resources.UserResource;
import ch.sportchef.server.services.Service;
import ch.sportchef.server.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

public class App extends Application<SportChefConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    private static final Map<Integer, Service> services = new HashMap<>();

    public static void main(@Nonnull final String[] args) throws Exception {
        LOGGER.info("Starting application with arguments: %s", new Object[]{args});
        new App().run(args);
    }

    public static <T extends Service> T getService(Class<T> serviceClass) {
        return serviceClass.cast(services.get(serviceClass.hashCode()));
    }

    @Override
    public void initialize(@Nonnull final Bootstrap<SportChefConfiguration> bootstrap) {
        // Configure Assets
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "html/index.html"));

        // Register additional Jackson modules
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JSR310Module());
    }

    @Override
    public void run(@Nonnull final SportChefConfiguration configuration, @Nonnull final Environment environment) throws Exception {
        environment.jersey().setUrlPattern("/api/*");

        final DBIFactory factory = new DBIFactory();
        final DBI dbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");

        services.put(UserService.class.hashCode(), new UserService( dbi.onDemand(UserDAO.class) )) ;

        environment.jersey().register(new UserResource());
    }
}
