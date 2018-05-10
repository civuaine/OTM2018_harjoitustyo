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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
    
    public void setUkkostaa(boolean arvo) {
        this.cloud.setUkkostaa(arvo);
    }

    public boolean getUkkostaa() {
        return this.cloud.getUkkostaa();
    }

    public void setVesiTaiLumi(boolean arvo) {
        this.cloud.setVesiTaiLumi(arvo);
    }

    public boolean getVesiTaiLumi() {
        return this.cloud.getVesiTaiLumi();
    }

    public void setsataaRakeita(boolean arvo) {
        this.cloud.setsataaRakeita(arvo);
    }

    public boolean getsataaRakeita() {
        return this.cloud.getsataaRakeita();
    }

    public void setMuuSateenOlomuoto(boolean arvo) {
        this.cloud.setMuuSateenOlomuoto(arvo);
    }

    public boolean getMuuSateenOlomuoto() {
        return this.cloud.getMuuSateenOlomuoto();
    }

    public void setKuurottainenSade(boolean arvo) {
        this.cloud.setKuurottainenSade(arvo);
    }

    public boolean getKuurottainenSade() {
        return this.cloud.getKuurottainenSade();
    }

    public void setVoimakasSade(boolean arvo) {
        this.cloud.setVoimakasSade(arvo);
    }

    public boolean getVoimakasSade() {
        return this.cloud.getVoimakasSade();
    }

    public void setTummaPohjaValkoinenTorni(boolean arvo) {
        this.cloud.setTummaPohjaValkoinenTorni(arvo);
    }

    public boolean getTummaPohjaValkoinenTorni() {
        return this.cloud.getTummaPohjaValkoinenTorni();
    }

    public void setSolumainen(boolean arvo) {
        this.cloud.setSolumainen(arvo);
    }

    public boolean getSolumainen() {
        return this.cloud.getSolumainen();
    }

    public void setIsoSolu(boolean arvo) {
        this.cloud.setIsoSolu(arvo);
    }

    public boolean getIsoSolu() {
        return this.cloud.getIsoSolu();
    }

    public void setAaltomainen(boolean arvo) {
        this.cloud.setAaltomainen(arvo);
    }

    public boolean getAaltomainen() {
        return this.cloud.getAaltomainen();
    }

    public void setIsoAalto(boolean arvo) {
        this.cloud.setIsoAalto(arvo);
    }

    public boolean getIsoAalto() {
        return this.cloud.getIsoAalto();
    }

    public void setHarsomainenJaTaiSaikeinen(boolean arvo) {
        this.cloud.setHarsomainenJaTaiSaikeinen(arvo);
    }

    public boolean getHarsomainenJaTaiSaikeinen() {
        return this.cloud.getHarsomainenJaTaiSaikeinen();
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
        String kaupunki = "helsinki"; // paikkakunta sisältää vain kirjaimia (sisältö tarkistettu).
        String baseURL = "https://query.yahooapis.com/v1/public/yql?q=";
        String query = "select item.forecast from weather.forecast where woeid in (select woeid from geo.places(1) where text='" + kaupunki + "')and u='c'";
        String fullURLString = baseURL + URLEncoder.encode(query, "UTF-8") + "&format=json";
//        String request = baseURL + URLEncoder.encode(query, "UTF-8") + "&format=json";  

        URL fullURL = new URL(fullURLString);
        InputStream is = fullURL.openStream();
        BufferedReader br1 = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br1.readLine()) != null) {
            System.out.println(line);
            return line;
        }
        br1.close();
        return line;
        // JSON parser --> GSON esimerkiksi
        // lisätään tekstinä aluksi, ehkä myöhemmin kuvana, jos onnistuu
    }

//    public String parse(String jsonLine) {
//        JsonElement jelement = new JsonParser().parse(jsonLine);
//        JsonObject jobject = jelement.getAsJsonObject();
//        jobject = jobject.getAsJsonObject("data");
//        JsonArray jarray = jobject.getAsJsonArray("translations");
//        jobject = jarray.get(0).getAsJsonObject();
//        String result = jobject.get("translatedText").getAsString();
//        return result;
//    }
    public void parse (String jsonLine) {
        Gson gson = new Gson();
        CloudSoftService css = gson.fromJson(jsonLine, CloudSoftService.class);
    }
        /**
         * Metodi ojentaa käyttöliittymälle sääennusteen sopivassa muodossa.
         *
         * @return sääennuste oikeassa muodossa
         * @throws Exception
         */
    public String tulostaEnnuste() throws Exception {
        String line = yahoowebservice();
        //String parseLine = parse(line);
        return line; // normi line toimii
    }

}
