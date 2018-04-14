package CloudSoft.dao;

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
 * Vastaa havaintojen lukemisesta ja viemisestä tietokantaan.
 */
public class ObservationDatabase implements ObservationDao {

    private String databaseAddress;

    public ObservationDatabase(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    @Override
    public Connection getConnection() throws SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        if (dbUrl != null && dbUrl.length() > 0) {
            return DriverManager.getConnection(dbUrl);
        }

        return DriverManager.getConnection(databaseAddress);
    }

    @Override
    public void init() {
        List<String> luontilauseet = createDatabase();

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : luontilauseet) {
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
        }
    }

    private List<String> createDatabase() {
        List<String> luontiLista = new ArrayList<>();

        luontiLista.add("CREATE TABLE Havainnot (paikka varchar(200), paivamaara varchar(20), pilvi varchar(20))");
        return luontiLista;
    }

    @Override
    public void save() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addData() {
        List<String> lauseet = new ArrayList<>();

        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Hanko', '10.4.2018', 'Altostratus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Jyväskylä', '8.4.2018', 'Cirrus')");
        lauseet.add("INSERT INTO Havainnot (paikka, paivamaara, pilvi) VALUES ('Helsinki', '12.4.2018', 'Stratocumulus')");

        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
        }
    }

    @Override
    public List<String> getAllByCity() throws SQLException {
        List<String> kaupunginMukaan = new ArrayList<>();

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Havainnot ORDER BY paikka");

            ResultSet rs = stmt.executeQuery();

            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }

            while (rs.next()) {
                String paikka = rs.getString("paikka");
                String paivamaara = rs.getString("paivamaara");
                String pilvi = rs.getString("pilvi");
                kaupunginMukaan.add(new String(paikka + "    " + paivamaara + "    " + pilvi));
            }

            conn.close();
            return kaupunginMukaan;

        } catch (Throwable t) {
            // jos jotain menee pieleen, mitään ei palauteta
        }

        return kaupunginMukaan;
    }

    @Override
    public List<String> getAllByDate() throws SQLException {

        List<String> paivanMukaan = new ArrayList<>();

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Havainnot ORDER BY paivamaara");

            ResultSet rs = stmt.executeQuery();

            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }

            while (rs.next()) {
                String paikka = rs.getString("paikka");
                String paivamaara = rs.getString("paivamaara");
                String pilvi = rs.getString("pilvi");
                paivanMukaan.add(new String(paikka + "    " + paivamaara + "    " + pilvi));
            }

            conn.close();
            return paivanMukaan;

        } catch (Throwable t) {
            // jos jotain menee pieleen, mitään ei palauteta
        }

        return paivanMukaan;

    }

}
