package projpoo01;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import projpoo01.gestion.personne.*;

public class Reseau {

	private Map<String, Client> clients;
	private Map<String, Fournisseur> fournisseurs;
	private List<Salarie> salaries;

	public Reseau() {
		clients = new HashMap<String, Client>();
		fournisseurs = new HashMap<String, Fournisseur>();
		salaries = new ArrayList<Salarie>();
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
			sc.nextLine();//conscome le EOL laissé par le nextInt() precedant
			switch(categorie) {
			case "salarié":saisieS(groupSize, sc);break;
			case "client":saisieC(groupSize, sc);break;
			default:saisieF(groupSize ,sc);//default vaut toujours fournisseur
			}
		}
		
		sc.close();
	}

	private void saisieC(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			System.out.print("Client "+i+" : \nN°Client ? ");
			String numClient=sc.nextLine();
			
			String[] newP = {};
			try{
				newP = saisieP(sc);
			} catch(IllegalArgumentException e) {
				System.err.println(e.getMessage());
				i--;//entrée non comptabilisée
				continue;
			}
			
			try {
				clients.put(
					numClient,
					new Client(newP[0], newP[1], newP[2], newP[3], newP[4], numClient)
				);
				
			} catch(IllegalArgumentException e) {//Map n'accpte pas 2 clés identique : unicité garantie
				System.out.println("Impossible de créer le client.\n"
					+ "Verifiez les informations saisies, le numero de client n'existe t'il pas déjà?");
				i--;//entrée non comptabilisée
				//continue;//not needed : end of loop
			}
		}
	}

	private void saisieF(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			System.out.print("Fournisseur "+i+" :\nN°Fournisseur ? ");
			String numFour=sc.nextLine();
			String[] newP = {};
			
			try{
				newP = saisieP(sc);
			} catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				i--;//entrée non comptabilisée
				continue;
			}
			
			try {
				fournisseurs.put(
					numFour,
					new Fournisseur(newP[0], newP[1], newP[2], newP[3], newP[4], numFour)
				);
				
			} catch(IllegalArgumentException e) {//Map n'accpte pas 2 clés identique : unicité garantie
				System.out.println("Impossible de créer le client.\n"
					+ "Verifiez les informations saisies, le numero de fournisseur n'existe t'il pas déjà?");
				i--;//entrée non comptabilisée
				//continue;//not needed : end of loop
			}
		}
		
	}

	private void saisieS(int groupSize, Scanner sc) {
		for(int i=1;i<=groupSize;i++) {
			System.out.print("Salarié "+i+" :\nN° de securité sociale ? ");
			String insee=sc.nextLine();
			if(!Format.checkInsee(insee)) {
				System.out.println("Numero de securité sociale invalide, 13 chiffres requis!");
				i--;//entrée non comptabilisée
				continue;
			}  
			
			System.out.print("Salaire (precision max 0,01€)? ");
			String salaire=sc.nextLine();
			double salaireFormaté=0;
			try {//test centimes
				salaireFormaté = Double.parseDouble(salaire.replace(',', '.'));
				new DecimalFormat("###,###,###.00").format(salaireFormaté);
			} catch (ArithmeticException e) {
				System.out.println("Format de salaire non supporté : XXXXXX.XX uniquement");
				i--;//entrée non comptabilisée
				continue;
			}
			
			String[] newP = {};
			try{
				newP = saisieP(sc);
			} catch(IllegalArgumentException e) {
				System.out.println(e.getMessage());
				i--;//entrée non comptabilisée
				continue;
			}
			salaries.add( new Salarie(newP[0], newP[1], newP[2], newP[3], newP[4], insee, salaireFormaté) );
			
			
		}
		
	}
	
	private String[] saisieP(Scanner sc) throws IllegalArgumentException {
		System.out.print("Nom ? ");
		String n = sc.nextLine();
		
		System.out.print("Prenom ? ");
		String p = sc.nextLine();
		
		System.out.print("Adresse ? ");
		String a = sc.nextLine();
		
		System.out.print("Code Postal ? ");
		String cp = sc.nextLine();
		if(!Format.checkCP(cp)) {
			throw new IllegalArgumentException("Code postal invalide, 5 chiffres requis");
		}
		
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
		for(Salarie s : salaries) {
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
