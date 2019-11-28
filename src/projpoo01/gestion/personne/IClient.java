package projpoo01.gestion.personne;

import java.util.List;

public interface IClient {

	public void achete(List<Achat> achats);
	public boolean paie();
}
