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
    private Cloud cloud;

    @Before
    public void setUp() throws Exception {

        this.cloudsoftservice = new CloudSoftService();
        this.cloudsoftservice.tietokannatKayttovalmiiksi();
        this.cloud = new Cloud();

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
    public void tarkistaPaikkakuntaPalauttaaFalseKunJokuKolmestaEhdostaVaarin() {
        assertEquals(false, cloudsoftservice.tarkistaPaikkakunta(""));
    }

    @Test
    public void tarkistaPaivamaaraPalauttaaTrueKunKaikkiKunnossa() {
        assertEquals(true, cloudsoftservice.tarkistaPaivamaara("13/4/2018"));
    }

    @Test
    public void tarkistaPaivamaaraPalauttaaFalseKunPaivaysVaarin() {
        assertEquals(false, cloudsoftservice.tarkistaPaivamaara("12.4.2018"));
    }

    //Aseta päivämäärä jos haluat testata
//    @Test
//    public void annetaankoLahivuorokausienEnnustePalauttaaTrueJosAlle3pvEroaHavainnollaJaTallapaivalla() {
//        cloudsoftservice.tarkistaPaivamaara("1/5/2018");
//        assertEquals(true, cloudsoftservice.annetaankoLahivuorokausienEnnuste());
//    }
    @Test
    public void annetaankoLahivuorokausienEnnustePalauttaaFalseJosYli3pvEroaHavainnollaJaTallapaivalla() {
        cloudsoftservice.tarkistaPaivamaara("20/4/2018");
        assertEquals(false, cloudsoftservice.annetaankoLahivuorokausienEnnuste());
    }

    @Test
    public void paikkakuntaOikeinAnnettuPalauttaaTrue() {
        assertEquals(true, cloudsoftservice.paikkakuntaOikeinAnnettu());
    }

    @Test
    public void paivamaaraOikeinAnnettuPalauttaaTrue() {
        assertEquals(true, cloudsoftservice.paivamaaraOikeinAnnettu());
    }

    @Test
    public void setSadeAsettaaoikeinTrue() {
        cloudsoftservice.setSade(true);
        assertEquals(true, cloudsoftservice.getSade());
    }

    @Test
    public void setSadeAsettaaoikeinFalse() {
        cloudsoftservice.setSade(false);
        assertEquals(false, cloudsoftservice.getSade());
    }

    @Test
    public void setKokoAsettaaOikeinTrue() {
        cloudsoftservice.setKoko(true);
        assertEquals(true, cloudsoftservice.getKoko());
    }

    @Test
    public void setKokoAsettaaOikeinfalse() {
        cloudsoftservice.setKoko(false);
        assertEquals(false, cloudsoftservice.getKoko());
    }

    @Test
    public void setVariAsettaaoikeinTrue() {
        cloudsoftservice.setVari(true);
        assertEquals(true, cloudsoftservice.getVari());
    }

    @Test
    public void setVariAsettaaoikeinFalse() {
        cloudsoftservice.setVari(false);
        assertEquals(false, cloudsoftservice.getVari());
    }

    @Test
    public void setLapikuultavaAsettaaOikeinTrue() {
        cloudsoftservice.setLapikuultava(true);
        assertEquals(true, cloudsoftservice.getLapikuultava());
    }

    @Test
    public void setLapikuultavaAsettaaOikeinFalse() {
        cloudsoftservice.setLapikuultava(false);
        assertEquals(false, cloudsoftservice.getLapikuultava());
    }

    @Test
    public void setSelvaRajainenAsettaaOikeinTrue() {
        cloudsoftservice.setSelvarajainen(true);
        assertEquals(true, cloudsoftservice.getSelvarajainen());
    }

    @Test
    public void setSelvaRajainenAsettaaOikeinFalse() {
        cloudsoftservice.setSelvarajainen(false);
        assertEquals(false, cloudsoftservice.getSelvarajainen());
    }

}
