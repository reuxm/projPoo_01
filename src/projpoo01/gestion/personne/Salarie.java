package projpoo01.gestion.personne;

import java.text.DecimalFormat;

public class Salarie extends Personne implements IClient, IFournisseur {

	private String insee;
	private double salaire;
	
	public Salarie(String firstName, String lastName, String adresse, String vill, 
			String codePostal, String insee, double salaire, boolean client, boolean fournisseur) {
		super(firstName, lastName, adresse, vill, codePostal);
		this.insee = insee;
		this.salaire = salaire;
		this.client=client;
		this.fournisseur=fournisseur;
	}
	
	public String getSalaire() { //et formate    
		return (new DecimalFormat("###,###,###.00").format(salaire))+" €";
	}
	
	// === From Object ===
	
	@Override
	public String toString() {
		String description = "Salarié [ N°Secu : "+insee+", "+super.toString()+", salaire : "+getSalaire()+ "]";
		if(client) {
			description +=" role suplementaire : client";	
		}
		if(fournisseur) {
			description += (client?", ":" role suplementaire : ")+"fournisseur";
		}
		return description;
	}

}
