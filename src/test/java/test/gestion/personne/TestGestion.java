package test.gestion.personne;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.gestion.saisie.TestSaisieAchat;
import test.gestion.saisie.TestSaisieUnicite;

@RunWith(Suite.class)
@SuiteClasses({ TestClient.class, TestFournisseur.class, TestSaisieUnicite.class, TestSaisieAchat.class })
public class TestGestion {

}
