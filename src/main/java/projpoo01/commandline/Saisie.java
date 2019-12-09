package projpoo01.commandline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import projpoo01.Reseau;
import projpoo01.gestion.item.*;
import projpoo01.gestion.personne.*;
import projpoo01.util.PersonneComposer;
import projpoo01.validity.*;

/**
 * Classe du projet responsable de la saisie utilisateur
 * 
 * @author Matthias
 *
 */
public class Saisie {

	private Reseau reseau;
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Constructeur de la classe de Saisie
	 * 
	 * @param r le {@link Reseau} dans lequel les informations saisies serront enregistrees
	 */
	public Saisie(Reseau r) {
		this.reseau = r;
	}

	/**
	 * Le scanner de l'application est gere par cette classe
	 * 
	 * @return une reference au {@link java.util.Scanner} utilise dans l'application
	 */
	public static Scanner getScanner() {
		return scanner;
	}

	/**
	 * La saisie initiale du {@link Reseau}, avec ses {@link Salarie}s, ses {@link Client}s et {@link Fournisseur}s
	 */
	public void saisieInitiale() {
		Scanner sc = Saisie.getScanner();
		
		String[] categories= {"salarie","client","fournisseur"};
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
			} while( !validNumber );
			
			switch(categorie) {
				case "salarie":this.saisieSalaries(groupSize);break;
				case "client":this.saisieClients(groupSize);break;
				default:this.saisieFournisseurs(groupSize);//default vaut toujours fournisseur, voir String[] categories
			}
		}
	}
	
	/**
	 * Saisie d'un groupe de {@link Salarie}s
	 * 
	 * @param groupSize le nombre de {@link Salarie}s a saisir
	 */
	public void saisieClients(int groupSize) {
		for(int i=1;i<=groupSize;i++) {
			String numClient;
			boolean validID;
			do{
				System.out.print("Client "+i+" : \nNum Client ? ");
				numClient=scanner.nextLine();
				try {//Contrainte : unicité
					Format.checkPK(numClient, reseau.getClients().keySet(), "numero Client");
					validID = true;
				} catch(FormatException e) {
					System.out.println(e.getMessage());
					validID = false;
				}
			} while(!validID);
			
			String[] newP = saisiePersonne();

			boolean f = getBoolean("Ce client est-il aussi un fournisseur ? [Y/N]");
			
			reseau.getClients().put(
				numClient,
				new Client(newP[0], newP[1], newP[2], newP[3], newP[4], numClient, f)
			);
		}
	}

	/**
	 * Saisie d'un groupe de {@link Fournisseur}s
	 * 
	 * @param groupSize le nombre de {@link Fournisseur}s a saisir
	 */
	public void saisieFournisseurs(int groupSize) {
		for(int i=1;i<=groupSize;i++) {
			String numFour;
			boolean validID;
			do {
				System.out.print("Fournisseur "+i+" :\nNum Fournisseur ? ");
				numFour=scanner.nextLine();
				try {//Contrainte : unicité
					Format.checkPK(numFour, reseau.getFournisseurs().keySet(), "numero Fournisseur");
					validID = true;
				} catch (FormatException e) {
					System.out.println(e.getMessage());
					validID = false;
				}
			} while( !validID );
			
			String[] newP = saisiePersonne();

			boolean c = getBoolean("Ce fournissseur est-il aussi un client ? [Y/N]");
				
			reseau.getFournisseurs().put(
				numFour,
				new Fournisseur(newP[0], newP[1], newP[2], newP[3], newP[4], numFour, c)
			);
			
		}
	}

	/**
	 * Saisie du {@link Patron}
	 * 
	 * @return un {@link Salarie} qui doit etre promu par l'appelant
	 */
	public Salarie saisiePatron() {
		Salarie s = saisieSalarie("Patron");
		//pas d'info suplémentaires à saisir - pour l'instant
		return s;
	}
	
	/**
	 * Selectionne un {@link Salarie} a promouvoir {@link Patron}
	 * 
	 * @return l'heureux elu
	 */
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

	private Salarie saisieSalarie(String namePrompt) {
		String insee;
		boolean validInsee;
		do {
			System.out.print(namePrompt+" :\nNum de securite sociale ? ");
			insee=scanner.nextLine();
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
			String salaire=scanner.nextLine();
			try {
				salaireFormate= Format.checkSalaire(salaire);
				validSalaire = true;
			} catch (FormatException e) {
				System.out.println(e.getMessage());
				validSalaire = false;
			}
		} while(!validSalaire);
		String[] newP = saisiePersonne();
		
		boolean c = getBoolean("Ce salarie est-il aussi un client ? [Y/N]");
		
		return new Salarie(newP[0], newP[1], newP[2], newP[3], newP[4], insee, salaireFormate, c);
	}

	/**
	 * Saisie d'un groupe de {@link Salarie}s
	 * 
	 * @param groupSize le nombre de {@link Salarie}s a saisir
	 */
	public void saisieSalaries(int groupSize) {
		for(int i=1;i<=groupSize;i++) {
			Salarie s = saisieSalarie("Salarie "+i);
			reseau.getSalaries().put( s.getInsee(), s );	
		}
	}
	
	private String[] saisiePersonne() {
		System.out.print("Nom ? ");
		String n = scanner.nextLine();
		
		System.out.print("Prenom ? ");
		String p = scanner.nextLine();
		
		System.out.print("Adresse ? ");
		String a = scanner.nextLine();

		boolean validCP;
		String cp;
		do{
			System.out.print("Code Postal ? ");
			cp = scanner.nextLine();
			try {
				Format.checkCP(cp);//Contrainte : 5 chiffres
				validCP = true;
			} catch(FormatException e) {
				System.out.println(e.getMessage());
				validCP = false;
			}
		} while(!validCP);
		
		System.out.print("Ville ? ");
		String v = scanner.nextLine();
		
		String[] data = {p, n, a, v, cp};
		return  data;
	}
	
	/**
	 * Saisie d'une liste d'{@link Achat}s
	 * 
	 * @return
	 */
	public List<Achat> saisieAchats(){
		List<Achat> achats = new ArrayList<Achat>();
		
		boolean more = false;
		do {
			System.out.print("Intitule de l'article ? ");
			String intitule = scanner.nextLine();

			int qte = getInt("Quantite ? ");
			
			Date date = new Date();;
			boolean validDate;
			do {
				System.out.print("Date d'achat (laissez vide pour indiquer maintenant) ?");
				try {
					date = Format.checkDate( scanner.nextLine() );
					validDate = true;
				} catch (FormatException e) {
					System.out.println( e.getMessage() );
					validDate = false;
				}
			} while( !validDate );
			
			achats.add( new Achat( date, intitule, qte) );
			
			more = getBoolean("Saisir un autre achat ? [Y/N]");
			
		} while( more );
		
		return achats;
	}

	/**
	 * Choix d'un {@link IClient} parmi ceux enregistre
	 * 
	 * @return le {@link} IClient choisi
	 * @throws NoOptionException si aucun IClient n'existe
	 */
	public IClient selectIClient() throws NoOptionException {
		Map<Integer, Personne> clients = reseau.listClients();
		if( clients.isEmpty() ) {
			throw new NoOptionException("Aucun client enregistre");
		}

		for(Entry<Integer, Personne> client : clients.entrySet()) {
			System.out.println( client.getKey()+">\t"+client.getValue() );
		}
		System.out.print("Quel client agit ? ");
		boolean validChoice = true;
		boolean nan;
		int clientKey=-1;
		do {
			System.out.println("Votre choix ? ");
			String input = scanner.nextLine();
			try {
				clientKey = Integer.parseInt(input);
				validChoice=(clientKey>0 && clientKey<=clients.size());
				nan = false;
			} catch (NumberFormatException e) {
				System.out.println("Veuillez entrer un nombre");
				nan = true;
			}
		} while( nan || !validChoice );
		
		return (IClient)clients.get(clientKey);
	}

	public static int getInt(String prompt) {
		int value = 0;
		boolean valid = false;
		while( !valid ) {
			System.out.print( prompt );
			String s = scanner.nextLine();
			if( !s.equals( "" ) ) {
				try {
					value = Integer.parseInt( s );
					valid = true;
				} catch(NumberFormatException e) {
					System.out.print("Veuillez entrer un nombre ");
				}// + loop, valid is still false
			}
		}
		return value;
	}

	public static boolean getBoolean(String prompt) {
		List<String> literalT = new ArrayList<String>(Arrays.asList("Y","y","O","o","1"));
		List<String> literalF = new ArrayList<String>(Arrays.asList("N","n","0"));
		
		boolean value = false;
		boolean valid = false;
		while( !valid ) {
			System.out.print( prompt );
			String s = scanner.nextLine();
			if( !s.equals( "" ) ) {
				if(literalF.contains(s) || literalT.contains(s)) {
					value = literalT.contains(s);
					valid = true;
				}
				else {
					System.out.print("Veuillez entrer Y ou N" );
				}// + loop, valid is still false
			}
		}
		return value;
	}
}
