package ua.training.model.dao.impl;

import ua.training.model.dao.ReservationDao;
import ua.training.model.entity.Reservation;
import ua.training.model.entity.Room;
import ua.training.model.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCReservationDao implements ReservationDao {
    private final Connection connection;

    public JDBCReservationDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Reservation entity) {
        String query = "";

    }

    @Override
    public Reservation findById(int id) {
        String query = "";

        return null;
    }

    @Override
    public List<Reservation> findAll(int currentPage, int recordsPerPage, String sortColumn, String searchParam, String searchField) {
        return null;
    }

    @Override
    public int getNumberOfRows(String searchParam, String searchField) {
        return 0;
    }

    @Override
    public void update(Reservation entity) {
        String query = "";

    }

    @Override
    public void delete(int id) {
        String query = "";

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reservation> findReservationsByUser(User user){
        String query = "";

        return null;
    }

    public List<Reservation> findReservationsByRoom (Room room){
        String query = "";

        return null;
    }
}
