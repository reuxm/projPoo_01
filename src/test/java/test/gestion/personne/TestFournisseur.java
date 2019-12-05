package test.gestion.personne;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import projpoo01.gestion.item.Achat;
import projpoo01.gestion.personne.Client;
import projpoo01.gestion.personne.Fournisseur;

public class TestFournisseur {
	
	Fournisseur f;
	Fournisseur f1;

	private String oId = "foo";
	private boolean oClient = false;
	
	private String oId1 = "bar";
	private boolean oClient1 = true;
	
	@Before
	public void init() {
		f = new Fournisseur("ga", "bu", "zo", "meu", "buga", oId, oClient);
		f1 = new Fournisseur("ga", "bu", "zo", "meu", "buga", oId1, oClient1);
	}
	

	@Test
	public void testToString() {
		assertNotEquals( "", f.toString());
		assertNotEquals( "", f1.toString());
	}

	@Test
	public void testFournisseur() {
		assertNotNull( f );
		assertNotNull( f1 );
	}

	@Test
	public void testIsFournisseur() {
		assertTrue( f.isFournisseur() && f1.isFournisseur() );
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
	public void testAchete() {
		assertEquals( new Integer(0), new Integer(f.getHisto().size()) );
		Achat achat = new Achat(new Date(), "1", 1);
		f.achete( java.util.Arrays.asList( achat ) );
		assertEquals( new Integer(1), new Integer(f.getHisto().size()) );
		assertEquals( achat, f.getHisto().get(0).items().get(0));
	}

	@Test
	public void testPaie() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsClient() {
		assertFalse( f.isClient() );
		assertTrue( f1.isClient() );
	}

}
