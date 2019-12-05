package projpoo01.validity;

/**
 * Exception levee lorsqu'une donnee incorecte est saisie
 * 
 * @author matthias
 */
public class FormatException extends Exception {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = 264068810656368437L;

	public FormatException(String string) {
		super(string);
	}

}
