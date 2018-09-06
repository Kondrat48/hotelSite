package ua.training.model.dao;

import ua.training.model.entity.RoomType;

import java.util.List;
import java.util.Locale;

public interface RoomTypeDao extends GenericDao<RoomType> {

    List<RoomType> findAll(Locale locale);
}
