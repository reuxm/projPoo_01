package projpoo01.gestion.personne;

import java.text.DecimalFormat;

public class Salarie extends Personne {

	private String insee;
	private double salaire;
	
	public Salarie(String firstName, String lastName, String adresse,
			String vill, String codePostal, String insee, double salaire) {
		super(firstName, lastName, adresse, vill, codePostal);
		this.insee = insee;
		this.salaire = salaire;
	}
	
	public String getSalaire() {     
		return (new DecimalFormat("###,###,###.00").format(salaire))+" €";
	}
	
	@Override
	public String toString() {
		return "Salarié ["+super.toString()+"N°Secu : "+insee+", salaire : "+getSalaire()+ "]";
	}

}
