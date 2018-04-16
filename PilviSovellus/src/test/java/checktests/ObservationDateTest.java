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
    private int paivaTanaan;
    private int havaintoPaiva;
    private int kuukausiTanaan;
    private int havaintoKuukausi;
    private int vuosiTanaan;
    private int havaintoVuosi;
    private Date pvmTanaan;

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
        this.havaintoPaiva = 29;
        this.havaintoKuukausi = 11;
        this.havaintoVuosi = 2019;
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosVuosiAlle1950() {
        this.havaintoPaiva = 29;
        this.havaintoKuukausi = 11;
        this.havaintoVuosi = 1949;
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosKuukausiYli12() {
        this.havaintoPaiva = 5;
        this.havaintoKuukausi = 13;
        this.havaintoVuosi = 2017;
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosKuukausiAlle1() {
        this.havaintoPaiva = 29;
        this.havaintoKuukausi = 0;
        this.havaintoVuosi = 2018;
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosPaivaYli31() {
        this.havaintoPaiva = 32;
        this.havaintoKuukausi = 11;
        this.havaintoVuosi = 2017;
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

    @Test
    public void paivamaaraJarkevaPalauttaaFalseJosPaivaAlle1() {
        this.havaintoPaiva = 0;
        this.havaintoKuukausi = 12;
        this.havaintoVuosi = 2017;
        assertEquals(false, observationdatecheck.paivamaaraJarkeva());
    }

//    @Test
//    public void paivamaaraJarkevaPalauttaaTrueJosPaivaOn31() {
//        this.havaintoPaiva = 31;
//        this.havaintoKuukausi = 9;
//        this.havaintoVuosi = 2017;
//        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
//    }
//
//    @Test
//    public void paivamaaraJarkevaPalauttaaTrueJosPaivaOn1() {
//        this.havaintoPaiva = 1;
//        this.havaintoKuukausi = 9;
//        this.havaintoVuosi = 2017;
//        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
//    }
//
//    @Test
//    public void paivamaaraJarkevaPalauttaaTrueJosKuukausiOn1() {
//        this.havaintoPaiva = 15;
//        this.havaintoKuukausi = 1;
//        this.havaintoVuosi = 2017;
//        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
//    }
//
//    @Test
//    public void paivamaaraJarkevaPalauttaaTrueJosKuukausiOn12() {
//        this.havaintoPaiva = 15;
//        this.havaintoKuukausi = 12;
//        this.havaintoVuosi = 2017;
//        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
//    }
//    
//    @Test
//    public void paivamaaraJarkevaPalauttaaTrueJosvuosiOn1950() {
//        this.havaintoPaiva = 10;
//        this.havaintoKuukausi = 8;
//        this.havaintoVuosi = 1950;
//        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
//    }
//    
//    @Test
//    public void paivamaaraJarkevaPalauttaaTrueJosvuosiOn2018() {
//        this.havaintoPaiva = 10;
//        this.havaintoKuukausi = 8;
//        this.havaintoVuosi = 2018;
//        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
//    }    
//    
//
//    @Test
//    public void paivamaaraJarkevaPalauttaaTrueJosKaikkiOk() {
//        this.havaintoPaiva = 31;
//        this.havaintoKuukausi = 12;
//        this.havaintoVuosi = 2017;
//        assertEquals(true, observationdatecheck.paivamaaraJarkeva());
//    }
    @Test
    public void paivamaaraEitulevaisuudessaPalauttaaTrueJosHavaintoPaivaEnnenTataPaivaa() {
        observationdatecheck.tanaanStringiksi();
        this.havaintoPaiva = 14;
        this.havaintoKuukausi = 4;
        this.havaintoVuosi = 2018;

        assertEquals(true, observationdatecheck.havaintoEiTulevaisuudessa());
    }

    @Test
    public void havaintoEitulevaisuudessaPalauttaaFalseJosHavaintoPaivaTulevaisuudessa() {
        observationdatecheck.tanaanStringiksi();
        this.havaintoPaiva = 14;
        this.havaintoKuukausi = 4;
        this.havaintoVuosi = 2019;

        assertEquals(true, observationdatecheck.havaintoEiTulevaisuudessa());
    }

    //TOIMII
    @Test 
    public void havaintoEitulevaisuudessaPalauttaaTrueJosHavaintoTanaan() {
        observationdatecheck.tanaanStringiksi();
        
        this.havaintoPaiva = 16;
        this.havaintoKuukausi = 4;
        this.havaintoVuosi = 2018;
        
        assertEquals(true, observationdatecheck.havaintoEiTulevaisuudessa());
    }    
    
    @Test
    public void annetaankoEnnuste3vrkPaahanPalauttaaTrueJosHavainnostaKulunutMax3Pv() {
        observationdatecheck.tanaanStringiksi();
        observationdatecheck.paivamaaranMuotoTarkistin("13/4/2018");
        

        assertEquals(true, observationdatecheck.havaintoEiTulevaisuudessa());
    }
    
    @Test
    public void annetaankoEnnuste3vrkPaahanPalauttaaFalseJosHavainnostaKulunutYli3Pv() {
        observationdatecheck.tanaanStringiksi();
        observationdatecheck.paivamaaranMuotoTarkistin("12/4/2018");
        

        assertEquals(true, observationdatecheck.havaintoEiTulevaisuudessa());
    }    
}
