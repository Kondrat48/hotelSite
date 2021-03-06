package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.User;
import ua.training.model.exception.UnsuccessfulSqlOperationException;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.createUserDao();
    }

    public boolean checkLoginPassword(String login, String password){
        return userDao.validateUser(login, password);
    }

    public User.Role getUserRole(String login){
        return userDao.getByLogin(login).getRole();
    }

    public User getUser(String login){
        return userDao.getByLogin(login);
    }

    public int getUserId(String login){
        return userDao.getByLogin(login).getId();
    }

    public boolean checkLogin(String login){
        return userDao.loginIsExist(login);
    }

    public boolean checkEmail(String email){
        return userDao.emailIsExist(email);
    }

    public void register(User user){
        userDao.create(user);
    }

    public void updateUser(User user, String old_username) {
        User oldUser = userDao.getByLogin(old_username);
        user.setId(oldUser.getId());
        if(user.getRole()==null)user.setRole(oldUser.getRole());
        userDao.update(user);
    }

    public List<User> getUserList(int currentPage, int recordsPerPage, String sortColumn, String searchParam, String searchField){
        return userDao.findAll(currentPage,recordsPerPage,sortColumn,searchParam,searchField);
    }
    public int getNumberOfUsers(String search_param, String search_field){
        return userDao.getNumberOfRows(search_param, search_field);
    }

    public void deleteUser(String username) throws NullPointerException, UnsuccessfulSqlOperationException{
        userDao.delete(username);
    }
}
