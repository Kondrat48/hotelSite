package ua.training.model.dao;

import ua.training.model.entity.User;
import ua.training.model.exception.UnsuccessfulSqlOperationException;

public interface UserDao extends GenericDao<User> {
    boolean validateUser(String login, String password);
    boolean loginIsExist(String login);
    boolean emailIsExist(String email);
    User getByLogin(String login);
    void delete(String username) throws UnsuccessfulSqlOperationException;
}
