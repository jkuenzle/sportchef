package ch.sportchef.server.services;

import java.util.List;
import java.util.Optional;

import ch.sportchef.server.dao.UserDAO;
import ch.sportchef.server.representations.User;

public class UserService implements Service {

    private final UserDAO userDAO;

    public UserService(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> readUserById(final long userId) {
        return Optional.ofNullable(userDAO.readById(userId));
    }
    
    public List<User> readAllUsers() {
        return userDAO.readAll();
    }

    public User storeUser(final User user) {
        if (user.getId() == 0) {
            final long newUserId = userDAO.create(user);
            return new User(newUserId, user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail());
        }

        userDAO.update(user);
        return user;
    }

    public void removeUser(final User user) {
        userDAO.delete(user);
    }
}
