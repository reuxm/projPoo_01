package projpoo01.commandline;

import java.util.Scanner;

import projpoo01.Reseau;
import projpoo01.validity.Format;
import projpoo01.validity.FormatException;

public class Menu {

	private Reseau reseau;
	
	public Menu(Reseau r) {
		this.reseau = r;
	}
	
	public void menu(){
		Scanner sc = Saisie.getScanner();
		String[] actions = {
			"Nommer un patron",
			"Afficher les collaborateurs",
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
					patron(sc);
					break;
				case 2 ://afficher les listes
					System.out.println( reseau );
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
	
	private void patron(Scanner sc) {
		System.out.println("1> Nommer un patron");
		if(reseau.getPatron()!=null) {
			System.out.println("ATTENTION : Un patron existe déjà. Voulez vous le remplacer ? [Y/N]");
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
		
		boolean validChoice = true;
		do {
			System.out.println("\t1.1> Promouvoir un salarie\n"
				+ "\t1.2> Créer une nouvelle personne\n"
				+ "\t1.3> Annuler"
			);
			
			int choice = -1;
			boolean nan;
			do {
				System.out.print("\t Votre choix ? ");
				String choiceLiteral = sc.nextLine();
				try {
					choice = Integer.parseInt(choiceLiteral);
					nan = false;
				} catch(NumberFormatException e) {
					System.out.println("Entrez un nombre SVP");
					nan = true;
				}
	
			}while(nan);	
			switch(choice) {
				case 1 :
					break;
				case 2 :
					reseau.readPatron();
					break;
				case 3 :
					break;
				default :
					validChoice = false;
			}	
		}while(!validChoice);
	}
	
}
