package projpoo01.util;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

public interface ListLabeler<T> {

	public Map<Integer, T> numericLabel(Collection<? extends T> values);
	public Map<Integer, T> numericLabel(Collection<? extends T> values, Function<T, Boolean> conditional);
	
}
