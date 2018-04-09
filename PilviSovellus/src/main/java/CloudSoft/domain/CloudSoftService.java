package CloudSoft.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

/**
 *
 * Sovelluslogiikasta vastaava luokka
 */
public class CloudSoftService {

    private String havaintoPaikkakunta;
    private int paivaTanaan;
    private int havaintoPaiva;
    private int kuukausiTanaan;
    private int havaintoKuukausi;
    private int vuosiTanaan;
    private int havaintoVuosi;

    public CloudSoftService() {
        //tyhjä konstruktori
    }

    public boolean paivamaaraTarkistin(String annettupvm) {
        // tarkistetaan päivämäärän oikeellisuus ja annetaan ilmoitus sen mukaan
        //tänään    
        SimpleDateFormat tanaan = new SimpleDateFormat("yyyy/MM/dd");
        Date pvmNyt = new Date();
        String nyt = tanaan.format(pvmNyt);

        
        List<String> nytLista = Arrays.asList(nyt.split("/"));

        int vuosiNyt = Integer.parseInt(nytLista.get(0));
        int kkNyt = Integer.parseInt(nytLista.get(1));
        int pvNyt = Integer.parseInt(nytLista.get(2));
        this.paivaTanaan = pvNyt;
        this.kuukausiTanaan = kkNyt;
        this.vuosiTanaan = vuosiNyt;

        //annettu päivämäärä    
        int vuosi = 0;
        int kk = 0;
        int pv = 0;

        List<String> annettupvmLista = Arrays.asList(annettupvm.split("."));
        System.out.println(annettupvm);
        
        vuosi = Integer.parseInt(annettupvmLista.get(0));
        System.out.println(vuosi);
        kk = Integer.parseInt(annettupvmLista.get(1));
        System.out.println(kk);
        pv = Integer.parseInt(annettupvmLista.get(2));
        System.out.println(pv);
//        try {
//            vuosi = Integer.parseInt(annettupvmLista.get(0));
//            kk = Integer.parseInt(annettupvmLista.get(1));
//            pv = Integer.parseInt(annettupvmLista.get(2));
//            
//        } catch (Exception e) {
//            return false;
//        }

        // annetun päivämäärän oikea järjestys ja järkevä suuruusluokka
        if (vuosi > 2018 || vuosi < 1950) {
            return false;
        } else if (kk < 1 || kk > 12) {
            return false;
        } else if (pv < 28 || pv > 31) {
            return false;
        }
        Date pvmhavainto = new Date(vuosi, kk, pv);

        if (pvmNyt.before(pvmhavainto)) {
            return false;
        }
        this.havaintoPaiva = pv;
        this.havaintoKuukausi = kk;
        this.havaintoVuosi = vuosi;
        return true;

    }

    public void havaintoPaikkakunta(String havaintoPaikkakunta) {
        // tallennetaan havaintopaikkakunta muistiin IL:n dataa varten.
        System.out.println("Havaintopaikkakunta: " + havaintoPaikkakunta);
        this.havaintoPaikkakunta = havaintoPaikkakunta;
    }

    public boolean annetaankoEnnuste() {
        if ((this.vuosiTanaan >= this.havaintoVuosi) && (this.kuukausiTanaan >= this.havaintoKuukausi)) {
            if ((this.paivaTanaan - this.havaintoPaiva) <= 3) {
                return true;
            }
            
        }
        return false;
    }
}
