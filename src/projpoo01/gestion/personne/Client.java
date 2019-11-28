package projpoo01.gestion.personne;

public class Client extends Personne implements IClient, IFournisseur {

	private String num;
	
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
			description +=" role suplementaire : fournisseur";
		}
		return description;
	}

	@Override
	public boolean isClient() {
		return true;//un client est toujours client
	}
}
