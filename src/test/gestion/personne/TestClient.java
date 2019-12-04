package test.gestion.personne;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import projpoo01.gestion.item.Achat;
import projpoo01.gestion.personne.Client;

public class TestClient {

	Client c;
	Client c1;

	private String oId = "foo";
	private boolean oFour = false;
	
	private String oId1 = "bar";
	private boolean oFour1 = true;
	
	@Before
	public void init() {
		c = new Client("ga", "bu", "zo", "meu", "buga", oId, oFour);
		c1 = new Client("ga", "bu", "zo", "meu", "buga", oId1, oFour1);
	}
	
	@Test
	public void testToString() {
		assertNotEquals( "", c.toString());
		assertNotEquals( "", c1.toString());
	}

	@Test
	public void testClient() {
		assertNotNull( c );
		assertNotNull( c1 );
	}

	@Test
	public void testIsClient() {
		assertTrue( c.isClient() && c1.isClient() );
	}

	@Test
	public void testAchete() {
		assertEquals( new Integer(0), new Integer(c.getHisto().size()) );
		Achat achat = new Achat(new Date(), "1", 1);
		c.achete( java.util.Arrays.asList( achat ) );
		assertEquals( new Integer(1), new Integer(c.getHisto().size()) );
		assertEquals( achat, c.getHisto().get(0).items().get(0));
	}

	@Test
	public void testPaie() {
		fail("Not yet implemented");
	}

	@Test
	public void testLivre() {
		fail("Not yet implemented");
	}

	@Test
	public void testCommand() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsFournisseur() {
		assertFalse( c.isFournisseur() );
		assertTrue( c1.isFournisseur() );
	}

}
