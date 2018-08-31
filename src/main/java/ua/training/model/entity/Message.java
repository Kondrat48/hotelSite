package ua.training.model.entity;

import java.util.Objects;

public class Message {
    private int id;
    private User sender, recipient;
    private Reservation reservation;
    private String text;
    private boolean read;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message = (Message) o;
        return id == message.id &&
                read == message.read &&
                Objects.equals(sender, message.sender) &&
                Objects.equals(recipient, message.recipient) &&
                Objects.equals(reservation, message.reservation) &&
                Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, recipient, reservation, text, read);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public static class Builder {
        private int id;
        private String text;
        private boolean read;

        public Builder buildId(int id) {
            this.id = id;
            return this;
        }

        public Builder buildText(String text) {
            this.text = text;
            return this;
        }

        public Builder buildReaded(boolean read) {
            this.read = read;
            return this;
        }

        public Message getMessage() {
            Message message = new Message();
            message.setId(id);
            message.setRead(read);
            message.setText(text);
            return message;
        }

    }
}
