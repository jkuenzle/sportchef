package ch.sportchef.server.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Nonnull;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import ch.sportchef.server.representations.User;

public class UserMapper implements ResultSetMapper<User> {

    @Override
    public @Nonnull User map(final int index, @Nonnull final ResultSet r, @Nonnull final StatementContext ctx)
            throws SQLException {
        return new User(
                r.getLong("id"),
                r.getString("firstname"),
                r.getString("lastname"),
                r.getString("phone"),
                r.getString("email")
        );
    }
}