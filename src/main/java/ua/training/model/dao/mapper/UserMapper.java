package ua.training.model.dao.mapper;

import ua.training.model.entity.User;
import ua.training.model.service.resourceManager.DBColumnManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {

    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        DBColumnManager manager = new DBColumnManager();
        return new User.Builder().buildId(rs.getInt(manager.getProperty("user.id")))
                .buildName(rs.getString(manager.getProperty("user.name")))
                .buildUsername(rs.getString(manager.getProperty("user.username")))
                .buildPassword(rs.getString(manager.getProperty("user.password")))
                .buildSurname(rs.getString(manager.getProperty("user.surname")))
                .buildPhone(rs.getString(manager.getProperty("user.phone_number")))
                .buildEmail(rs.getString(manager.getProperty("user.email")))
                .buildRole(User.Role.valueOf(rs.getString(manager.getProperty("user.role"))))
                .buildMoney(rs.getLong(manager.getProperty("user.money")))
                .getUser();
    }

    @Override
    public User makeUnique(Map<Integer, User> users, User user) {
        users.putIfAbsent(user.getId(), user);
        return users.get(user.getId());
    }
}
