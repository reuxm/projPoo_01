package projpoo01.gestion.personne;

import java.text.DecimalFormat;

public class Salarie extends Personne implements IClient, IFournisseur, IPatron {

	private String insee;
	private double salaire;
	
	public Salarie(String firstName, String lastName, String adresse,
			String vill, String codePostal, String insee, double salaire) {
		super(firstName, lastName, adresse, vill, codePostal);
		this.insee = insee;
		this.salaire = salaire;
	}
	
	public String getSalaire() { //et formate    
		return (new DecimalFormat("###,###,###.00").format(salaire))+" €";
	}
	
	// === From Object ===
	
	@Override
	public String toString() {
		return "Salarié ["+super.toString()+"N°Secu : "+insee+", salaire : "+getSalaire()+ "]";
	}

	// === From IPatron ===
	
	@Override
	public void embauche() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void licencie() {
		// TODO Auto-generated method stub
		
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
