package projpoo01;

import java.text.DecimalFormat;

public class Format {

	public static void checkCP(String cp) {
		boolean b = false;
		try {
			Integer.parseInt(cp);
		} catch (NumberFormatException e) {
			b = true;
		}
		b |= cp.length()!=5;
		if(b) {
			throw new IllegalArgumentException("Code postal invalide, 5 chiffres requis");
		}
	}

	public static void checkInsee(String insee) {
		boolean b = false;
		try {
			Long.parseLong(insee);//int too small for 13 digits
		} catch (NumberFormatException e) {
			b = true;
		}
		b|=insee.length()!=13;
		if(b) {
			System.out.println("Numero de securité sociale invalide, 13 chiffres requis!");
		}
	}
	
	public static double checkSalaire(String salaire) {
		try{
			double salaireFormaté = Double.parseDouble(salaire.replace(',', '.'));
			new DecimalFormat("###,###,###.00").format(salaireFormaté);
			return salaireFormaté;
		} catch (ArithmeticException e) {
			throw new IllegalArgumentException("Format de salaire non supporté : XXXXXX.XX uniquement");
		}
	}
}
