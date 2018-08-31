package ua.training.model.dao.impl;

import ua.training.model.dao.RoomDao;
import ua.training.model.dao.mapper.RoomClassMapper;
import ua.training.model.dao.mapper.RoomMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Room;
import ua.training.model.entity.User;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class JDBCRoomDao implements RoomDao {

    private final Connection connection;

    public JDBCRoomDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Room entity) {
        String query = "";

    }

    @Override
    public Room findById(int id) {
        String query = "";

        return null;
    }

    @Override
    public List<Room> findAll(int currentPage, int recordsPerPage, String sortColumn) {
        return null;
    }

    @Override
    public int getNumberOfRows() {
        return 0;
    }

    @Override
    public void update(Room entity) {
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

    public List<Room> findAllFreeInRange(LocalDate from, LocalDate to) {
        String query = "select * from r " +
                "left join room_has_user rhu on r.room_id = rhu.room_id" +
                "left join user u on u.user_id = rhu.user_id" +
                "left join room_class rc on rc.room_class_id = r.room_class_id" +
                "where not exists (" +
                "    select room_has_user_id from dates " +
                "    where room_has_user_id = rhu.room_has_user_id " +
                "    and ( /*dateFrom*/? between dates.date_from and dates.date_to" +
                "    or /*dateTo*/? between dates.date_from and dates.date_to)" +
                ")" +
                "or rhu.room_has_user_id is null";
        List<Room> resultList = new ArrayList<>();
        Map<Integer, Room> rooms = new HashMap<>();
        Map<Integer, User> users = new HashMap<>();
        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setDate(1,java.sql.Date.valueOf(from));
            st.setDate(2,java.sql.Date.valueOf(to));
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                UserMapper userMapper = new UserMapper();
                RoomMapper roomMapper = new RoomMapper(new Locale("uk_UA"));
                RoomClassMapper roomClassMapper = new RoomClassMapper();
                Room room = roomMapper.extractFromResultSet(rs);
                room.setRoomType(roomClassMapper.extractFromResultSet(rs));
                User user = userMapper.extractFromResultSet(rs);
                room = roomMapper.makeUnique(rooms, room);
                user = userMapper.makeUnique(users, user);
                room.getUsers().add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

}
