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
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.HeadApplication;
import sg.edu.nus.comp.cs4218.impl.app.TailApplication;

public class TailApplicationTest {
	static TailApplication tailApp;
	static InputStream is;
	static OutputStream os;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tailApp = new TailApplication();
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
	public void testTailOptionAndFile() {
		// 12 lines in test2.txt, should print lines line 8 to 12
		String args[] = "-n 5 test2.txt".split(" ");
		String expected = "line 8\nline 9\nline 10\nline 11\nline 12\n";
		try {
			tailApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = os.toString();
		assertEquals(expected, output);
	}

	@Test
	public void testTailOptionExceedFileLines() {
		// 4 lines in test.txt, should all 4 lines
		String args[] = "-n 999 test.txt".split(" ");
		String expected = "line 1\nline 2\nline 3\nline 4\n";
		try {
			tailApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = os.toString();
		assertEquals(expected, output);
	}

	@Test
	public void testTailFileOnly() {
		// 12 lines in test2.txt, should print 10 lines
		String args[] = "test2.txt".split(" ");
		String expected = "line 3\nline 4\nline 5\nline 6\nline 7\nline 8\nline 9\nline 10\nline 11\nline 12\n";
		try {
			tailApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = os.toString();
		assertEquals(expected, output);
	}

	@Test
	public void testEmptyFile() {
		String args[] = "fileTestEmpty.txt".split(" ");
		String expected = ""; // newline is not added
		try {
			tailApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = os.toString();
		assertEquals(expected, output);
	}

	@Test
	public void testOnlySpacesFile() {
		String args[] = "fileTest_onlyspaces.txt".split(" ");
		String expected = "     \n";
		try {
			tailApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = os.toString();
		assertEquals(expected, output);
	}

	@Test
	public void testOnlyNewlinesFile() {
		String args[] = { "fileTest only newlines.txt" };
		String expected = "\n\n\n\n";
		try {
			tailApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = os.toString();
		assertEquals(expected, output);
	}

	@Test
	public void testManyNewlinesFile() {
		String args[] = { "fileTest manynewlines.txt" };
		String expected = "\n\n\n\n\n\n\n\n\n\n";
		try {
			tailApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String output = os.toString();
		assertEquals(expected, output);
	}

	@Test
	public void testTailEmptyInput() {
		String args[] = "".split(" ");
		Exception exc = new Exception();
		try {
			tailApp.run(args, is, os);

		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			exc = e;
		}
		assertEquals("tail: File not found", exc.getMessage());
	}

	@Test
	// Integrated Test
	public void testOverAllFromStdin() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "cat test.txt | tail -n 2";
		String expected = "line 3\nline 4\n";
		shell.parseAndEvaluate(args, os);
		assertEquals(expected, os.toString());

	}

	@Test
	public void testOverAllFromFile() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "tail -n 2 test.txt";
		String expected = "line 3\nline 4\n";
		shell.parseAndEvaluate(args, os);
		assertEquals(expected, os.toString());
	}

}
