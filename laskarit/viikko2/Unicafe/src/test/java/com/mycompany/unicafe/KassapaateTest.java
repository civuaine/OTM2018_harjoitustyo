/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

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
public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti maksukortti;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(1000);
    }

    @Test
    public void kassapaatteessaOikeaMaaraRahaaAlussa() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void lounaidenMaaraAlussaOikea() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty() + kassapaate.maukkaitaLounaitaMyyty());
    }

    // vaihtoraha edullisessa ja maukkaassa toimii
    @Test
    public void syoEdullisestiKateismaksussaVaihtorahaToimii() {
        assertEquals(10, kassapaate.syoEdullisesti(250));
    }

    @Test
    public void syoMaukkaastiKateismaksussaVaihtorahaToimii() {
        assertEquals(50, kassapaate.syoMaukkaasti(450));
    }

    // kassan rahamaara kasvaa edullisessa ja maukkaassa
    @Test
    public void syoEdullisestiKateisellaKassanRahamaaraKasvaa() {
        kassapaate.syoEdullisesti(250); // 10snt yli hinnan
        assertEquals(100240, kassapaate.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiKateisellaKassanRahamaaraKasvaa() {
        kassapaate.syoMaukkaasti(450); // 50snt yli hinnan
        assertEquals(100400, kassapaate.kassassaRahaa());
    }

    // lounaiden maara lisaantyy edullisessa ja maukkaassa
    @Test
    public void syoEdullisestiRahanRiittaessaMyytyjenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(370);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiRahanRiittaessaMyytyjenLounaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(470);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    // jos käteismaksu ei ole riittävä
    // kassassa oleva rahamäärä ei muutu
    @Test
    public void syoEdullisestiKateisellaRahaEiRiitaKassanRahamaaraEiMuutu() {
        kassapaate.syoEdullisesti(140); // 1 euron alle hinnan
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiKateisellaRahaEiRiitaKassanRahamaaraEiMuutu() {
        kassapaate.syoMaukkaasti(300); // 1 euron alle hinnan
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    // Kaikki rahat palautetaan vaihtorahana
    @Test
    public void syoEdullisestiKateisellaRahaEiRiitaPalautetaanVaihtorahana() {
        // 10snt alle hinnan
        assertEquals(230, kassapaate.syoEdullisesti(230));
    }

    @Test
    public void syoMaukkaastiKateisellaRahaEiRiitaPalautetaanVaihtorahana() {
        // 10snt alle hinnan
        assertEquals(390, kassapaate.syoMaukkaasti(390));
    }

    // myydyissä lounaiden määrässä ei ole muutosta
    @Test
    public void syoEdullisestiRahaEiRiitaaMyytyjenLounaidenMaaraEiMuutu() {
        kassapaate.syoEdullisesti(170);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiRahaEiRiitaMyytyjenLounaidenMaaraEiMuutu() {
        kassapaate.syoMaukkaasti(370);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    // Maksukortin testaus
    // kortilla tarpeeksi rahaa: maukkaasti ja edullisesti toimii
    @Test
    public void syoEdullisestiKorttiOstoSummaVeloitetaanOikein() {
        kassapaate.syoEdullisesti(maksukortti);
        // 10 euroa - 2.40 = 7.60
        assertEquals(760, maksukortti.saldo());
    }

    @Test
    public void syoMaukkaastiKorttiOstoSummaVeloitetaanOikein() {
        kassapaate.syoMaukkaasti(maksukortti);
        // 10 euroa - 4.00 = 6.00
        assertEquals(600, maksukortti.saldo());
    }

    // palautetaan true kun korttiosto on onnistunut
    @Test
    public void syoEdullisestiKortillaRahatRiittaaPalauttaaTrue() {
        assertEquals(true, kassapaate.syoEdullisesti(maksukortti));
    }

    @Test
    public void syoMaukkaastiKortillaRahatRiittaaPalauttaaTrue() {
        assertEquals(true, kassapaate.syoMaukkaasti(maksukortti));
    }

    // Myytyjen lounaiden määrä kasvaa kun kortilla on rahaa
    @Test
    public void syoEdullisestiKortillaMyytyjenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKortillaMyytyjenLounaidenMaaraKasvaa() {
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    
    // jos kortilla ei rahaa...
    
    // ...kortin rahamäärä ei muutu
    
    @Test
    public void syoEdullisestiKortillaRahatEiRiitaSaldoEiMuutu() {
        kassapaate.syoMaukkaasti(maksukortti);
        kassapaate.syoMaukkaasti(maksukortti);
        // rahaa 10-8 eruoa = 2 euroa
        maksukortti.otaRahaa(100);
        // rahaa 1 euroa
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(100, maksukortti.saldo());
    }
    
    @Test
    public void syoMaukkaastiKortillaRahatEiRiitaSaldoEiMuutu() {
        kassapaate.syoMaukkaasti(maksukortti);
        kassapaate.syoMaukkaasti(maksukortti);
        // rahaa 10-8 eruoa = 2 euroa
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(200, maksukortti.saldo());
    }    
    
    
    //.. myytyjen lounaiden määrä on muuttumaton
    
    
    @Test
    public void syoEdullisestiKortillaRahatEiRiitaMyytyjenLounaidenMaaraEiMuutu() {
        maksukortti.otaRahaa(900);
        // rahaa 1 euro
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKortillaRahatEiRiitaMyytyjenLounaidenMaaraEiMuutu() {
        maksukortti.otaRahaa(800);
        // rahaa 2 euroa
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }    
    
    
    //... ja palautetaan false
    
    @Test
    public void syoEdullisestiRahatEiRiitaPalautaFalse() {
        maksukortti.otaRahaa(900);
        assertEquals(false, kassapaate.syoEdullisesti(maksukortti));
    }
    
    @Test
    public void syoMaukkaastiRahatEiRiitaPalautaFalse() {
        maksukortti.otaRahaa(800);
        assertEquals(false, kassapaate.syoMaukkaasti(maksukortti));
    }
    
    //... kassan rahamaara ei muutu kortilla ostettaessa
    
    @Test
    public void syoEdullisestiKortillaKassanRahamaaraMuuttumaton() {
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiKortillaKassanRahamaaraMuuttumaton() {
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());        
    }
    
    
    // kun kortille ladataan rahaa, kortin saldo muuttuu
    
    @Test
    public void kortilleLadataanRahaaSaldoMuuttuu() {
        kassapaate.lataaRahaaKortille(maksukortti, 100); // 1 euro
        assertEquals(1100, maksukortti.saldo());
    }
    
    @Test 
    public void kortilleLadattuArvoKasvattaaKassanRahamaaraa() {
        kassapaate.lataaRahaaKortille(maksukortti, 100);
        assertEquals(100100, kassapaate.kassassaRahaa());
    }
       
    // negatiivista summaa ei voi lisätä kortille
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        kassapaate.lataaRahaaKortille(maksukortti, -10); // -10 snt
        assertEquals(1000, maksukortti.saldo());
    }
    
    
    // Pääohjelman testaus
    
}
