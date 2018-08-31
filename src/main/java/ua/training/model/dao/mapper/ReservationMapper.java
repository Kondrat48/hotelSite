package ua.training.model.dao.mapper;

import ua.training.model.entity.Reservation;
import ua.training.model.service.resourceManager.DBColumnManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

public class ReservationMapper implements ObjectMapper<Reservation> {
    private Locale locale;

    @Override
    public Reservation extractFromResultSet(ResultSet rs) throws SQLException {
        DBColumnManager manager = new DBColumnManager();
        return new Reservation.Builder()
                .buildId(rs.getInt(manager.getProperty("reservation.id")))
                .buildEndDate(rs.getDate(manager.getProperty("reservation.checkout")).toLocalDate())
                .buildStartDate(rs.getDate(manager.getProperty("reservation.arrival")).toLocalDate())
                .buildStatus(Reservation.Status.valueOf(rs.getString(manager.getProperty("reservation.status"))))
                .getReservation();
    }

    @Override
    public Reservation makeUnique(Map<Integer, Reservation> reservations, Reservation reservation) {
        reservations.putIfAbsent(reservation.getId(), reservation);
        return reservations.get(reservation.getId());
    }
}
