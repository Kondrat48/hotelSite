package ua.training.model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Confirmation {
    private int id;
    private LocalDate arrival,
            checkout;
    private Reservation reservation;
    private User user;
    private Room room;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Confirmation)) return false;
        Confirmation that = (Confirmation) o;
        return id == that.id &&
                Objects.equals(arrival, that.arrival) &&
                Objects.equals(checkout, that.checkout) &&
                Objects.equals(reservation, that.reservation) &&
                Objects.equals(user, that.user) &&
                Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, arrival, checkout, reservation, user, room);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
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

    public static class Builder{

        private int id;
        private LocalDate arrival,
                checkout;

        public Builder buildId(int id){
            this.id = id;
            return this;
        }

        public Builder buildArrival(LocalDate arrival){
            this.arrival = arrival;
            return this;
        }

        public Builder buildCheckout(LocalDate checkout){
            this.checkout = checkout;
            return this;
        }

        public Confirmation getConfirmation(){
            Confirmation confirmation = new Confirmation();
            confirmation.setId(id);
            confirmation.setArrival(arrival);
            confirmation.setCheckout(checkout);
            return confirmation;
        }
    }
}
