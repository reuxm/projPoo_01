package projpoo01.gestion.personne;

import java.util.List;

import projpoo01.gestion.item.Achat;

/**
 * Represente une personne agissant en tant que client
 * 
 * @author matthias
 */
public interface IClient {

	/**
	 * Passe une commande
	 * 
	 * @param achats la liste des achats
	 */
	public void achete(List<Achat> achats);
	
	/**
	 * paie la derniere commande impayee
	 * 
	 * @return une confirmationd e payement
	 */
	public boolean paie();
	
	/**
	 * Verifie que la personne a une activite de client
	 */
	public boolean isClient();
}
