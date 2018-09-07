package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.RoomDao;
import ua.training.model.entity.Room;
import ua.training.model.entity.RoomType;

import java.util.List;
import java.util.Locale;

public class RoomService {
    private RoomDao roomDao;
    private Locale locale;

    public RoomService() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        roomDao = daoFactory.createRoomDao();
    }

    public RoomService(String language) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        roomDao = daoFactory.createRoomDao();
        locale = new Locale(language);
    }

    public Room getRoomById(int room_id) {
        return roomDao.findById(room_id);
    }

    public int getNumberOfRooms(String search_param, String search_field) {
        return roomDao.getNumberOfRows(search_param, search_field);
    }

    public List<Room> getRoomList(int currentPage, int recordsPerPage, String sortColumn, String searchParam, String searchField) {
        return roomDao.findAll(currentPage,recordsPerPage,sortColumn,searchParam,searchField, locale);
    }

    public void createRoom(int roomId, int roomTypeId) {
        Room room = new Room.Builder().buildId(roomId).getRoom();
        room.setRoomType(new RoomType.Builder().buildId(roomTypeId).getRoomClass());
        roomDao.create(room);
    }

    public boolean isExistId(int roomId) {
        return roomDao.isExistId(roomId);
    }

    public void deleteRoomById(int roomId) {
        roomDao.delete(roomId);
    }
}
