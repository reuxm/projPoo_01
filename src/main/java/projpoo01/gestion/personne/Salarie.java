package projpoo01.gestion.personne;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import projpoo01.gestion.item.Achat;
import projpoo01.gestion.item.Transaction;

/**
 * Un salarie de l'entreprise
 * 
 * @author Matthias
 */
public class Salarie extends Personne implements IClient {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = 5863414884939995432L;
	
	private String insee;
	private double salaire;
	protected boolean client;
	
	/**
	 * Creation d'un salarie. Voir {@link Personne#Personne(String, String, String, String, String)}
	 * pour les paramètres communs.
	 * 
	 * @param insee le numero de securite sociale
	 * @param salaire le salaire
	 * @param client le salarie a-t-il egalement un role de client?
	 */
	public Salarie(String firstName, String lastName, String adresse, String vill, 
			String codePostal, String insee, double salaire, boolean client) {
		super(firstName, lastName, adresse, vill, codePostal);
		this.insee = insee;
		this.salaire = salaire;
		this.client=client;
	}
	
	/**
	 * Copie un Salarie
	 * 
	 * @param base l'original
	 */
	public Salarie(Salarie base) {
		this(base.getFirstName(), base.getLastName(), base.getAdresse(), base.getVill(),
				base.getCodePostal(), base.getInsee(), base.getSalaireValue(), base.isClient());
	}

	/**
	 * Format ela salaire au formar de {@link Locale.FRANCE}
	 * 
	 * @return le salaire formate
	 */
	@SuppressWarnings("static-access")
	public String getSalaire() { //et formate    
		return NumberFormat.getCurrencyInstance(Locale.FRANCE).format(salaire).replace(",", ".");
	}
	
	/**
	 * Recupere le salaire en tant que valuer numerique simple
	 * 
	 * @return
	 */
	public double getSalaireValue() {
		return this.salaire;
	}
	
	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}
	
	public String getInsee() {
		return this.insee;
	}
	
	// === From Object ===
	
	@Override
	public String toString() {
		String description = "Salarie [ N°Secu : "+insee+", "+super.toString()+", salaire : "+getSalaire()+ "]";
		if(client) {
			description +="\n\t\trole suplementaire : client";	
		}
		return description;
	}
	
	// === From IClient ===

	@Override
	public void achete(List<Achat> achats) {
		enregistre( new Transaction(achats) );
	}

	@Override
	public boolean paie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClient() {
		return client;
	}

}
