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
    //private String ulkoNako;  // miten kysytään viimeinen asia.. Haetaanko tietokannasta ulko
    //näkötekstit ja käyttäjä valitsee vai...
    private String pilvi;
    private boolean sataaVettaTaiLunta;
    private boolean sataaRakeita;
    private boolean muuSateenOlomuoto;
    private boolean kuurottainenSade;
    private boolean voimakasSade;
    private boolean tummaPohjaValkoinenTorni;
    private boolean solumainen;
    private boolean isoSolu;
    private boolean aaltomainen;
    private boolean isoAalto;
    private boolean harsomainenJaTaiSaikeinen;
    private boolean kukkakaalimainen;
    private boolean korkeaPilvi;
    private boolean sumuinen;
    private boolean haloja;

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

    public void setPilviOnKorkea(boolean arvo) {
        this.korkeaPilvi = arvo;
    }

    public boolean getPilviOnKorkea() {
        return this.korkeaPilvi;
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

//    public void setPilvenUlkoNako(String arvo) {
//        this.ulkoNako = arvo;
//    }
//
//    public String getPilvenUlkoNako() {
//        return this.ulkoNako;
//    }
    public void setUkkostaa(boolean arvo) {
        this.ukkostaa = arvo;
    }

    public boolean getUkkostaa() {
        return this.ukkostaa;
    }

    public void setVesiTaiLumi(boolean arvo) {
        this.sataaVettaTaiLunta = arvo;
    }

    public boolean getVesiTaiLumi() {
        return this.sataaVettaTaiLunta;
    }

    public void setsataaRakeita(boolean arvo) {
        this.sataaRakeita = arvo;
    }

    public boolean getsataaRakeita() {
        return this.sataaRakeita;
    }

    public void setMuuSateenOlomuoto(boolean arvo) {
        this.muuSateenOlomuoto = arvo;
    }

    public boolean getMuuSateenOlomuoto() {
        return this.muuSateenOlomuoto;
    }

    public void setKuurottainenSade(boolean arvo) {
        this.kuurottainenSade = arvo;
    }

    public boolean getKuurottainenSade() {
        return this.kuurottainenSade;
    }

    public void setVoimakasSade(boolean arvo) {
        this.voimakasSade = arvo;
    }

    public boolean getVoimakasSade() {
        return this.voimakasSade;
    }

    public void setTummaPohjaValkoinenTorni(boolean arvo) {
        this.tummaPohjaValkoinenTorni = arvo;
    }

    public boolean getTummaPohjaValkoinenTorni() {
        return this.tummaPohjaValkoinenTorni;
    }

    public void setSolumainen(boolean arvo) {
        this.solumainen = arvo;
    }

    public boolean getSolumainen() {
        return this.solumainen;
    }

    public void setIsoSolu(boolean arvo) {
        this.isoSolu = arvo;
    }

    public boolean getIsoSolu() {
        return this.isoSolu;
    }

    public void setAaltomainen(boolean arvo) {
        this.aaltomainen = arvo;
    }

    public boolean getAaltomainen() {
        return this.aaltomainen;
    }

    public void setIsoAalto(boolean arvo) {
        this.isoAalto = arvo;
    }

    public boolean getIsoAalto() {
        return this.isoAalto;
    }

    public void setHarsomainenJaTaiSaikeinen(boolean arvo) {
        this.harsomainenJaTaiSaikeinen = arvo;
    }

    public boolean getHarsomainenJaTaiSaikeinen() {
        return this.harsomainenJaTaiSaikeinen;
    }

    public void setKukkakaalimainen(boolean arvo) {
        this.kukkakaalimainen = arvo;
    }

    public boolean getKukkakaalimainen() {
        return this.kukkakaalimainen;
    }
    
    public void setHaloja(boolean arvo) {
        this.haloja = arvo;
    }

    public boolean getHaloja(){
        return this.haloja;
    }
//    public void setSumuinen(boolean arvo) {
//        this.sumuinen = arvo;
//    }
//    
//    public boolean getSumuinen() {
//        return this.sumuinen;
//    }
    public boolean onkoPilviCumulus() { 
        if ((this.sataa == true) && (this.sataaVettaTaiLunta == true) && (this.kuurottainenSade == true) && (this.voimakasSade == false)) {
            return true;
        } else if ((this.sataa == false) && (this.kukkakaalimainen == true) && (this.iso == false)
                && (this.tummaPohjaValkoinenTorni == true) && (this.selvaRajainen == true) && (this.korkeaPilvi == true)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onkoPilviStratus() {
        if ((this.sataa == true) && (this.muuSateenOlomuoto == true) && (this.valkoinenPilvi == false)) {
            return true;
        } else if ((this.sataa == false) && (this.iso == true) && (this.valkoinenPilvi == false) && (this.lapikuultava == false) && (this.selvaRajainen == false)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onkoPilviAltoStratus() {
        if((this.harsomainenJaTaiSaikeinen == true) && (this.valkoinenPilvi == false) && (this.lapikuultava == true) && (this.haloja == false)) {
            return true;
        } else if ((this.sataa == true) && (this.sataaVettaTaiLunta == true) && (this.kuurottainenSade == false) && (this.valkoinenPilvi == false) && (this.iso == true) && (this.lapikuultava == true)
                && (this.haloja == false)) {
            return true;
        } else if ((this.sataa == false) && (this.valkoinenPilvi == false) && (this.iso == true) && (this.harsomainenJaTaiSaikeinen == true) && (this.haloja == false)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onkoPilviStratoCumulus() {
        if((this.aaltomainen == true) && (this.isoAalto == true) && (this.valkoinenPilvi == false)) {
            return true;
        } else if ((this.sataa == true) && (this.sataaVettaTaiLunta == true) && (this.kuurottainenSade == false) && (this.iso == true)
                && (this.korkeaPilvi == false) && (this.valkoinenPilvi == false) && (this.selvaRajainen == true)) {
            return true;
        } else if ((this.sataa == false) && (this.iso == true) && (this.korkeaPilvi == false) && (this.valkoinenPilvi == false) && (this.selvaRajainen == true)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onkoPilviNimboStratus() {
        if ((this.sataa == true) && (this.sataaVettaTaiLunta == true) && (this.kuurottainenSade == false) && (this.iso == true) && (this.valkoinenPilvi == false) && (this.lapikuultava == false)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onkoPilviCumuloNimbus() {
        if ((this.sataa == true) && ((this.sataaRakeita == true) || (this.ukkostaa == true))) {
            return true;
        } else if ((this.sataa == true) && (this.sataaVettaTaiLunta == true) && (this.voimakasSade == true) && (this.korkeaPilvi == true) && (this.tummaPohjaValkoinenTorni == true)
                && (this.selvaRajainen == true) && (this.kukkakaalimainen == true)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean onkoPilviCirrus() {
        if ((this.harsomainenJaTaiSaikeinen == true) && (this.haloja == false) && (this.valkoinenPilvi == true) && (this.lapikuultava == true)) {
            return true;
        } 
        return false;
    }

    public boolean onkoPilviCirroStratus() {
        if((this.harsomainenJaTaiSaikeinen == true) && (this.haloja == true) && (this.valkoinenPilvi == true)) {
            return true;
        } else if((this.sataa == false) && (this.lapikuultava == true) && (this.harsomainenJaTaiSaikeinen == true) && (this.valkoinenPilvi == true)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean onkoPilviAltoCumulus() {
        if((this.solumainen == true) && (this.isoSolu == true) && (this.valkoinenPilvi == false)) {
            return true;
        } else if ((this.sataa == false) && (this.valkoinenPilvi == false) && (this.korkeaPilvi == false) && (this.lapikuultava == false) && (this.harsomainenJaTaiSaikeinen == false)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean onkoPilviCirroCumulus() {
        if((this.solumainen == true) && (this.isoSolu == false) && (this.lapikuultava == true)) {
            return true;
        } else if((this.aaltomainen == true) && (this.isoAalto == false) && (this.lapikuultava == true)) {
            return true;
        } else if((this.sataa == false) && (this.valkoinenPilvi == true) && (this.lapikuultava == true) && (this.harsomainenJaTaiSaikeinen == false)) {
            return true;
        } else {
            return false;
        }
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
        } else if ((this.sataa == false) && (this.valkoinenPilvi == true) && (this.lapikuultava == true) && (this.iso == true) && (this.selvaRajainen == false)) {
            setPilvi("Cirrostratus");
        } else if ((this.sataa == true) && (this.valkoinenPilvi == false) && (this.iso == true) && (this.lapikuultava == true) && (this.selvaRajainen == false)) {
            setPilvi("Altostratus"); // voi sataa tai olla satamatta, iso tai melko iso
        } else if ((this.sataa == false) && (this.iso == true) && (this.lapikuultava == true) && (this.valkoinenPilvi == true) && (this.selvaRajainen == true)) {
            setPilvi("Altocumulus"); // valkoinen tai harmaa, koko vaihtelee
        } else if ((this.iso == true) && (this.selvaRajainen == true) && (this.valkoinenPilvi == true) && (this.sataa == false)) {
            setPilvi("Stratocumulus"); // sataa tai ei sada, harmaa tai valkoinen
        } else if ((this.sataa == false) && (this.iso == true) && (this.valkoinenPilvi == true) && (this.lapikuultava == true) && (this.selvaRajainen == false)) {
            setPilvi("Cirrocumulus"); //laaja tai melkolaaja
        }
    }
}
