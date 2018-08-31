package ua.training.model.dao.mapper;

import ua.training.model.entity.RoomType;
import ua.training.model.service.resourceManager.DBColumnManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public class RoomClassMapper implements ObjectMapper<RoomType> {

    @Override
    public RoomType extractFromResultSet(ResultSet rs) throws SQLException {
        DBColumnManager manager = new DBColumnManager();
        return new RoomType.Builder()
                .buildId(rs.getInt(manager.getProperty("room_type.id")))
                .buildClassName(rs.getString(manager.getProperty("room_type.name")))
                .buildDailyPrice(rs.getDouble(manager.getProperty("room_type.price_per_night")))
                .buildDescription(rs.getString(manager.getProperty("room_type.description")))
                .buildNumberOfPlaces(rs.getInt(manager.getProperty("room_type.places")))
                .buildImagePath(rs.getString(manager.getProperty("room_type.image_path")))
                .getRoomClass();
    }

    @Override
    public RoomType makeUnique(Map<Integer, RoomType> roomClasses, RoomType roomType) {
        roomClasses.putIfAbsent(roomType.getId(), roomType);
        return roomClasses.get(roomType.getId());
    }
}
