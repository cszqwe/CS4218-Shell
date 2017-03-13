package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class ShellTestEF1 {
	static ShellImpl shell;
	static OutputStream os;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	}

	@Test
	//Test the globbing function.
	public void testGlobbing() throws AbstractApplicationException, ShellException {
		assertEquals(ShellImpl.processGlobbing("cat globbingTest\\*"), "cat globbingTest/test.txt globbingTest/test2.txt");
		assertEquals(ShellImpl.processGlobbing("cat globbingTest\\test*"), "cat globbingTest/test.txt globbingTest/test2.txt");
		assertEquals(ShellImpl.processGlobbing("cat globbingTest\\test2*"), "cat globbingTest/test2.txt");
		os = new ByteArrayOutputStream();
		//A normal one
		String cmdline = "cat globbingTest\\*";
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4line 1\r\nline 2\r\nline 3\r\nline 4\r\nline 5\r\nline 6\r\nline 7\r\nline 8\r\nline 9\r\nline 10\r\nline 11\r\nline 12";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());		
	}

	@Test
	//Test the redirect function
	public void testRedirection() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline1 = "cat test.txt > redirectionTest.txt";
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4";
		String cmdline = "echo < redirectionTest.txt";
		shell.parseAndEvaluate(cmdline1, os);
		os = new ByteArrayOutputStream();
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}
	
}
