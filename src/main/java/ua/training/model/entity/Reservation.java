package ua.training.model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private int id;
    private User user;
    private Room room;
    private LocalDate startDate,
            endDate;
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return getId() == that.getId() &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getRoom(), that.getRoom()) &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getRoom(), getStartDate(), getEndDate(), status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public enum Status {
        CONFIRMED, REJECTED, NOT_CONSIDERED, CANCELED
    }


    public static class Builder {

        private int requiredElements;
        private int id;
        private LocalDate startDate,
        endDate;

        private Status status;

        public Builder buildId(int id) {
            this.id = id;
            return this;
        }

        public Builder buildStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder buildEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder buildStatus(Status status) {
            this.status = status;
            return this;
        }

        public Reservation getReservation() {
            Reservation reservation = new Reservation();
            reservation.setId(id);
            reservation.setStartDate(startDate);
            reservation.setEndDate(endDate);
            reservation.setStatus(status);
            return reservation;
        }

    }
}
