package projpoo01.gestion.personne;

import java.util.List;

import projpoo01.gestion.item.Achat;
import projpoo01.gestion.item.Commande;

public class Client extends Personne implements IClient, IFournisseur {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -6654763354377890195L;
	
	private String num;
	protected boolean fournisseur;
	
	public Client(String firstName, String lastName, String adresse, String vill,
			String codePostal, String numClient, boolean fournisseur) {
		super(firstName, lastName, adresse, vill, codePostal);
		this.num = numClient;
		this.fournisseur=fournisseur;
	}
	
	// === From Object ===
	
	@Override
	public String toString() {
		String description = "Client n°"+num+" : ["+super.toString()+"]";
		if(fournisseur) {
			description +="\n\t\trole suplementaire : fournisseur";
		}
		return description;
	}
	
	// === From IClient

	@Override
	public boolean isClient() {
		return true;//un client est toujours client
	}

	@Override
	public void achete(List<Achat> achats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean paie() {
		// TODO Auto-generated method stub
		return false;
	}
	
	// === IFournisseur ===

	@Override
	public boolean livre() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void command(List<Commande> commandes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFournisseur() {
		return fournisseur;
	}
}
