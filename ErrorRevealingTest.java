package sg.edu.nus.comp.cs4218test.integration;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.SedApplication;
import sg.edu.nus.comp.cs4218.impl.app.SortApplication;
import sg.edu.nus.comp.cs4218.impl.app.WcApplication;

public class ErrorRevealingTest {
	private static final String EXCEPTION_NOT = "Should not throw exception";
	private static final String TEST_DIR = "testDir";
	private final String firstFilePath = TEST_DIR + File.separator + "firstTest.txt";
	private final String secondFilePath = TEST_DIR + File.separator + "secondDir" + File.separator + "secondTest.txt";
	private final String firstFileString = "First" + System.lineSeparator() + "Sample";
	private final String secondFileString = "Second" + System.lineSeparator() + "Sample";
	SedApplication sed = new SedApplication();
	private OutputStream stdout;
	private InputStream stdin;
	private File firstDir, secondDir;
	SortApplication sortApp;
	WcApplication wcApp;
	ShellImpl shell;
	
	InputStream is;
	OutputStream os;
	@Before
	public void setUp() throws Exception {
		firstDir = new File(TEST_DIR);
		firstDir.mkdir();
		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
		writer.write(firstFileString);
		writer.close();
		is = null;
		os = new ByteArrayOutputStream();
		secondDir = new File(firstDir, "secondDir");
		secondDir.mkdir();
		File testFile2 = new File(secondFilePath);
		testFile2.createNewFile();
		writer = new BufferedWriter(new FileWriter(testFile2));
		writer.write(secondFileString);
		writer.close();
		shell = new ShellImpl();
		sortApp = new SortApplication();
		wcApp = new WcApplication();
		shell = new ShellImpl();
		is = null;
		os = new ByteArrayOutputStream();
	

	}

	@After
	public void tearDown() throws Exception {
		for (File file : secondDir.listFiles()) {
			file.delete();
		}
		secondDir.delete();

		for (File file : firstDir.listFiles()) {
			file.delete();
		}
		firstDir.delete();
	}
	@Test
	public void testBugRevealing1() {
		os = new ByteArrayOutputStream();
		String[] args = { "-n", "fileBugRevealing1.txt" };
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {

		}
		assertEquals("(4th line)\n3rd line\nLine 2\nline 1\n", os.toString());
		// instead returns "(4th line)\nLine 2\nline 1\n3rd line\n"
	}

	@Test
	public void testBugRevealing2() {
		os = new ByteArrayOutputStream();
		String[] args = { "-n", "fileBugRevealing2.txt" };
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {

		}
		assertEquals(" \ntest\ntest2\n", os.toString());
		// instead throws exception
	}

	@Test
	public void testBugRevealing3() {
		os = new ByteArrayOutputStream();
		String[] args = { "fileBugRevealing1.txt", "fileBugRevealing3.txt" };
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {

		}
		assertEquals("(4th line)\n3rd line\nLINE 1\nLINE 2\nLINE 3\nLine 2\nline 1\n", os.toString());
		// instead returns "(4th line)LINE 1\n3rd line\nLINE 2\nLINE 3\nLine 2\nline 1\n"
	}
	
	@Test
	public void testBugRevealing4() {
		os = new ByteArrayOutputStream();
		String[] args = { "fileBugRevealing4.txt" };
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {

		}
		assertEquals("*\n:\n;\n<\n=\n>\n?\n@\n^\n1\nL\nw\n", os.toString());
		// instead returns "*\n1\n:\n;\n<\n=\n>\n?\n@\nL\n^\nw\n"
	}
	
	@Test
	public void testBugRevealing5() throws FileNotFoundException {
		os = new ByteArrayOutputStream();
		String[] args = { "-n" };
		is = new FileInputStream("fileBugRevealing5.txt");
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {

		}
		assertEquals("2\n30\n100", os.toString());
		// instead returns "100\n2\n30\n"
	}

	@Test
	public void testBugRevealing6() {
		os = new ByteArrayOutputStream();
		String[] args = { "-n", "fileBugRevealing6.txt" };
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {

		}
		assertEquals("\n\n\n\n\n\n", os.toString());
		// instead returns some less number of (or no) newlines
	}
	
	@Test
	public void testBugRevealing7() {
		os = new ByteArrayOutputStream();
		String[] args = { "fileBugRevealing1.txt", "fileBugRevealing3.txt" };
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {

		}
		assertEquals("36 8 3\n22 6 2\n", os.toString());
		// instead throws exception
	}
	
	@Test
	public void testBugRevealing8() {
		os = new ByteArrayOutputStream();
		String args = "sort \"fileBugRevealing8 with space.txt\"";
		try {
			shell.parseAndEvaluate(args, os);
		} catch (Exception e) {

		}
		assertEquals("asdf\nqwerty\n", os.toString());
		// instead throws exception
	}
	
	@Test
	public void testBugRevealing14() {
		os = new ByteArrayOutputStream();
		String args = "sort `echo fileBugRevealing1.txt` `echo fileBugRevealing3.txt`";
		try {
			shell.parseAndEvaluate(args, os);
		} catch (Exception e) {

		}
		assertEquals("(4th line)\n3rd line\nLINE 1\nLINE 2\nLINE 3\nLine 2\nline 1\n", os.toString());
		// instead throws exception
	}
	@Test
	public void testBugRevealing9() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String input = "echo a\"b\"";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("ab", stdout.toString());
	}
	@Test
	public void testBugRevealing10() throws AbstractApplicationException, ShellException {
		String input = "echo a\'b\'";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("ab", stdout.toString());
	}
	
	@Test
	public void testBugRevealing11() throws AbstractApplicationException, ShellException {
		String input = "echo a > 1.txt; cat < 1.txt 1.txt";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("a", stdout.toString());
	}

	@Test
	public void testBugRevealing12() throws AbstractApplicationException, ShellException {
		String input = "echo a | cat; echo b | cat";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("a\nb\n", stdout.toString());
	}
	
	@Test
	public void testBugRevealing13() throws AbstractApplicationException, ShellException {
		String input = "cat testDir/*";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("\n\n", stdout.toString());
	}

	
	@Test
	public void testBugRevealing15() throws AbstractApplicationException, ShellException {
		String input = "echo a | sed s/[a]/b/ ";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("b\n", stdout.toString());
	}
	
	@Test
	public void testBugRevealing16() throws AbstractApplicationException, ShellException {
		String input = "echo a | sed ssasbs ";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("b\n", stdout.toString());
	}
	
	@Test
	public void testBugRevealing17() throws AbstractApplicationException, ShellException {
		String input = "echo `echo a | cat`";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("a\n", stdout.toString());
	}

	@Test
	public void testBugRevealing18() throws AbstractApplicationException, ShellException {
		String input = "cal 2017 | cat";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("","");
	}
	
	@Test (expected = CalException.class)
	public void testBugRevealing19() throws AbstractApplicationException, ShellException {
		String input = "cal 1 2 3";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}
	
	@Test
	public void testBugRevealing20() throws AbstractApplicationException, ShellException {
		String input = "cat fileBugRevealing20.txt | grep ab";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("ab",stdout.toString());
	}
	
	
	@Test
	public void testBugRevealing21() throws AbstractApplicationException, ShellException {
		String input = "echo \"a b\" | grep ' '";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
		assertEquals("a b\n",stdout.toString());
	}
	
	
	

}


