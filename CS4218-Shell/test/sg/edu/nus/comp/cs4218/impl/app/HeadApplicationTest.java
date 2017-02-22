package sg.edu.nus.comp.cs4218.impl.app;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.HeadException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.HeadApplication;

public class HeadApplicationTest {
	static HeadApplication headApp;
	static InputStream is;
	static OutputStream os;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		headApp = new HeadApplication();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		is = null;
		os = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testHeadOptionAndFile() {
		String args[] = "-n 3 test.txt".split(" ");
		String expected ="line 1\nline 2\nline 3\n";
		try {
			headApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testHeadOptionExceedFileLines() {
		String args[] = "-n 1000 test.txt".split(" ");
		// 4 lines in test.txt, should print all 4 lines
		String expected ="line 1\nline 2\nline 3\nline 4\n";
		try {
			headApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHeadFileOnly() {
		String args[] = "test2.txt".split(" ");
		// 12 lines in test2.txt, should only print 10 lines
		String expected ="line 1\nline 2\nline 3\nline 4\nline 5\nline 6\nline 7\nline 8\nline 9\nline 10\n";
		try {
			headApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testEmptyFile() {
		String args[] = "fileTestEmpty.txt".split(" ");
		String expected = ""; // newline is not added
		try {
			headApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOnlySpacesFile() {
		String args[] = "fileTest_onlyspaces.txt".split(" ");
		String expected = "     \n";
		try {
			headApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOnlyNewlinesFile() {
		String args[] = {"fileTest only newlines.txt"};
		String expected = "\n\n\n\n";
		try {
			headApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testManyNewlinesFile() {
		String args[] = {"fileTest manynewlines.txt"};
		String expected = "\n\n\n\n\n\n\n\n\n\n";
		try {
			headApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHeadEmptyInput() {
		String args[] = "".split(" ");
		try {
			headApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			assertEquals("head: File not found", e.getMessage());
		}
	}
	
	@Test
	public void testHeadInvalidFile() {
		String args[] = "sdfsd".split(" ");
		try {
			headApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			assertEquals("head: File not found", e.getMessage());
		}
	}
	
	@Test
	//Integrated Test
	public void testOverAllFromStdin() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "cat test.txt | head -n 2";
		String expected = "line 1\nline 2\n";
		shell.parseAndEvaluate(args,os);
		assertEquals(expected, os.toString());
		
	}
	
	@Test
	public void testOverAllFromFile() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "head -n 2 test.txt";
		String expected = "line 1\nline 2\n";
		shell.parseAndEvaluate(args,os);
		assertEquals(expected, os.toString());
	}

}
