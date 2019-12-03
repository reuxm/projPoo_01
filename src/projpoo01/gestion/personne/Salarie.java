package projpoo01.gestion.personne;

import java.text.DecimalFormat;
import java.util.List;

import projpoo01.gestion.item.Achat;

public class Salarie extends Personne implements IClient {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = 5863414884939995432L;
	
	private String insee;
	private double salaire;
	protected boolean fournisseur;
	protected boolean client;
	
	public Salarie(String firstName, String lastName, String adresse, String vill, 
			String codePostal, String insee, double salaire, boolean client) {
		super(firstName, lastName, adresse, vill, codePostal);
		this.insee = insee;
		this.salaire = salaire;
		this.client=client;
	}
	
	public String getSalaire() { //et formate    
		return (new DecimalFormat("###,###,###.00").format(salaire))+" €";
	}
	
	public double getSalaireValue() {
		return this.salaire;
	}
	
	public String getInsee() {
		return this.insee;
	}
	
	// === From Object ===
	
	@Override
	public String toString() {
		String description = "Salarie [ N°Secu : "+insee+", "+super.toString()+", salaire : "+getSalaire()+ "]";
		if(client) {
			description +="\n\t\trole suplementaire : client";	
		}
		if(fournisseur) {
			description += (client?", ":"\n\t\trole suplementaire : ")+"fournisseur";
		}
		return description;
	}

	@Override
	public void achete(List<Achat> achats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean paie() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClient() {
		return client;
	}

}
