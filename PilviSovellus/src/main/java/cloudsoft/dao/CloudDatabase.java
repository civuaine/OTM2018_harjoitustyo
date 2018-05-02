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
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Stratus', 'hyvä sää tulossa!')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Stratocumulus', 'katotaan millanen sää tulee.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Cumulus', 'Saas nähä sataako.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Cumulonimbus', 'oho, voi ukkostaa.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Altostratus', 'tää ei voi sataa.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Altocumulus', 'tää voi sataa tihkua')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Nimbostratus', 'tasaista sadetta.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Cirrus', 'voi enteillä rintamaa')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Cirrocumulus', 'epävakautta yläilmakehässä. Mahdollisesti rintama tulossa.')");
        lauseet.add("INSERT INTO Pilvet(nimi, ennuste) VALUES ('Cirrostratus', 'Hienoja haloilmiöitä!')");

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
