package ua.training.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ua.training.StringConstants.LINE_SEPARATOR;

public class User {
    private User() {
    }

    private int id;
    private long money;
    private String name,
            password,
            username,
            surname,
            phoneNumber,
            email;
    private Role role;
    private List<Reservation> reservations = new ArrayList<>();
    private List<Confirmation> confirmations = new ArrayList<>();
    private List<Room> rooms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                user.getMoney() == getMoney() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(getPhoneNumber(), user.getPhoneNumber()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMoney(), getName(), getPassword(), getUsername(), getSurname(), getPhoneNumber(), getEmail(), getRole());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Confirmation> getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(List<Confirmation> confirmations) {
        this.confirmations = confirmations;
    }

    @Override
    public String toString() {
        return "Username: \t" + username + LINE_SEPARATOR +
                "Name: \t" + name + LINE_SEPARATOR +
                "Surname: \t" + surname + LINE_SEPARATOR +
                "Phone: \t" + phoneNumber + LINE_SEPARATOR +
                "Email: \t" + email + LINE_SEPARATOR +
                "Money: \t" + money + LINE_SEPARATOR +
                "Role: \t" + role.name();
    }

    public enum Role {
        GUEST(0),
        BANNED(1),
        USER(2),
        MANAGER(3),
        ADMIN(4);

        private final int significance;

        Role(int significance) {
            this.significance = significance;
        }

        public int getSignificance() {
            return significance;
        }


        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public static class Builder {
        private int id;
        private long money;
        private String name,
                password,
                username,
                surname,
                phoneNumber,
                email;
        private Role role;

        int requiredElements;

        public Builder buildId(int id) {
            this.id = id;
            return this;
        }

        public Builder buildMoney(long money){
            this.money = money;
            return this;
        }

        public Builder buildName(String name) {
            this.name = name;
            return this;
        }

        public Builder buildPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder buildUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder buildSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder buildPhone(String phone) {
            this.phoneNumber = phone;
            return this;
        }

        public Builder buildEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder buildRole(Role role) {
            this.role = role;
            return this;
        }


        public User getUser() {
            User user = new User();
            user.setId(id);
            user.setMoney(money);
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setPhoneNumber(phoneNumber);
            user.setSurname(surname);
            user.setUsername(username);
            user.setRole(role);

            return user;
        }
    }
}

