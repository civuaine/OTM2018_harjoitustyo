package CloudSoft.domain;

import CloudSoft.dao.CloudDatabase;
import CloudSoft.dao.ObservationDatabase;
import CloudSoft.domain.ObservationDateCheck;
import CloudSoft.domain.CityCheck;
import CloudSoft.domain.Cloud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

/**
 *
 * Sovelluslogiikasta vastaava luokka.
 */
public class CloudSoftService {

    private CityCheck CC;
    private ObservationDateCheck ODC;
    private Cloud cloud;
    private CloudDatabase cloudDatabase;
    private ObservationDatabase observationDatabase;

    public CloudSoftService() {
        this.ODC = new ObservationDateCheck();
        this.CC = new CityCheck();
        this.cloud = new Cloud();

    }

    
    public void tietokannatKayttovalmiiksi() throws ClassNotFoundException {
                
        this.cloudDatabase = new CloudDatabase("jdbc:sqlite:Pilvitietokanta.db");
        this.observationDatabase = new ObservationDatabase("jdbc:sqlite:Havaintotietokanta.db");
        
        this.cloudDatabase.init();
        this.observationDatabase.init();
        
        this.cloudDatabase.addData();
        this.observationDatabase.addData();
        
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

//    public void mikaPilviOnKyseessa() {
//        if (cloud.sataako == true)
//        if (cloud.koko == false) jne..
//    }
}
