package ua.training.model.dao.impl;

import ua.training.model.dao.RoomDao;
import ua.training.model.dao.mapper.RoomTypeMapper;
import ua.training.model.dao.mapper.RoomMapper;
import ua.training.model.dao.mapper.UserMapper;
import ua.training.model.entity.Room;
import ua.training.model.entity.User;
import ua.training.model.service.resourceManager.DBQueryManager;

import java.sql.*;
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
    public List<Room> findAll(int currentPage, int recordsPerPage, String sortColumn, String searchParam, String searchField) {
        return findAll(currentPage,recordsPerPage,sortColumn,searchParam,searchField,new Locale("en_US"));
    }

    @Override
    public List<Room> findAll(int currentPage, int recordsPerPage, String sortColumn, String searchParam, String searchField, Locale locale) {
        DBQueryManager queryManager = new DBQueryManager();
        RoomMapper roomMapper = new RoomMapper();
        RoomTypeMapper roomTypeMapper = new RoomTypeMapper(locale);
        List<Room> rooms = new ArrayList<>();

        int start = currentPage * recordsPerPage - recordsPerPage;

        try(PreparedStatement st = connection.prepareStatement(
                searchField == null || searchField.equals("")?
                        queryManager.getProperty("sql.select.rooms.with_pagination_and_sort.first_part") + " room.id " + queryManager.getProperty("sql.select.with_pagination_and_sort.second_part") :
                        queryManager.getProperty("sql.select.rooms.with_pagination_and_sort.first_part") + " " + searchField + " " + queryManager.getProperty("sql.select.with_pagination_and_sort.second_part")
        )) {
            st.setString(
                    1,
                    searchParam == null || searchField == null || searchParam.equals("") || searchField.equals("") ?
                            "%" : searchParam.equals("id") || searchParam.equals("room.id") ?
                            searchParam : '%' + searchParam + '%'
            );
            st.setString(2, sortColumn);
            st.setInt(3, start);
            st.setInt(4, recordsPerPage);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                Room room = roomMapper.extractFromResultSet(rs);
                room.setRoomType(roomTypeMapper.extractFromResultSet(rs));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    @Override
    public int getNumberOfRows(String searchParam, String searchField) {
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
//        String query = "select * from r " +
//                "left join room_has_user rhu on r.room_id = rhu.room_id" +
//                "left join user u on u.user_id = rhu.user_id" +
//                "left join room_class rc on rc.room_class_id = r.room_class_id" +
//                "where not exists (" +
//                "    select room_has_user_id from dates " +
//                "    where room_has_user_id = rhu.room_has_user_id " +
//                "    and ( /*dateFrom*/? between dates.date_from and dates.date_to" +
//                "    or /*dateTo*/? between dates.date_from and dates.date_to)" +
//                ")" +
//                "or rhu.room_has_user_id is null";
//        List<Room> resultList = new ArrayList<>();
//        Map<Integer, Room> rooms = new HashMap<>();
//        Map<Integer, User> users = new HashMap<>();
//        try (PreparedStatement st = connection.prepareStatement(query)) {
//            st.setDate(1,java.sql.Date.valueOf(from));
//            st.setDate(2,java.sql.Date.valueOf(to));
//            ResultSet rs = st.executeQuery();
//            while (rs.next()){
//                UserMapper userMapper = new UserMapper();
//                RoomMapper roomMapper = new RoomMapper(new Locale("uk_UA"));
//                RoomTypeMapper roomTypeMapper = new RoomTypeMapper(new Locale("uk_UA"));
//                Room room = roomMapper.extractFromResultSet(rs);
//                room.setRoomType(roomTypeMapper.extractFromResultSet(rs));
//                User user = userMapper.extractFromResultSet(rs);
//                room = roomMapper.makeUnique(rooms, room);
//                user = userMapper.makeUnique(users, user);
//                room.getUsers().add(user);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return /*resultList*/null;
    }

}
