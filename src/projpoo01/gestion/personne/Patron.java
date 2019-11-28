package projpoo01.gestion.personne;

public class Patron extends Salarie implements IPatron {

	public Patron(String firstName, String lastName, String adresse, String vill,
			String codePostal, String insee,
			double salaire, boolean client, boolean fournisseur) {
		super(firstName, lastName, adresse, vill, codePostal, insee, salaire, client, fournisseur);
		// TODO Auto-generated constructor stub
	}
	
	// === From Object ===
	
	@Override
	public String toString() {
		return super.toString()+( (client||fournisseur)?", ":" role suplementaire : " )+"patron";
	}

	// === From IPatron ===
	
	@Override
	public void remunere() {
		// TODO Auto-generated method stub
	}

	@Override
	public void embauche() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void licencie() {
		// TODO Auto-generated method stub
		
	}

}
