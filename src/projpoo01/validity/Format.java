package projpoo01.validity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Format {

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

	public static void checkInsee(String insee) throws FormatException {
		boolean b = false;
		try {
			Long.parseLong(insee);//int too small for 13 digits
		} catch (NumberFormatException e) {
			b = true;
		}
		b |= insee.length()!=13;
		if(b) {
			throw new FormatException("Numero de securité sociale invalide, 13 chiffres requis!");
		}
	}
	
	public static double checkSalaire(String salaire) throws FormatException {
		try{
			double salaireFormaté = Double.parseDouble(salaire.replace(',', '.'));
			new DecimalFormat("###,###,###.00").format(salaireFormaté);
			return salaireFormaté;
		} catch (NumberFormatException e) {
			throw new FormatException("Format de salaire non supporté : XXXXXX.XX uniquement");
		}
	}

	public static void checkPK(String key, Set<String> keySet, String nomage) throws FormatException {
		if(keySet.contains(key) ) {
			throw new FormatException("Le "+nomage+" existe déjà associé à une autre personne ou entité");
		}
	}

	public static boolean checkBoolean(String txt) throws FormatException {
		List<String> literalT = new ArrayList<String>(Arrays.asList("Y","y","O","o"));
		List<String> literalF = new ArrayList<String>(Arrays.asList("N","n"));
		if(literalF.contains(txt) || literalT.contains(txt)) {
			return literalT.contains(txt);
		}
		throw new FormatException("Booleen non reconnu");
	}
}
