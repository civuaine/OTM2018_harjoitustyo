package cloudsoft.ui;

import cloudsoft.dao.CloudDatabase;
import cloudsoft.dao.ObservationDatabase;
import cloudsoft.domain.Cloud;
import cloudsoft.domain.CloudSoftService;
import cloudsoft.domain.ObservationDateCheck;

import java.awt.Image;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CloudSoftUi extends Application {

    private CloudSoftService cloudsoftservice;
    private Scene paanakyma;
    private Scene havaintosivu;
    private Scene tilastosivu;
    private Scene kyselysivu;

    private TextField paikkakunta;
    private TextField pvm;
    private Label hyvaksymistekstiPaikkakunta;
    private Label hyvaksymistekstiPvm;

    @Override
    public void init() throws Exception {
        this.cloudsoftservice = new CloudSoftService();
        this.cloudsoftservice.tietokannatKayttovalmiiksi();
    }

    public void siirryEtusivulle(Button etusivulle, Stage ikkuna) {
        etusivulle.setOnAction((event) -> {
            System.out.println("Siirrytään etusivulle");
            // kysy haluaako käyttäjä varmasti poistua, prosessi keskeytyy jos niin tekee...
            ikkuna.setScene(this.paanakyma);
        });

    }

    public Button etusivuNappi(BorderPane asettelu) {
        Button etusivulle = new Button("Etusivulle");
        etusivulle.setAlignment(Pos.TOP_LEFT);
        asettelu.setBottom(etusivulle);
        BorderPane.setMargin(etusivulle, new Insets(20));

        return etusivulle;
    }

    public String sadekysymys() {
        String kyssari = "Sataako havaitsemasi pilvi?";
        return kyssari;
    }

    public String kokokysymys() {
        String kyssari = "Onko pilvi iso (peittääkö pilvi taivaan horisontaalipinta-alasta 6/8 - 8/8)";
        return kyssari;
    }

    public String varikysymys() {
        String kyssari = "Onko pilvi kokonaan valkoinen (ei harmaita osia)";
        return kyssari;
    }

    public String lapinakyvakysymys() {
        String kyssari = "Onko pilvi läpikuultava, eli voiko sen läpi erottaa Auringon sijainnin?";
        return kyssari;
    }

    public String selvarajainenkysymys() {
        String kyssari = "Onko pilvi selvärajainen (selvärajainen pilvi ei näytä kuitumaiselta)";
        return kyssari;
    }

    public void asetaParametritOikein(Label kysymys, boolean arvo, HBox kylEi, Button teeUudestaan, Button tallennaTietokantaan) { //Button tallennaTietokantaan
        if (kysymys.getText().equals(sadekysymys())) {
            this.cloudsoftservice.setSade(arvo);
            kysymys.setText(kokokysymys());
        } else if (kysymys.getText().equals(kokokysymys())) {
            this.cloudsoftservice.setKoko(arvo);
            kysymys.setText(varikysymys());
        } else if (kysymys.getText().equals(varikysymys())) {
            this.cloudsoftservice.setVari(arvo);
            kysymys.setText(lapinakyvakysymys());
        } else if (kysymys.getText().equals(lapinakyvakysymys())) {
            this.cloudsoftservice.setLapikuultava(arvo);
            kysymys.setText(selvarajainenkysymys());
        } else if (kysymys.getText().equals(selvarajainenkysymys())) {
            this.cloudsoftservice.setSelvarajainen(arvo);
            try {
                String ennuste = cloudsoftservice.noudaEnnustePilvenPerusteella();
                String saaennuste = cloudsoftservice.tulostaEnnuste();
                kysymys.setText("Kysely on valmis. Alla ennuste, mitä havaitsemasi pilvi voi tarkoittaa"
                        + " lähituntien/-päivien sään kannalta (Kysely ja tämä sivu tulee monipuolistumaan. Tulokset vielä vähän höpöjä ja testitasolla)\n" + ennuste + "\n" + "JSON:in noutaminen ja sen muuttaminen vielä vaiheessa...: " + saaennuste);
                cloudsoftservice.yahoowebservice();
                kylEi.getChildren().clear();
                if (!cloudsoftservice.getPilvi().equals("Pilveä ei löydy")) {
                    kylEi.getChildren().addAll(teeUudestaan, tallennaTietokantaan);
                } else {
                    kylEi.getChildren().add(teeUudestaan);
                }

            } catch (Exception ex) {
            }
        }
    }

    public void nollaaKaikkiAnnetutParametrit() {
        paikkakunta.clear();
        pvm.clear();
        hyvaksymistekstiPaikkakunta.setText("");
        hyvaksymistekstiPvm.setText("");
        // pilviparametrit
    }

    public void suoritaKysely(Label kysymys, Button kylla, Button ei, HBox kylEi, Button teeUudestaan, Button tallennaTietokantaan) {
        kysymys.setText(sadekysymys());
        kylEi.getChildren().clear();
        kylEi.getChildren().addAll(kylla, ei);

        kylla.setOnAction((event) -> {
            asetaParametritOikein(kysymys, true, kylEi, teeUudestaan, tallennaTietokantaan); // label, boolean arvo, hbox, button uudestaan ja tietokantaan
        });

        ei.setOnAction((event) -> {
            asetaParametritOikein(kysymys, false, kylEi, teeUudestaan, tallennaTietokantaan);
        });
    }

    public BorderPane paaNakyma() throws Exception {
        BorderPane asettelija = new BorderPane();

        Label tervehdysViesti = new Label("Tervetuloa Pilvisovellukseen!");
        tervehdysViesti.setFont(Font.font("aridia", 40));
        tervehdysViesti.setTextFill(Color.DARKTURQUOISE);
        tervehdysViesti.autosize();
        BorderPane.setAlignment(tervehdysViesti, Pos.TOP_CENTER);
        asettelija.setTop(tervehdysViesti);

        String teksti = new String(Files.readAllBytes(Paths.get("teksti.txt")));
        TextArea kayttoOhje = new TextArea(teksti);
        kayttoOhje.setEditable(false);
        kayttoOhje.getStyleClass().addAll("model-text-area");

        HBox apu = new HBox(kayttoOhje);
        apu.setAlignment(Pos.CENTER);
        apu.setPadding(new Insets(20));
        HBox.setHgrow(kayttoOhje, Priority.ALWAYS);
        kayttoOhje.setWrapText(true);
        kayttoOhje.setMaxWidth(700);
        kayttoOhje.setMaxHeight(300);
        kayttoOhje.setFont(Font.font("aridia", 14));
        asettelija.setCenter(apu);
        BorderPane.setMargin(tervehdysViesti, new Insets(20));
        BorderPane.setMargin(kayttoOhje, new Insets(20));
        return asettelija;
    }

    public BorderPane havaintoNakyma() throws Exception {
        BorderPane asettelija = new BorderPane();
        String havteksti = new String(Files.readAllBytes(Paths.get("teksti2.txt")));
        Label havOhje = new Label(havteksti);

        HBox havApu = new HBox(havOhje);
        havApu.setAlignment(Pos.TOP_CENTER);
        havApu.setPadding(new Insets(20));
        HBox.setHgrow(havOhje, Priority.ALWAYS);
        havOhje.setWrapText(true);
        havOhje.setMaxWidth(700);
        havOhje.setMaxHeight(100);
        havOhje.setFont(Font.font("aridia", 14));
        asettelija.setTop(havApu);
        BorderPane.setMargin(havOhje, new Insets(20));
        return asettelija;
    }

    public BorderPane tilastoNakyma() throws Exception {
        BorderPane asettelija = new BorderPane();
        String tilastoTeksti = new String(Files.readAllBytes(Paths.get("teksti3.txt")));
        Label tilastoOhje = new Label(tilastoTeksti);
        HBox tilApu = new HBox(tilastoOhje);
        tilApu.setAlignment(Pos.CENTER);
        tilApu.setPadding(new Insets(20));
        HBox.setHgrow(tilApu, Priority.ALWAYS);
        tilastoOhje.setWrapText(true);
        tilastoOhje.setMaxWidth(700);
        tilastoOhje.setMaxHeight(80);
        tilastoOhje.setFont(Font.font("aridia", 14));
        asettelija.setTop(tilApu);
        return asettelija;
    }

    public void haeHavainnotUudestaan(Label havainnot) throws Exception{
        String havaintoTeksti = "";
        List<String> hav = cloudsoftservice.getHavainnotPaiva();
        for (String yksi : hav) {
            havaintoTeksti += yksi + "\n";
        }
        havainnot.setText(havaintoTeksti);
    }

//    public BorderPane kyselyNakyma() throws Exception {
//        BorderPane asettelija = new BorderPane();
//        Label kysymys = new Label();
//        kysymys.setAlignment(Pos.TOP_CENTER);
//        
//        return asettelija;
//    }
    @Override
    public void start(Stage ikkuna) throws Exception {
//graafisten käyttöliittymän asettelijat
        System.out.println("Pilvisovellus on käynnissä...");
        BorderPane asettelu = paaNakyma();
        BorderPane havAsettelu = havaintoNakyma();
        BorderPane tilAsettelu = tilastoNakyma();
        BorderPane kysAsettelu = new BorderPane(); // kyselyNakyma

        this.paanakyma = new Scene(asettelu, 1600, 900);
        this.havaintosivu = new Scene(havAsettelu, 1600, 900);
        this.tilastosivu = new Scene(tilAsettelu, 1600, 900);
        this.kyselysivu = new Scene(kysAsettelu, 1600, 900);

        this.hyvaksymistekstiPaikkakunta = new Label();
        this.hyvaksymistekstiPvm = new Label();
        this.paikkakunta = new TextField();
        this.pvm = new TextField();

//Etusivu
        // napit
        Button aloitusNappi = new Button("Tee pilvihavainto");
        Button statistiikkaNappi = new Button("Tilastoja havainnoista");

        HBox napit = new HBox(10, aloitusNappi, statistiikkaNappi);
        napit.setAlignment(Pos.CENTER);
        HBox.setHgrow(aloitusNappi, Priority.ALWAYS);
        HBox.setHgrow(statistiikkaNappi, Priority.ALWAYS);
        aloitusNappi.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        statistiikkaNappi.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        asettelu.setBottom(napit);
        BorderPane.setMargin(napit, new Insets(20));
        aloitusNappi.setOnAction((event) -> {
            System.out.println("Tehdään havainto!");
            ikkuna.setScene(this.havaintosivu);
            nollaaKaikkiAnnetutParametrit();

        });
        final Label havainnot = new Label();
        statistiikkaNappi.setOnAction((event) -> {
            try {
                System.out.println("Katsellaan tilastoja!");
                haeHavainnotUudestaan(havainnot);
                ikkuna.setScene(this.tilastosivu);
            } catch (Exception ex) {
            }
        });

        //String css = CloudSoftUi.class.getResource("/home/sini/Documents/Sini/Helsingin_yliopisto/Tietojenkasittely/OTM2018_harjoitustyo/PilviSovellus/style.css").toExternalForm();
        //taustakuva
        //asettelu.setStyle("-fx-background-image: url(\"/home/sini/Documents/Sini/Helsingin_yliopisto/Tietojenkasittely/OTM2018_harjoitustyo/PilviSovellus/kukka.jpeg\");-fx-background-size: 500, 500;-fx-background-repeat: no-repeat;");
//        String kuva = CloudSoftUi.class.getResource("kukka.jpeg").toExternalForm();
//        asettelu.setStyle("-fx-background-image: url('" + kuva + "'); " +
//           "-fx-background-position: center center; " +
//           "-fx-background-repeat: stretch;");
        // päänäkymän muodostaminen
        ikkuna.setScene(this.paanakyma);
        ikkuna.setTitle("Pilvisovellus");
        ikkuna.show();

//Havaintosivu
        // asettelija = havAsettelu
        // nappi
        // tänne voi tuunaa, jos haluaa ikkunan koon muuttuvan
        Button havNappi = etusivuNappi(havAsettelu);
        siirryEtusivulle(havNappi, ikkuna);

        // paikkakunta ja päivämäärä
        VBox paikkakuntaKokonaisuus = new VBox();
        VBox pvmKokonaisuus = new VBox();
        HBox pvmJaPaikka = new HBox();

        Label paikkakuntaTeksti = new Label("Anna havaintopaikkakunnan nimi:");
        Label pvmTeksti = new Label("Anna havaintopäivämäärä (pp.kk.vvvv TAI p.k.vvvv):");

        Button paikkakuntaNappi = new Button("Tallenna");
        Button pvmNappi = new Button("Tallenna");

        paikkakuntaNappi.setOnAction((event) -> {
            String pk = paikkakunta.getText();

            // tarkistetaan heti löytyykö paikkakunta IL:n tietokannasta.
            Boolean paikkakuntatesti = cloudsoftservice.tarkistaPaikkakunta(pk);
            if (!paikkakuntatesti) {
                hyvaksymistekstiPaikkakunta.setText("Anna paikkakunta, tarkista oikeinkirjoitus!");  // paikkakuntaa ei vältts löydy yahoon palvelusta
                hyvaksymistekstiPaikkakunta.setTextFill(Color.FIREBRICK);
            } else {
                hyvaksymistekstiPaikkakunta.setText("Paikkakunta annettu!");
                hyvaksymistekstiPaikkakunta.setTextFill(Color.DARKOLIVEGREEN);
            }

        });

        pvmNappi.setOnAction((event) -> {
            String pvmnauha = pvm.getText();
            Boolean pvmtesti = cloudsoftservice.tarkistaPaivamaara(pvmnauha);

            if (!pvmtesti) {
                hyvaksymistekstiPvm.setText("Tarkista päivämäärä, anna se muodossa pp.kk.vvvv tai p.k.vvvv"
                        + "\nhavainnon pitää olla vuoden 1950 jälkeen tehty, havaintoaika ei voi olla tulevaisuudessa");
                hyvaksymistekstiPvm.setTextFill(Color.FIREBRICK);
            } else {
                hyvaksymistekstiPvm.setText("Päivämäärä annettu!");
                hyvaksymistekstiPvm.setTextFill(Color.DARKOLIVEGREEN);

            }

        });

        Button jatkoNappi = new Button("Jatka");
        Label jatkoHyvaksynta = new Label();

        paikkakuntaKokonaisuus.getChildren().addAll(paikkakuntaTeksti, paikkakunta, paikkakuntaNappi, hyvaksymistekstiPaikkakunta, jatkoNappi, jatkoHyvaksynta);
        paikkakuntaKokonaisuus.setSpacing(10);
        paikkakuntaKokonaisuus.setPadding(new Insets(20));

        pvmKokonaisuus.getChildren().addAll(pvmTeksti, pvm, pvmNappi, hyvaksymistekstiPvm);
        pvmKokonaisuus.setSpacing(10);
        pvmKokonaisuus.setPadding(new Insets(20));

        pvmJaPaikka.getChildren().addAll(paikkakuntaKokonaisuus, pvmKokonaisuus);
        pvmJaPaikka.setSpacing(30);
        pvmJaPaikka.setPadding(new Insets(20));
        pvmJaPaikka.setAlignment(Pos.CENTER);

        havAsettelu.setCenter(pvmJaPaikka);

// kyselysivu
        // asettlija = kysAsettelu
        //jatkoNapin toiminta
//        jatkoNappi.setOnAction((event) -> {
//            if ((hyvaksymistekstiPaikkakunta.getText().equals("Paikkakunta annettu!")) && (hyvaksymistekstiPvm.getText().equals("Päivämäärä annettu!"))) {
//                System.out.println("Siirrytään kyselyyn");
//                ikkuna.setScene(this.kyselysivu); // tuleeko tänne alustuskäsky...
//                suoritaKysely(kysymys, kylla, ei, kylEi, teeUudestaan, tallennaTietokantaan);
//            } else {
//                jatkoHyvaksynta.setText("Tarkista antamasi syötteet!");
//                jatkoHyvaksynta.setTextFill(Color.FIREBRICK);
//            }
//        });
        Label kysymys = new Label();
        kysymys.setAlignment(Pos.TOP_CENTER);
        VBox edellisNapit = new VBox();

        Button etusivuNappi = etusivuNappi(kysAsettelu);
        siirryEtusivulle(etusivuNappi, ikkuna);

        edellisNapit.getChildren().addAll(etusivuNappi);
        edellisNapit.setSpacing(10);
        edellisNapit.setPadding(new Insets(20));

        Button kylla = new Button("Kyllä");
        Button ei = new Button("Ei");

        HBox kylEi = new HBox();
        VBox ulkoasu = new VBox();

        kylEi.getChildren().addAll(kylla, ei);
        kylEi.setSpacing(50);
        kylEi.setPadding(new Insets(20));
        kylEi.setAlignment(Pos.CENTER);
        HBox.setHgrow(kylEi, Priority.ALWAYS);

        ulkoasu.getChildren().addAll(kysymys, kylEi);
        ulkoasu.setSpacing(70);
        ulkoasu.setPadding(new Insets(20));
        VBox.setVgrow(ulkoasu, Priority.ALWAYS);
        ulkoasu.setAlignment(Pos.CENTER);
        kysAsettelu.setBottom(edellisNapit);
        kysAsettelu.setCenter(ulkoasu);
        Button teeUudestaan = new Button("Suorita kysely uudestaan");
        Button tallennaTietokantaan = new Button("Tallenna havainto tietokantaan");

// jatkonapin toiminta        
        jatkoNappi.setOnAction((event) -> {
            if ((hyvaksymistekstiPaikkakunta.getText().equals("Paikkakunta annettu!")) && (hyvaksymistekstiPvm.getText().equals("Päivämäärä annettu!"))) {
                System.out.println("Siirrytään kyselyyn");
                ikkuna.setScene(this.kyselysivu); // tuleeko tänne alustuskäsky...
                suoritaKysely(kysymys, kylla, ei, kylEi, teeUudestaan, tallennaTietokantaan);
            } else {
                jatkoHyvaksynta.setText("Tarkista antamasi syötteet!");
                jatkoHyvaksynta.setTextFill(Color.FIREBRICK);
            }
        });

        // itse kysely
        //suoritaKysely(kysymys, kylla, ei, kylEi, teeUudestaan, tallennaTietokantaan);
        //Mahdollisuus uusia kysely joka tapauksessa
        tallennaTietokantaan.setOnAction((event) -> {
            try {
                cloudsoftservice.tallennaHavainto(cloudsoftservice.getPilvi());
                nollaaKaikkiAnnetutParametrit();
                ikkuna.setScene(paanakyma);
            } catch (Exception ex) {
                //älä palauta mitään
            }
        });

        teeUudestaan.setOnAction((event) -> {
            nollaaKaikkiAnnetutParametrit();
            ikkuna.setScene(this.havaintosivu);
            //alusta jatkonappi
        });
//        teeUudestaan.setOnAction((event) -> {
//            nollaaKaikkiAnnetutParametrit(paikkakunta, pvm, hyvaksymistekstiPaikkakunta, hyvaksymistekstiPvm);
//            kysymys.setText(sadekysymys());
//            kylla.setOnAction((event1) -> {
//                asetaParametritOikein(kysymys, true, kylEi, teeUudestaan, tallennaTietokantaan); // label, boolean arvo, hbox, button uudestaan ja tietokantaan
//            });
//
//            ei.setOnAction((event2) -> {
//                asetaParametritOikein(kysymys, false, kylEi, teeUudestaan, tallennaTietokantaan);
//            });
//        });

//Tilasto
        // asettelija = tilAsettelu
        //Otsikko
        // Tietokannan havainnot
        //String havaintoTeksti = "";
        //final Label havainnot = new Label();
        //List<String> hav = cloudsoftservice.getHavainnotPaiva();
        //for (String yksi : hav) {
        //    havaintoTeksti += yksi + "\n";
        //}
        //havainnot.setText(havaintoTeksti);
        haeHavainnotUudestaan(havainnot);
        // napit
        Button tilNappi = etusivuNappi(tilAsettelu);
        siirryEtusivulle(tilNappi, ikkuna);

        Button pvmMukaan = new Button("Järjestä päivämäärän mukaan");
        Button paikanMukaan = new Button("Järjestä paikan mukaan");

        pvmMukaan.setOnAction((event) -> {
            try {
                String havaintoTekstiUusi = "";
                List<String> havpvm = cloudsoftservice.getHavainnotPaiva();

                for (String yksi : havpvm) {
                    havaintoTekstiUusi += yksi + "\n";
                }

                havainnot.setText(havaintoTekstiUusi);
            } catch (Exception ex) {
                //älä tee mitään
            }
        });

        paikanMukaan.setOnAction((event) -> {
            try {
                String havaintoTekstiPaikka = "";
                List<String> havpaikka = cloudsoftservice.getHavainnotPaikka();
                for (String yksi : havpaikka) {
                    havaintoTekstiPaikka += yksi + "\n";
                }

                havainnot.setText(havaintoTekstiPaikka);
            } catch (Exception ex) {
                //älä tee mitään
            }
        });

        VBox tilastot = new VBox();
        HBox jarjestysNapit = new HBox(paikanMukaan, pvmMukaan);
        jarjestysNapit.setSpacing(20);
        jarjestysNapit.setPadding(new Insets(20));
        jarjestysNapit.setAlignment(Pos.CENTER);

        tilastot.getChildren().addAll(havainnot, jarjestysNapit);
        tilastot.setAlignment(Pos.CENTER);
        tilastot.setSpacing(10);
        tilastot.setPadding(new Insets(20));

        tilAsettelu.setCenter(tilastot);
    }

    @Override
    public void stop() {
        // tee lopetustoimenpiteet täällä
        System.out.println("Pilvisovellus sulkeutuu");
    }

    public static void main(String[] args) {
        System.out.println("Pilvisovellus käynnistyy");
        launch(CloudSoftUi.class);

    }

}
