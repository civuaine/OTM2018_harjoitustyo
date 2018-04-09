package CloudSoft.ui;

import CloudSoft.domain.CloudSoftService;

import java.awt.Image;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import javafx.application.Application;
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
// importtaa vielä muut CloudSoft paketit!!

public class CloudSoftUi extends Application {

    private CloudSoftService cloudsoftservice;
    private Scene paanakyma;
    private Scene havaintosivu;
    private Scene tilastosivu;

    @Override
    public void init() {
        this.cloudsoftservice = new CloudSoftService();
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
        etusivulle.setAlignment(Pos.BOTTOM_LEFT);
        asettelu.setBottom(etusivulle);
        BorderPane.setMargin(etusivulle, new Insets(20));

        return etusivulle;
    }

    @Override
    public void start(Stage ikkuna) throws Exception {
//graafisten käyttöliittymän asettelijat
        System.out.println("Pilvisovellus on käynnissä...");
        BorderPane asettelu = new BorderPane();
        BorderPane havAsettelu = new BorderPane();
        BorderPane tilAsettelu = new BorderPane();

        this.paanakyma = new Scene(asettelu, 1850, 1000);
        this.havaintosivu = new Scene(havAsettelu, 1850, 1000);
        this.tilastosivu = new Scene(tilAsettelu, 1850, 1000);

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

        aloitusNappi.setOnAction((event) -> {
            System.out.println("Tehdään havainto!");
            ikkuna.setScene(this.havaintosivu);

        });

        statistiikkaNappi.setOnAction((event) -> {
            System.out.println("Katsellaan tilastoja!");
            ikkuna.setScene(this.tilastosivu);
        });

        // tervehdysviesti
        Label tervehdysViesti = new Label("Tervetuloa Pilvisovellukseen!");
        tervehdysViesti.setFont(Font.font("aridia", 40));
        tervehdysViesti.setTextFill(Color.DARKTURQUOISE);
        asettelu.setTop(tervehdysViesti);
        tervehdysViesti.autosize();
        BorderPane.setAlignment(tervehdysViesti, Pos.TOP_CENTER);

        // TekstiAlue
        String teksti = new String(Files.readAllBytes(Paths.get("/home/sini/Documents/Sini/Helsingin_yliopisto/Tietojenkasittely/OTM2018_harjoitustyo/PilviSovellus/teksti.txt")));
        TextArea kayttoOhje = new TextArea(teksti);

        HBox apu = new HBox(kayttoOhje);
        apu.setAlignment(Pos.CENTER);
        apu.setPadding(new Insets(20));
        HBox.setHgrow(kayttoOhje, Priority.ALWAYS);
        kayttoOhje.setWrapText(true);
        kayttoOhje.setMaxWidth(700);
        kayttoOhje.setMaxHeight(300);
        kayttoOhje.setFont(Font.font("aridia", 14));
        asettelu.setCenter(apu);

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

//Havainto
        // asettelija = havAsettelu
        // Ohjeet
        String havteksti = new String(Files.readAllBytes(Paths.get("/home/sini/Documents/Sini/Helsingin_yliopisto/Tietojenkasittely/OTM2018_harjoitustyo/PilviSovellus/teksti2.txt")));
        //TextArea havOhje = new TextArea(havteksti);
        Label havOhje = new Label(havteksti);

        HBox havApu = new HBox(havOhje);
        havApu.setAlignment(Pos.CENTER);
        havApu.setPadding(new Insets(20));
        HBox.setHgrow(havOhje, Priority.ALWAYS);
        havOhje.setWrapText(true);
        havOhje.setMaxWidth(700);
        havOhje.setMaxHeight(60);
        havOhje.setFont(Font.font("aridia", 14));

        havAsettelu.setTop(havApu);

        // nappi
        // tänne voi tuunaa, että muuttuu sovelluksen ikkunan koon kasvaessa/pienetessä
        Button havNappi = etusivuNappi(havAsettelu);
        siirryEtusivulle(havNappi, ikkuna);

        // paikkakunta ja päivämäärä
        VBox paikkakuntaKokonaisuus = new VBox();
        VBox pvmKokonaisuus = new VBox();
        HBox pvmJaPaikka = new HBox();

        Label paikkakuntaTeksti = new Label("Anna havaintopaikkakunnan nimi:");
        Label pvmTeksti = new Label("Anna havaintopäivämäärä (pp.kk.vvvv):");
        Label hyvaksymistekstiPaikkakunta = new Label();
        Label hyvaksymistekstiPvm = new Label();

        TextField paikkakunta = new TextField();
        TextField pvm = new TextField();

        Button paikkakuntaNappi = new Button("Tallenna");
        Button pvmNappi = new Button("Tallenna");

        paikkakuntaNappi.setOnAction((event) -> {
            String pk = paikkakunta.getText();
            if (pk.isEmpty()) {
                hyvaksymistekstiPaikkakunta.setText("Anna paikkakunta!");
                hyvaksymistekstiPaikkakunta.setTextFill(Color.FIREBRICK);
            } else {
                cloudsoftservice.havaintoPaikkakunta(pk);
                hyvaksymistekstiPaikkakunta.setText("Paikkakunta annettu!");
                hyvaksymistekstiPaikkakunta.setTextFill(Color.DARKOLIVEGREEN);
            }

        });

        pvmNappi.setOnAction((event) -> {
            String pvmnauha = pvmNappi.getText();
            Boolean pvmilmo = cloudsoftservice.paivamaaraTarkistin(pvmnauha);
            if (pvmilmo = true) {
                hyvaksymistekstiPvm.setText("Päivämäärä annettu!");
                hyvaksymistekstiPvm.setTextFill(Color.DARKOLIVEGREEN);
            } else {
                hyvaksymistekstiPvm.setText("Tarkista päivämäärä! Anna se muodossa pp.kk.vvvv, päivämäärä ei saa olla tulevaisuudessa.)");
                hyvaksymistekstiPvm.setTextFill(Color.FIREBRICK);
            }

        });

        paikkakuntaKokonaisuus.getChildren().addAll(paikkakuntaTeksti, paikkakunta, paikkakuntaNappi, hyvaksymistekstiPaikkakunta);
        paikkakuntaKokonaisuus.setSpacing(10);
        paikkakuntaKokonaisuus.setPadding(new Insets(20));

        pvmKokonaisuus.getChildren().addAll(pvmTeksti, pvm, pvmNappi, hyvaksymistekstiPvm);
        pvmKokonaisuus.setSpacing(10);
        pvmKokonaisuus.setPadding(new Insets(20));

        pvmJaPaikka.getChildren().addAll(paikkakuntaKokonaisuus, pvmKokonaisuus);
        pvmJaPaikka.setSpacing(30);
        pvmJaPaikka.setPadding(new Insets(20));

        havAsettelu.setCenter(pvmJaPaikka);

//Tilasto
        // asettelija = tilAsettelu
        //Otsikko
        String tilastoTeksti = new String(Files.readAllBytes(Paths.get("/home/sini/Documents/Sini/Helsingin_yliopisto/Tietojenkasittely/OTM2018_harjoitustyo/PilviSovellus/teksti3.txt")));
        //TextArea tilastoOhje = new TextArea(tilastoTeksti);
        Label tilastoOhje = new Label(tilastoTeksti);

        HBox tilApu = new HBox(tilastoOhje);
        tilApu.setAlignment(Pos.CENTER);
        tilApu.setPadding(new Insets(20));
        HBox.setHgrow(tilApu, Priority.ALWAYS);
        tilastoOhje.setWrapText(true);
        tilastoOhje.setMaxWidth(700);
        tilastoOhje.setMaxHeight(80);
        tilastoOhje.setFont(Font.font("aridia", 14));
        tilAsettelu.setTop(tilApu);

        // nappi
        Button tilNappi = etusivuNappi(tilAsettelu);
        siirryEtusivulle(tilNappi, ikkuna);

        // Yleinen asettelu sivujen komponenteille
        BorderPane.setMargin(napit, new Insets(20));
        BorderPane.setMargin(tervehdysViesti, new Insets(20));
        BorderPane.setMargin(kayttoOhje, new Insets(20));
        BorderPane.setMargin(havOhje, new Insets(20));

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
