
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
			System.out.println("Connessione al database magazzino riuscita!\n");
			/****************************************************************************/
			/*
			 * ESECUZIONE DELLA QUERY DI SELECT
			 */
			/****************************************************************************/
			String selectFromProdottoByCodiceProdotto =
					    " SELECT *" 
			            + " FROM prodotto "
					    + " WHERE prodotto.codice_prodotto = ?";
			String parametroCodiceProdotto = "LVZPXS54";
			
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
				Float prezzo = rsSelect.getFloat("prezzo");
				if (rsSelect.wasNull()) {
					prezzo = 0f;
				}
				p = new Prodotto(codiceProdotto, descrizione,quantitaDisponibile,prezzo);

			}
			if (p != null) {
				System.out.println("Dati del prodotto:\n " + p.toString());
			} else {
				System.out.println("Il prodotto non è cercato!!!");
			}
			
			
			/****************************************************************************/
			/*
			 * ESECUZIONE DELLA QUERY DI SELECT CON LIKE
			 */
			/****************************************************************************/
			String selectFromProdottoByDescrizione =
				    " SELECT *" 
		            + " FROM prodotto "
				    + " WHERE prodotto.codice_prodotto LIKE  ? ";
			String parametroCodiceProdotto1 = "%TV%";
			
			PreparedStatement preparedStatementLike = jdbcConnectionToDatabase
					.prepareStatement(selectFromProdottoByDescrizione);
			preparedStatementLike.setString(1, parametroCodiceProdotto1);
			
			ResultSet rsSelectLike =
					                 preparedStatementLike.executeQuery();
			ArrayList<Prodotto> elencoProdotti = new ArrayList<>();  // "List" implementa l'"ArrayList"
			while(rsSelectLike.next()){
				String codiceProdotto = rsSelectLike.getString("codice_prodotto");
				if (rsSelectLike.wasNull())
				{
					codiceProdotto = "";
				}
				String descrizione = rsSelectLike.getString("descrizione"); 
			 if (rsSelectLike.wasNull())
			 {
				   descrizione = "";
			 }
			 Integer quantitaDisponibile = rsSelectLike.getInt("quantita_disponibile");
				if (rsSelectLike.wasNull()) {
					quantitaDisponibile = 0;
				}
				Float prezzo = rsSelectLike.getFloat("prezzo");
				if (rsSelectLike.wasNull()) {
					prezzo = 0f;
				}
			 Prodotto prodottiVisualizzati = new Prodotto(codiceProdotto,descrizione,quantitaDisponibile,prezzo);
             elencoProdotti.add(prodottiVisualizzati);
			  
			}
			if (elencoProdotti.size() != 0)
			{
				System.out.println("Dati del prodotto trovati : \n"+ elencoProdotti.toString() );
			}
			else {
				System.out.println("Nessun prodotto trovato");
			}
			
			/************************************************************************/
			/* ESECUZIONE DELLA QUERY DI INSERT PRODOTTO
			*/
			/************************************************************************/
			Prodotto prodottoInsert = new Prodotto("TVLGT345", "Televisore LG T345",5,500f);
			String insertProdotto = "INSERT INTO prodotto"
					+ "              (codice_prodotto, descrizione,quantita_disponibile,prezzo)"
					+ "                VALUES (?, ?, ? ,?) ";
			PreparedStatement preparedStatementInsertProdotto =
			jdbcConnectionToDatabase.prepareStatement(insertProdotto);
			
			preparedStatementInsertProdotto.setString(1, prodottoInsert.getCodiceProdotto());
			preparedStatementInsertProdotto.setString(2, prodottoInsert.getDescrizione());
			preparedStatementInsertProdotto.setInt(3 ,prodottoInsert.getQuantitaDisponibile());
			preparedStatementInsertProdotto.setFloat(4,prodottoInsert.getPrezzo());
			
			preparedStatementInsertProdotto.executeUpdate();
			System.out.println("Prodotto inserito : " + prodottoInsert.toString());
		} catch (

		SQLException e) {

			e.printStackTrace();

		}
	}
}