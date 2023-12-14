import java.util.Objects;

/**
 * Classe entity-bean Cliente che effettua il mapping del record della tabella
 * Cliente
 *
 * @author Angelo Pasquarelli
 */
public class Prodotto {
	/***********************/
// DEFINIZIONE ATTRIBUTI
	/***********************/
	private String codiceProdotto;
	private String descrizione;
	private Integer quantitaDisponibile;
	private Float prezzo;

	

	/***************/
// COSTRUTTORI
	/***************/
	public Prodotto(String codiceProdotto, String descrizione,Integer quantitaDisponibile, Float prezzo) {
		this.codiceProdotto = codiceProdotto;
		this.descrizione = descrizione;
		this.quantitaDisponibile=quantitaDisponibile;
		this.prezzo=prezzo;
	}

	/********************/
// GETTERS & SETTERS
	/********************/
	public String getCodiceProdotto() {
		return codiceProdotto;
	}

	public String getDescrizione() {
		return descrizione;
	}
public Integer getQuantitaDisponibile() {
	return quantitaDisponibile;
}
	/***********************************************************************/
// METODI DERIVATI DALLA CLASSE OBJECT: toString(), equals(), hashCode()
	/***********************************************************************/

	@Override
	public String toString() {
		return "Prodotto{" + "codiceProdotto=" + codiceProdotto + ", descrizione=" + descrizione + " quantità="+ quantitaDisponibile + " prezzo=" + prezzo + '}';
	}
}