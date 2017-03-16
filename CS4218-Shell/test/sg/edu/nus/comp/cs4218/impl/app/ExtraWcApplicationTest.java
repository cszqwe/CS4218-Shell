package sg.edu.nus.comp.cs4218.impl.app;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class ExtraWcApplicationTest {
	private static final String FILE_SEPARATOR = File.separator;
	private static final String TEST_FILE_EMPTY = String.format("files%swcTestFiles%sempty.txt", FILE_SEPARATOR,
			FILE_SEPARATOR);
	private static final String TEST_FILE_SINGLE_WORD = String.format("files%swcTestFiles%ssingleWord.txt",
			FILE_SEPARATOR, FILE_SEPARATOR);
	private static final String TEST_FILE_TITLES = String.format("files%stitles.txt", FILE_SEPARATOR);
	private static final File TITLE_FILES = new File(TEST_FILE_TITLES);
	private static final String TEST_FILE_NAME_HAS_SPACE = String.format("files%sname has space.txt", FILE_SEPARATOR);
	private static final String LINE_SEPARATOR = "\n";

	WcApplication wcApp;
	OutputStream stdout;
	InputStream stdin;

	@Before
	public void setUp() throws Exception {
		wcApp = new WcApplication();
		stdin = null;
		stdout = new ByteArrayOutputStream();
	}

	@Test(expected = WcException.class)
	public void testWcWithNullStdout() throws WcException {
		String[] args = null;
		wcApp.run(args, stdin, null);
	}

	@Test(expected = WcException.class)
	public void testWcWithNullArgsAndNullStdin() throws WcException {
		String[] args = null;
		wcApp.run(args, null, stdout);
	}

	@Test(expected = WcException.class)
	public void testWcWithEmptyArgsAndNullStdin() throws WcException {
		String[] args = new String[0];
		wcApp.run(args, null, stdout);
	}

	@Test
	public void testWcWithEmptyFile() throws WcException {
		String filePath = TEST_FILE_EMPTY;
		String[] args = { filePath };
		wcApp.run(args, stdin, stdout);
		assertEquals(String.format("0 0 0 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
	}

	@Test
	public void testWcWithNoFlag() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { filePath };
		wcApp.run(args, stdin, stdout);
		assertEquals(String.format("5 1 1 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
	}

	@Test(expected = WcException.class)
	public void testWcWithInvalidFlag() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-x", filePath };
		wcApp.run(args, stdin, stdout);
	}

	@Test(expected = WcException.class)
	public void testWcWithMixValidAndInvalidFlags() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-m", "-x", "-l", filePath };
		wcApp.run(args, stdin, stdout);
	}

	@Test(expected = WcException.class)
	public void testWcWithInvalidComplexFlags() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-mxl", filePath };
		wcApp.run(args, stdin, stdout);
	}

	@Test
	public void testWcWithOnlyCharFlag() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-m", filePath };
		wcApp.run(args, stdin, stdout);
		assertEquals(String.format("5 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
	}

	@Test
	public void testWcWithOnlyWordFlag() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-w", filePath };
		wcApp.run(args, stdin, stdout);
		assertEquals(String.format("1 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
	}

	@Test
	public void testWcWithOnlyNewlineFlag() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-l", filePath };
		wcApp.run(args, stdin, stdout);
		assertEquals(String.format("1 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
	}

	@Test
	public void testWcWithComplexFlags() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-mlw", filePath };
		wcApp.run(args, stdin, stdout);
		assertEquals(String.format("5 1 1 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
	}

	@Test
	public void testWcWithMultipleSingleFlags() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-w", "-m", "-l", filePath };
		wcApp.run(args, stdin, stdout);
		assertEquals(String.format("5 1 1 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
	}

	@Test
	public void testWcWithMixFlags() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-w", "-ml", "-mlw", filePath };
		wcApp.run(args, stdin, stdout);
		assertEquals(String.format("5 1 1 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
	}

	@Test
	public void testWcWithRepeativeSingleFlags() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-w", "-w", "-w", filePath };
		wcApp.run(args, stdin, stdout);
		assertEquals(String.format("1 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
	}

	@Test
	public void testWcWithDuplicateFlags() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-wwwww", filePath };
		wcApp.run(args, stdin, stdout);
		assertEquals(String.format("1 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
	}

	@Test
	public void testWcWithDifferentFlagOrders() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		OutputStream output1 = new ByteArrayOutputStream();
		String[] args1 = { "-wlm", filePath };
		wcApp.run(args1, stdin, output1);
		String[] args2 = { "-lmw", filePath };
		OutputStream output2 = new ByteArrayOutputStream();
		wcApp.run(args2, stdin, output2);
		assertEquals(output1.toString(), output2.toString());
		assertEquals(String.format("5 1 1 %s%s", filePath, LINE_SEPARATOR), output1.toString());
	}

	@Test
	public void testWcWithOutOfOrderFlag() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { filePath, "-w" };
		try {
			wcApp.run(args, stdin, stdout);
		} catch (WcException e) {
			assertEquals(String.format("5 1 1 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
			assertEquals("wc: Could not read file", e.getMessage());
		}
	}

	@Test
	public void testWcWithInOrderFlagAndOutOfOrderFlag() throws WcException {
		String filePath = TEST_FILE_SINGLE_WORD;
		String[] args = { "-w", filePath, "-w" };
		try {
			wcApp.run(args, stdin, stdout);
		} catch (WcException e) {
			assertEquals(String.format("1 %s%s", filePath, LINE_SEPARATOR), stdout.toString());
			assertEquals("wc: Could not read file", e.getMessage());
		}
	}

	@Test
	public void testWcWithMultipleFiles() throws WcException {
		String[] args = { TEST_FILE_EMPTY, TEST_FILE_SINGLE_WORD, TEST_FILE_TITLES };
		wcApp.run(args, stdin, stdout);
		String emptyFileExpected = String.format("0 0 0 %s%s", TEST_FILE_EMPTY, LINE_SEPARATOR);
		String singleWordFileExpected = String.format("5 1 1 %s%s", TEST_FILE_SINGLE_WORD, LINE_SEPARATOR);
		String titlesFileExpected = String.format("4129 717 251 %s%s", TEST_FILE_TITLES, LINE_SEPARATOR);
		String expectedResult = String.format("%s%s%s", emptyFileExpected, singleWordFileExpected, titlesFileExpected);
		
		System.out.println(expectedResult);
		System.out.println("");
		String res = stdout.toString();
		assertEquals(expectedResult, res);
		System.out.println(res);
	}

	/**
	@Test
	public void testWcReadFromStdin() throws WcException {
		String[] args = {};
		stdin = new InputStream("hello world");
		wcApp.run(args, stdin, stdout);
		String expected = String.format("      11       2       0%s", LINE_SEPARATOR);
		assertEquals(expected, stdout.toString());
	}

	@Test
	public void testWcReadFromStdinWithFlag() throws WcException {
		String[] args = { "-m" };
		stdin = new WritableInputStream("hello world");
		wcApp.run(args, stdin, stdout);
		String expected = String.format("      11%s", LINE_SEPARATOR);
		assertEquals(expected, stdout.toString());
	}

	@Test(expected = WcException.class)
	public void testWcReadFromStdinWithInvalidFlag() throws WcException {
		String[] args = { "-x" };
		stdin = new WritableInputStream("hello world");
		wcApp.run(args, stdin, stdout);
	}

	@Test
	public void testWcWithBothStdinAndFile() throws WcException {
		String[] args = { TEST_FILE_SINGLE_WORD };
		stdin = new WritableInputStream("not single word");
		String singleWordFileExpected = String.format("       5       1       0 %s%s", TEST_FILE_SINGLE_WORD,
				LINE_SEPARATOR);
		wcApp.run(args, stdin, stdout);
		assertEquals(singleWordFileExpected, stdout.toString());
	}
	
	**/
	@Test
	public void testWcWithFileNameContainSpace() throws WcException {
		String[] args = { TEST_FILE_NAME_HAS_SPACE };
		// File testFile = new File(TEST_FILE_NAME_HAS_SPACE);
		// long byteCount = testFile.length(); // Our implementation removes \r
		String singleWordFileExpected = String.format("27 6 6 %s%s", TEST_FILE_NAME_HAS_SPACE, LINE_SEPARATOR);
		wcApp.run(args, stdin, stdout);
		assertEquals(singleWordFileExpected, stdout.toString());
	}

	// Integration testing
	
	@Test
	public void testWcAllFromCat() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "cat test.txt | wc";
		String expected = "27 8 4 \n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}
	
	@Test
	public void testWcCharsFromCat() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "cat test.txt | wc -m";
		String expected = "27 \n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}
	
	
	/**
	 * These tests are on interfaces
	 
	@Test
	public void testPrintCharacterCountInFile() {
		String expected = String.format("%8d %s%s", TITLE_FILES_TOTAL_BYTES, TEST_FILE_TITLES, LINE_SEPARATOR);
		String result = wcApp.printCharacterCountInFile(String.format("wc -m %s", TEST_FILE_TITLES));
		assertEquals(expected, result);
	}

	@Test
	public void testPrintCharacterCountInInvalidFile() {
		String result = wcApp.printCharacterCountInFile("wc -m invalid.txt");
		assertEquals("wc: Could not read file", result);
	}

	@Test
	public void testPrintWordCountInFile() {
		String expected = String.format("     717 %s%s", TEST_FILE_TITLES, LINE_SEPARATOR);
		String result = wcApp.printWordCountInFile(String.format("wc -w %s", TEST_FILE_TITLES));
		assertEquals(expected, result);
	}

	@Test
	public void testPrintWordCountInInvalidFile() {
		String result = wcApp.printWordCountInFile("wc -m invalid.txt");
		assertEquals("wc: Could not read file", result);
	}

	@Test
	public void testPrintNewlineCountInFile() {
		String expected = String.format("     250 %s%s", TEST_FILE_TITLES, LINE_SEPARATOR);
		String result = wcApp.printNewlineCountInFile(String.format("wc -l %s", TEST_FILE_TITLES));
		assertEquals(expected, result);
	}

	@Test
	public void testPrintNewlineCountInInvalidFile() {
		String expected = String.format("wc: Could not read file", LINE_SEPARATOR);
		String result = wcApp.printNewlineCountInFile("wc -m invalid.txt");
		assertEquals(expected, result);
	}

	@Test
	public void printAllCountsInFile() {
		String expected = String.format("%8d     717     250 %s%s", TITLE_FILES_TOTAL_BYTES, TEST_FILE_TITLES,
				LINE_SEPARATOR);
		String result = wcApp.printAllCountsInFile(String.format("wc %s", TEST_FILE_TITLES));
		assertEquals(expected, result);
	}

	@Test
	public void printAllCountsInInvalidFile() {
		String result = wcApp.printAllCountsInFile("wc -m invalid.txt");
		assertEquals("wc: Could not read file", result);
	}

	@Test
	public void printCharacterCountInStdin() {
		String expected = String.format("%8d%s", TITLE_FILES_TOTAL_BYTES, LINE_SEPARATOR);
		String result = wcApp.printCharacterCountInStdin(String.format("cat %s | wc -m", TEST_FILE_TITLES));
		assertEquals(expected, result);
	}

	@Test
	public void printWordCountInStdin() {
		String expected = String.format("     717%s", LINE_SEPARATOR);
		String result = wcApp.printWordCountInStdin(String.format("cat %s | wc -w", TEST_FILE_TITLES));
		assertEquals(expected, result);
	}

	@Test
	public void printNewlineCountInStdin() {
		String expected = String.format("     250%s", LINE_SEPARATOR);
		String result = wcApp.printNewlineCountInStdin(String.format("cat %s | wc -l", TEST_FILE_TITLES));
		assertEquals(expected, result);
	}

	@Test
	public void printAllCountsInStdin() {
		String expected = String.format("%8d     717     250%s", TITLE_FILES_TOTAL_BYTES, LINE_SEPARATOR);
		String result = wcApp.printAllCountsInStdin(String.format("cat %s | wc", TEST_FILE_TITLES));
		assertEquals(expected, result);
	}
	**/
}
