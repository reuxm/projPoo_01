package projpoo01.gestion.personne;

public class Personne {

	private String firstName;
	private String lastName;
	private String adresse;
	private String vill;
	private String codePostal;

	public Personne(String firstName, String lastName, String adresse,
			String vill, String codePostal) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.adresse = adresse;
		this.vill = vill;
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return "prenom :" + getFirstName()
		+ ", nom :" + getLastName() + ", adresse :"
		+ getAdresse() + ", ville :" + getVill()
		+ ", code postal" + getCodePostal();
	}
	
	//only getters & setters bellow
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getVill() {
		return vill;
	}
	public void setVill(String vill) {
		this.vill = vill;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
}
