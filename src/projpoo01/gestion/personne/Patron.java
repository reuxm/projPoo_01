package projpoo01.gestion.personne;

public class Patron extends Salarie implements IPatron {

	public Patron(String firstName, String lastName, String adresse, String vill, String codePostal, String insee,
			double salaire) {
		super(firstName, lastName, adresse, vill, codePostal, insee, salaire);
		// TODO Auto-generated constructor stub
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
