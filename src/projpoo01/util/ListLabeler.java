package projpoo01.util;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * Transforme des Collection d'objets en {@link java.util.Map} dont les valeurs sont les
 * donnees en entree et les clefs des labels numeriques
 * 
 * @author Matthias
 *
 * @param <T> un type de donnees a ordonner
 */
public interface ListLabeler<T> {

	/**
	 * Label simplement les donnes d'entree
	 * 
	 * @param values Donnees d'entree
	 * @return les donnes d'entree avec des labels
	 */
	public Map<Integer, T> numericLabel(Collection<? extends T> values);
	
	/**
	 * Label les doonnes d'entree
	 * 
	 * @param values les donnes d'entree
	 * @param conditional une fonction permettant de selectionner les donnees
	 * @return les donnees d'entree filtree et attachees a des labels
	 */
	public Map<Integer, T> numericLabel(Collection<? extends T> values, Function<T, Boolean> conditional);
	
}
