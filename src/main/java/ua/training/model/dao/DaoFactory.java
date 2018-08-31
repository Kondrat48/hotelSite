package ua.training.model.dao;


import ua.training.model.dao.impl.JDBCDaoFactory;
import ua.training.model.entity.Reservation;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract ReservationDao createReservationDao();
    public abstract RoomDao createRoomDao();
    public abstract UserDao createUserDao();

    public static DaoFactory getInstance(){
        if( daoFactory == null ){
            synchronized (DaoFactory.class){
                if(daoFactory==null){
                    DaoFactory temp = new JDBCDaoFactory();
                    daoFactory = temp;
                }
            }
        }
        return daoFactory;
    }
}
