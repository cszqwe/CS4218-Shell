package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class SedApplicationTest {

	static SedApplication sedApp;
	static InputStream is;
	static OutputStream os;
	static ShellImpl shell;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sedApp = new SedApplication();
		os = new ByteArrayOutputStream();
		shell = new ShellImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		os = new ByteArrayOutputStream();
	}

	@Test
	public void testReplaceFirstSubStringInFile() {
		os = new ByteArrayOutputStream();
		String args = "sed s/[1234]/test/ test.txt";
		String expected = "line test\r\nline 2\r\nline 3\r\nline 4";
		String result = sedApp.replaceFirstSubStringInFile(args);
		assertEquals(expected, result);
	}
	
	@Test
	public void testReplaceAllSubStringInFile() {
		os = new ByteArrayOutputStream();
		String args = "sed s/[l]/L/g test.txt";
		String expected = "Line 1\r\nLine 2\r\nLine 3\r\nLine 4";
		String result = sedApp.replaceAllSubstringsInFile(args);
		assertEquals(expected, result);
	}
	
	@Test
	public void testReplaceSubstringWithInvalidRegex() {
		os = new ByteArrayOutputStream();
		String args = "test";
		String expected = "The command test has invalid Regex";
		String result = sedApp.replaceSubstringWithInvalidRegex(args);
		assertEquals(expected, result);
	}
	
	@Test
	public void testReplaceSubstringWithInvalidReplacement() {
		os = new ByteArrayOutputStream();
		String args = "test";
		String expected = "The command test has invalid Replacement";
		String result = sedApp.replaceSubstringWithInvalidReplacement(args);
		assertEquals(expected, result);
		
	}
	
	@Test
	//Integrated Test
	public void testOverAllFromStdin() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String args = "cat test.txt | sed s/l/L/g";
		String expected = "Line 1\nLine 2\nLine 3\nLine 4";
		shell.parseAndEvaluate(args,os);
		assertEquals(expected, os.toString());
		
	}
	
	@Test
	public void testOverAllFromFile() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String args = "sed s/l/L/g test.txt";
		String expected = "Line 1\r\nLine 2\r\nLine 3\r\nLine 4";
		shell.parseAndEvaluate(args,os);
		assertEquals(expected, os.toString());
		
	}
	

}
