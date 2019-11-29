package projpoo01;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import projpoo01.gestion.personne.*;
import projpoo01.validity.*;

public class Reseau {

	private Map<String, Client> clients;
	private Map<String, Fournisseur> fournisseurs;
	private Map<String, Salarie> salaries;

	public Reseau() {
		clients = new HashMap<String, Client>();
		fournisseurs = new HashMap<String, Fournisseur>();
		salaries = new HashMap<String, Salarie>();
	}
	
	public static void main(String[] args) {
		Reseau reseau = new Reseau();
		reseau.saisieInitiale();
		System.out.println(reseau);
	}
	
	public void saisieInitiale() {
		Scanner sc = new Scanner(System.in);
		
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
				case "salarié":saisieS(groupSize, sc);break;
				case "client":saisieC(groupSize, sc);break;
				default:saisieF(groupSize ,sc);//default vaut toujours fournisseur, voir String[] categories
			}
		}
		sc.close();
	}

	private void saisieC(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			String numClient;
			boolean validID;
			do{
				System.out.print("Client "+i+" : \nN°Client ? ");
				numClient=sc.nextLine();
				try {//Contrainte : unicité
					Format.checkPK(numClient, clients.keySet(), "numero Client");
					validID = true;
				} catch(FormatException e) {
					System.out.println(e.getMessage());
					validID = false;
				}
			} while(!validID);
			
			String[] newP = saisieP(sc);

			boolean f = false;
			boolean validB;
			do{
				System.out.println("Ce client est-il aussi un fournisseur ? [Y:N]");
				String fournisseur = sc.nextLine();
				validB = true;
				try{
					f = Format.checkBoolean(fournisseur);
				} catch(FormatException e) {
					System.out.println(e.getMessage());
					validB = false;
				}
			} while(!validB);
			
			clients.put(
				numClient,
				new Client(newP[0], newP[1], newP[2], newP[3], newP[4], numClient, f)
			);
		}
	}

	private void saisieF(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			String numFour;
			boolean validID;
			do {
				System.out.print("Fournisseur "+i+" :\nN°Fournisseur ? ");
				numFour=sc.nextLine();
				try {//Contrainte : unicité
					Format.checkPK(numFour, fournisseurs.keySet(), "numero Fournissuer");
					validID = true;
				} catch (FormatException e) {
					System.out.println(e.getMessage());
					validID = false;
				}
			} while(!validID);
			
			String[] newP = saisieP(sc);

			boolean c = false;
			boolean validB;
			do {
				System.out.println("Ce fournissseur est-il aussi un client ? [Y:N]");
				String client = sc.nextLine();
				try {
					c = Format.checkBoolean(client);
					validB = true;
				} catch (FormatException e) {
					System.out.println(e.getMessage());
					validB = false;
				}
			} while(!validB);
				
				fournisseurs.put(
					numFour,
					new Fournisseur(newP[0], newP[1], newP[2], newP[3], newP[4], numFour, c)
				);
			
		}
	}

	private void saisieS(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			String insee;
			boolean validInsee;
			do {
				System.out.print("Salarié "+i+" :\nN° de securité sociale ? ");
				insee=sc.nextLine();
				try{
					Format.checkInsee(insee) ;//Contrainte : 13 chiffres 
					Format.checkPK(insee, salaries.keySet(), "numero de securite sociale");//Contrainte : unicité
					validInsee = true;
				} catch(FormatException e) {//deux gestions dans le meme catch : on veut resaisir la donnée dans les deux cas
					System.out.println(e.getMessage());
					validInsee = false;;
				}
			}while (!validInsee);
			 
			double salaireFormaté = 0;
			boolean validSalaire;
			do {
				System.out.print("Salaire (precision max 0,01€)? ");
				String salaire=sc.nextLine();
				try {
					salaireFormaté= Format.checkSalaire(salaire);
					validSalaire = true;
				} catch (FormatException e) {
					System.out.println(e.getMessage());
					validSalaire = false;
				}
			} while(!validSalaire);
			String[] newP = saisieP(sc);
			
			boolean c = false;
			boolean validBC;
			do {
				System.out.println("Ce fournissseur est-il aussi un client ? [Y:N]");
				String client = sc.nextLine();
				try {
					c = Format.checkBoolean(client);
					validBC = true;
				} catch (FormatException e) {
					System.out.println(e.getMessage());
					validBC = false;
				}
			} while(!validBC);
			
			boolean f = false;
			boolean validBF;
			do{
				System.out.println("Ce salarie est-il aussi un fournisseur ? [Y:N]");
				String fournisseur = sc.nextLine();
				validBF = true;
				try{
					f = Format.checkBoolean(fournisseur);
				} catch(FormatException e) {
					System.out.println(e.getMessage());
					validBF = false;
				}
			} while(!validBF);
			
			salaries.put(
				insee,
				new Salarie(newP[0], newP[1], newP[2], newP[3], newP[4], insee, salaireFormaté, c, f)
			);	
		}
	}
	
	private String[] saisieP(Scanner sc) {
		System.out.print("Nom ? ");
		String n = sc.nextLine();
		
		System.out.print("Prenom ? ");
		String p = sc.nextLine();
		
		System.out.print("Adresse ? ");
		String a = sc.nextLine();

		boolean validCP;
		String cp;
		do{
			System.out.print("Code Postal ? ");
			cp = sc.nextLine();
			try {
				Format.checkCP(cp);//Contrainte : 5 chiffres
				validCP = true;
			} catch(FormatException e) {
				System.out.println(e.getMessage());
				validCP = false;
			}
		} while(!validCP);
		
		System.out.print("Ville ? ");
		String v = sc.nextLine();
		
		String[] data = {p, n, a, v, cp};
		return  data;
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
		print += "\n\nNombre de clients : "+clients.size();
		for(Client c : clients.values()) {
			print += "\n\t"+c.toString();
		}
		//fournissuers
		print += "\n\nNombre de clients : "+clients.size();
		for(Fournisseur f : fournisseurs.values()) {
			print += "\n\t"+f.toString();
		}
		
		return print;
	}

}
