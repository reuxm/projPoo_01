package projpoo01.validity;

import java.io.IOException;

/**
 * Exception levee en cas d'erreur lors de la manipulation des fichiers de sauvegarde
 * 
 * @author Matthias
 */
public class FileInteractionException extends IOException {

	/**
	 * Generated serial version ID
	 */
	private static final long serialVersionUID = -1477619776576438475L;

	public FileInteractionException(String message) {
		super( message );
	}

}
