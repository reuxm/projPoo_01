package projpoo01.gestion.personne;

import java.util.List;

import projpoo01.gestion.item.Achat;
import projpoo01.gestion.item.Commande;

public class Client extends Personne implements IClient, IFournisseur {

	private String num;
	private boolean fournisseur;
	
	public Client(String firstName, String lastName, String adresse, String vill,
			String codePostal, String numClient) throws IllegalArgumentException {
		super(firstName, lastName, adresse, vill, codePostal);
		this.num = numClient;
	}
	
	// === From Object ===
	
	@Override
	public String toString() {
		return "Client n°"+num+" : ["+super.toString()+"]";
	}

	// === From IFournisseur ===
	
	@Override
	public boolean livre() {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void command(List<Commande> commandes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFournisseur() {
		return this.fournisseur;
	}

	// === From IClient ===
	
	@Override
	public void achete(List<Achat> achats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean paie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClient() {
		return true;//un client est toujours client
	}
}
