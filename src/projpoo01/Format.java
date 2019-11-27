package projpoo01;

public class Format {

	public static boolean checkCP(String cp) {
		try {
			Integer.parseInt(cp);
		} catch (NumberFormatException e) {
			return false;
		}
		return cp.length()==5;
	}

	public static boolean checkInsee(String insee) {
		try {
			Long.parseLong(insee);//int too small for 13 digits
		} catch (NumberFormatException e) {
			return false;
		}
		return insee.length()==13;
	}
}
