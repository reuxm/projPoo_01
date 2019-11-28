package projpoo01.gestion.personne;

import java.util.List;

import projpoo01.gestion.item.Commande;

public interface IFournisseur {

	public boolean livre();
	public void command(List<Commande> commandes);
	public boolean isFournisseur();
	
}
