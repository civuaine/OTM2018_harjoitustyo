package CloudSoft.dao;

import CloudSoft.domain.Cloud;
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
        // luodaan tietokantataulu
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
        ArrayList<String> lista = new ArrayList<>();

        lista.add("CREATE TABLE Pilvet(nimi varchar(20), ennuste varchar(1000))");

        return lista;
    }

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
    public String getInformation(String nimi) throws SQLException {

        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT Pilvi.ennuste WHERE Pilvi.nimi = ?");
            stmt.setString(1, nimi);

            ResultSet rs = stmt.executeQuery();
            boolean hasOne = rs.next();
            if (!hasOne) {
                return null;
            }
            String ennuste = rs.getString("ennuste");
            conn.close();

            return ennuste;

        } catch (Throwable t) {
            // jos jotain menee pieleen, mitään ei palauteta
            return null;
        }
    }

    public boolean onTyhja() throws Exception {
        
        try (Connection conn = getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Pilvet");

            ResultSet rs = stmt.executeQuery();

            boolean hasOne = rs.next();
            return !hasOne; // jos on tyhjä --> true
        }
    }

}
