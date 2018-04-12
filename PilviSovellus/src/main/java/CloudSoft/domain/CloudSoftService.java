package CloudSoft.domain;

import CloudSoft.domain.ObservationDateCheck;
import CloudSoft.domain.CityCheck;

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

    private CityCheck CC;
    private ObservationDateCheck ODC;

    public CloudSoftService() {
        this.ODC = new ObservationDateCheck();
        this.CC = new CityCheck();
    }

    public boolean tarkistaPaikkakunta(String pk) {
        if (CC.paikkakuntaSisaltaaVainKirjaimia(pk) && CC.paikkakuntaOnOlemassa(pk) && CC.paikkakuntaEiTyhja(pk)) {
            return true;
        }
        return false;
    }

    public boolean tarkistaPaivamaara(String pvm) {
        ODC.tanaanStringiksi();

        boolean a = ODC.paivamaaranMuotoTarkistin(pvm);
        boolean b = ODC.paivamaaraJarkeva();
        boolean c = ODC.havaintoEiTulevaisuudessa();
        if (a && b && c) {
            return true;
        } else {
            return false;
        }
    }

    public boolean annetaankoLahivuorokausienEnnuste() {
        if (!ODC.annetaankoEnnuste3vrkPaahan()) {
            return false;
        } else {
            return true;
        }

    }

    public boolean paikkakuntaOikeinAnnettu() {
        return true;
    }

    public boolean paivamaaraOikeinAnnettu() {
        return true;
    }

}
