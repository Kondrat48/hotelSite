package ua.training.model.service;

import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.RoomTypeDao;
import ua.training.model.entity.RoomType;

import java.util.List;

public class RoomTypeService {

    RoomTypeDao roomTypeDao;

    public RoomTypeService() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        roomTypeDao = daoFactory.createRoomTypeDao();
    }

    public List<RoomType> getAllTypes() {
        return null;
    }
}
