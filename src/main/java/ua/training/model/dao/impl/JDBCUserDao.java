package ua.training.model.dao.impl;

import ua.training.model.dao.UserDao;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.User;
import ua.training.model.exception.UnsuccessfulSqlOperationException;
import ua.training.model.exception.WrongDataException;
import ua.training.model.service.resourceManager.DBQueryManager;

import java.sql.*;
import java.util.*;

public class JDBCUserDao implements UserDao {
    private final Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        DBQueryManager manager = new DBQueryManager();
        try (PreparedStatement st = connection.prepareStatement(manager.getProperty("sql.insert.user"))) {
            st.setString(1, entity.getUsername());
            st.setString(2, entity.getPassword());
            st.setString(3, entity.getName());
            st.setString(4, entity.getSurname());
            st.setString(5, entity.getPhoneNumber());
            st.setString(6, entity.getEmail());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new WrongDataException();
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll(int currentPage, int recordsPerPage, String sortColumn, String searchParam, String searchField) {
        DBQueryManager manager = new DBQueryManager();
        UserMapper mapper = new UserMapper();
        List<User> users = new ArrayList<>();

        int start = currentPage * recordsPerPage - recordsPerPage;

        try (PreparedStatement st = connection.prepareStatement(

                searchField == null || searchField.equals("")?
                        manager.getProperty("sql.select.users.with_pagination_and_sort.first_part") + " user.id " + manager.getProperty("sql.select.with_pagination_and_sort.second_part") :
                        manager.getProperty("sql.select.users.with_pagination_and_sort.first_part") + " " + searchField + " " + manager.getProperty("sql.select.with_pagination_and_sort.second_part")
        )) {
            st.setString(
                    1,
                    searchParam == null || searchField == null || searchParam.equals("") || searchField.equals("") ?
                            "%" : searchParam.equals("id") || searchParam.equals("user.id") ?
                            searchParam : '%' + searchParam + '%'
            );
            st.setString(2, sortColumn);
            st.setInt(3, start);
            st.setInt(4, recordsPerPage);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                users.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int getNumberOfRows(String searchParam, String searchField) {
        DBQueryManager manager = new DBQueryManager();
        try (PreparedStatement st = connection.prepareStatement(
                searchField == null || searchParam == null || searchField.equals("") || searchParam.equals("") ?
                        manager.getProperty("sql.count.users") : searchField.equals("id") || searchField.equals("id")?
                        manager.getProperty("sql.count.users") + " where " + searchField + " like '" + searchParam + "'":
                        manager.getProperty("sql.count.users") + " where " + searchField + " like '%" + searchParam + "%'"
        )) {
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public void update(User entity) throws UnsupportedOperationException {
        DBQueryManager manager = new DBQueryManager();
        try (PreparedStatement st = connection.prepareStatement(manager.getProperty("sql.update.user.by_id"))) {
            st.setString(1, entity.getUsername());
            st.setString(2, entity.getPassword());
            st.setString(3, entity.getName());
            st.setString(4, entity.getSurname());
            st.setString(5, entity.getPhoneNumber());
            st.setString(6, entity.getEmail());
            st.setString(7, entity.getRole().name());
            st.setLong(8, entity.getMoney());
            st.setInt(9, entity.getId());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void delete(String username) throws UnsuccessfulSqlOperationException {
        DBQueryManager manager = new DBQueryManager();
        try (PreparedStatement st = connection.prepareStatement(manager.getProperty("sql.delete.user.by_username"))) {
            st.setString(1, username);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnsuccessfulSqlOperationException();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean validateUser(String login, String password) {
        DBQueryManager manager = new DBQueryManager();
        try (PreparedStatement st = connection.prepareStatement(manager.getProperty("sql.select.users.by_username_and_password"))) {
            st.setString(1, login);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public User getByLogin(String login) {
        DBQueryManager manager = new DBQueryManager();
        try (PreparedStatement st = connection.prepareStatement(manager.getProperty("sql.select.users.by_username"))) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return new UserMapper().extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return new User.Builder().buildRole(User.Role.GUEST).getUser();
    }

    @Override
    public boolean loginIsExist(String login) {
        return paramIsExist(login, "sql.count.users.by_username");
    }

    @Override
    public boolean emailIsExist(String email) {
        return paramIsExist(email, "sql.count.users.by_email");
    }

    private boolean paramIsExist(String param, String paramSqlProperty) {
        DBQueryManager manager = new DBQueryManager();
        try (PreparedStatement st = connection.prepareStatement(manager.getProperty(paramSqlProperty))) {
            st.setString(1, param);
            ResultSet rs = st.executeQuery();
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("total");
            }
            return count > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
