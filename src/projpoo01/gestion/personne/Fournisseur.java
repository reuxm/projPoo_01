package projpoo01.gestion.personne;

import java.util.List;

public class Fournisseur extends Personne implements IClient, IFournisseur {

	private String num;

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
	
}
