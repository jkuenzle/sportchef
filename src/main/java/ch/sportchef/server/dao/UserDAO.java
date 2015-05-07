package ch.sportchef.server.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import ch.sportchef.server.dao.mapper.UserMapper;
import ch.sportchef.server.representations.User;

@RegisterMapper(UserMapper.class)
public interface UserDAO {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO user (id, firstName, lastName, phone, email) VALUES (NULL, :firstName, :lastName, :phone, :email)")
    long create(@BindBean User user);

    @SqlQuery("SELECT * FROM user WHERE id = :id")
    User readById(@Bind("id") final long id);
    
    @SqlQuery("SELECT * FROM user")
    List<User> readAll();

    @SqlUpdate("UPDATE user SET firstName = :firstName, lastName = :lastName, phone = :phone, email = :email WHERE id = :id")
    void update(@BindBean User user);

    @SqlUpdate("DELETE FROM user WHERE id = :id")
    void delete(@BindBean User user);
}
