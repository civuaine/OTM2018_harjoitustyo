
package CloudSoft.domain;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * Sovelluslogiikasta vastaava luokka
 */

public class CloudSoftService {
    private String havaintoPaikkakunta;
    
    
    
    public CloudSoftService() {
        
    }
    
    public boolean paivamaaraTarkistin(String annettupvm) {
        // tarkistetaan päivämäärän oikeellisuus ja annetaan ilmoitus sen mukaan
        return true;
    }
    
    public void havaintoPaikkakunta(String havaintoPaikkakunta) {
        // tallennetaan havaintopaikkakunta muistiin.
        this.havaintoPaikkakunta = havaintoPaikkakunta;
    }
}
