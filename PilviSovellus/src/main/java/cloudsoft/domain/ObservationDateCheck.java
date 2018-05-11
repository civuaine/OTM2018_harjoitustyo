package cloudsoft.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

/**
 *
 * Luokka vastaa annetun päivämäärän muodon ja oikeellisuuden tarkastamisesta
 */
public class ObservationDateCheck {

    private int paivaTanaan;
    private int havaintoPaiva;
    private int kuukausiTanaan;
    private int havaintoKuukausi;
    private int vuosiTanaan;
    private int havaintoVuosi;
    private Date pvmTanaan;

    public ObservationDateCheck() {
        tanaanStringiksi();
    }

    public void setpv(int pv) {
        this.havaintoPaiva = pv;
    }

    public void setkk(int kk) {
        this.havaintoKuukausi = kk;
    }

    public void setvvvv(int vvvv) {
        this.havaintoVuosi = vvvv;
    }

    public int getpv() {
        return this.havaintoPaiva;
    }

    public int getkk() {
        return this.havaintoKuukausi;
    }

    public int getvvvv() {
        return this.havaintoVuosi;
    }

    public void setpvtanaan(int pv) {
        this.paivaTanaan = pv;
    }

    public void setkktanaan(int kk) {
        this.kuukausiTanaan = kk;
    }

    public void setvvvvtanaan(int vvvv) {
        this.vuosiTanaan = vvvv;
    }

    public int getpvtanaan() {
        return this.paivaTanaan;
    }

    public int getkktanaan() {
        return this.kuukausiTanaan;
    }

    public int getvvvvtanaan() {
        return this.vuosiTanaan;
    }

// muita settereitä ja gettereitä ei enää testata sillä nämäkin tuntuu toimivan    
    /**
     * Metodi muuttaa sovelluksen käynnistyshetken päivämäärän String-muotoon.
     */
    public void tanaanStringiksi() {
        //tänään    
        // Ei käyttäjän syötettä, joten ei tarvitse testata paivamaaraOnInteger-metodilla.
        SimpleDateFormat tanaan = new SimpleDateFormat("yyyy.MM.dd");
        Date pvmNyt = new Date();
        String nyt = tanaan.format(pvmNyt);

        List<String> nytLista = Arrays.asList(nyt.split("\\."));

        int vuosiNyt = Integer.parseInt(nytLista.get(0));
        int kkNyt = Integer.parseInt(nytLista.get(1));
        int pvNyt = Integer.parseInt(nytLista.get(2));

        this.pvmTanaan = new Date(vuosiNyt, kkNyt, pvNyt);
        this.paivaTanaan = pvNyt;
        this.kuukausiTanaan = kkNyt;
        this.vuosiTanaan = vuosiNyt;

    }

    /**
     * Metodi tarkistaa että käyttäjän syöttämän päivämäärän osat ovat varmasti
     * numeroita eikä kirjaimia.
     *
     * @param s päivämäärän osa (päivä, kuukausi tai vuosi)
     * @return true, jos kaikki päivämäärän osaset ovat numeroita
     */
    public boolean paivamaaraOnInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Metodi tarkistaa, että käyttäjä on antanut päivämäärän oikeassa muodossa,
     * oikeilla välimerkeillä eroteltuna. Päivämäärässä tulee olla oikea määrä
     * osia (päivä, kk, vuosi).
     *
     * @param annettupvm käyttäjän syöttämä päivämäärä
     * @return true, jos päivämäärä on annettu oikeassa muodossa ja sen kaikki
     * osat ovat numeroita.
     */
    public boolean paivamaaranMuotoTarkistin(String annettupvm) {
        // tarkistetaan päivämäärän oikea muoto --> kolme osaa, jotka kaikki on Integer (ei stringejä)
        List<String> annettupvmLista = Arrays.asList(annettupvm.split("\\."));

        if (paivamaaraOnInteger(annettupvmLista.get(0)) && paivamaaraOnInteger(annettupvmLista.get(1)) && paivamaaraOnInteger(annettupvmLista.get(2))) {
            setvvvv(Integer.parseInt(annettupvmLista.get(2)));
            setkk(Integer.parseInt(annettupvmLista.get(1)));
            setpv(Integer.parseInt(annettupvmLista.get(0)));
            return true;
        } else {
            return false;
        }
    }

    /**
     * Metodi tarkistaa, että päivämäärän suuruusluokka on järkevä
     *
     * @return true,jos suuruusluokka on järkevä (EI saa olla esim
     * tulevaisuudessa)
     */
    public boolean paivamaaraJarkeva() {

        if (this.havaintoVuosi > 2018 || this.havaintoVuosi < 1950) {
            return false;
        } else if (this.havaintoKuukausi < 1 || this.havaintoKuukausi > 12) {
            return false;
        } else if (this.havaintoPaiva < 1 || this.havaintoPaiva > 31) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Metodi tarkistaa, että käyttäjän syöttämä havaintopäivämäärä ei ole
     * tulevaisuudessa
     *
     * @return true, jos päiväys EI ole tulevaisuudessa, eli on hyväksyttävä
     */
    public boolean havaintoEiTulevaisuudessa() {
        Date pvmhavainto = new Date(this.havaintoVuosi, this.havaintoKuukausi, this.havaintoPaiva);

        if (pvmhavainto.after(this.pvmTanaan)) {
            return false;
        } else {
            return true; // true kun havaintopäivä EI tulevaisuudessa eli OLTAVA tätä päivää ennen.
        }
    }

    /**
     * Metodi tarkistaa kuinka monta päivää sovelluksen käynnistämishetkestä on
     * havaintopäivään ja päättää sen perusteella onko 7 vrk ennusteen antaminen
     * järkevää.
     *
     * @return true, jos "tämän" päivän ja havaintopäivän välissä on korkeintaan
     * 3 päivää välissä
     */
    public boolean annetaankoEnnuste3vrkPaahan() {
        if ((this.vuosiTanaan == this.havaintoVuosi) && (this.kuukausiTanaan == this.havaintoKuukausi)) {
            if ((this.paivaTanaan - this.havaintoPaiva) <= 3) {
                return true;
            }

        }
        return false;
    }
}
