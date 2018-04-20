package cloudsoft.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * Vastaa havaintorajapinnan metodeista.
 */
public interface ObservationDao {

    public Connection getConnection() throws SQLException;

    public void init();

    public void save(String paikka, Date paivamaara, String pilvi) throws SQLException;

    public void addData();

    List<String> getAllByCity() throws SQLException;

    List<String> getAllByDate() throws SQLException;

}
