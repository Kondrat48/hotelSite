package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.RoomTypeDao;
import ua.training.model.entity.RoomType;

import java.util.List;
import java.util.Locale;

public class RoomTypeService {

    RoomTypeDao roomTypeDao;
    Locale locale;

    public RoomTypeService(String locale) {
        this.locale = new Locale(locale);
        DaoFactory daoFactory = DaoFactory.getInstance();
        roomTypeDao = daoFactory.createRoomTypeDao();
    }

    public List<RoomType> getAllTypes() {
        return roomTypeDao.findAll(locale);
    }

    public boolean isExistId(int roomTypeId) {
        return roomTypeDao.isExistId(roomTypeId);
    }
}
