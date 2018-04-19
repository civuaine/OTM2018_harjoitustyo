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

    public void tanaanStringiksi() {
        //tänään    
        // Ei käyttäjän syötettä, joten ei tarvitse testata paivamaaraOnInteger-metodilla.
        SimpleDateFormat tanaan = new SimpleDateFormat("yyyy/MM/dd");
        Date pvmNyt = new Date();
        String nyt = tanaan.format(pvmNyt);

        List<String> nytLista = Arrays.asList(nyt.split("/"));

        int vuosiNyt = Integer.parseInt(nytLista.get(0));
        int kkNyt = Integer.parseInt(nytLista.get(1));
        int pvNyt = Integer.parseInt(nytLista.get(2));

        this.pvmTanaan = new Date(vuosiNyt, kkNyt, pvNyt);
        this.paivaTanaan = pvNyt;
        this.kuukausiTanaan = kkNyt;
        this.vuosiTanaan = vuosiNyt;

    }

    public boolean paivamaaraOnInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;

        // s.matches("-?\\d") // negative sign none or one.
        // s.matches("^[+-]?\\d+$")
    }

    public boolean paivamaaranMuotoTarkistin(String annettupvm) {
        // tarkistetaan päivämäärän oikea muoto --> kolme osaa, jotka kaikki on Integer (ei stringejä)
        List<String> annettupvmLista = Arrays.asList(annettupvm.split("/"));

        if (paivamaaraOnInteger(annettupvmLista.get(0)) && paivamaaraOnInteger(annettupvmLista.get(1)) && paivamaaraOnInteger(annettupvmLista.get(2))) {
            setvvvv(Integer.parseInt(annettupvmLista.get(2)));
            setkk(Integer.parseInt(annettupvmLista.get(1)));
            setpv(Integer.parseInt(annettupvmLista.get(0)));
            return true;
        } else {
            return false;
        }
    }

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

    public boolean havaintoEiTulevaisuudessa() {
        Date pvmhavainto = new Date(this.havaintoVuosi, this.havaintoKuukausi, this.havaintoPaiva);

        if (pvmhavainto.after(this.pvmTanaan)) {
            return false;
        } else {
            return true; // true kun havaintopäivä EI tulevaisuudessa eli OLTAVA tätä päivää ennen.
        }
    }

    public boolean annetaankoEnnuste3vrkPaahan() {
        if ((this.vuosiTanaan == this.havaintoVuosi) && (this.kuukausiTanaan == this.havaintoKuukausi)) {
            if ((this.paivaTanaan - this.havaintoPaiva) <= 3) {
                return true;
            }

        }
        return false;
    }
}
