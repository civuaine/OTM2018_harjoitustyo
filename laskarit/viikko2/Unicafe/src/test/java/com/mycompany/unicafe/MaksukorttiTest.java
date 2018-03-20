package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10000); // 100 euroa
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals("saldo: 100.0", kortti.toString()); // vastaus ilmoitetaan euroissa.senteissä
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1000); // 10 euroa
        assertEquals("saldo: 110.0", kortti.toString());
    }
    
    // rahan ottamisen testit
    @Test
    public void saldoVaheneeKunRahaaTarpeeksi() {
        kortti.otaRahaa(5000); // 50 euroa
        assertEquals("saldo: 50.0", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(11000);
        assertEquals("saldo: 100.0", kortti.toString());
        
    }
    
    @Test
    public void metodiPalauttaaTrueJosRahatRiittivat() {
        kortti.otaRahaa(5000);
        assertEquals(true, kortti.otaRahaa(5000));
    }
    
    @Test
    public void metdoiPalauttaaFalseJosRahatEivatRiita() {
        kortti.otaRahaa(11000);
        assertEquals(false, kortti.otaRahaa(11000));
    }
    
    @Test public void testaaPalauttaakoOikeanSaldon() {
        assertEquals(10000,kortti.saldo());
    }
    
    
}
