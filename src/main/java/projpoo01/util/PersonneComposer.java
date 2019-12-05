package projpoo01.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import projpoo01.gestion.personne.Personne;

/**
 * L'instance de {@link ListLabeler} utilisee pour nos {@link Personne}
 * 
 * @author Matthias
 *
 * @param <T>
 */
public class PersonneComposer<T extends Personne> implements ListLabeler<Personne> {

	@Override
	public Map<Integer, Personne> numericLabel(Collection<? extends Personne> personnes) {
		return numericLabel(personnes, new Function<Personne, Boolean>() {
			@Override
			public Boolean apply(Personne arg0) {
				return true;
			}
		});
	}

	@Override
	public Map<Integer, Personne> numericLabel(Collection<? extends Personne> personnes, Function<Personne, Boolean> conditional) {
		Map<Integer, Personne> map = new HashMap<Integer, Personne>();
		int i = 1;
		for(Personne p : personnes) {
			if(conditional.apply(p)) {
				map.put(i, p);
				i++;
			}
		}
		return map;
	}

	
}
