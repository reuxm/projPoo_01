package projpoo01.gestion.personne;

public class Fournisseur extends Personne {

	private String num;

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
	
}
