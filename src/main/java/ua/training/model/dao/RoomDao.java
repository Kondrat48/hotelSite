package ua.training.model.dao;

import ua.training.model.entity.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface RoomDao extends GenericDao<Room> {
    List<Room> findAll(int currentPage, int recordsPerPage, String sortColumn, String searchParam, String searchField, Locale locale);

    List<Room> findAllFreeInRange(LocalDate from, LocalDate to);
}
