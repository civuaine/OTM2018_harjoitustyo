package cloudsoft.dao;

import java.util.List;
import java.sql.*;
import cloudsoft.domain.ObservationDateCheck;
import cloudsoft.domain.CityCheck;
import cloudsoft.domain.Cloud;

/**
 *
 * Määrittelee pilvien käsittelyä varten tietokantarajapinnan metodit.
 */
public interface CloudDao {

    public Connection getConnection() throws SQLException;

    public void init();

    public void addData();

    public String getInformation(String nimi) throws SQLException;

}
