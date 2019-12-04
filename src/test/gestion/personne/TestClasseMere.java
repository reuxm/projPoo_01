package test.gestion.personne;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.gestion.saisie.TestSaisiePersonne;

@RunWith(Suite.class)
@SuiteClasses({ TestPersonne.class, TestSaisiePersonne.class })
public class TestClasseMere {

}
