package projpoo01.gestion.personne;

public class Fournisseur extends Personne {

	private String num;

	public Fournisseur(String firstName, String lastName, String adresse,
			String vill, String codePostal, String numFour) {
		super(firstName, lastName, adresse, vill, codePostal);
		
		this.num = numFour;
	}
	
	@Override
	public String toString() {
		return "Fournisseur n°"+num+" : ["+super.toString()+"]";
	}
	
}
