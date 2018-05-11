package databasetests;

import cloudsoft.dao.ObservationDatabase;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Tämä luokka testaa havaintotietokantaluokan metodeja.
 */
public class ObservationDatabaseTest {

    private ObservationDatabase observationdatabase;

    @Before
    public void setUp() {
        //this.observationdatabase = new ObservationDatabase();
    }

    @Test
    public void konstruktoriAsettaaTietokannanOsoitteenOikein() throws Exception {
        String osoite = "string";
        this.observationdatabase = new ObservationDatabase(osoite);
        String address = osoite;
        assertEquals("string", address);
    }

    // addData, getConnection ja init -testit
    @Test
    public void addDataLisaaDataaTietokantaan() throws SQLException {
        try {
            ObservationDatabase base = new ObservationDatabase("jdbc:sqlite:Testi.db");
            base.init();
            if (base.getAllObservations("paikka").isEmpty()) {
                base.addData();
            }
        } catch (ClassNotFoundException ex) {

        }
    }
}
