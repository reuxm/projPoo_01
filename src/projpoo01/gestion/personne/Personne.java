package projpoo01.gestion.personne;

import java.util.List;

import projpoo01.gestion.item.Achat;
import projpoo01.gestion.item.Commande;

public abstract class Personne implements IClient, IFournisseur{

	private String firstName;
	private String lastName;
	private String adresse;
	private String vill;
	private String codePostal;
	protected boolean fournisseur;
	protected boolean client;

	public Personne(String firstName, String lastName, String adresse,
			String vill, String codePostal) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.adresse = adresse;
		this.vill = vill;
		this.codePostal = codePostal;
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

	// === From IFournisseur ===
	
	@Override
	public boolean livre() {
		return false;
	}

	@Override
	public void command(List<Commande> commandes) {
	}

	// === From IClient ===
	
	@Override
	public void achete(List<Achat> achats) {
	}

	@Override
	public boolean paie() {
		return false;
	}

	@Override
	public boolean isFournisseur() {
		return this.fournisseur;
	}

	@Override
	public boolean isClient() {
		return this.client;
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
	public void setClient(boolean client) {
		this.client = client;
	}
	public void setFournisseur(boolean fournisseur) {
		this.fournisseur = fournisseur;
	}
}
