package ua.training.model.dao.impl;

import ua.training.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();


    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ReservationDao createReservationDao() {
        return new JDBCReservationDao(getConnection());
    }

    @Override
    public RoomDao createRoomDao() {
        return new JDBCRoomDao(getConnection());
    }

    @Override
    public RoomTypeDao createRoomTypeDao() {
        return new JDBCRoomTypeDao(getConnection());
    }
    
    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }
}
