package cloudsoft.domain;

import cloudsoft.dao.CloudDatabase;
import cloudsoft.dao.ObservationDatabase;
import cloudsoft.domain.ObservationDateCheck;
import cloudsoft.domain.CityCheck;
import cloudsoft.domain.Cloud;
import java.sql.Date;

import java.util.List;


/**
 *
 * Sovelluslogiikasta vastaava luokka.
 */
public class CloudSoftService {

    private CityCheck cc;
    private ObservationDateCheck odc;
    private Cloud cloud;
    private CloudDatabase cloudDatabase;
    private ObservationDatabase observationDatabase;

    public CloudSoftService() {
        this.odc = new ObservationDateCheck();
        this.cc = new CityCheck();
        this.cloud = new Cloud();

    }

    public void tietokannatKayttovalmiiksi() throws Exception {
        this.cloudDatabase = new CloudDatabase("jdbc:sqlite:Pilvitietokanta.db");
        this.observationDatabase = new ObservationDatabase("jdbc:sqlite:Havaintotietokanta.db");

        this.cloudDatabase.init();
        this.observationDatabase.init();

        if (observationDatabase.getAllByDate().isEmpty()) {
            this.observationDatabase.addData();
        }

        if (cloudDatabase.onTyhja()) {
            this.cloudDatabase.addData();
        }

    }

    public List<String> getHavainnotPaiva() throws Exception {
        List<String> havainnot = this.observationDatabase.getAllByDate();
        return havainnot;
    }

    public List<String> getHavainnotPaikka() throws Exception {
        List<String> havainnot = this.observationDatabase.getAllByCity();
        return havainnot;
    }

    public boolean tarkistaPaikkakunta(String pk) {
        if (cc.paikkakuntaSisaltaaVainKirjaimia(pk) && cc.paikkakuntaOnOlemassa(pk) && cc.paikkakuntaEiTyhja(pk)) {
            String pienilla = pk.toLowerCase();
            this.cc.setPaikkakunta(pienilla.substring(0, 1).toUpperCase() + pienilla.substring(1));
            return true;
        }
        return false;
    }

    public boolean tarkistaPaivamaara(String pvm) {
        boolean a = odc.paivamaaranMuotoTarkistin(pvm);
        boolean b = odc.paivamaaraJarkeva();
        boolean c = odc.havaintoEiTulevaisuudessa();            
        if (a && b && c) {
            return true;
        } else {
            return false;
        }
    }

    public boolean annetaankoLahivuorokausienEnnuste() {
        if (!odc.annetaankoEnnuste3vrkPaahan()) {
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

    public void tallennaHavainto(String pilvi) throws Exception {
        
        int paiva = odc.getpv();
        int kk = odc.getkk();
        int vuosi = odc.getvvvv();
        Date paivays = new Date(vuosi,kk,paiva);
        String paikka = this.cc.getPaikkakunta();
        
        observationDatabase.save(paikka, paivays, pilvi);
    }
    
    public String etsiPilvi() throws Exception{
        String pilvi = cloud.getPilvi();
        if(pilvi.equals("Pilveä ei löydy")) {
            return "Pilveä ei löydy tietokannasta antamillasi tiedoilla";
        } else {
            String ennuste = cloudDatabase.getInformation(pilvi);
            return ennuste;
        }
    }
    
}
