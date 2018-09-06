package ua.training.controller.dto;

import ua.training.model.entity.Room;

public class RoomDto {
    int id, roomTypeId;
    String typeName;

    public RoomDto(Room room) {
        id = room.getId();
        typeName = room.getRoomType().getTypeName();
        roomTypeId = room.getRoomType().getId();
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
