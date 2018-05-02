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
    private boolean ukkostaa;
    private String ulkoNako;  // miten kysytään viimeinen asia.. Haetaanko tietokannasta ulko
    //näkötekstit ja käyttäjä valitsee vai...
    private String pilvi;

    public Cloud() {
        this.pilvi = "Pilveä ei löydy";
    }

    public String getPilvi() {
        return this.pilvi;
    }

    public void setPilvi(String nimi) {
        this.pilvi = nimi;
    }

    //setterit ja getterit
    public void setPilviSataa(boolean arvo) {
        this.sataa = arvo;
    }

    public boolean getPilvisataa() {
        return this.sataa;
    }

    public void setPilviOnIso(boolean arvo) {
        this.iso = arvo;
    }

    public boolean getPilviOnIso() {
        return this.iso;
    }

    public void setPilviOnValkoinen(boolean arvo) {
        this.valkoinenPilvi = arvo;
    }

    public boolean getPilviOnValkoinen() {
        return this.valkoinenPilvi;
    }

    public void setPilviOnLapikuultava(boolean arvo) {
        this.lapikuultava = arvo;
    }

    public boolean getPilviOnLapikuultava() {
        return this.lapikuultava;
    }

    public void setPilviOnSelvaRajainen(boolean arvo) {
        this.selvaRajainen = arvo;
    }

    public boolean getPilviOnSelvarajainen() {
        return this.selvaRajainen;
    }

    public void setPilvenUlkoNako(String arvo) {
        this.ulkoNako = arvo;
    }

    public String getPilvenUlkoNako() {
        return this.ulkoNako;
    }

    public void setUkkostaa(boolean arvo) {
        this.ukkostaa = arvo;
    }

    public boolean getUkkostaa() {
        return this.ukkostaa;
    }

    // metodit sateen tyypille ja olomuodolle.
    // lisää myös falseominaisuudet mukaan vielä (jokaisella mahd.monta arvoa joita katsotaan)
    // lisää vielä pilven ulkonäölliset ja tarkentavat ominaisuudet
    /**
     * Metodi iteroi käyttäjän syötteen perusteella, mikä havaittu pilvi voisi
     * olla.
     */
    public void etsiPilvi() {
        if ((this.sataa == true) && (this.iso == true) && (this.valkoinenPilvi == false) && (this.lapikuultava == false) && (this.selvaRajainen == false)) {
            setPilvi("Nimbostratus");
        } else if ((this.sataa == true) && (this.ukkostaa == true) && (this.valkoinenPilvi == true)) {
            setPilvi("Cumulonimbus");
        } else if ((this.iso == false) && (this.selvaRajainen == true) && (this.valkoinenPilvi == true)) {
            setPilvi("Cumulus"); // voi sataa tai olla satamatta
        } else if ((this.sataa == false) && (this.iso == true) && (this.valkoinenPilvi == false) && (this.selvaRajainen == false)) {
            setPilvi("Stratus"); // voi sataa tai olla satamatta
        } else if ((this.sataa == false) && (this.valkoinenPilvi == true) && (this.lapikuultava == true) && (this.selvaRajainen == false) && (this.iso == false)) {
            setPilvi("Cirrus");
        } else if ((this.sataa == false) && (this.valkoinenPilvi == true) && (this.lapikuultava == true) && (this.iso == true) && (this.selvaRajainen == false) && (this.getPilvenUlkoNako().equals("tasainen"))) {
            setPilvi("Cirrostratus");
        } else if ((this.sataa == true) && (this.valkoinenPilvi == false) && (this.iso == true) && (this.lapikuultava == true) && (this.selvaRajainen == false)) {
            setPilvi("Altostratus"); // voi sataa tai olla satamatta, iso tai melko iso
        } else if ((this.sataa == false) && (this.iso == true) && (this.lapikuultava == true) && (this.valkoinenPilvi == true) && (this.selvaRajainen == true)) {
            setPilvi("Altocumulus"); // valkoinen tai harmaa, koko vaihtelee
        } else if ((this.iso == true) && (this.selvaRajainen == true) && (this.valkoinenPilvi == true) && (this.sataa == false)) {
            setPilvi("Stratocumulus"); // sataa tai ei sada, harmaa tai valkoinen
        } else if ((this.sataa == false) && (this.iso == true) && (this.valkoinenPilvi == true) && (this.lapikuultava == true) && (this.selvaRajainen == false) && (this.getPilvenUlkoNako().equals("palleroinen"))) {
            setPilvi("Cirrocumulus"); //laaja tai melkolaaja
        }
    }
}
