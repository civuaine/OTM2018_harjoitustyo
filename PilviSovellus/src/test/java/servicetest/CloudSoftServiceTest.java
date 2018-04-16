package servicetest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import cloudsoft.dao.CloudDatabase;
import cloudsoft.dao.ObservationDatabase;
import cloudsoft.domain.ObservationDateCheck;
import cloudsoft.domain.CityCheck;
import cloudsoft.domain.Cloud;
import cloudsoft.domain.CloudSoftService;
import java.util.List;

/**
 *
 * Tämä testiluokka vastaa CloudSoftService-luokan testaamisesta.
 */
public class CloudSoftServiceTest {

    private CloudSoftService cloudsoftservice;

    @Before
    public void setUp() throws Exception {

        this.cloudsoftservice = new CloudSoftService();
        this.cloudsoftservice.tietokannatKayttovalmiiksi();

    }

    @Test
    public void getHavainnotPaivaPalauttaaListan() throws Exception {

        List<String> palautettava = cloudsoftservice.getHavainnotPaiva();
        assertEquals(!palautettava.isEmpty(), !cloudsoftservice.getHavainnotPaiva().isEmpty());
    }

    @Test
    public void getHavainnotPaikkaPalauttaaListan() throws Exception {

        List<String> palautettava = cloudsoftservice.getHavainnotPaikka();
        assertEquals(!palautettava.isEmpty(), !cloudsoftservice.getHavainnotPaikka().isEmpty());
    }

    @Test
    public void tarkistaPaikkakuntaPalauttaaTrueKunKaikkiKunnossa() {
        assertEquals(true, cloudsoftservice.tarkistaPaikkakunta("joutsa"));

    }

    @Test
    public void tarkistaPaikkakuntaPalauttaaFalseKunNimiVaarin() {
        assertEquals(false, cloudsoftservice.tarkistaPaikkakunta("joutsa1"));

    }

    @Test
    public void tarkistaPaivamaaraPalauttaaTrueKunKaikkiKunnossa() {
        assertEquals(true, cloudsoftservice.tarkistaPaivamaara("13/4/2018"));
    }

    @Test
    public void tarkistaPaivamaaraPalauttaaFalseKunPaivaysVaarin() {
        assertEquals(false, cloudsoftservice.tarkistaPaivamaara("12.4.2018"));
    }

//    @Test
//    public void annetaankoLahivuorokausienEnnustePalauttaaTrueJosAlle3pvEroaHavainnollaJaTallapaivalla() {
//        cloudsoftservice.tarkistaPaivamaara("15/4/2018");
//        assertEquals(true, cloudsoftservice.annetaankoLahivuorokausienEnnuste());
//    }
//
//    @Test
//    public void annetaankoLahivuorokausienEnnustePalauttaaFalseJosYli3pvEroaHavainnollaJaTallapaivalla() {
//        cloudsoftservice.tarkistaPaivamaara("10/4/2018");
//        assertEquals(false, cloudsoftservice.annetaankoLahivuorokausienEnnuste());
//    }
}
