package CloudSoft.ui;

import CloudSoft.domain.CloudSoftService;

import java.io.FileInputStream;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
        tervehdysViesti.setFont(Font.font("aridia", 30));
        tervehdysViesti.setTextFill(Color.DARKTURQUOISE);
        asettelu.setTop(tervehdysViesti);
        tervehdysViesti.autosize();
        BorderPane.setAlignment(tervehdysViesti, Pos.TOP_CENTER);
   
// Tekstikenttä
        TextArea kayttoOhje = new TextArea("Ole oma meteorologisi! \nSovelluksen avulla voit selvittää mikä havaitsemasi \n"
                + "pilvi on luokaltaan ja lajiltaan, ja mitä se voi mahdollisesti kertoa tulevasta säästä. \n"
                + "\n"
                + "'Tee pilvihavainto' -napin takana sinulta kysytään muutamia kysymyksiä pilvihavaintoosi liittyen ja pyrkii niiden avulla \n"
                + "kertomaan sinulle, mikä havaitsemasi pilvi on ja millaista säätä"
                + "se ennustaa lähitunneille tai päiville. \nVoit lopuksi halutessasi tallentaa pilvihavaintosi pilvihavaintotietokantaan, "
                + "jossa on muiden käyttäjien tekemiä havaintoja. \nLopuksi saat havaintopaikallesi myös Ilmatieteen laitoksen "
                + "tarjoaman paikkakuntakohtaisen sääennusteen muutamalle lähipäivälle. \nPääset halutessasi katsomaan muiden käyttäjien havaintoja"
                + "'Tilastoja havainnoista' -napin kautta. ");
        
        HBox apu = new HBox(kayttoOhje);
        apu.setAlignment(Pos.CENTER);
        apu.setPadding(new Insets(20));
        HBox.setHgrow(kayttoOhje, Priority.ALWAYS);
        //kayttoOhje.setWrapText(true);
        asettelu.setCenter(kayttoOhje);
        
// Yleinen asettelu sivun komponenteille
        BorderPane.setMargin(napit, new Insets(20));
        BorderPane.setMargin(tervehdysViesti, new Insets(20));
        BorderPane.setMargin(kayttoOhje, new Insets(20));
        
        
// Näkymän muodostaminen
        this.nakyma = new Scene(asettelu, 1850, 1000); // muokkaa avautuvaksi aina full screen
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
