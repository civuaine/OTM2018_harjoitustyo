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

    // pilven ominaisuuksien setterit ja getterit ilman sanoja set ja get
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

    public void ukkostaa(boolean arvo) {
        this.ukkostaa = arvo;
    }

    // metodit sateen tyypille ja olomuodolle.
    // lisää myös falseominaisuudet mukaan vielä (jokaisella mahd.monta arvoa joita katsotaan)
    // lisää vielä pilven ulkonäölliset ja tarkentavat ominaisuudet
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
        } else if ((this.sataa == false) && (this.valkoinenPilvi == true) && (this.lapikuultava == true) && (this.iso == true) && (this.selvaRajainen == false)) {
            setPilvi("Cirrostratus");
        } else if ((this.sataa == true) && (this.valkoinenPilvi == false) && (this.iso == true) && (this.lapikuultava == true) && (this.selvaRajainen == false)) {
            setPilvi("Altostratus"); // voi sataa tai olla satamatta, iso tai melko iso
        } else if ((this.sataa == false) && (this.iso == true) && (this.lapikuultava == true) && (this.valkoinenPilvi == true)) {
            setPilvi("Altocumulus"); // valkoinen tai harmaa, koko vaihtelee
        } else if ((this.iso == true) && (this.selvaRajainen == true) && (this.valkoinenPilvi == true) && (this.sataa == false)) {
            setPilvi("Stratocumulus"); // sataa tai ei sada, harmaa tai valkoinen
        } else if ((this.sataa == false) && (this.iso == true) && (this.valkoinenPilvi == true) && (this.lapikuultava == true) && (this.selvaRajainen == false)) {
            setPilvi("Cirrocumulus"); //laaja tai melkolaaja
        }
    }
}
