
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCrud_Prodotto {
	public static void main(String[] args) {

		String databaseName = "magazzino_angelo";
		String dbmsUserName = "root";
		String dbmsPassword = "";
		String jdbcUrl = "jdbc:mariadb://localhost:3306/" + databaseName;
		try {

			/****************************************************************************/
			/*
			 * CONNESSIONE AL DATABASE
			 */
			/****************************************************************************/
			Connection jdbcConnectionToDatabase = DriverManager.getConnection(jdbcUrl, dbmsUserName, dbmsPassword);
			System.out.println("Connessione al database magazzino riuscita!");
			/****************************************************************************/
			/*
			 * ESECUZIONE DELLA QUERY DI SELECT
			 */
			/****************************************************************************/
			String selectFromProdottoByCodiceProdotto =
					    " SELECT codice_prodotto, descrizione,quantita_disponibile,prezzo" 
			            + " FROM prodotto "
					    + " WHERE prodotto.codice_prodotto = ? ";
			String parametroCodiceProdotto = "CPAPMB20";
			PreparedStatement preparedStatement = jdbcConnectionToDatabase
					.prepareStatement(selectFromProdottoByCodiceProdotto);
			preparedStatement.setString(1, parametroCodiceProdotto);

			ResultSet rsSelect = preparedStatement.executeQuery();

			Prodotto p = null;
			if (rsSelect.next()) {
				String codiceProdotto = rsSelect.getString("codice_prodotto");
				if (rsSelect.wasNull()) {
					codiceProdotto = "";
				}
				String descrizione = rsSelect.getString("descrizione");
				if (rsSelect.wasNull()) {
					descrizione = "";
				}
				Integer quantitaDisponibile = rsSelect.getInt("quantita_disponibile");
				if (rsSelect.wasNull()) {
					quantitaDisponibile = 0;
				}
				Float prezzo = rsSelect.getFloat("quantita_disponibile");
				if (rsSelect.wasNull()) {
					prezzo = 0f;
				}
				p = new Prodotto(codiceProdotto, descrizione,quantitaDisponibile,prezzo);

			}
			if (p != null) {
				System.out.println("Dati del prodotto> " + p.toString());
			} else {
				System.out.println("Il prodotto non è cercato!!!");
			}
		} catch (

		SQLException e) {

			e.printStackTrace();

		}
	}
}