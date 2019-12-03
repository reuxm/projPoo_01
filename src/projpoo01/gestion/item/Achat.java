package projpoo01.gestion.item;

import java.util.Date;

public class Achat extends Item {
	
	private Date date;
	private String intitule;
	private int quantite;
	
	public Achat(Date d, String s, int i) {
		this.date = d;
		this.intitule = s;
		this.quantite = i;
	}

	public Date getDate() {
		return date;
	}

	public String getIntitule() {
		return intitule;
	}

	public int getQuantite() {
		return quantite;
	}
	
	
}
