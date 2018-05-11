package cloudsoft.domain;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.Normalizer;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Tämä luokka vastaa kaikesta paikkakuntaan tai kaupunkiin liittyvästä
 * tarkastamisesta.
 */
public class CityCheck {

    private String havaintoPaikkakunta;

    public CityCheck() {
        // tyhjä konstrukotri
    }

    public String getPaikkakunta() {
        return this.havaintoPaikkakunta;
    }

    public void setPaikkakunta(String pk) {
        this.havaintoPaikkakunta = pk;
    }

    /**
     * Metodi tarkistaa, että käyttäjän syöte sisältää vain kirjaimia, eikä
     * mitään muita merkkejä.
     *
     * @param havaintoPaikkakunta käyttäjän syöttämä paikkakunta
     * @return true, jos käyttäjän syöte sisältää vain kirjaimia. Muuten false.
     */
    public boolean paikkakuntaSisaltaaVainKirjaimia(String havaintoPaikkakunta) {
        // tallennetaan havaintopaikkakunta muistiin IL:n dataa varten.

        char[] kirjaimet = havaintoPaikkakunta.toCharArray();

        for (char k : kirjaimet) {
            if (!Character.isLetter(k)) {
                return false;
            }
        }

        this.havaintoPaikkakunta = havaintoPaikkakunta;
        return true;
    }

    /**
     * Metodi tarkistaa Yahoon palvelusta, että käyttäjän syöttämä paikkakunta
     * on olemassa.
     *
     * @param havaintoPaikkakunta käyttäjän syöttämä paikkaunta
     * @return true, jos paikkakunta löytyy Yahoon tietokannasta
     */
    public boolean paikkakuntaOnOlemassa(String havaintoPaikkakunta) {
        try {
            //Testataan löytyykö annettua paikkakuntaa Yahoo!:n tietokannasta
            String pienilla = havaintoPaikkakunta.toLowerCase();
            pienilla = pienilla.replace('ä', 'a');
            pienilla = pienilla.replace('ö','o');
            boolean onkoOlemassa = yahoowebservice(havaintoPaikkakunta);
            return onkoOlemassa;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Metodi tarkistaa, että käyttäjän syöttämä paikkakunta ei ole tyhjä tai
     * täynnä välilyöntejä.
     *
     * @param havaintoPaikkakunta käyttäjän syöttämä paikkakunta
     * @return true, jos paikkakunta EI sisällä välilyöntejä tai ole tyhjä
     */
    public boolean paikkakuntaEiTyhja(String havaintoPaikkakunta) {
        if (havaintoPaikkakunta.isEmpty()) {
            return false;
        } else if (havaintoPaikkakunta.trim().isEmpty()) {
            return false;
        }
        return true;
    }
    
        /**
     * Metodi tarkistaa käyttäjän syöttämän kaupungin olemassaolon
     * yahoon web-palvelimelta.
     *
     * @param kaupunki käyttäjän syöttämä kaupunki
     * @return true, jos kaupunki löytyy ja false, jos ei tai nettiyhteys ei toimi.
     * @throws Exception
     */
    // yahoon säärajapinnan käyttö
    public boolean yahoowebservice(String kaupunki) throws Exception { // annetaan paikkakunta metodiin muuttujana.
        //String kaupunki = "helsinki"; // paikkakunta sisältää vain kirjaimia (sisältö tarkistettu).
        String baseURL = "https://query.yahooapis.com/v1/public/yql?q=";
        String query = "select item.forecast from weather.forecast where woeid in (select woeid from geo.places(1) where text='" + kaupunki + "')and u='c'";
        String fullURLString = baseURL + URLEncoder.encode(query, "UTF-8") + "&format=json";
//        String request = baseURL + URLEncoder.encode(query, "UTF-8") + "&format=json";  

        URL fullURL = new URL(fullURLString);
        InputStream is = fullURL.openStream();
        try (BufferedReader br1 = new BufferedReader(new InputStreamReader(is))) {
            String line;
            String search = "null";
            while ((line = br1.readLine()) != null) {
                //System.out.println(line);
                if(line.contains(search)) {
                    return false;
                }
                return true;
                
            }
        }
        return false;
        // JSON parser --> GSON esimerkiksi
        // lisätään tekstinä aluksi, ehkä myöhemmin kuvana, jos onnistuu
    }

}
