package cloudsoft.domain;

/**
 *
 * Tämä luokka kuvaa yhtä pilveä ja sen ominaisuuksia.
 */
public class Cloud {

    private boolean sataa;
    private boolean iso;
    private boolean valkoinenPilvi;
    private boolean lapikuultava;
    private boolean selvaRajainen;
    private String ulkoNako;  // miten kysytään viimeinen asia.. Haetaanko tietokannasta ulko
    //näkötekstit ja käyttäjä valitsee vai...

    public Cloud() {

    }

    public void pilviSataa(boolean arvo) {
        this.sataa = arvo;
    }

    public void pilviOnIso(boolean arvo) {
        this.iso = arvo;
    }

    public void pilviOnValkoinen(boolean arvo) {
        this.valkoinenPilvi = arvo;
    }

    public void pilviOnLapikuultava(boolean arvo) {
        this.lapikuultava = arvo;
    }

    public void pilviOnSelvaRajainen(boolean arvo) {
        this.selvaRajainen = arvo;
    }

    public void pilvenUlkoNako() {
        this.ulkoNako = "jotain kivaa";
    }
}
