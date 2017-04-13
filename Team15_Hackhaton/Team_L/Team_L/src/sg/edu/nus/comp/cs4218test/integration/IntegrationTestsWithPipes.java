package sg.edu.nus.comp.cs4218test.integration;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class IntegrationTestsWithPipes {
	private static final String EXCEPTION_NOT = "Should not throw exception";
	private static final String TEST_DIR = "testDir";
	private final String firstFilePath = TEST_DIR + File.separator + "firstTest.txt";
	private final String secondFilePath = TEST_DIR + File.separator + "secondDir" + File.separator + "secondTest.txt";
	private final String firstFileString = "First" + System.lineSeparator() + "Sample";
	private final String secondFileString = "Second" + System.lineSeparator() + "Sample";

	private File firstDir, secondDir;
	private Shell shell;

	@Before
	public void setUp() throws Exception {
		firstDir = new File(TEST_DIR);
		firstDir.mkdir();
		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
		writer.write(firstFileString);
		writer.close();

		secondDir = new File(firstDir, "secondDir");
		secondDir.mkdir();
		File testFile2 = new File(secondFilePath);
		testFile2.createNewFile();
		writer = new BufferedWriter(new FileWriter(testFile2));
		writer.write(secondFileString);
		writer.close();
		shell = new ShellImpl();
		// stdout = new ByteArrayOutputStream();
		// System.out.println(Environment.currentDirectory);

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
	public void testPipeTwoEcho() {
		String input = "echo \'abc\' | echo \'eee\'";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			assertEquals("eee" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}

	}

	@Test
	public void testPipeEchoAndSed() {
		String input = "echo \"abcdefg\"  | sed s/a/BBB/";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			assertEquals(("BBBbcdefg" + System.lineSeparator()), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}

	}

	@Test(expected = SedException.class)
	public void testPipeEchoAndIncompleteSed() throws AbstractApplicationException, ShellException {
		String input = "echo \"abcdefg\"  | sed s/a/BBB";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}

	@Test
	public void testPipeEchoAndWc() {
		String input = "echo \"abc def g\"  | wc -m -w -l";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			assertEquals("10 3 1 " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}

	}

	@Test(expected = WcException.class)
	public void testPipeEchoAndInvalidWc() throws AbstractApplicationException, ShellException {
		String input = "echo \"abc def g\"  | wc -mx -w -l";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}

	@Test
	public void testPipePwdAndWc() {
		String input = "pwd  | wc -m -w -l";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			String str = Environment.currentDirectory;
			int length = str.length() + 1;
			assertEquals(length + " 1 1 " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test(expected = WcException.class)
	public void testPipePwdAndInvalidWc() throws AbstractApplicationException, ShellException {
		String input = "pwd  | wc -m -wlk -l";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}

	@Test
	public void testPipePwdAndWcAndSed() {
		String input = "pwd  | wc -m -w -l | sed s/1/gg/g";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			String str = Environment.currentDirectory;
			int length = str.length() + 1;
			assertEquals((length + " gg gg " + System.lineSeparator()), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test(expected = WcException.class)
	public void testPipePwdAndInvalidWcAndSed() throws AbstractApplicationException, ShellException {
		String input = "pwd  | wc -mx | sed s/1/gg/g";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}

	@Test
	public void testPipeCalAndWc() {
		String input = "cal | wc ";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			assertEquals("142 40 7" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test(expected = CalException.class)
	public void testPipeInvalidCalAndWc() throws AbstractApplicationException, ShellException {
		String input = "cal 3 | wc ";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}

	@Test(expected = WcException.class)
	public void testPipeCalAndInvalidWc() throws AbstractApplicationException, ShellException {
		String input = "cal | wc -x";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}

	@Test
	public void testPipeCatAndSedAndWc() {
		String input = "cat " + firstFilePath + " | sed s/Wed/Break/ | wc";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			assertEquals("13 2 2" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test(expected = CatException.class)
	public void testPipeCatInvalidFileAndSedAndWc() throws AbstractApplicationException, ShellException {
		String input = "cat " + firstFilePath + "z" + " | sed s/Wed/Break/ | wc";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}

	@Test(expected = WcException.class)
	public void testPipeCatAndSedAndInvalidWc() throws AbstractApplicationException, ShellException {
		String input = "cat " + firstFilePath + " | sed s/Wed/Break/ | wc xx";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}

	@Test
	public void testPipeInvalidCdAndCal() {
		String input = "cd " + secondFilePath + " | wc";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("Not a directory"));
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test
	public void testPipeHeadAndTailAndGrep() {
		String input = "head " + firstFilePath + "| tail -n 1 | grep Sample";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			assertEquals("Sample" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test(expected = TailException.class)
	public void testPipeHeadAndInvalidTailAndGrep() throws AbstractApplicationException, ShellException {
		String input = "head " + firstFilePath + "| tail -n -1 | grep Sample";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}

	@Test
	public void testValidTailAndValidWc() {
		String input = "tail " + firstFilePath + " | wc -w";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			assertEquals("2 " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test(expected = TailException.class)
	public void testInvalidTailAndValidWc() throws AbstractApplicationException, ShellException {
		String input = "tail -n -2" + firstFilePath + " | wc -wx";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}

	@Test
	public void testValidTailAndInvalidWc() {
		String input = "tail " + secondFilePath + " | wc -j";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("Illegal option"));
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test
	public void testInvalidTailAndInvalidWc() {
		String input = "tail 22 | wc -j";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("No such file"));
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test
	public void testIllegalTailCountAndInvalidWc() {
		String input = "tail -n -1" + firstFilePath + " | wc -w";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("illegal line count"));
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	
	
	@Test
	public void testPipeSortSedWc() {
		String input = "sort " + firstFilePath + "  | sed s/Sample/2016/g | wc -m";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			assertEquals("11 " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}
	
	@Test
	public void testInvalidSortSedWc(){
		String input = "sort abcd | sed s/Sample/2016/g | wc -m";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("No such file or directory"));
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}
	
	@Test
	public void testInvalidHeadSort(){
		String input = "head abcd | sort";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("No such file or directory"));
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}
	
	@Test
	public void testInvalidHeadOptionSort(){
		String input = "head -pp 12 " + firstFilePath + " | sort";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("Invalid options"));
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}
	
	@Test
	public void testPwdIllegalWcOption(){
		String input = "pwd | wc -o";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("Illegal option"));
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}
	
	@Test 
	public void testPipeDateSedWc(){
		String input = "date | sed s/2017/17/ | wc -w";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate(input, stdout);
			assertEquals("6 " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("Illegal option"));
		} catch (ShellException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test(expected = WcException.class)
	public void testPipeDateSedInvalidWc() throws AbstractApplicationException, ShellException {
		String input = "date | sed s/2017/17/ | wc -wx";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		shell.parseAndEvaluate(input, stdout);
	}
}
