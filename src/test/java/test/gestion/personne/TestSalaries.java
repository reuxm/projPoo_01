package test.gestion.personne;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.gestion.saisie.TestSaisieSalarie;

@RunWith(Suite.class)
@SuiteClasses({ TestPatron.class, TestSalarie.class, TestSaisieSalarie.class })
public class TestSalaries {

}
