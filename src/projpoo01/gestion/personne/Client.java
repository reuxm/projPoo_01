package projpoo01.gestion.personne;

public class Client extends Personne {

	private String num;
	
	public Client(String firstName, String lastName, String adresse, String vill,
			String codePostal, String numClient) throws IllegalArgumentException {
		super(firstName, lastName, adresse, vill, codePostal);
		this.num = numClient;
	}
	
	@Override
	public String toString() {
		return "Client n°"+num+" : ["+super.toString()+"]";
	}
}
