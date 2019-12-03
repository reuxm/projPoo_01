package projpoo01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import projpoo01.commandline.*;
import projpoo01.gestion.personne.*;
import projpoo01.util.PersonneComposer;
import projpoo01.validity.FileInteractionException;

public class Reseau implements Serializable {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -454784963153042344L;
	
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
		Reseau reseau = init();
		Saisie saisie = new Saisie(reseau);
		new Menu(reseau, saisie).menu();
	}
	
	public static Reseau init() {
		Scanner sc = Saisie.getScanner();
		System.out.print("Fichier à ouvrir (laisser vide pour creer un nouveau reseau) : ");
		String path = sc.nextLine();
		if( !path.equals("") ) {
			try {
				return load(path);
			} catch (FileInteractionException e) {
				//en cas d'erreur, traiter comme une entree vide -> fait hors du try/catch
			}
		}
		return new Reseau();
	}
	
	public static Reseau load(String target) throws FileInteractionException {
		Reseau reseau;
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream( target );
		} catch (FileNotFoundException e1) {
			throw new FileInteractionException("Fichier introuvable");
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(fis);
			try {
				reseau = (Reseau)ois.readObject();
			} catch (ClassNotFoundException e) {
				throw new FileInteractionException("Fichier au format incorrect");
			} finally {
				ois.close();
				fis.close();
			}
		} catch (IOException e) {
			throw new FileInteractionException("Erreur lors de la lecture");
		}
		
		return reseau;
	}

	public void save(String target) throws FileInteractionException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream( target );
		} catch (FileNotFoundException e1) {
			throw new FileInteractionException("Fichier cible indisponible");
		}
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			throw new FileInteractionException("Echec de l'ecriture");
		}
	}
	
	@Override
	public String toString() {
		String print = "\n### Contacts du réseau :\n"		
		//salariés
			+ "Nombre de salaries : "+salaries.size();
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

	public void setPatron(Salarie p) {
		if( patron != null) {//destitution
			String oldPatronKey = patron.getInsee();
			salaries.put(
					oldPatronKey,
				new Salarie( salaries.get(oldPatronKey) )
			);
		}
		patron = new Patron( p );
		salaries.put(patron.getInsee(), patron);
	}
	
	public Map<Integer, Personne> listClients() {
		Collection<Personne> colaborateurs = new ArrayList<Personne>();
		colaborateurs.addAll( salaries.values() );
		colaborateurs.addAll( clients.values() );
		colaborateurs.addAll( fournisseurs.values() );
		return new PersonneComposer<Personne>().numericLabel(colaborateurs, new Function<Personne, Boolean>() {
			@Override
			public Boolean apply(Personne arg0) {
				return arg0 instanceof IClient && ((IClient)arg0).isClient();
			}
		});
	}
	
	public Map<Integer, Personne> listFournisseurs() {
		Collection<Personne> colaborateurs = new ArrayList<Personne>();
		colaborateurs.addAll( salaries.values() );
		colaborateurs.addAll( clients.values() );
		colaborateurs.addAll( fournisseurs.values() );
		return new PersonneComposer<Personne>().numericLabel(colaborateurs, new Function<Personne, Boolean>() {
			@Override
			public Boolean apply(Personne arg0) {
				return arg0 instanceof IFournisseur && ((IFournisseur)arg0).isFournisseur();
			}
		});
	}

}
