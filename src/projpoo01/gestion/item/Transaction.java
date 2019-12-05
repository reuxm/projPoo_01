package projpoo01.gestion.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation d'une action impliquant des {@link Item} que l'on souhaite enregistrer,
 * 
 * @author Matthias
 */
public class Transaction implements Serializable{

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -6262927591634991570L;
	
	private static int number=1;
	
	private int id;
	private List<Item> items;
	private boolean paye;
	
	/**
	 * Creation de la transaction a partir des echange la constituannt
	 * 
	 * @param list les echanges a prendre en compte
	 */
	public Transaction(List<? extends Item> list) {
		 items = new ArrayList<Item>();
		 for(Item i : list) {
			 items.add( i );
		 }
		 paye = false;
		 number++;
		 id = number;
		 
	}
	
	public List<Item> items(){
		return items;
	}
	
	@Override
	public String toString() {
		String print = "Transaction "+id+"[";
		for(Item i : items) {
			print += "\n"+i;
		}
		print+="\n\tpaye : "+paye+"\n]";
		
		return print;
	}
}
