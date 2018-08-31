package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {
    private Room(){}

    private int id;
    private RoomType roomType;
    private List<Confirmation> confirmations = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return getId() == room.getId() &&
                Objects.equals(getRoomType(), room.getRoomType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRoomType());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Confirmation> getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(List<Confirmation> confirmations) {
        this.confirmations = confirmations;
    }

    @Override
    public String toString() {
        return super.toString();//TODO override
    }

    public static class Builder {
        private int id;

        public Builder buildId(int id){
            this.id=id;
            return this;
        }
        public Room getRoom() {

            Room room = new Room();
            room.setId(id);
            return room;
        }
    }
}
