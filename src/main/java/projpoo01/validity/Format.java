package projpoo01.validity;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 * Classe verifiant la validite des donnees circulant dans l'application
 * 
 * @author Matthias
 */
public class Format {

	/**
	 * Verifie le code postal<br>
	 * la contrainte est : le code postal doit etre compose d'exactement 5 chiffres
	 * 
	 * @param cp le code postal a valider
	 * @throws FormatException si la contrainte n'est pas validee
	 */
	public static void checkCP(String cp) throws FormatException {
		boolean b = false;
		try {
			Integer.parseInt(cp);
		} catch (NumberFormatException e) {
			b = true;
		}
		b |= cp.length()!=5;
		if(b) {
			throw new FormatException("Code postal invalide, 5 chiffres requis");
		}
	}

	/**
	 * Verifie le numero de securite social<br>
	 * la contrainte est : le numero doit etre compose d'exactement 13 chiffres
	 * 
	 * @param cp le numero a valider
	 * @throws FormatException si la contrainte n'est pas validee
	 */
	public static void checkInsee(String insee) throws FormatException {
		boolean b = false;
		try {
			Long.parseLong(insee);//int too small for 13 digits
		} catch (NumberFormatException e) {
			b = true;
		}
		b |= insee.length()!=13;
		if(b) {
			throw new FormatException("Numero de securite sociale invalide, 13 chiffres requis!");
		}
	}
	
	/**
	 * Verifie le format du salaire<br>
	 * le salaire doit etre compose de chiffres, avec un point ou une virgule comme
	 * separateur
	 * 
	 * @param salaire le salaire a tester
	 * @return le salaire choisi sous forme numerique
	 * @throws FormatException si la contrainte n'est pas validee
	 */
	public static double checkSalaire(String salaire) throws FormatException {
		try{
			double salaireFormate = Double.parseDouble(salaire.replace(',', '.'));
			new DecimalFormat("###,###,###.00").format(salaireFormate);
			return salaireFormate;
		} catch (NumberFormatException e) {
			throw new FormatException("Format de salaire non supporte : XXXXXX.XX uniquement");
		}
	}

	/**
	 * Verifie la contrainte d'unicite d'une clef
	 * 
	 * @param key la clef a tester
	 * @param keySet la liste des clefs auxquelles comparer l'entree
	 * @param nomage le nom de la clef a mettre dans les messages d'erreurs
	 * @throws FormatException si la contrainte n'est pas validee
	 */
	public static void checkPK(String key, Set<String> keySet, String nomage) throws FormatException {
		if(keySet.contains(key) ) {
			throw new FormatException("Le "+nomage+" existe deja associe a une autre personne ou entite");
		}
	}

	/**
	 * Verifie que l'entree est une date et la formate
	 * 
	 * @param value la donnee a tester
	 * @return l'entree sous forme de Date au format défini par {@link Locale.FRANCE}
	 * @throws FormatException si l'entree n'est pas une date
	 */
	public static Date checkDate(String value) throws FormatException {
		if(value.equals("")) {
			return new Date();
		}
		Date d;
		try {
			d = DateFormat.getDateInstance( DateFormat.LONG, Locale.FRANCE ).parse( value );
		} catch (ParseException e) {
			d=null;
		}
		if(d!=null) {
			return d;
		}
		throw new FormatException("Date invalide");
		
	}
}
