package projpoo01.gestion;

import projpoo01.commandline.Saisie;

/**
 * {@link Field} de type {@link String}
 * 
 * @author Matthias
 */
public class StringField implements IField<String>{

	protected String name;
	
	/**
	 * @param fieldName le nom du champ
	 */
	public StringField(String fieldName) {
		this.name = fieldName;
	}
	
	/**
	 * Saisi le champ qui est contraint a etre non vide
	 */
	@Override
	public String saisie() {
		return Saisie.getString(name+" ? ");
	}

}
