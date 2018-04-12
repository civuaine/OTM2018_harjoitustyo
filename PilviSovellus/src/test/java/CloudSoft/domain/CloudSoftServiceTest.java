/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CloudSoft.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sini
 */
public class CloudSoftServiceTest {

    private CloudSoftService cloudsoftservice;
    private boolean annetaankoEnnuste; 
    private int paivaTanaan;
    private int havaintoPaiva;
    private int kuukausiTanaan;
    private int havaintoKuukausi;
    private int vuosiTanaan;
    private int havaintoVuosi;

    public CloudSoftServiceTest() {

    }

    @Before
    public void setUp() {
        this.cloudsoftservice = new CloudSoftService();
        this.paivaTanaan = 9;
        this.havaintoPaiva = 6;
        this.kuukausiTanaan = 4;
        this.havaintoKuukausi = 4;
        this.vuosiTanaan = 2018;
        this.havaintoVuosi = 2018;
        
    }

//    @Test
//    public void EiAnnaEnnustettaJosPaivamaaraEroYli3Paivaa() {
//        assertEquals(true, cloudsoftservice.annetaankoEnnuste());
//    }
}
