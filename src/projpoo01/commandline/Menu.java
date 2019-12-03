package projpoo01.commandline;

import java.util.Scanner;

import projpoo01.Reseau;
import projpoo01.gestion.personne.*;
import projpoo01.validity.FileInteractionException;
import projpoo01.validity.Format;
import projpoo01.validity.FormatException;
import projpoo01.validity.NoOptionException;

public class Menu {

	private Reseau reseau;
	private Saisie saisie;
	
	public Menu(Reseau r, Saisie s) {
		this.reseau = r;
		this.saisie = s;
	}
	
	public void menu(){
		Scanner sc = Saisie.getScanner();
		String[] actions = {
			"Saisir des Personnes",
			"Nommer un patron",
			"Afficher les collaborateurs",
			"Action clients",
			"Action fournisseur",
			"Sauver et quitter",
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
				case 1 : //saisir des personnes
					saisie.saisieInitiale();
					break;
				case 2 ://nommer un patron
					patron(sc);
					break;
				case 3 ://afficher les listes
					System.out.println( reseau );
					break;
				case 4 :
					actionClients();
					break;
				case 5 ://action fournisseurs
					actionFournisseur();
					break;
				case 6 ://save & quit
					save();
					choice++;//change la valeur à 'quitter'
				case 7 : break;//quit
				default : System.out.println("Ce n'est pas une option valide");
			}
		} while(choice!=actions.length);
	}

	private void save() {
		System.out.print("Enegistrer sous : ");
		boolean validFile;
		do{
			String target = Saisie.getScanner().nextLine();
			try {
				reseau.save( target );
				validFile = true;
			} catch (FileInteractionException e) {
				System.out.println(e.getMessage());
				validFile = false;
			}
		} while( !validFile );
	}

	private void patron(Scanner sc) {
		System.out.println("1> Nommer un patron");
		if(reseau.getPatron()!=null) {
			System.out.println("ATTENTION : Un patron existe deja. Voulez vous le remplacer ? [Y/N]");
			boolean confirme = true;
			boolean validB;
			do {
				String confirmLiteral = sc.nextLine();
				try {
					confirme = Format.checkBoolean(confirmLiteral);
					validB = true;
				} catch(FormatException e) {
					System.out.println(e.getMessage());
					validB = false;
				}
			} while(!validB);
			if(!confirme) {
				return;
			}
		}
		
		boolean validChoice;
		do {
			System.out.println("\t> Creer un patron\n"
				+ "\t2> Promouvoir une salarie\n"
				+ "\t3> Annuler"
			);
			
			int choice = -1;
			boolean nan;
			do {
				System.out.print("\tVotre choix ? ");
				String choiceLiteral = sc.nextLine();
				try {
					choice = Integer.parseInt(choiceLiteral);
					nan = false;
				} catch(NumberFormatException e) {
					System.out.println("Entrez un nombre SVP");
					nan = true;
				}
	
			}while(nan);
			
			Salarie s;
			validChoice = true;
			switch(choice) {
				case 1 :
					s = saisie.saisiePatron( sc );
					reseau.setPatron( s );
					break;
				case 2 :
					s = saisie.choosePatron();
					reseau.setPatron( s );
					break;
				case 3 ://exit
					break;
				default :
					validChoice = false;
			}	
		}while(!validChoice);
	}
	
	private void actionClients() {
		Scanner sc = Saisie.getScanner();
		
		IClient client;
		try {
			client = saisie.selectIClient();
		} catch (NoOptionException e) {
			System.out.println( e.getMessage() );
			return;
		}
		
		String[] actions = {
			"faire des achats",
			"valider la commande",
			"consulter historique",
			"payer",
			"annuler"
		};
		
		int choice = -1;
		boolean validChoice;
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
			
			validChoice = true;
			switch(choice) {
				case 1 ://faire des achats
					client.achete( saisie.saisieAchats() );
					break;
				case 2 ://payer
					client.paie();
					break;
				case 3 ://historique
					System.out.println(client);
					((Personne)client).printHisto();
				case 4 ://annuler
					break;
				default :
					validChoice = false;
			}
		} while(!validChoice);
	}
	
	private void actionFournisseur() {
		System.out.println("Pas encore implementé m(_ _)m");
	}
}
