package projpoo01.gestion.personne.fields;

import projpoo01.commandline.Saisie;
import projpoo01.gestion.StringField;

/**
 * Enregistrement du code postal sous forme de texte<br>
 * Afin d'accepter les differents formats
 * 
 * @author Matthias
 */
public class CPField extends StringField {

	public CPField(String fieldName) {
		super(fieldName);
	}
	
	/**
	 * Saisi un code postal qui doit etre constitue de 5 chiffres
	 */
	@Override
	public String saisie() {
		return Saisie.getCp(name+" ? ");
	}

}
