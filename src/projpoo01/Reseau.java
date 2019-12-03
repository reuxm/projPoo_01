package projpoo01;

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
		Reseau reseau = new Reseau();
		Saisie saisie = new Saisie(reseau);
		saisie.saisieInitiale();
		new Menu(reseau, saisie).menu();
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
