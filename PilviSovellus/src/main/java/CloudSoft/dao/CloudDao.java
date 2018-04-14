package CloudSoft.dao;

import java.util.List;
import java.sql.*;
import CloudSoft.domain.ObservationDateCheck;
import CloudSoft.domain.CityCheck;
import CloudSoft.domain.Cloud;

/**
 *
 * Määrittelee pilvien käsittelyä varten tietokantarajapinnan metodit.
 */


public interface CloudDao {
    
    public Connection getConnection() throws SQLException;
    
    public void init() throws SQLException;
    
    public void addData() throws SQLException;
    
    public Cloud getInformation(String nimi) throws SQLException;
    
}
