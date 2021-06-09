package testing;


import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataManager.ConcordanceDataManager;
import interfaces.ConcordanceDataManagerInterface;

/**
 * This is the test file for the ConcordanceDataManager
 * which is implemented from the ConcordanceDataManagerInterface
 * 
 * @author Professor Kartchner
 *
 */
public class ConcordanceDataManager_GFA_Test {
	private ConcordanceDataManagerInterface concordanceManager = new ConcordanceDataManager();
	private File inputFile, outputFile;
	private String text;

	/**
	 * Create an instance of ConcordanceDataManager
	 * Create a string for testing
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		concordanceManager = new ConcordanceDataManager();
		text = "hello world";
	}

	/**
	 * Set concordanceManager reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		concordanceManager = null;
	}

	/**
	 * Test for the createConcordanceArray method
	 * Use the String text created in setUp()
	 */
	@Test
	public void testCreateConcordanceArray() {
		ArrayList<String> words = concordanceManager.createConcordanceArray(text);
		assertEquals(words.get(0).charAt(0),'h');
	}

}
