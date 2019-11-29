package projpoo01;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import projpoo01.commandline.*;
import projpoo01.gestion.personne.*;
import projpoo01.util.PersonneComposer;

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
		new Menu(reseau).menu();
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
				case "salarié":saisie.saisieSalaries(groupSize, sc);break;
				case "client":saisie.saisieClients(groupSize, sc);break;
				default:saisie.saisieFournisseurs(groupSize ,sc);//default vaut toujours fournisseur, voir String[] categories
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

	public Map<String, Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public Map<String, Salarie> getSalaries() {
		return salaries;
	}
	
	public Patron getPatron() {
		return patron;
	}

	public void readPatron() {
		patron = new Saisie(this).saisiePatron(Saisie.getScanner());
	}

	public void choosePatron() {
		Salarie newPatron = new Saisie(this).choosePatron();
		patron = new Patron( newPatron );
		salaries.put(patron.getInsee(), patron);
	}

}
