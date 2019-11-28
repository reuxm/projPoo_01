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
		reseau.saisie();
		System.out.println(reseau);
	}
	
	public void saisie() {
		Scanner sc = new Scanner(System.in);
		
		String[] categories= {"salarié","client","fournisseur"};
		for( String categorie:categories) {
			System.out.print("Combien de "+categorie+"(s) ? : ");
			int groupSize = sc.nextInt();
			sc.nextLine();//conssome le EOL laissé par le nextInt() precedent
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
			try{
				System.out.print("Client "+i+" : \nN°Client ? ");
				String numClient=sc.nextLine();
				Format.checkPK(numClient, clients.keySet(), "numero Client");//Contrainte : unicité
				
				String[] newP = saisieP(sc);
				
				System.out.println("Ce client est-il aussi un fournisseur ? [Y:N]");
				String fournisseur = sc.nextLine();
				boolean f = Format.checkBoolean(fournisseur);
				clients.put(
					numClient,
					new Client(newP[0], newP[1], newP[2], newP[3], newP[4], numClient, f)
				);
			} catch (FormatException e) {
				System.out.println(e.getMessage());
				i--;//entrée non comptabilisée
			}
		}
	}

	private void saisieF(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			try{
				System.out.print("Fournisseur "+i+" :\nN°Fournisseur ? ");
				String numFour=sc.nextLine();
				Format.checkPK(numFour, fournisseurs.keySet(), "numero Fournissuer");//Contrainte : unicité
				
				String[] newP = saisieP(sc);
				
				System.out.println("Ce fournissseur est-il aussi un client ? [Y:N]");
				String client = sc.nextLine();
				boolean c = Format.checkBoolean(client);
				fournisseurs.put(
					numFour,
					new Fournisseur(newP[0], newP[1], newP[2], newP[3], newP[4], numFour, c)
				);
			} catch (FormatException e) {
				System.out.println(e.getMessage());
				i--;//entrée non comptabilisée
			}
		}
	}

	private void saisieS(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			try{
				System.out.print("Salarié "+i+" :\nN° de securité sociale ? ");
				String insee=sc.nextLine();
				Format.checkInsee(insee) ;//Contrainte : 13 chiffres 
				Format.checkPK(insee, salaries.keySet(), "numero de securite sociale");//Contrainte : unicité
				
				System.out.print("Salaire (precision max 0,01€)? ");
				String salaire=sc.nextLine();
				double salaireFormaté= Format.checkSalaire(salaire);
				
				String[] newP = saisieP(sc);
				
				System.out.println("Ce fournissseur est-il aussi un client ? [Y:N]");
				String client = sc.nextLine();
				boolean c = Format.checkBoolean(client);
				
				System.out.println("Ce client est-il aussi un fournisseur ? [Y:N]");
				String fournisseur = sc.nextLine();
				boolean f = Format.checkBoolean(fournisseur);
				
				salaries.put(
					insee,
					new Salarie(newP[0], newP[1], newP[2], newP[3], newP[4], insee, salaireFormaté, c, f)
				);
			} catch(FormatException e) {
				System.out.println(e.getMessage());
				i--;//entrée non comptabilisée
			} 
		}
	}
	
	private String[] saisieP(Scanner sc) throws FormatException {
		System.out.print("Nom ? ");
		String n = sc.nextLine();
		
		System.out.print("Prenom ? ");
		String p = sc.nextLine();
		
		System.out.print("Adresse ? ");
		String a = sc.nextLine();
		
		System.out.print("Code Postal ? ");
		String cp = sc.nextLine();
		Format.checkCP(cp);//Contrainte : 5 chiffres
		
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
