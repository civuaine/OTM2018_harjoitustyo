package cloudsoft.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        luontiLista.add("CREATE TABLE Havainnot (paikka varchar(200), paivamaara varchar(20), pilvi varchar(20))");
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
    public void save(String paikka, Date paivamaara, String pilvi) throws SQLException {

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES (?,?,?)");
            stmt.setString(1, paikka);
            stmt.setDate(2, paivamaara);
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

        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Jyväskylä', '2018-04-10', 'Altostratus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Maarianhamina', '2018-04-08', 'Cirrus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Helsinki', '2018-04-12', 'Stratocumulus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Äänekoski', '1995-04-18', 'Cirrostratus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Oulu', '2010-05-31', 'Stratus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Viitasaari', '2012-11-23', 'Cumulonimbus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Rovaniemi', '2017-06-19', 'Cumulonimbus')");
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
     * Metodi hakee tietokannasta kaikki havainnot paikan mukaan
     * aakkosjärjestyksessä ja palauttaa ne listamuodossa eteenpäin.
     *
     * @return Lista havainnoista
     * @throws SQLException
     */
    @Override
    public List<String> getAllByCity() throws SQLException {
        List<String> kaupunginMukaan = new ArrayList<>();
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Havainnot ORDER BY paikka ASC");
            ResultSet rs = stmt.executeQuery();
//            if (!(rs.next())) { // hasnext
//                return null;
//            }
            while (rs.next()) {

                String paikka = rs.getString("paikka");
                String paivamaara = rs.getString("paivamaara");
                String pilvi = rs.getString("pilvi");
                String rivi = paikka + "        " + paivamaara + "        " + pilvi;
                kaupunginMukaan.add(rivi);
            }
            conn.close();
            return kaupunginMukaan;
        } catch (Exception e) {
            // jos jotain menee pieleen, mitään ei palauteta
        }
        return kaupunginMukaan;
    }

    /**
     * Metodi hakee tietokannasta kaikki havainnot päivämäärän mukaan
     * järjestettynä (uusin ensin) ja palauttaa ne listamuodossa eteenpäin.
     *
     * @return Lista havainnoista
     * @throws SQLException
     */
    @Override
    public List<String> getAllByDate() throws SQLException {
        List<String> paivanMukaan = new ArrayList<>();
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Havainnot ORDER BY paivamaara DESC");
            ResultSet rs = stmt.executeQuery();
//            if (!(rs.next())) {
//                return null;
//            }
            while (rs.next()) {
                String paikka = rs.getString("paikka");
                String paivamaara = rs.getString("paivamaara");
                String pilvi = rs.getString("pilvi");
                String rivi = paikka + "        " + paivamaara + "        " + pilvi;
                paivanMukaan.add(rivi);
            }
            conn.close();
            return paivanMukaan;
        } catch (Exception e) {
            // jos jotain menee pieleen, mitään ei palauteta
        }
        return paivanMukaan;
    }

}
