package projpoo01.commandline;

import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import projpoo01.Reseau;
import projpoo01.gestion.personne.*;
import projpoo01.util.PersonneComposer;
import projpoo01.validity.*;

public class Saisie {

	private Reseau reseau;
	private static Scanner scanner = new Scanner(System.in);
	
	public Saisie(Reseau r) {
		this.reseau = r;
	}

	public static Scanner getScanner() {
		return scanner;
	}
	
	public void saisieClients(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			String numClient;
			boolean validID;
			do{
				System.out.print("Client "+i+" : \nNum Client ? ");
				numClient=sc.nextLine();
				try {//Contrainte : unicité
					Format.checkPK(numClient, reseau.getClients().keySet(), "numero Client");
					validID = true;
				} catch(FormatException e) {
					System.out.println(e.getMessage());
					validID = false;
				}
			} while(!validID);
			
			String[] newP = saisiePersonne(sc);

			boolean f = false;
			boolean validB;
			do{
				System.out.println("Ce client est-il aussi un fournisseur ? [Y/N]");
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

	public void saisieFournisseurs(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			String numFour;
			boolean validID;
			do {
				System.out.print("Fournisseur "+i+" :\nNum Fournisseur ? ");
				numFour=sc.nextLine();
				try {//Contrainte : unicité
					Format.checkPK(numFour, reseau.getFournisseurs().keySet(), "numero Fournissuer");
					validID = true;
				} catch (FormatException e) {
					System.out.println(e.getMessage());
					validID = false;
				}
			} while(!validID);
			
			String[] newP = saisiePersonne(sc);

			boolean c = false;
			boolean validB;
			do {
				System.out.println("Ce fournissseur est-il aussi un client ? [Y/N]");
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

	public Patron saisiePatron(Scanner sc) {
		Salarie s = saisieSalarie(sc, "Patron");
		Patron patron = new Patron( s );
		reseau.getSalaries().put(s.getInsee(), patron);
		return patron;
	}
	
	public Salarie choosePatron() {
		Map<Integer, Personne> personnes = new PersonneComposer<Salarie>().numericLabel(reseau.getSalaries().values());
		Scanner sc = Saisie.getScanner();
		
		System.out.println("Liste des salaries : ");
		for(Entry<Integer, Personne> entry : personnes.entrySet()) {
			System.out.println(entry.getKey()+"\t"+entry.getValue());
		}
		boolean validChoice = true;
		boolean nan;
		int choice=-1;
		do {
			System.out.println("Votre choix ? ");
			String input = sc.nextLine();
			try {
				choice = Integer.parseInt(input);
				validChoice=(choice>0 && choice<=personnes.size());
				nan = false;
			} catch (NumberFormatException e) {
				System.out.println("Veuillez entrer un nombre");
				nan = true;
			}
		} while(nan || !validChoice);
		
		return (Salarie) personnes.get(choice);
	}

	private Salarie saisieSalarie(Scanner sc, String namePrompt) {
		String insee;
		boolean validInsee;
		do {
			System.out.print(namePrompt+" :\nNum de securite sociale ? ");
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
		 
		double salaireFormate = 0;
		boolean validSalaire;
		do {
			System.out.print("Salaire (precision max 0,01€)? ");
			String salaire=sc.nextLine();
			try {
				salaireFormate= Format.checkSalaire(salaire);
				validSalaire = true;
			} catch (FormatException e) {
				System.out.println(e.getMessage());
				validSalaire = false;
			}
		} while(!validSalaire);
		String[] newP = saisiePersonne(sc);
		
		boolean c = false;
		boolean validBC;
		do {
			System.out.println("Ce salarie est-il aussi un client ? [Y/N]");
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
			System.out.println("Ce salarie est-il aussi un fournisseur ? [Y/N]");
			String fournisseur = sc.nextLine();
			validBF = true;
			try{
				f = Format.checkBoolean(fournisseur);
			} catch(FormatException e) {
				System.out.println(e.getMessage());
				validBF = false;
			}
		} while(!validBF);
		
		return new Salarie(newP[0], newP[1], newP[2], newP[3], newP[4], insee, salaireFormate, c, f);
	}

	public void saisieSalaries(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			Salarie s = saisieSalarie(sc, "Salarie "+i);
			reseau.getSalaries().put( s.getInsee(), s );	
		}
	}
	
	private String[] saisiePersonne(Scanner sc) {
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
	
}
