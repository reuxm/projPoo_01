package projpoo01.gestion.personne;

import java.util.List;

import projpoo01.gestion.item.Achat;

public interface IClient {

	public void achete(List<Achat> achats);
	public boolean paie();
	public boolean isClient();
}
