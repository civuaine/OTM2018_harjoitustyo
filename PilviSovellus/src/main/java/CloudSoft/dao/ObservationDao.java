package CloudSoft.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * Vastaa havaintorajapinnan metodeista.
 */
public interface ObservationDao {
    
    public Connection getConnection() throws SQLException;
    public void init();
    public void save() throws SQLException;
    public void addData();
    List<String> getAllByCity() throws SQLException;
    List<String> getAllByDate() throws SQLException;
    
    
}
