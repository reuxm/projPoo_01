package test.gestion.personne;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import projpoo01.gestion.item.Achat;
import projpoo01.gestion.item.Transaction;
import projpoo01.gestion.personne.Personne;

public class TestPersonne {
	
	private Personne p;
	
	private final String oPrenom = "John";
	private final String oNom = "Doe";
	private final String oAdresse = "ga";
	private final String oVille = "bu";
	private final String oCP = "zo";

	private final String nPrenom = "foo";
	private final String nNom = "bar";
	private final String nAdresse = "baz";
	private final String nVille = "qux";
	private final String nCP = "quux";
	
	private final Date oDate = new Date();
	private final String oIntitule = "meu";
	private final int oQte = 1;
	private final Transaction transa = new Transaction(
		java.util.Arrays.asList( new Achat(oDate, oIntitule, oQte) )
	);
	
	@Before
	public void init() {
		p = new Personne(oPrenom, oNom, oAdresse, oVille, oCP){
			private static final long serialVersionUID = 1L;
		};//classe anonyme car Personne est abstract
	}

	@Test
	public void testPersonne() {
		assertNotNull( p );
	}

	@Test
	public void testEnregistre() {
		assertEquals( new Integer(0), new Integer( p.getHisto().size() ) );
		p.enregistre( transa );
		assertEquals( new Integer(1), new Integer( p.getHisto().size() ) );
	}

	@Test
	public void testGetHisto() {
		p.enregistre( transa );
		assertEquals( new Integer(1), new Integer(p.getHisto().size()) );
	}

	@Test
	public void testPrintHisto() {
		ByteArrayOutputStream nOut = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		
		System.setOut( new PrintStream(nOut) );
		
		assertEquals( "", nOut.toString());
		p.enregistre( transa );
		p.printHisto();
		assertNotEquals( "", nOut.toString() );
		
		System.setOut( originalOut );
	}

	@Test
	public void testToString() {
		assertNotEquals( "", p.toString() );
	}

	@Test
	public void testGetFirstName() {
		assertEquals( oPrenom, p.getFirstName() );
		testSetFirstName();
	}

	@Test
	public void testSetFirstName() {
		p.setFirstName( nPrenom );
		assertEquals( nPrenom , p.getFirstName() );
	}

	@Test
	public void testGetLastName() {
		assertEquals( oNom, p.getLastName() );
		testSetFirstName();
	}

	@Test
	public void testSetLastName() {
		p.setLastName( nNom );
		assertEquals( nNom, p.getLastName() );
	}

	@Test
	public void testGetAdresse() {
		assertEquals( oAdresse, p.getAdresse() );
		testSetAdresse();
	}

	@Test
	public void testSetAdresse() {
		p.setAdresse( nAdresse );
		assertEquals( nAdresse, p.getAdresse() );
	}

	@Test
	public void testGetVill() {
		assertEquals( oVille, p.getVill() );
		testSetVill();
	}

	@Test
	public void testSetVill() {
		p.setVill( nVille );
		assertEquals( nVille, p.getVill() );
	}

	@Test
	public void testGetCodePostal() {
		assertEquals( oCP, p.getCodePostal() );
		testSetCodePostal();
	}

	@Test
	public void testSetCodePostal() {
		p.setCodePostal( nCP );
		assertEquals( nCP, p.getCodePostal() );
	}

}
