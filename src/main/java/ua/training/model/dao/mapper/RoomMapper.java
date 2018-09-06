package ua.training.model.dao.mapper;

import ua.training.model.entity.Room;
import ua.training.model.service.resourceManager.DBColumnManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public class RoomMapper implements ObjectMapper<Room> {
    @Override
    public Room extractFromResultSet(ResultSet rs) throws SQLException {
        DBColumnManager manager = new DBColumnManager();
        return new Room.Builder()
                .buildId(rs.getInt(manager.getProperty("room.id")))
                .getRoom();
    }

    @Override
    public Room makeUnique(Map<Integer, Room> rooms, Room room) {
        rooms.putIfAbsent(room.getId(), room);
        return rooms.get(room.getId());
    }
}
