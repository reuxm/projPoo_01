package projpoo01.gestion.personne;

import java.util.List;

public interface IFournisseur {

	public boolean livre();
	public void command(List<Commande> commandes);
	
}
