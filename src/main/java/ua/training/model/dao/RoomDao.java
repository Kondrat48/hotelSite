package ua.training.model.dao;

import ua.training.model.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomDao extends GenericDao<Room> {
    List<Room> findAllFreeInRange(LocalDate from, LocalDate to);
}
