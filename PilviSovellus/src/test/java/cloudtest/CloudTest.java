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
    public void getUkkostaa() {
        cloud.setUkkostaa(true);
        assertEquals(true, cloud.getUkkostaa());
    }
// Muita settereitä ja gettereitä ei enää testata, sillä nämäkin tuntuu toimivan

    @Test
    public void etsiPilviPalauttaaOikeinNimbostratus() {
        cloud.setPilviSataa(true);
        cloud.setVesiTaiLumi(true);
        cloud.setKuurottainenSade(false);
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
        cloud.setVesiTaiLumi(true);
        cloud.setVoimakasSade(true);
        cloud.setTummaPohjaValkoinenTorni(true);
        cloud.setKukkakaalimainen(true);
        cloud.setPilviOnSelvaRajainen(true);
        cloud.setPilviOnKorkea(true);
        cloud.etsiPilvi();
        assertEquals("Cumulonimbus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinCumulonimbusToinenTapaus() {
        cloud.setPilviSataa(true);
        cloud.setUkkostaa(true);
        cloud.etsiPilvi();
        assertEquals("Cumulonimbus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinCumulonimbuskolmasTapaus() {
        cloud.setPilviSataa(true);
        cloud.setsataaRakeita(true);
        cloud.etsiPilvi();
        assertEquals("Cumulonimbus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinCumulus() {
        cloud.setPilviSataa(true);
        cloud.setKuurottainenSade(true);
        cloud.setVesiTaiLumi(true);
        cloud.setVoimakasSade(false);
        cloud.etsiPilvi();
        assertEquals("Cumulus", cloud.getPilvi());
    }
    
    @Test
    public void etsiPilviPalauttaaOikeinCumulusToinenTapaus() {
        cloud.setPilviSataa(false);
        cloud.setKukkakaalimainen(true);
        cloud.setPilviOnIso(false);
        cloud.setTummaPohjaValkoinenTorni(true);
        cloud.setPilviOnSelvaRajainen(true);
        cloud.setPilviOnKorkea(true);
        cloud.etsiPilvi();
        assertEquals("Cumulus", cloud.getPilvi());
    }    

    @Test
    public void etsiPilviPalauttaaOikeinStratus() {
        cloud.setPilviSataa(true);
        cloud.setMuuSateenOlomuoto(true);
        cloud.setPilviOnValkoinen(false);
        cloud.etsiPilvi();
        assertEquals("Stratus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinStratusToinenTapaus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnValkoinen(false);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnLapikuultava(false);
        cloud.setPilviOnSelvaRajainen(false);
        cloud.etsiPilvi();
        assertEquals("Stratus", cloud.getPilvi());
    }    
    
    @Test
    public void etsiPilviPalauttaaOikeinCirrus() {
        cloud.setHarsomainenJaTaiSaikeinen(true);
        cloud.setHaloja(false);
        cloud.setPilviOnValkoinen(true);
        cloud.setPilviOnLapikuultava(true);
        cloud.etsiPilvi();
        assertEquals("Cirrus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinCirrostratus() {
        cloud.setHarsomainenJaTaiSaikeinen(true);
        cloud.setHaloja(true);
        cloud.setPilviOnValkoinen(true);
        cloud.setPilviOnLapikuultava(true);
        cloud.etsiPilvi();
        assertEquals("Cirrostratus", cloud.getPilvi());
    }
    
    @Test
    public void etsiPilviPalauttaaOikeinAltostratus() {
        cloud.setHaloja(false);
        cloud.setPilviOnValkoinen(false);
        cloud.setPilviOnLapikuultava(true);
        cloud.setHarsomainenJaTaiSaikeinen(true);
        cloud.etsiPilvi();
        assertEquals("Altostratus", cloud.getPilvi());
    }
    
    @Test
    public void etsiPilviPalauttaaOikeinAltostratusToinenTapaus() {
        cloud.setPilviSataa(true);
        cloud.setVesiTaiLumi(true);
        cloud.setKuurottainenSade(false);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnValkoinen(false);
        cloud.setPilviOnLapikuultava(true);
        cloud.setHaloja(false);
        cloud.etsiPilvi();
        assertEquals("Altostratus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinAltostratusKolmasTapaus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnValkoinen(false);
        cloud.setHarsomainenJaTaiSaikeinen(true);
        cloud.setHaloja(false);
        cloud.setPilviOnLapikuultava(true);
        cloud.etsiPilvi();
        assertEquals("Altostratus", cloud.getPilvi());
    }    
    
    @Test
    public void etsiPilviPalauttaaOikeinAltocumulus() {
        cloud.setSolumainen(true);
        cloud.setIsoSolu(true);
        cloud.setPilviOnValkoinen(false);
        cloud.etsiPilvi();
        assertEquals("Altocumulus", cloud.getPilvi());
    }
    
    @Test
    public void etsiPilviPalauttaaOikeinAltocumulusToinenTapaus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnKorkea(false);
        cloud.setPilviOnValkoinen(false);
        cloud.setPilviOnLapikuultava(false);
        cloud.setHarsomainenJaTaiSaikeinen(false);
        cloud.etsiPilvi();
        assertEquals("Altocumulus", cloud.getPilvi());
    }
    
    @Test
    public void etsiPilviPalauttaaOikeinStratocumulus() {
        cloud.setAaltomainen(true);
        cloud.setIsoAalto(true);
        cloud.setPilviOnValkoinen(false);
        cloud.etsiPilvi();
        assertEquals("Stratocumulus", cloud.getPilvi());
    }

    @Test
    public void etsiPilviPalauttaaOikeinStratocumulusToinenTapaus() {
        cloud.setPilviSataa(false);
        cloud.setVesiTaiLumi(true);
        cloud.setKuurottainenSade(false);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnKorkea(false);
        cloud.setPilviOnValkoinen(false);
        cloud.setPilviOnSelvaRajainen(true);
        cloud.etsiPilvi();
        assertEquals("Stratocumulus", cloud.getPilvi());
    }    

    @Test
    public void etsiPilviPalauttaaOikeinStratocumulusKolmasTapaus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnIso(true);
        cloud.setPilviOnValkoinen(false);
        cloud.setPilviOnKorkea(false);
        cloud.setPilviOnSelvaRajainen(true);
        cloud.etsiPilvi();
        assertEquals("Stratocumulus", cloud.getPilvi());
    }    
    @Test
    public void etsiPilviPalauttaaOikeinCirrocumulus() {
        cloud.setSolumainen(true);
        cloud.setIsoSolu(false);
        cloud.setPilviOnLapikuultava(true);
        cloud.etsiPilvi();
        assertEquals("Cirrocumulus", cloud.getPilvi());
    }
    
    @Test
    public void etsiPilviPalauttaaOikeinCirrocumulusToinenTapaus() {
        cloud.setAaltomainen(true);
        cloud.setIsoAalto(false);
        cloud.setPilviOnLapikuultava(true);
        cloud.etsiPilvi();
        assertEquals("Cirrocumulus", cloud.getPilvi());
    } 
    
    @Test
    public void etsiPilviPalauttaaOikeinCirrocumulusKolmasTapaus() {
        cloud.setPilviSataa(false);
        cloud.setPilviOnValkoinen(true);
        cloud.setPilviOnLapikuultava(true);
        cloud.setHarsomainenJaTaiSaikeinen(false);
        cloud.etsiPilvi();
        assertEquals("Cirrocumulus", cloud.getPilvi());
    }    
}
