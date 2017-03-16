package sg.edu.nus.comp.cs4218.impl.app;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.CatApplication;
import sg.edu.nus.comp.cs4218.impl.app.HeadApplication;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class CatApplicationTest {
	static CatApplication catApplication;
	static InputStream is;
	static OutputStream os;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		catApplication = new CatApplication();
	}

	@Before
	public void setUp() throws Exception {
		is = null;
		os = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		os = new ByteArrayOutputStream();
	}
	
	@Test
	public void testCatEmptyArgs() {
		String[] args = "".split(" ");
		try {
			catApplication.run(args, is, os);
		} catch (CatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			assertEquals("cat: This is a directory", e.getMessage());
		}
	}
	
	@Test
	public void testCatWithFile() {
		String[] args = "test.txt".split(" ");
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4";
		try {
			catApplication.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (CatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
	@Test
	public void testCatWithInvalidFile() {
		String[] args = "blah.txt".split(" ");
		try {
			catApplication.run(args, is, os);
		} catch (CatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			assertEquals("cat: Could not read file", e.getMessage());
		}
	}
	
	@Test
	public void testCatWithEmptyFile() {
		String[] args = "fileTestEmpty.txt".split(" ");
		String expected = "";
		try {
			catApplication.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (CatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
	@Test
	public void testCatWithOnlySpacesFile() {
		String[] args = "fileTest_onlyspaces.txt".split(" ");
		String expected = "     ";
		try {
			catApplication.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (CatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
	@Test
	public void testCatWithOnlyNewlinesFile() {
		String[] args = {"fileTest only newlines.txt"};
		String expected = "\r\n\r\n\r\n\r\n";
		try {
			catApplication.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (CatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}
	
	@Test
	public void testCatWithManyNewlinesFile() {
		String[] args = {"fileTest manyNewlines.txt"};
		String expected = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n";
		try {
			catApplication.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (CatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	@Test
	//Integrated Test
	public void testOverAllFromStdin() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "cat test.txt | cat";
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4\r\n".trim();
		shell.parseAndEvaluate(args,os);
	
		assertEquals(expected, os.toString().trim());
		
	}
	
	@Test
	public void testOverAllFromFile() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "cat test.txt";
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4";
		shell.parseAndEvaluate(args,os);
		assertEquals(expected, os.toString());
	}
}
