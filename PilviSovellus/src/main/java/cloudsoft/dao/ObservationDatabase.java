package cloudsoft.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Vastaa käyttäjän tekmien havaintojen lukemisesta ja viemisestä tietokantaan.
 */
public class ObservationDatabase implements ObservationDao {

    private String databaseAddress;

    public ObservationDatabase(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
     * Metodi ottaa yhteyden tietokantaan ja palauttaa siihen tarvittavat
     * tiedot, Connecion -olion.
     *
     * @return Tietokantayhteyden tiedot
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
     * Metodi luo tietokannan.
     */
    @Override
    public void init() {
        List<String> luontilauseet = createDatabase();

        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            // suoritetaan komennot
            for (String lause : luontilauseet) {
                stmt.executeUpdate(lause);
            }
            conn.close();
        } catch (Exception e) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
        }
    }

    private List<String> createDatabase() {
        List<String> luontiLista = new ArrayList<>();

        luontiLista.add("CREATE TABLE Havainnot (paikka varchar(200), paivamaara Date, pilvi varchar(20))");
        return luontiLista;
    }

    /**
     * Metodi tallentaa käyttäjän tekemän havainnon tietokantaan.
     *
     * @param paikka käyttäjän syöttämä (tarkistettu) havaintopaikka
     * @param paivamaara käyttäjän syöttämä (tarkistettu) havaintopäivämäärä.
     * @param pilvi käyttäjän havaitsema pilvi (kyselyn tulos)
     * @throws SQLException
     */
    @Override
    public void save(String paikka, String paivamaara, String pilvi) throws SQLException {
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES (?,?,?)");
            stmt.setString(1, paikka);
            stmt.setDate(2, java.sql.Date.valueOf(paivamaara));
            stmt.setString(3, pilvi);
            stmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
        }
    }

    /**
     * Metodi lisää tietokantaan testidataa.
     */
    @Override
    public void addData() {
        List<String> lauseet = new ArrayList<>();

        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Jyväskylä', '1523350800000', 'Altostratus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Maarianhamina', '1523178000000', 'Cirrus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Helsinki', '1523523600000', 'Stratocumulus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Äänekoski', '806403600000', 'Cirrostratus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Oulu', '1275296400000', 'Stratus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Viitasaari', '1353661200000', 'Cumulonimbus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Rovaniemi', '1497862800000', 'Cumulonimbus')");
        int i = 0;
        try (Connection conn = getConnection()) {
            Statement stmt = conn.createStatement();
            for (String lause : lauseet) {
                stmt.executeUpdate(lause);
            }

            conn.close();
        } catch (Exception e) {
            //System.err.print(e.getMessage());
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
        }
    }

    /**
     * Metodi hakee tietokannasta havainnot paikkojen mukaan
     * aakkosjärjestyksessä tai päivämäärän mukaan aikajärjestyksessä (uusin
     * ensin) riippuen parametrista paikkaTaiPaivays.
     *
     * @param paikkaTaiPaivays kumman mukaan tietokannan sisältö järjestetään:
     * paikan mukaan vai päiväyksen mukaan
     * @return Järjestetty lista havainnoista
     */
    @Override
    public List<String> getAllObservations(String paikkaTaiPaivays) throws SQLException {
        List<String> listaHavainnoista = new ArrayList<>();
        String lause = "";
        try (Connection conn = getConnection()) {
            if (paikkaTaiPaivays.equals("paikka")) {
                lause = "SELECT * FROM Havainnot ORDER BY paikka ASC";
            } else if (paikkaTaiPaivays.equals("paivays")) {
                lause = "SELECT * FROM Havainnot ORDER BY paivamaara DESC";
            }
            PreparedStatement stmt = conn.prepareStatement(lause);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String paikka = rs.getString("paikka");
                Date paivamaara = rs.getDate("paivamaara");
                String pilvi = rs.getString("pilvi");
                String rivi = paikka + "        " + paivamaara + "        " + pilvi;
                listaHavainnoista.add(rivi);
            }
            conn.close();
        } catch (Exception ex) { // tarvitaanko excpetionia?
        }
        return listaHavainnoista;

    }

}
