package projpoo01.gestion.personne;

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
	public void livre() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void command() {
		// TODO Auto-generated method stub
		
	}

	// === From IClient ===
	
	@Override
	public void achete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paie() {
		// TODO Auto-generated method stub
		
	}
	
}
