package test.gestion.saisie;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import projpoo01.gestion.item.Achat;

public class TestSaisieAchat {

	public Achat achat;
	
	public final Date oDate = new Date();
	public final String oIntitule = "";
	public final int oQte = 1;
	
	@Before
	public void init() {
		achat = new Achat( oDate, oIntitule, oQte );
	}
	
	@Test
	public void testAchat() {
		assertNotNull( achat );
	}
	
	@Test
	public void testGetDate() {
		assertEquals( oDate, achat.getDate());
	}
	
	@Test
	public void testIntitule() {
		assertEquals( oIntitule, achat.getIntitule() );
	}
	
	@Test
	public void testQte() {
		assertEquals( new Integer(oQte), new Integer(achat.getQuantite()) );
	}
}
