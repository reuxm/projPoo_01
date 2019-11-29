package projpoo01.commandline;

import java.util.Scanner;

import projpoo01.Reseau;
import projpoo01.gestion.personne.Client;
import projpoo01.gestion.personne.Fournisseur;
import projpoo01.gestion.personne.Salarie;
import projpoo01.validity.Format;
import projpoo01.validity.FormatException;

public class Saisie {

	private Reseau reseau;
	private static Scanner scanner = new Scanner(System.in);
	
	public Saisie(Reseau r) {
		this.reseau = r;
	}

	public void saisieC(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			String numClient;
			boolean validID;
			do{
				System.out.print("Client "+i+" : \nN°Client ? ");
				numClient=sc.nextLine();
				try {//Contrainte : unicité
					Format.checkPK(numClient, reseau.getClients().keySet(), "numero Client");
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
			
			reseau.getClients().put(
				numClient,
				new Client(newP[0], newP[1], newP[2], newP[3], newP[4], numClient, f)
			);
		}
	}

	public void saisieF(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			String numFour;
			boolean validID;
			do {
				System.out.print("Fournisseur "+i+" :\nN°Fournisseur ? ");
				numFour=sc.nextLine();
				try {//Contrainte : unicité
					Format.checkPK(numFour, reseau.getFournisseurs().keySet(), "numero Fournissuer");
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
				
				reseau.getFournisseurs().put(
					numFour,
					new Fournisseur(newP[0], newP[1], newP[2], newP[3], newP[4], numFour, c)
				);
			
		}
	}

	public void saisieS(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			String insee;
			boolean validInsee;
			do {
				System.out.print("Salarié "+i+" :\nN° de securité sociale ? ");
				insee=sc.nextLine();
				try{
					Format.checkInsee(insee) ;//Contrainte : 13 chiffres 
					Format.checkPK(insee, reseau.getSalaries().keySet(), "numero de securite sociale");//Contrainte : unicité
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
			
			reseau.getSalaries().put(
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

	public static Scanner getScanner() {
		return scanner;
	}
	
}