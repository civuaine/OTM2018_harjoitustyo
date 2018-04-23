package cloudtest;

import cloudsoft.domain.Cloud;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * Luokka vastaa pilviluokan testaamisesta.
 */
public class CloudTest {

    private Cloud cloud;

    public CloudTest() {
        this.cloud = new Cloud();
    }

    @Before
    public void setUp() {

    }

    @Test
    public void getPilviPalauttaaPilvenOikein() {
        cloud.setPilvi("Nimbostratus");
        assertEquals("Nimbostratus", cloud.getPilvi());
    }

    @Test
    public void getPilviSataaPalauttaaOikeinTrue() {
        cloud.setPilviSataa(true);
        assertEquals(true, cloud.getPilvisataa());
    }

    @Test
    public void getPilviSataaPalauttaaOikeinFalse() {
        cloud.setPilviSataa(false);
        assertEquals(false, cloud.getPilvisataa());
    }

    @Test
    public void getPilviOnIsoPalauttaaOikeintrue() {
        cloud.setPilviOnIso(true);
        assertEquals(true, cloud.getPilviOnIso());
    }

    @Test
    public void getPilviOnIsoPalauttaaOikeinFalse() {
        cloud.setPilviOnIso(false);
        assertEquals(false, cloud.getPilviOnIso());
    }

    @Test
    public void getPilviOnValkoinenPalauttaaOikeinTrue() {
        cloud.setPilviOnValkoinen(true);
        assertEquals(true, cloud.getPilviOnValkoinen());
    }

    @Test
    public void getPilviOnValkoinenPalauttaaOikeinFalse() {
        cloud.setPilviOnValkoinen(false);
        assertEquals(false, cloud.getPilviOnValkoinen());
    }

    @Test
    public void getPilviOnLapikuultavaPalauttaaOikeinTrue() {
        cloud.setPilviOnLapikuultava(true);
        assertEquals(true, cloud.getPilviOnLapikuultava());
    }

    @Test
    public void getPilviOnLapikuultavaPalauttaaOikeinFalse() {
        cloud.setPilviOnLapikuultava(false);
        assertEquals(false, cloud.getPilviOnLapikuultava());
    }

    @Test
    public void getPilviOnSelvarajainenPalauttaaOikeinTrue() {
        cloud.setPilviOnSelvaRajainen(true);
        assertEquals(true, cloud.getPilviOnSelvarajainen());
    }

    @Test
    public void getPilviOnSelvarajainenPalauttaaOikeinFalse() {
        cloud.setPilviOnSelvaRajainen(false);
        assertEquals(false, cloud.getPilviOnSelvarajainen());
    }

    @Test
    public void getPilvenUlkoNakoPalauttaaOikein() {
        cloud.setPilvenUlkoNako("repaleinen");
        assertEquals("repaleinen", cloud.getPilvenUlkoNako());
    }

    @Test
    public void getUkkostaa() {
        cloud.setUkkostaa(true);
        assertEquals(true, cloud.getUkkostaa());
    }

    @Test
    public void etsiPilviPalauttaaOikeinNimbostratus() {
        cloud.setPilviSataa(true);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnValkoinen(false);
        cloud.setPilviOnLapikuultava(false);
        cloud.setPilviOnSelvaRajainen(false);
        cloud.etsiPilvi();
        assertEquals("Nimbostratus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinCumulonimbus() {
        cloud.setPilviSataa(true);
        cloud.setUkkostaa(true);
        cloud.setPilviOnValkoinen(true);
        cloud.etsiPilvi();
        assertEquals("Cumulonimbus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinCumulus() {
        cloud.setPilviOnIso(false);
        cloud.setPilviOnValkoinen(true);
        cloud.setPilviOnSelvaRajainen(true);
        cloud.etsiPilvi();
        assertEquals("Cumulus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinStratus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnValkoinen(false);
        cloud.setPilviOnSelvaRajainen(false);
        cloud.etsiPilvi();
        assertEquals("Stratus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinCirrus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnIso(false);
        cloud.setPilviOnValkoinen(true);
        cloud.setPilviOnLapikuultava(true);
        cloud.setPilviOnSelvaRajainen(false);
        cloud.etsiPilvi();
        assertEquals("Cirrus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinCirrostratus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnValkoinen(true);
        cloud.setPilviOnLapikuultava(true);
        cloud.setPilviOnSelvaRajainen(false);
        cloud.setPilvenUlkoNako("tasainen");
        cloud.etsiPilvi();
        assertEquals("Cirrostratus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinAltostratus() {
        cloud.setPilviSataa(true);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnValkoinen(false);
        cloud.setPilviOnLapikuultava(true);
        cloud.setPilviOnSelvaRajainen(false);
        cloud.etsiPilvi();
        assertEquals("Altostratus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinAltocumulus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnValkoinen(true);
        cloud.setPilviOnLapikuultava(true);
        cloud.setPilviOnSelvaRajainen(true);
        cloud.etsiPilvi();
        assertEquals("Altocumulus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinStratocumulus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnValkoinen(true);
        //cloud.setPilviOnLapikuultava(true);
        cloud.setPilviOnSelvaRajainen(true);
        cloud.etsiPilvi();
        assertEquals("Stratocumulus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinCirrocumulus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnValkoinen(true);
        cloud.setPilviOnLapikuultava(true);
        cloud.setPilviOnSelvaRajainen(false);
        cloud.setPilvenUlkoNako("palleroinen");
        cloud.etsiPilvi();
        assertEquals("Cirrocumulus", cloud.getPilvi());
    }
}
