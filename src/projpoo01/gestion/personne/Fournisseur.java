package projpoo01.gestion.personne;

import java.util.List;

import projpoo01.gestion.item.Achat;
import projpoo01.gestion.item.Commande;

public class Fournisseur extends Personne implements IClient, IFournisseur {

	private String num;
	private boolean client;

	public Fournisseur(String firstName, String lastName, String adresse,
			String vill, String codePostal, String numFour) {
		super(firstName, lastName, adresse, vill, codePostal);
		
		this.num = numFour;
	}
	
	// === From Object ===
	
	@Override
	public String toString() {
		return "Fournisseur n°"+num+" : ["+super.toString()+"]";
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
		return true;//un fournisseur est toujours un fournisseur
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
		return this.client;
	}
	
}
