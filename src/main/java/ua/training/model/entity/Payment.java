package ua.training.model.entity;

import java.util.Objects;

public class Payment {
    private Reservation reservation;
    private User user;
    private String description;
    private Long money;
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return id == payment.id &&
                Objects.equals(reservation, payment.reservation) &&
                Objects.equals(user, payment.user) &&
                Objects.equals(description, payment.description) &&
                Objects.equals(money, payment.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservation, user, description, money, id);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static class Builder {
        private int id;
        private String description;
        private Long money;

        public Builder buildId(int id){
            this.id = id;
            return this;
        }
        public Builder buildDescription(String description){
            this.description = description;
            return this;
        }
        public Builder buildMoney(Long money){
            this.money = money;
            return this;
        }

        public Payment getPayment(){
            Payment payment = new Payment();
            payment.setId(id);
            payment.setMoney(money);
            payment.setDescription(description);
            return payment;
        }
    }
}
