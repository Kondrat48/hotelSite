package ua.training.model.entity;

import java.util.Objects;

public class RoomType {
    private RoomType(){}
    private int id,
            numberOfPlaces;
    private long dailyPrice;
    private String imagePath,
            typeName,
            description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomType)) return false;
        RoomType roomType = (RoomType) o;
        return getId() == roomType.getId() &&
                getNumberOfPlaces() == roomType.getNumberOfPlaces() &&
                Double.compare(roomType.getDailyPrice(), getDailyPrice()) == 0 &&
                Objects.equals(getImagePath(), roomType.getImagePath()) &&
                Objects.equals(getTypeName(), roomType.getTypeName()) &&
                Objects.equals(getDescription(), roomType.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumberOfPlaces(), getDailyPrice(), getImagePath(), getTypeName(), getDescription());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(long dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Builder {
        private int requiredElements;


        private int id,
                numberOfPlaces;
        private long dailyPrice;
        private String imagePath,
                typeName,
                description;


        public Builder buildId(int id) {
            this.id = id;
            return this;
        }

        public Builder buildDailyPrice(long dailyPrice) {
            this.dailyPrice = dailyPrice;
            return this;
        }

        public Builder buildNumberOfPlaces(int numberOfPlaces) {
            this.numberOfPlaces = numberOfPlaces;
            return this;
        }

        public Builder buildImagePath(String imagePath) {
            this.imagePath = imagePath;
            return this;
        }

        public Builder buildClassName(String className) {
            this.typeName = className;
            return this;
        }

        public Builder buildDescription(String description) {
            this.description = description;
            return this;
        }

        public RoomType getRoomClass() {
            RoomType roomType = new RoomType();
            roomType.setId(id);
            roomType.setDailyPrice(dailyPrice);
            roomType.setNumberOfPlaces(numberOfPlaces);
            roomType.setImagePath(imagePath);
            roomType.setTypeName(typeName);
            roomType.setDescription(description);
            return roomType;
        }
    }
}
