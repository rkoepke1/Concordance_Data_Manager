package testing;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataManager.ConcordanceDataManager;
import interfaces.ConcordanceDataManagerInterface;

/**
 * This is the test file for the ConcordanceDataManager
 * which is implemented from the ConcordanceDataManagerInterface
 * 
 * @author Ryan Koepke
 *
 */
public class ConcordanceDataManagerTest_STUDENT {
	private ConcordanceDataManagerInterface mngr = new ConcordanceDataManager();
	private File inputFile, outputFile;
	private String text;

	/**
	 * Create an instance of ConcordanceDataManager
	 * Create a string for testing
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		mngr = new ConcordanceDataManager();
		text = "One rainy day, the rats heard a great noise in the loft.\n "
				+ "The pine rafters were all rotten, so that the barn was\n "
				+ "rather unsafe. At last the joists gave way and fell to\n "
				+ "the ground. The walls shook and all the rats' hair stood\n"
				+"\n"
				+"\n"
				+ "on end with fear and horror. This won't do, said the\n "
				+ "captain. I'll send out scouts to search for a new home.\n";
	}

	/**
	 * Set mngr reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		mngr = null;
	}

	/**
	 * Test for the createConcordanceArray method
	 * Use the String text created in setUp()
	 */
	@Test
	public void testCreateConcordanceArray() {
		ArrayList<String> words = mngr.createConcordanceArray(text);
		assertEquals(words.get(0),"all: 2, 4\n");
		assertEquals(	words.get(2), "captain: 8\n");
		assertEquals(words.get(4),"end: 7\n");
		assertEquals(words.get(5),"fear: 7\n");
		assertEquals(words.get(6),"fell: 3\n");
		assertEquals(words.get(7),"for: 8\n");
		assertEquals(words.get(15),"i'll: 8\n");
		assertEquals(words.get(16),"joists: 3\n");
		assertEquals(words.get(17),"last: 3\n");
		assertEquals(words.get(18),"loft: 1\n");
		assertEquals(words.get(19),"new: 8\n");
	}
	
	
	/**
	 * Test for createConcordanceFile method
	 * This is intended to be used with the test file:
	 * Now_is_the_time.txt
	 */
	@Test
	public void testCreateConcordanceFileA() {
		ArrayList<String> words = new ArrayList<String>();
		try {
			inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.print("With tenure, Suzie'd\n have all the more leisure\nfor yachting, but her publications\n are no good.");
			
			inFile.close();
			outputFile = new File("Test1Out.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
			outFile.print(" ");
			
			mngr.createConcordanceFile(inputFile, outputFile);
			Scanner scan = new Scanner(outputFile);
			while (scan.hasNext())
			{
				words.add(scan.nextLine());
			}

			scan.close();
			outFile.close();
		 
			assertEquals(words.get(0),"all: 2");
			assertEquals(words.get(1), "are: 4");
			assertEquals(words.get(2),"but: 3");
			assertEquals(words.get(3),"for: 3");
			assertEquals(words.get(4),"good: 4");
			assertEquals(words.get(5),"have: 2");
			assertEquals(words.get(6),"her: 3");
			assertEquals(words.get(7),"leisure: 2");
			assertEquals(words.get(8),"more: 2");
			assertEquals(words.get(9),"publications: 3");
			assertEquals(words.get(10),"suzie'd: 1");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an FileNotFoundException");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("This should not have caused an Exception");
		}
	}

	/**
	 * Test for createConcordanceFile method
	 * Create an inputFile "Test.txt" 
	 * and an outputFile "TestOut.txt"
	 */
	
	@Test
	public void testCreateConcordanceFileB() {
		ArrayList<String> words = new ArrayList<String>();
		try {
			inputFile = new File("Test2.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.print("After the Sea-Ship-after the whistling winds;\n" + 
					"\n" + 
					"After the white-gray sails, taut to their spars and ropes,\n" + 
					"\n" + 
					"Below, a myriad, myriad waves, hastening, lifting up their necks,\n" + 
					"\n" + 
					"Tending in ceaseless flow toward the track of the ship:\n" + 
					"\n" + 
					"Waves of the ocean, bubbling and gurgling, blithely prying,\n" + 
					"\n" + 
					"Waves, undulating waves-liquid, uneven, emulous waves,\n" + 
					" \n" + 
					"Toward that whirling current, laughing and buoyant, with curves,\n" + 
					" \n" + 
					"Where the great Vessel, sailing and tacking, displaced the surface");
			inFile.close();
			outputFile = new File("TestOut1.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
		 
			mngr.createConcordanceFile(inputFile, outputFile);
			Scanner scan = new Scanner(outputFile);
			while (scan.hasNext())
			{
				words.add(scan.nextLine());
				
			}

			scan.close();
			outFile.close();
		
			 
			assertEquals("after: 1, 3", words.get(0));
			assertEquals("below: 5", words.get(1));
			assertEquals("blithely: 9", words.get(2));
			assertEquals("bubbling: 9", words.get(3));
			assertEquals("buoyant: 13", words.get(4));
			assertEquals("emulous: 11", words.get(9));
			assertEquals("waves: 5, 9, 11", words.get(37));
			assertEquals("wavesliquid: 11", words.get(38));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateConcordanceFileC() {
		try {
			inputFile = new File("Test3.txt");
			inputFile.setReadable(false);
			outputFile = new File("Test3Out.txt");
			PrintWriter outFile = new PrintWriter(outputFile);
			outFile.print(" ");
			
			mngr.createConcordanceFile(inputFile, outputFile);
			assertTrue("This should have raised an exception", false);
			outFile.close();
		
		} catch (FileNotFoundException e) {
			assertTrue("This should have raised a FileNotFoundexception", true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateConcordanceFileD() {
		try {
			inputFile = new File("Test3.txt");
			outputFile = new File("Test3Out.txt");
			outputFile.setWritable(false);
			
			mngr.createConcordanceFile(inputFile, outputFile);
			assertTrue("This should have raised an exception", false);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			assertTrue("This should have raised a FileNotFoundException", true);
		}
	}
	
}
