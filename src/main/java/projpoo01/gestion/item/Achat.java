package projpoo01.gestion.item;

import java.util.Date;

/**
 * Objet representant un achat d'un certain nombre d'objet identiques
 * 
 * @author matthias
 */
public class Achat extends Item {
	
	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -3016369385593508290L;
	private Date date;
	private String intitule;
	private int quantite;
	
	public Achat(Date d, String s, int i) {
		this.date = d;
		this.intitule = s;
		this.quantite = i;
	}

	@Override
	public String toString() {
		return quantite+" "+intitule+" - achete le "+date;
	}
	
	/**
	 * @return la date d'achat
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return le nom de l'objet concerne par l'achat
	 */
	public String getIntitule() {
		return intitule;
	}

	/**
	 * @return la quanite d'objets achete
	 */
	public int getQuantite() {
		return quantite;
	}
	
	
}
