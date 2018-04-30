package cloudsoft.domain;

import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

import cloudsoft.dao.CloudDatabase;
import cloudsoft.dao.ObservationDatabase;
import cloudsoft.domain.ObservationDateCheck;
import cloudsoft.domain.CityCheck;
import cloudsoft.domain.Cloud;
import java.net.URL;
import java.net.URLEncoder;
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

    public void setSade(boolean arvo) {
        this.cloud.setPilviSataa(arvo);
    }

    public boolean getSade() {
        return this.cloud.getPilvisataa();
    }

    public void setKoko(boolean arvo) {
        this.cloud.setPilviOnIso(arvo);
    }

    public boolean getKoko() {
        return this.cloud.getPilviOnIso();
    }

    public void setVari(boolean arvo) {
        this.cloud.setPilviOnValkoinen(arvo);
    }

    public boolean getVari() {
        return this.cloud.getPilviOnValkoinen();
    }

    public void setLapikuultava(boolean arvo) {
        this.cloud.setPilviOnLapikuultava(arvo);
    }

    public boolean getLapikuultava() {
        return this.cloud.getPilviOnLapikuultava();
    }

    public void setSelvarajainen(boolean arvo) {
        this.cloud.setPilviOnSelvaRajainen(arvo);
    }

    public boolean getSelvarajainen() {
        return this.cloud.getPilviOnSelvarajainen();
    }

    public void tallennaHavainto(String pilvi) throws Exception {

        int paiva = odc.getpv();
        int kk = odc.getkk();
        int vuosi = odc.getvvvv();
        Date paivays = new Date(vuosi, kk, paiva);
        String paikka = this.cc.getPaikkakunta();

        observationDatabase.save(paikka, paivays, pilvi);
    }

    public String noudaEnnustePilvenPerusteella() throws Exception {
//        System.out.println("Sade: " + this.cloud.getPilvisataa());
//        System.out.println("Koko: " + this.cloud.getPilviOnIso());
//        System.out.println("Väri: " + this.cloud.getPilviOnValkoinen());
//        System.out.println("Läpikuultavuus: " + this.cloud.getPilviOnLapikuultava());
//        System.out.println("Selvärajaisuus: " + this.cloud.getPilviOnSelvarajainen());

        this.cloud.etsiPilvi();

        String pilvi = cloud.getPilvi();
        //System.out.println("ennen if ja else:" + pilvi);

        if (pilvi.equals("Pilveä ei löydy")) {
            return "Pilveä ei löydy tietokannasta antamillasi tiedoilla";
        } else {
            String ennuste = cloudDatabase.getInformation(pilvi);
            return ennuste;
        }
    }

    // yahoon säärajapinnan käyttö
    public void yahoowebservice() throws Exception { // annetaan paikkakunta metodiin muuttujana.
//
//        String kaupunki = "Helsinki"; // paikkakunta sisältää vain kirjaimia (sisältö tarkistettu).
//        String baseURL = "https://query.yahooapis.com/v1/public/yql?q=";
//        String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=" + kaupunki + ")and u='C'";
//        String fullURLString = baseURL + URLEncoder.encode(query, "UTF-8") + "&format=json";
//        
//        URL fullURL = new URL(fullURLString);
//        InputStream is = fullURL.openStream();
        
        // JSON parser --> GSON esimerkiksi
        
        
        
        
        String request = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Helsinki%22)and%20u%3D'c'&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(request);

        // Send GET request
        int statusCode = client.executeMethod(method);

        if (statusCode != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + method.getStatusLine());
        }
        InputStream rstream = null;

        // Get the response body
        rstream = method.getResponseBodyAsStream();

        // Process the response from Yahoo! Web Services
        BufferedReader br = new BufferedReader(new InputStreamReader(rstream));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }

}
