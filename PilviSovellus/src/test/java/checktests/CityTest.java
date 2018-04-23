package checktests;

import cloudsoft.domain.CityCheck;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Tämä luokka testaa kaupunkitestiluokkaa.
 */
public class CityTest {

    CityCheck citycheck;

    @Before
    public void setUp() {
        citycheck = new CityCheck();
    }

    @Test
    public void sisaltaaVainKirjaimiaPalauttaaFalseJosPaikassaNumeroita() {
        assertEquals(false, citycheck.paikkakuntaSisaltaaVainKirjaimia("1val0"));
    }

    @Test
    public void sisaltaaVainKirjaimiaPalauttaaTrueJosPaikanNimiSallittu() {
        assertEquals(true, citycheck.paikkakuntaSisaltaaVainKirjaimia("Muurame"));
    }

    @Test
    public void paikkakuntaEiTyhjaPalauttaaFalseJosPaikkakuntaKenttaOnTyhja() {
        assertEquals(false, citycheck.paikkakuntaEiTyhja(""));
    }

    @Test
    public void paikkakuntaEiTyhjaPalauttaaFalseJosPaikkakuntaKenttasisaltaaValeja() {
        assertEquals(false, citycheck.paikkakuntaEiTyhja("     "));
    }
    
    @Test
    public void paikkakuntaEiTyhjaPalauttaaTrueJosPaikkakuntaKenttaSisaltaaJotain() {
        assertEquals(true, citycheck.paikkakuntaEiTyhja("Jyväskylä2"));
    }   
    
    @Test
    public void getPaikkakuntaPalauttaaOikein() {
        citycheck.setPaikkakunta("Tuusula");
        assertEquals("Tuusula", citycheck.getPaikkakunta());
    }

}
