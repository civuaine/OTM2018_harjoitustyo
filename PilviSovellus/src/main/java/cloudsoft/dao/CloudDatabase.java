package cloudsoft.dao;

import cloudsoft.domain.Cloud;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Pilvitietokannasta vastaava luokka: Lukee tietoa sieltä.
 */
public class CloudDatabase implements CloudDao {

    private String databaseAddress;

    public CloudDatabase(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
     * Metodi ottaa yhteyden tietokantaan.
     *
     * @return yhteyden ottamiseen tarvittavat tiedot: Connection-olio
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if (dbUrl != null && dbUrl.length() > 0) {
            return DriverManager.getConnection(dbUrl);
        }

        return DriverManager.getConnection(databaseAddress);
    }

    /**
     * Metodi luo pilvitietokannan.
     */
    @Override
    public void init() {
        // luodaan tietokantataulu
        List<String> luontilauseet = createDatabase();

        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            for (String lause : luontilauseet) {
                stmt.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
        }

    }

    private List<String> createDatabase() {
        ArrayList<String> lista = new ArrayList<>();

        lista.add("CREATE TABLE Pilvet(nimi varchar(20), ennuste varchar(1000))");

        return lista;
    }

    /**
     * Metodi lisää testidataa pilvitietokantaan.
     */
    @Override
    public void addData() {
        List<String> lauseet = new ArrayList<>();
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Stratus', 'Lähitunteina voi sataa tihkua tai vettä, sen jälkeen sää poutaantuu ja selkene.!')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Stratocumulus', 'Säässä ei mitään erikoista muutosta luvassa.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Cumulus', 'Kauniin ilman pilviä. Suureksi paisuessaa voivat sataa ja kehittyä kuuro- ja ukkospilviksi')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Cumulonimbus', 'Pilvi sataa aina. Pilveen liittyvät voimakkaat laskuvirtaukset voivat \n"
                + "viilentää ilmaa.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Altostratus', 'Pilvi voi sataa. Joskus se syntyy jämänä jonkun laajemman "
                + "pilven hajotessa,\nmutta useimmiten se syntyy laajamittaiseen nousuliikeeseen ja enteilee rintamasysteemiä sekä sadetta noin 12h kuluessa.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Altocumulus', 'Syntynyt jämänä jostain muusta pilvestä tai enteilee lähestyvää rintamaa.\n"
                + "Rintamaan liittyessä rintama on poikkeuksellisen laaja-alainen.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Nimbostratus', 'Sateen loputtuessa sää selkenee ja poutaantuu noin vuorokaudeksi,\n"
                + "jonka jälkeen kylmärintama voi tuoda lisää sateita ja viileämpää ilmaa')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Cirrus', 'Ohuet Cirrukset syntyvät korkeapaineessa, paksummat liittyvät"
                + " rintamiin ja huonontuvaan säähän.\nJos pilven päässä on koukkumainen muoto, sää todennäköisesti huonontuu ja "
                + "sade alkaa tyypillisesti 24h kuluessa.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Cirrocumulus', 'Epävakautta yläilmakehässä. Rintamasysteemi voi olla "
                + "lähestymässä.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Cirrostratus', 'Cirrostratus syntyy kun laaja ilmakerros kohoaa ja saavuttaa"
                + " kyllästystilan.\nTyypillisesti enteilee rintamasysteemiä, johon liittyessä sade alkaa alle 24h kuluessa.')");

        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            for (String lause : lauseet) {
                stmt.executeUpdate(lause);
            }
            //conn.close();
        } catch (Throwable t) {
            //ei tee mitään jos virhe
        }
    }

    /**
     * Metodi hakee pilveen liittyvän ennusteen, joka on tietokannassa.
     *
     * @param nimi pilven nimi
     * @return Pilven ennuste lähihetkille
     * @throws SQLException
     */
    @Override
    public String getInformation(String nimi) throws SQLException {

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT Pilvet.ennuste FROM Pilvet WHERE Pilvet.nimi = ?");
            stmt.setString(1, nimi);

            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                return null;
            }
            String ennuste = rs.getString("ennuste");
            conn.close();

            return "Havaitsemasi pilvi: " + nimi + ".\nLähitulevaisuuden sää: " + ennuste;

        } catch (Throwable t) {
            // jos jotain menee pieleen, mitään ei palauteta
            return null;
        }
    }

    /**
     * Metodi tarkistaa, onko tietokantataulu tyhjä (Testidatan lisäystä
     * varten).
     *
     * @return true, jos tietokantataulu on tyhjä ja testidataa pitää lisätä.
     * Muuten false.
     * @throws Exception
     */
    public boolean onTyhja() throws Exception {

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Pilvet");

            ResultSet rs = stmt.executeQuery();
            return !rs.next(); // jos on tyhjä --> true
        }
    }

}
