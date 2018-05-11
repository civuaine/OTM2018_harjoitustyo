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
        assertEquals(true, observationdatecheck.paivamaaranMuotoTarkistin("15.04.2018"));
    }

    @Test
    public void paivamaaranMuotoTarkistinPalauttaaTrueJosPaivaysLahesOikeinAnnettu() {
        assertEquals(true, observationdatecheck.paivamaaranMuotoTarkistin("15.4.2018"));
    }

    @Test
    public void paivamaaranMuotoTarkistinPalauttaaFalseJosPaivaysVaarinAnnettu() {
        assertEquals(false, observationdatecheck.paivamaaranMuotoTarkistin("15/04/2018"));
    }

    @Test
    public void paivamaaranMuotoTarkistinPalauttaaFalseJosJokuAnnetuistaVaarin() {
        assertEquals(false, observationdatecheck.paivamaaranMuotoTarkistin("15.ii.2018"));
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
//  Aseta päivämäärä jos haluat testata
//    @Test
//    public void havaintoEitulevaisuudessaPalauttaaTrueJosHavaintoTanaan() {
//        //observationdatecheck.tanaanStringiksi();
//        observationdatecheck.setpv(2);
//        observationdatecheck.setkk(5);
//        observationdatecheck.setvvvv(2018);
//        assertEquals(true, observationdatecheck.havaintoEiTulevaisuudessa());
//    }
//  Aseta päivämäärä jos haluat testata
//    @Test
//    public void annetaankoEnnuste3vrkPaahanPalauttaaTrueJosHavainnostaKulunutMax3Pv() {
//        //observationdatecheck.tanaanStringiksi();
//        observationdatecheck.setpv(1);
//        observationdatecheck.setkk(5);
//        observationdatecheck.setvvvv(2018);
//        assertEquals(true, observationdatecheck.annetaankoEnnuste3vrkPaahan());
//    }

    @Test
    public void annetaankoEnnuste3vrkPaahanPalauttaaFalseJosHavainnostaKulunutYli3Pv() {
        observationdatecheck.setpv(12);
        observationdatecheck.setkk(4);
        observationdatecheck.setvvvv(2018);
        assertEquals(false, observationdatecheck.annetaankoEnnuste3vrkPaahan());
    }

    @Test
    public void paivamaaraOnIntegerPalauttaafalseException() {
        assertEquals(false, observationdatecheck.paivamaaraOnInteger("ir"));
    }

    @Test
    public void paivamaaraOnIntegerPalauttaaFalseJosNullpoint() {
        assertEquals(false, observationdatecheck.paivamaaraOnInteger(null));
    }

    @Test
    public void getpvPalauttaaOikein() {
        observationdatecheck.setpv(12);
        assertEquals(12, observationdatecheck.getpv());
    }

    @Test
    public void getkkPalauttaaOikein() {
        observationdatecheck.setkk(4);
        assertEquals(4, observationdatecheck.getkk());
    }

    @Test
    public void getvvvvPalauttaaOikein() {
        observationdatecheck.setvvvv(2018);
        assertEquals(2018, observationdatecheck.getvvvv());
    }

    @Test
    public void getpvtanaanPalauttaaOikein() {
        observationdatecheck.setpvtanaan(12);
        assertEquals(12, observationdatecheck.getpvtanaan());
    }

    @Test
    public void getkktanaanPalauttaaOikein() {
        observationdatecheck.setkktanaan(5);
        assertEquals(5, observationdatecheck.getkktanaan());
    }

    @Test
    public void getvvvvtanaanPalauttaaOikein() {
        observationdatecheck.setvvvvtanaan(2018);
        assertEquals(2018, observationdatecheck.getvvvvtanaan());
    }

    @Test
    public void annetaankoEnnuste3vrkPaahanPalauttaaFalseKunSamaVuosiEriKuukausi() {
        observationdatecheck.setvvvvtanaan(2018);
        observationdatecheck.setvvvv(2018);
        observationdatecheck.setkk(4);
        observationdatecheck.setkktanaan(5);
        assertEquals(false, observationdatecheck.annetaankoEnnuste3vrkPaahan());
    }

    @Test
    public void annetaankoEnnuste3vrkPaahanPalauttaaFalseKunPaivienValillaYli3vrkEroa() {
        observationdatecheck.setvvvvtanaan(2018);
        observationdatecheck.setvvvv(2018);
        observationdatecheck.setkk(5);
        observationdatecheck.setkktanaan(5);
        observationdatecheck.setpvtanaan(10);
        observationdatecheck.setpv(6);
        assertEquals(false, observationdatecheck.annetaankoEnnuste3vrkPaahan());
    }

    @Test
    public void annetaankoEnnuste3vrkPaahanPalauttaaTrueKunPaivienValillaTasan3vrkEroa() {
        observationdatecheck.setvvvvtanaan(2018);
        observationdatecheck.setvvvv(2018);
        observationdatecheck.setkk(5);
        observationdatecheck.setkktanaan(5);
        observationdatecheck.setpvtanaan(10);
        observationdatecheck.setpv(7);
        assertEquals(true, observationdatecheck.annetaankoEnnuste3vrkPaahan());
    }
    
    @Test
    public void annetaankoEnnuste3vrkPaahanPalauttaaTrueKunPaivienValillaAlle3vrkEroa() {
        observationdatecheck.setvvvvtanaan(2018);
        observationdatecheck.setvvvv(2018);
        observationdatecheck.setkk(5);
        observationdatecheck.setkktanaan(5);
        observationdatecheck.setpvtanaan(10);
        observationdatecheck.setpv(9);
        assertEquals(true, observationdatecheck.annetaankoEnnuste3vrkPaahan());
    }        
}
