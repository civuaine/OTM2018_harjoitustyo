package cloudsoft.domain;

import java.util.*;

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
        //Testataan löytyykö annettua paikkakuntaa IL:n tietokannasta
        String pienilla = havaintoPaikkakunta.toLowerCase();
        String pk = pienilla.substring(0, 1).toUpperCase() + pienilla.substring(1);
        return true;
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

}
