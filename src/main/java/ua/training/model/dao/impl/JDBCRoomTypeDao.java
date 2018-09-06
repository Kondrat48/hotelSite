package ua.training.model.dao.impl;

import com.mysql.cj.xdevapi.Collection;
import ua.training.model.dao.RoomTypeDao;
import ua.training.model.dao.mapper.RoomTypeMapper;
import ua.training.model.entity.RoomType;
import ua.training.model.exception.UnsuccessfulSqlOperationException;
import ua.training.model.service.RoomTypeService;
import ua.training.model.service.resourceManager.DBQueryManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class JDBCRoomTypeDao implements RoomTypeDao {
    private final Connection connection;
    
    public JDBCRoomTypeDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(RoomType entity) {
        
    }

    @Override
    public RoomType findById(int id) {
        return null;
    }

    @Override
    public List<RoomType> findAll(int currentPage, int recordsPerPage, String sortColumn, String searchParam, String searchField) {
        return null;
    }
    
    @Override
    public List<RoomType> findAll(Locale locale) {
        DBQueryManager queryManager = new DBQueryManager();
        List<RoomType> roomTypes = new ArrayList<>();
        try(PreparedStatement st = connection.prepareStatement(queryManager.getProperty("sql.select.room_types"))) {
            ResultSet rs = st.executeQuery();
            RoomTypeMapper mapper = new RoomTypeMapper(locale);
            while (rs.next()){
                roomTypes.add(mapper.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomTypes;
    }

    @Override
    public int getNumberOfRows(String searchParam, String searchField) {
        return 0;
    }

    @Override
    public void update(RoomType entity) throws UnsuccessfulSqlOperationException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
