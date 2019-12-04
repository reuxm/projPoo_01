package test.gestion.personne;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import projpoo01.gestion.item.Achat;
import projpoo01.gestion.personne.Salarie;

public class TestSalarie {

	private Salarie s;
	private Salarie s1;
	
	private final String oInsee = "ga";
	private final double oSalaire = 0.0;
	private final boolean oClient = false;
	
	private final String oInsee1 = "bu";
	private final double oSalaire1 = 1234567.89;
	private final boolean oClient1 = true;

	private final double nSalaire = 1.0;
	
	@Before
	public void init() {
		s = new Salarie("ga", "bu", "zo", "meu", "buga", oInsee, oSalaire, oClient);
		s1 = new Salarie("ga", "bu", "zo", "meu", "buga", oInsee1, oSalaire1, oClient1);
	}
	
	@Test
	public void testToString() {
		assertNotEquals( "", s.toString() );
		assertNotEquals( "", s1.toString() );
		assertTrue( s1.toString().contains("client") );
		assertFalse( s.toString().contains("patron") );
		assertFalse( s1.toString().contains("patron") );
	}

	@Test
	public void testSalarieFromSalarie() {
		Salarie s2 = new Salarie( s );
		assertNotNull( s2 );
		assertEquals( s.getFirstName(), s2.getFirstName() );
		assertEquals( s.getLastName(), s2.getLastName() );
		assertEquals( s.getAdresse(), s2.getAdresse() );
		assertEquals( s.getCodePostal(), s2.getCodePostal() );
		assertEquals( s.getVill(), s2.getVill() );
		assertEquals( s.getInsee(), s2.getInsee() );
		assertEquals( s.getSalaire(), s2.getSalaire() );
		assertEquals( new Boolean(s.isClient()), new Boolean(s2.isClient()) );
	}

	@Test
	public void testSalarie() {
		assertNotNull( s );
	}

	@Test
	public void testGetSalaire() {
		assertEquals( "0.00 €", s.getSalaire() );
		assertEquals( "1 234 567.89 €", s1.getSalaire() );//NB espace insecables
	}

	@Test
	public void testGetSalaireValue() {
		assertEquals( new Double(oSalaire), new Double(s.getSalaireValue()) );
		testSetSalaire();
	}
	
	@Test
	public void testSetSalaire() {
		s.setSalaire( nSalaire );
		assertEquals( new Double(nSalaire), new Double(s.getSalaireValue()) );
	}

	@Test
	public void testGetInsee() {
		assertEquals( oInsee, s.getInsee() );
	}

	@Test
	public void testAchete() {
		assertEquals( new Integer(0), new Integer(s.getHisto().size()) );
		Achat achat = new Achat(new Date(), "1", 1);
		s.achete( java.util.Arrays.asList( achat ) );
		assertEquals( new Integer(1), new Integer(s.getHisto().size()) );
		assertEquals( achat, s.getHisto().get(0).items().get(0));
	}

	@Test
	public void testPaie() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsClient() {
		assertFalse( s.isClient() );
		assertTrue( s1.isClient() );
	}

}
