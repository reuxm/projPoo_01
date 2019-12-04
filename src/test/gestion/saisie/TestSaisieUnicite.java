package test.gestion.saisie;

import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import projpoo01.validity.Format;
import projpoo01.validity.FormatException;

public class TestSaisieUnicite {
	
	private final Set<String> list = new HashSet<String>(java.util.Arrays.asList("a", "b"));

	@Test
	public void testUnicite() {
		try {
			Format.checkPK("c", list, "");
		} catch (FormatException e) {
			fail("Exception inattendu lors du cas nominal");
		}
	}
	
	@Test(expected = FormatException.class)
	public void testUnicite_doublon() throws FormatException {
		Format.checkPK("a", list, "");
	}
	
	public void testUnicite_doublonUpper() {
		try {
			Format.checkPK("A", list, "");
		} catch (FormatException e) {
			fail("Exception inattendu lors du cas nominal");
		}

	}

}
