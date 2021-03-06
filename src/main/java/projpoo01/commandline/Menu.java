package projpoo01.commandline;

import java.util.HashMap;
import java.util.Map;

import projpoo01.Reseau;
import projpoo01.gestion.personne.*;
import projpoo01.validity.FileInteractionException;
import projpoo01.validity.NoOptionException;

/**
 * Menu de l'application en ligne de commande <br> Les donnees sont en dur
 * dans la classe : pas de personnalisation possible
 * 
 * @author Matthias
 *
 */
public class Menu {

	private Reseau reseau;
	private Saisie saisie;
	
	/**
	 * Constructeur du menu.<br>Cette classe necessite un {@link Reseau} a remplir
	 * et une classe de {@link Saisie}
	 * 
	 * @param r le {@link Reseau} a remplir
	 * @param s l'objet permettant d'obtenir les donnees utilisateur
	 */
	public Menu(Reseau r, Saisie s) {
		this.reseau = r;
		this.saisie = s;
	}
	
	/**
	 * Lance le menu principal
	 */
	public void menu(){
		String[] keys = {
			"Saisir des Personnes",
			"Nommer un patron",
			"Afficher les collaborateurs",
			"Action clients",
			"Action fournisseur",
			"Sauver",
			"Quitter"
		};
		MenuAction[] actions = {
			()->saisie.saisieInitiale(),
			()->patron(),
			()->System.out.println( reseau ),
			()->actionClients(),
			()->actionFournisseur(),
			()->save(),
			()->{;}//do nothing = quit
		};
		pick(keys, actions, 0, true);
	}

	private void patron() {
		System.out.println("1> Nommer un patron");
		if(reseau.getPatron()!=null) {
			System.out.println("ATTENTION : ");
			boolean confirme = Saisie.getBoolean("Un patron existe deja. Voulez vous le remplacer ? [Y/N]");
			if(!confirme) { return; }
		}

		String[] keys = {
			"Creer un patron",
			"Promouvoir une salarie",
			"Annuler"
		};
		MenuAction[] actions = {
			()->saisie.saisiePatron(),
			()->saisie.choosePatron(),
			()->{;}//do nothing = quit
		};
		pick(keys, actions, 1, false);
	}
	
	private void actionClients() {
		IClient client;
		try {
			client = saisie.selectIClient();
		} catch (NoOptionException e) {
			System.out.println( e.getMessage() );
			return;
		}

		String[] keys = {
			"faire des achats",
			"consulter historique",
			"payer",
			"annuler"
		};
		MenuAction[] actions = {
			()->client.achete( saisie.saisieAchats() ),
			()->{
				System.out.println(client);
				((Personne)client).printHisto();
			},
			()->client.paie(),
			()->{;}//do nothing = quit
		};
		pick(keys, actions, 1, true);
	}
	
	private void actionFournisseur() {
		System.out.println("Pas encore implementé m(_ _)m");
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
	
	/**
	 * Choisi un option dans la list donnee
	 * 
	 * @param keys le texte affiche pour chaque option
	 * @param values l'action effectuee pour chaque option
	 * @param level le niveau d'indentation
	 * @param loop fait-on plusieurs actions avant de revenir au menu precedent
	 */
	private void pick(String[] keys, MenuAction[] values, int level, boolean loop) {
		Map<String, MenuAction> actions = menuFrom(keys, values, level);

		int choice = -1;
		while( choice!=actions.size() ) {
			
			System.out.println("\nActions possible : ");
			actions.keySet().stream().sorted().forEach( System.out::println );
			choice = Saisie.getInt("Votre choix ? ");
			try{
				values[choice-1].act();
				if(!loop) { break;}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Ce n'est pas une option valide");
			}
		}
	}
	
	/**
	 * Cree une {@link Map} avec les clefs numerotees et indentees
	 * 
	 * @param keys les valeurs a formater et afficher
	 * @param values les actions liees aux clefs
	 * @param tabs le nombre d'indentations souhaitees
	 * @return une {@link Map} respectant les conditions donnees
	 * @throws ArrayIndexOutOfBoundsException si keys et values sont de taille differentes
	 */
	public static Map<String, MenuAction> menuFrom(String[] keys, MenuAction[]values, int tabs) throws ArrayIndexOutOfBoundsException {
		if(keys.length != values.length) {
			throw new ArrayIndexOutOfBoundsException("");
		}
		Map<String, MenuAction> actions = new HashMap<String, MenuAction>();
		for(int i=0 ; i<keys.length ; i++) {
			String prompt = (i+1)+"> ";
			for(int l=0 ; l<tabs ; l++) {
				prompt = "\t"+prompt;
			}
			actions.put( prompt+keys[i], values[i] );
		}
		return actions;
	}
}
