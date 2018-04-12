package CloudSoft.domain;

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
    
    
    public boolean paikkakuntaOnOlemassa(String havaintoPaikkakunta) {
        //Testataan löytyykö annettua paikkakuntaa IL:n tietokannasta
        return true;
    }
    
    public boolean paikkakuntaEiTyhja(String havaintoPaikkakunta) {
        if(havaintoPaikkakunta.isEmpty()) {
            return false;
        }
        return true;
    }

}
