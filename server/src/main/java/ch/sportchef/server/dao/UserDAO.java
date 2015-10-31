package ch.sportchef.server.dao;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import ch.sportchef.server.dao.mappers.UserMapper;
import ch.sportchef.server.representations.User;

public interface UserDAO {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO user (id, firstname, lastname, phone, email) VALUES (NULL, :firstname, :lastname, :phone, :email)")
    long createUser(@Bind("firstname") @Nonnull final String firstname,
                              @Bind("lastname") @Nonnull final String lastname,
                              @Bind("phone")  @Nonnull final String phone,
                              @Bind("email") @Nonnull final String email);

    @Mapper(UserMapper.class)
    @SqlQuery("SELECT * FROM user WHERE id = :id")
    @Nullable User readUserById(@Bind("id") final long id);

    @SqlUpdate("UPDATE user SET firstname=:firstname, lastname=:lastname, email=:email WHERE id=:id")
    void updateUser(@Bind("id") final long id,
                    @Bind("firstname") @Nonnull final String firstname,
                    @Bind("lastname") @Nonnull final String lastname,
                    @Bind("phone") @Nonnull final String phone,
                    @Bind("email") @Nonnull final String email);

    @SqlUpdate("DELETE FROM user WHERE id=:id")
    void deleteUser(@Bind("id") final long id);
}