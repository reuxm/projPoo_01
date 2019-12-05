package test.gestion.saisie;

import static org.junit.Assert.fail;

import org.junit.Test;

import projpoo01.validity.Format;
import projpoo01.validity.FormatException;

public class TestSaisieSalarie {

	@Test
	public void testInsee() {
		try {
			Format.checkInsee("1234567890123");
		} catch (FormatException e) {
			fail("Exception inattendu lors du cas nominal");
		}
	}
	
	@Test(expected = FormatException.class)
	public void testInsee_tooshort() throws FormatException {
		Format.checkInsee("123456789012");
	}
	
	@Test(expected = FormatException.class)
	public void testInsee_toolong() throws FormatException {
		Format.checkInsee("12345678901234");
	}
	
	@Test(expected = FormatException.class)
	public void testInsee_wihletters() throws FormatException {
		Format.checkInsee("abcdefghijklm");
	}

	
	@Test(expected = FormatException.class)
	public void testInsee_empty() throws FormatException {
		Format.checkInsee("");
	}
}
