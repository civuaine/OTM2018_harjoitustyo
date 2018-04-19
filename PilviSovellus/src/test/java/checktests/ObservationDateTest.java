package checktests;

import cloudsoft.domain.ObservationDateCheck;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Tämä luokka vastaa havaintopäivämäärän tarkistamisluokan testauksesta.
 */
public class ObservationDateTest {

    private ObservationDateCheck observationdatecheck;

    @Before
    public void setUp() {
        observationdatecheck = new ObservationDateCheck();
    }

    @Test
    public void paivamaaraOnIntegerPalauttaaFalseJosStringSisaltaaKirjaimia() {
        assertEquals(false, observationdatecheck.paivamaaraOnInteger("2e3"));
    }

    @Test
    public void paivamaaraOnIntegerPalauttaaTrueJosStringSisaltaaVainNumeroita() {
        assertEquals(true, observationdatecheck.paivamaaraOnInteger("23"));
    }

    @Test
    public void paivamaaranMuotoTarkistinPalauttaaTrueJosPaivaysOikeinAnnettu() {
        assertEquals(true, observationdatecheck.paivamaaranMuotoTarkistin("15/04/2018"));
    }

    @Test
    public void paivamaaranMuotoTarkistinPalauttaaTrueJosPaivaysLahesOikeinAnnettu() {
        assertEquals(true, observationdatecheck.paivamaaranMuotoTarkistin("15/4/2018"));
    }

    @Test
    public void paivamaaranMuotoTarkistinPalauttaaFalseJosPaivaysVaarinAnnettu() {
        assertEquals(false, observationdatecheck.paivamaaranMuotoTarkistin("15.04.2018"));
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosVuosiYli2018() {
        observationdatecheck.setpv(29);
        observationdatecheck.setkk(11);
        observationdatecheck.setvvvv(2019);

        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosVuosiAlle1950() {
        observationdatecheck.setpv(29);
        observationdatecheck.setkk(11);
        observationdatecheck.setvvvv(1949);
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosKuukausiYli12() {
        observationdatecheck.setpv(5);
        observationdatecheck.setkk(13);
        observationdatecheck.setvvvv(2017);
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosKuukausiAlle1() {
        observationdatecheck.setpv(29);
        observationdatecheck.setkk(0);
        observationdatecheck.setvvvv(2017);
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosPaivaYli31() {
        observationdatecheck.setpv(32);
        observationdatecheck.setkk(11);
        observationdatecheck.setvvvv(2017);
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosPaivaAlle1() {
        observationdatecheck.setpv(0);
        observationdatecheck.setkk(9);
        observationdatecheck.setvvvv(2017);
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaTrueJosPaivaOn31() {
        observationdatecheck.setpv(31);
        observationdatecheck.setkk(9);
        observationdatecheck.setvvvv(2017);
        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaTrueJosPaivaOn1() {
        observationdatecheck.setpv(1);
        observationdatecheck.setkk(9);
        observationdatecheck.setvvvv(2017);
        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaTrueJosKuukausiOn1() {
        observationdatecheck.setpv(15);
        observationdatecheck.setkk(1);
        observationdatecheck.setvvvv(2017);
        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaTrueJosKuukausiOn12() {
        observationdatecheck.setpv(15);
        observationdatecheck.setkk(12);
        observationdatecheck.setvvvv(2017);
        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaTrueJosvuosiOn1950() {
        observationdatecheck.setpv(10);
        observationdatecheck.setkk(8);
        observationdatecheck.setvvvv(1950);
        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaTrueJosvuosiOn2018() {
        observationdatecheck.setpv(29);
        observationdatecheck.setkk(11);
        observationdatecheck.setvvvv(2018);
        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaTrueJosKaikkiOk() {
        observationdatecheck.setpv(31);
        observationdatecheck.setkk(12);
        observationdatecheck.setvvvv(2017);
        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void havaintoEitulevaisuudessaPalauttaaTrueJosHavaintoPaivaEnnenTataPaivaa() {
        //observationdatecheck.tanaanStringiksi();
        observationdatecheck.setpv(14);
        observationdatecheck.setkk(4);
        observationdatecheck.setvvvv(2018);
        assertEquals(true, observationdatecheck.havaintoEiTulevaisuudessa());
    }

    @Test
    public void havaintoEitulevaisuudessaPalauttaaFalseJosHavaintoPaivaTulevaisuudessa() {
        //observationdatecheck.tanaanStringiksi();
        observationdatecheck.setpv(20);
        observationdatecheck.setkk(6);
        observationdatecheck.setvvvv(2018);
        assertEquals(false, observationdatecheck.havaintoEiTulevaisuudessa());
    }
//
//    @Test
//    public void havaintoEitulevaisuudessaPalauttaaTrueJosHavaintoTanaan() {
//        //observationdatecheck.tanaanStringiksi();
//        observationdatecheck.setpv(19);
//        observationdatecheck.setkk(4);
//        observationdatecheck.setvvvv(2018);
//        assertEquals(true, observationdatecheck.havaintoEiTulevaisuudessa());
//    }

//    @Test
//    public void annetaankoEnnuste3vrkPaahanPalauttaaTrueJosHavainnostaKulunutMax3Pv() {
//        //observationdatecheck.tanaanStringiksi();
//        observationdatecheck.setpv(18);
//        observationdatecheck.setkk(4);
//        observationdatecheck.setvvvv(2018);
//        assertEquals(true, observationdatecheck.annetaankoEnnuste3vrkPaahan());
//    }
//
//    @Test
//    public void annetaankoEnnuste3vrkPaahanPalauttaaFalseJosHavainnostaKulunutYli3Pv() {
//        //observationdatecheck.tanaanStringiksi();
//        observationdatecheck.setpv(12);
//        observationdatecheck.setkk(4);
//        observationdatecheck.setvvvv(2018);
//        assertEquals(false, observationdatecheck.annetaankoEnnuste3vrkPaahan());
//    }
//

}
