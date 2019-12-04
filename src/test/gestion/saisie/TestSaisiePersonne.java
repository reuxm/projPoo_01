package test.gestion.saisie;

import static org.junit.Assert.*;

import org.junit.Test;

import projpoo01.validity.Format;
import projpoo01.validity.FormatException;

public class TestSaisiePersonne {
	
	@Test
	public void testCP() {
		try {
			Format.checkCP("12345");
		} catch (FormatException e) {
			fail("Exception inattendu lors du cas nominal");
		}
	}

	@Test(expected = FormatException.class)
	public void testCP_withLetters() throws FormatException {
		Format.checkCP("abcde");
	}

	@Test(expected = FormatException.class)
	public void testCP_tooShort() throws FormatException {
		Format.checkCP("1234");
	}

	@Test(expected = FormatException.class)
	public void testCP_tooLong() throws FormatException {
		Format.checkCP("123456");
	}

	@Test(expected = FormatException.class)
	public void testCP_empty() throws FormatException {
		Format.checkCP("");
	}

}
