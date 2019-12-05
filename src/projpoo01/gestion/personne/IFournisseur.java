package projpoo01.gestion.personne;

import java.util.List;

import projpoo01.gestion.item.Commande;

/**
 * Represente une personne agissant en tant que fournisseur
 * 
 * @author Matthias
 */
public interface IFournisseur {

	/**
	 * Recoit une commande
	 * 
	 * @param achats la liste des produits commandes
	 */
	public void command(List<Commande> commandes);
	
	/**
	 * receptionne la livraison
	 * 
	 * @return une confirmation de la reception
	 */
	public boolean livre();
	
	/**
	 * Verifie si une personne a une activite de fournisseur
	 */
	public boolean isFournisseur();
	
}
