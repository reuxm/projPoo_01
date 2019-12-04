package test.gestion.personne;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import projpoo01.gestion.personne.Patron;
import projpoo01.gestion.personne.Salarie;

public class TestPatron {
	
	private Patron p;
	
	@Before
	public void init() {
		p = new Patron( new Salarie("ga", "bu", "zo", "meu", "buga", "bubu", 1.0, false) );
	}

	@Test
	public void testToString() {
		assertNotEquals( "", p.toString() );
		assertTrue( p.toString().contains("patron") );
	}

	@Test
	public void testPatron() {
		assertNotNull( p );
	}

	@Test
	public void testRemunere() {
		fail("Not yet implemented");
	}

	@Test
	public void testEmbauche() {
		fail("Not yet implemented");
	}

	@Test
	public void testLicencie() {
		fail("Not yet implemented");
	}

}
