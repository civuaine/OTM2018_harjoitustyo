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
    private Scene nakyma;

    @Override
    public void start(Stage ikkuna) throws Exception {
        // graafisen käyttöliittymän asettelija
        System.out.println("Pilvisovellus on käynnissä...");
        BorderPane asettelu = new BorderPane();
        
        Button aloitusNappi = new Button("Tee pilvihavainto");
        Button statistiikkaNappi = new Button("Tilastoja havainnoista");
        
// napit
        HBox napit = new HBox(10, aloitusNappi, statistiikkaNappi);
        napit.setAlignment(Pos.CENTER);
        HBox.setHgrow(aloitusNappi, Priority.ALWAYS);
        HBox.setHgrow(statistiikkaNappi, Priority.ALWAYS);
        aloitusNappi.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        statistiikkaNappi.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        asettelu.setBottom(napit);
        
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
        asettelu.setCenter(kayttoOhje);
        
        //String css = CloudSoftUi.class.getResource("/home/sini/Documents/Sini/Helsingin_yliopisto/Tietojenkasittely/OTM2018_harjoitustyo/PilviSovellus/style.css").toExternalForm();
        
// Yleinen asettelu sivun komponenteille
        BorderPane.setMargin(napit, new Insets(20));
        BorderPane.setMargin(tervehdysViesti, new Insets(20));
        BorderPane.setMargin(kayttoOhje, new Insets(20));
        //taustakuva
        //asettelu.setStyle("-fx-background-image: url(\"/home/sini/Documents/Sini/Helsingin_yliopisto/Tietojenkasittely/OTM2018_harjoitustyo/PilviSovellus/kukka.jpeg\");-fx-background-size: 500, 500;-fx-background-repeat: no-repeat;");
//        String kuva = CloudSoftUi.class.getResource("kukka.jpeg").toExternalForm();
//        asettelu.setStyle("-fx-background-image: url('" + kuva + "'); " +
//           "-fx-background-position: center center; " +
//           "-fx-background-repeat: stretch;");
        
// Näkymän muodostaminen
        this.nakyma = new Scene(asettelu, 1850, 1000); // muokkaa avautuvaksi aina full screen
        //this.nakyma.getStylesheets().add(css);
        ikkuna.setScene(nakyma);
        ikkuna.setTitle("Pilvisovellus");
        ikkuna.show();

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
