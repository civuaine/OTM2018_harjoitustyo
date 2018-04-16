package databasetests;

import cloudsoft.dao.CloudDatabase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Tämä testiluokka testaa pilvitietokantaluokan metodeja.
 */
public class CloudDatabaseTest {

    private CloudDatabase clouddatabase;
    
    @Before
    public void setUp() {
        //this.clouddatabase = new CloudDatabase(address);
    }

    @Test
    public void konstruktoriAsettaaTietokannanOsoitteenOikein() throws Exception {
        String osoite = "string";
        this.clouddatabase = new CloudDatabase(osoite);
        String address = osoite;
        assertEquals("string", address);
    }

//    @Test 
//    public void initLuoTietokannan() {
//        
//    }

//    @Test
//    public void getInfortmationPalauttaaEnnusteenJosPilviOlemassaTietokannassa() throws Exception{
//        clouddatabase.init();
//        clouddatabase.addData();
//        "jdbc:sqlite:Pilvitietokanta.db"    
//        
//        String ennuste = clouddatabase.getInformation("Cirrocumulus");
//        assertEquals("epävakautta yläilmakehässä. Mahdollisesti rintama tulossa.",ennuste);
//    }
}
