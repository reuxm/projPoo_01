package projpoo01.gestion.personne;

import java.util.List;

import projpoo01.gestion.item.Achat;
import projpoo01.gestion.item.Commande;
import projpoo01.gestion.item.Transaction;

public class Fournisseur extends Personne implements IClient, IFournisseur {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -960494257256975181L;
	
	private String num;
	protected boolean client;
	
	public Fournisseur(String firstName, String lastName, String adresse, String vill, 
			String codePostal, String numFour, boolean client) {
		super(firstName, lastName, adresse, vill, codePostal);
		this.num = numFour;
		this.client=client;
	}
	
	// === From Object ===
	
	@Override
	public String toString() {
		String description = "Fournisseur n°"+num+" : ["+super.toString()+"]";
		if(client) {
			description +="\n\t\trole suplementaire : client";
		}
		return description;
	}

	// === From IFournisseur ===
	
	@Override
	public boolean isFournisseur() {
		return true;//un fournisseur est toujours un fournisseur
	}

	@Override
	public boolean livre() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void command(List<Commande> commandes) {
		// TODO Auto-generated method stub
		
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
