package ua.training.model.dao;

import ua.training.model.entity.User;

public interface UserDao extends GenericDao<User> {
    boolean validateUser(String login, String password);
    boolean loginIsExist(String login);
    boolean emailIsExist(String email);
    User getByLogin(String login);
}
