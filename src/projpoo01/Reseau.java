package projpoo01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import projpoo01.commandline.Saisie;
import projpoo01.gestion.personne.*;

public class Reseau {

	private Map<String, Client> clients;
	private Map<String, Fournisseur> fournisseurs;
	private Map<String, Salarie> salaries;
	private Patron patron;

	public Reseau() {
		clients = new HashMap<String, Client>();
		fournisseurs = new HashMap<String, Fournisseur>();
		salaries = new HashMap<String, Salarie>();
	}
	
	public static void main(String[] args) {
		Reseau reseau = new Reseau();
		reseau.saisieInitiale();
		reseau.menu();
	}
	
	public void menu(){
		Scanner sc = Saisie.getScanner();
		String[] actions = {
			"Nommer un patron",
			"Afficher les collacorateurs",
			"Action clients",
			"Action fournisseur",
			"Quitter"
		};
		
		int choice = -1;
		do {
			System.out.println("\nActions possible : ");
			for(int i=0;i<actions.length;i++ ) {
				System.out.println( (i+1)+"> "+actions[i] );
			}
			System.out.print("Votre choix ? ");
			
			try {
				String choiceLiteral = sc.nextLine();
				choice = Integer.parseInt(choiceLiteral);
			} catch(NumberFormatException e) {
				System.out.println("Entrez un nombre SVP");
			}
			
			switch(choice) {
				case 1 ://nommer un patron
					break;
				case 2 ://afficher les listes
					System.out.println(this);
					break;
				case 3 ://actions client
					break;
				case 4 ://action fournisseurs
					break;
				case 5 : break;//quit
				default : System.out.println("Ce n'est pas une option valide");
			}
		} while(choice!=actions.length);
	}
	
	public void saisieInitiale() {
		Saisie saisie = new Saisie(this);
		Scanner sc = Saisie.getScanner();
		
		String[] categories= {"salarié","client","fournisseur"};
		for( String categorie:categories) {
			int groupSize=0;
			boolean validNumber;
			do{
				System.out.print("Combien de "+categorie+"(s) ? : ");
				try{
					groupSize = sc.nextInt();
					validNumber = true;
				} catch(java.util.InputMismatchException e) {
					System.out.println("Entrez un nombre SVP");
					validNumber = false;
				} finally {
					sc.nextLine();//conssome le EOL laissé par le nextInt() precedent
				}
			} while(!validNumber);
			
			switch(categorie) {
				case "salarié":saisie.saisieS(groupSize, sc);break;
				case "client":saisie.saisieC(groupSize, sc);break;
				default:saisie.saisieF(groupSize ,sc);//default vaut toujours fournisseur, voir String[] categories
			}
		}
	}
	
	@Override
	public String toString() {
		String print = "\n### Contacts du réseau :\n"		
		//salariés
			+ "Nombre de salariés : "+salaries.size();
		for(Salarie s : salaries.values()) {
			print += "\n\t"+s.toString();
		}
		//clients
		print += "\n\nNombre de clients : "+getClients().size();
		for(Client c : getClients().values()) {
			print += "\n\t"+c.toString();
		}
		//fournissuers
		print += "\n\nNombre de clients : "+getClients().size();
		for(Fournisseur f : getFournisseurs().values()) {
			print += "\n\t"+f.toString();
		}
		
		return print;
	}

	public Map<String, Client> getClients() {
		return clients;
	}

	public Map<String, Salarie> getSalaries() {
		return salaries;
	}

	public Map<String, Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

}
