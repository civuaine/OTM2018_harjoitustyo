package cloudsoft.domain;

import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;

import cloudsoft.dao.CloudDatabase;
import cloudsoft.dao.ObservationDatabase;
import cloudsoft.domain.ObservationDateCheck;
import cloudsoft.domain.CityCheck;
import cloudsoft.domain.Cloud;
import com.google.gson.Gson;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;

import java.util.List;

/**
 *
 * Sovelluslogiikasta vastaava luokka, jota käyttöliittymä kutsuu.
 *
 *
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

    /**
     * Metodi valmistelee tietokannat kutsumalla sopivasti muita metodeja, jotka
     * lisäävät testidataa tietokantaan.
     *
     *
     *
     */
    public void tietokannatKayttovalmiiksi() throws Exception {
        this.cloudDatabase = new CloudDatabase("jdbc:sqlite:Pilvitietokanta.db");
        this.observationDatabase = new ObservationDatabase("jdbc:sqlite:Havaintotietokanta.db");

        this.cloudDatabase.init();
        this.observationDatabase.init();

        if (observationDatabase.getAllObservations("paivays").isEmpty()) {
            this.observationDatabase.addData();
        }

        if (cloudDatabase.onTyhja()) {
            this.cloudDatabase.addData();
        }

    }

    /**
     * Metodi hakee tietokannasta kaikki havainnot ja palauttaa ne päivämäärän
     * mukaan järjestettynä (uusin ensin).
     *
     * @return Lista tietokannasta olevista havainnoista.
     * @throws Exception
     */
    public List<String> getHavainnotPaiva() throws Exception {
        List<String> havainnot = this.observationDatabase.getAllObservations("paivays");
        return havainnot;
    }

    /**
     * Metodi hakee tietokannasta kaikki havainnot ja palauttaa ne paikan mukaan
     * akkosjärjestettynä.
     *
     * @return Lista tietokannassa olevista havainnoista.
     * @throws Exception
     */
    public List<String> getHavainnotPaikka() throws Exception {
        List<String> havainnot = this.observationDatabase.getAllObservations("paikka");
        return havainnot;
    }

    /**
     * Metodi tarkistaa käyttäjän antaman paikkakunnan oikeellisuuden ja
     * kelpoisuuden ajamalla kolme erillistä testiä.
     *
     * @param pk paikkakunta
     * @return true, jos kaikki testit läpäisty, muuten false.
     */
    public boolean tarkistaPaikkakunta(String pk) {
        if (cc.paikkakuntaSisaltaaVainKirjaimia(pk) && cc.paikkakuntaOnOlemassa(pk) && cc.paikkakuntaEiTyhja(pk)) {
            String pienilla = pk.toLowerCase();
            this.cc.setPaikkakunta(pienilla.substring(0, 1).toUpperCase() + pienilla.substring(1));
            return true;
        }
        return false;
    }

    /**
     * Metodi tarkistaa käyttäjän syöttämän päivämäärän oikeellisuuden ajamalla
     * kolme erillistä testiä.
     *
     * @param pvm käyttäjän syöttämä päivämäärä
     * @return true, jos päivämäärä kelpaa ja on läpäissyt kaikki testit, muuten
     * false.
     */
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

    /**
     * Metodi tarkistaa kuinka monta päivää on havainnon ja havainnon
     * kirjaamispäivän välissä aikaa, jotta tiedetään, onko lähivuorokausien
     * sääennusteen antaminen järkevää.
     *
     * @return true, jos havainnosta kulunut alle 3 päivää
     */
    public boolean annetaankoLahivuorokausienEnnuste() {
        if (!odc.annetaankoEnnuste3vrkPaahan()) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * Metodi toimii merkkinä käyttöliittymässä, muuta merkitystä sillä ei ole.
     *
     * @return palauttaa aina true
     */
    public boolean paikkakuntaOikeinAnnettu() {
        return true;
    }

    /**
     * Metodi toimii merkkinä käyttöliittymässä, muuta merkitystä sillä ei ole.
     *
     * @return palauttaa aina true
     */
    public boolean paivamaaraOikeinAnnettu() {
        return true;
    }
    public String getPilvi() {
        return this.cloud.getPilvi();
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

    /**
     * Metodi kutsuu sopivasti muita metodeja, jotta käyttäjän tekemä havainto
     * voidaan tallentaa tietokantaan.
     *
     * @param pilvi käyttäjän havaitsema pilvi (kyselyn tulos)
     * @throws Exception
     */
    public void tallennaHavainto(String pilvi) throws Exception {

        int paiva = odc.getpv();
        int kk = odc.getkk();
        int vuosi = odc.getvvvv();
        
        String paivays = vuosi + "-" + kk + "-" + paiva;
        String paikka = this.cc.getPaikkakunta();
        observationDatabase.save(paikka, paivays, pilvi);
    }

    /**
     * Metodi noutaa havaitun pilven perusteella muita metodeja kutsumalla
     * tietokannasta pilveä vastaavan sääennusteen.
     *
     * @return Ennuste tekstimuodossa.
     * @throws Exception
     */
    public String noudaEnnustePilvenPerusteella() throws Exception {
        this.cloud.etsiPilvi();
        String pilvi = cloud.getPilvi();
        if (pilvi.equals("Pilveä ei löydy")) {
            return "Pilveä ei löydy tietokannasta antamillasi tiedoilla";
        } else {
            String ennuste = cloudDatabase.getInformation(pilvi);
            return ennuste;
        }
    }

    /**
     * Metodi hakee käyttäjän syöttämän paikkakunnan perusteella Yahoon
     * paikkakuntakohtaisen sääennusteen.
     *
     * @return sääennuste JSON-muodossa
     * @throws Exception
     */
    // yahoon säärajapinnan käyttö
    public String yahoowebservice() throws Exception { // annetaan paikkakunta metodiin muuttujana.
//        EI TOIMI VIELÄ. TOKAN TULOSTAMINEN EI ONNISTU.
//        String kaupunki = "Helsinki"; // paikkakunta sisältää vain kirjaimia (sisältö tarkistettu).
//        String baseURL = "https://query.yahooapis.com/v1/public/yql?q=";
//        String query = "select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=" + kaupunki + ")and u='C'";
//        String fullURLString = baseURL + URLEncoder.encode(query, "UTF-8") + "&format=json";
//        
//        URL fullURL = new URL(fullURLString);
//        System.out.println("eka");
//        InputStream is = fullURL.openStream();
//        System.out.println("toka");
//        BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
//        System.out.println("kolmas");
//        String line;
//        while ((line = br1.readLine()) != null) {
//            System.out.println(line);
//        }
//        br1.close();
//        // JSON parser --> GSON esimerkiksi
//        // lisätään tekstinä aluksi, ehkä myöhemmin kuvana, jos onnistuu
//        Gson gson = new Gson();
//        CloudSoftService tulos = gson.fromJson(line, CloudSoftService.class);
//        System.out.println(tulos);
//        

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
            //System.out.println(line);
            return line;
        }
        br.close();
        return null;
    }

    /**
     * Metodi ojentaa käyttöliittymälle sääennusteen sopivassa muodossa.
     *
     * @return sääennuste oikeassa muodossa
     * @throws Exception
     */
    public String tulostaEnnuste() throws Exception {
        String line = yahoowebservice();
        return line;
    }

}
