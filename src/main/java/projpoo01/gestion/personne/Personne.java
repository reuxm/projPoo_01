package projpoo01.gestion.personne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import projpoo01.gestion.item.Transaction;

/**
 * Squelette d'une personne comprenant les données minimales commune
 * a toute personne, physique ou morale, avec laquelle on interagit 
 * 
 * @author Matthias
 */
public abstract class Personne implements Serializable {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -5445791677982640136L;
	
	private String firstName;
	private String lastName;
	private String adresse;
	private String vill;
	private String codePostal;
	private List<Transaction> history;

	/**
	 * Creation d'une personne avec les donnees minimales
	 * 
	 * @param firstName le prenom
	 * @param lastName le nom
	 * @param adresse l'adresse
	 * @param codePostal le code postal
	 * @param ville la ville
	 */
	public Personne(String firstName, String lastName, String adresse,
			String ville, String codePostal) {
		this.history = new ArrayList<Transaction>();
		this.firstName = firstName;
		this.lastName = lastName;
		this.adresse = adresse;
		this.vill = ville;
		this.codePostal = codePostal;
	}
	
	public void enregistre(Transaction t) {
		history.add(t);
	}
	
	public List<Transaction> getHisto() {
		return history;
	}
	
	public void printHisto() {
		for(Transaction t : history) {
			System.out.println( t );
		}
	}

	// === From Object ===
	
	@Override
	public String toString() {
		return "prenom : " + firstName
		+ ", nom : " + lastName
		+ ", adresse : "+ adresse
		+ ", ville : " + vill
		+ ", code postal : " + codePostal;
	}
	
	// === getters & setters ===
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVill() {
		return vill;
	}
	public void setVill(String vill) {
		this.vill = vill;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
}
