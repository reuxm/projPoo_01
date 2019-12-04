package projpoo01.gestion.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transaction implements Serializable{

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -6262927591634991570L;
	
	public static int number=1;
	
	private int id;
	private List<Item> items;
	private boolean paye;
	
	public Transaction(List<? extends Item> list) {
		 items = new ArrayList<Item>();
		 for(Item i : list) {
			 items.add( i );
		 }
		 paye = false;
		 number++;
		 id = number;
		 
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
