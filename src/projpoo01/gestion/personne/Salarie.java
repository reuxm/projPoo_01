package projpoo01.gestion.personne;

import java.text.DecimalFormat;
import java.util.List;

public class Salarie extends Personne implements IClient, IFournisseur {

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

	// === From IFournisseur ===
	
	@Override
	public boolean livre() {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void command(List<Commande> commandes) {
		// TODO Auto-generated method stub
		
	}

	// === From IClient ===
	
	@Override
	public void achete(List<Achat> achats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean paie() {
		// TODO Auto-generated method stub
		return false;
	}

}
