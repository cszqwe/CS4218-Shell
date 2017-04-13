package sg.edu.nus.comp.cs4218.impl.app;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.HeadException;
import sg.edu.nus.comp.cs4218.exception.GrepException;
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

		String res = stdout.toString();
		assertEquals(expectedResult, res);
	}

	@Test
	public void testWcReadFromStdin() throws WcException {
		String[] args = {};
		stdin = new ByteArrayInputStream("hello world".getBytes());
		wcApp.run(args, stdin, stdout);
		String expected = String.format("11 2 1 %s", LINE_SEPARATOR);
		assertEquals(expected, stdout.toString());
	}

	@Test
	public void testWcReadFromStdinWithFlag() throws WcException {
		String[] args = { "-m" };
		stdin = new ByteArrayInputStream("hello world".getBytes());
		wcApp.run(args, stdin, stdout);
		String expected = String.format("11 %s", LINE_SEPARATOR);
		assertEquals(expected, stdout.toString());
	}

	@Test(expected = WcException.class)
	public void testWcReadFromStdinWithInvalidFlag() throws WcException {
		String[] args = { "-x" };
		stdin = new ByteArrayInputStream("hello world".getBytes());
		wcApp.run(args, stdin, stdout);
	}

	@Test
	public void testWcWithBothStdinAndFile() throws WcException {
		String[] args = { TEST_FILE_SINGLE_WORD };
		stdin = new ByteArrayInputStream("not single word".getBytes());
		String singleWordFileExpected = String.format("5 1 1 %s%s", TEST_FILE_SINGLE_WORD, LINE_SEPARATOR);
		wcApp.run(args, stdin, stdout);
		assertEquals(singleWordFileExpected, stdout.toString());
	}

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

	// With pipelines
	@Test
	public void testWcAllFromPipe() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "cat test.txt | wc";
		String expected = "27 8 4 \n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}

	public void testWcLinesFromPipe() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "date | wc -l";
		String expected = "1 \n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}

	@Test
	public void testWcCharsFromPipe() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "sort sortTestBasic.txt | wc -m";
		String expected = "42 \n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());

		stdout = new ByteArrayOutputStream();
		args = "wc -m sortTestBasic.txt";
		expected = "42 sortTestBasic.txt\n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString()); // both char count should be
													// the same since sort only
													// sorts the file
	}

	@Test
	public void testWcOverlappingOptionsFromPipe() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "head -n 2 fileTest.txt | wc -m -ml -w -wml";
		String expected = "63 13 2 \n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}

	// Test with pipeline where second app throws exception
	@Test(expected = WcException.class)
	public void testPipeWcException() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "head test2.txt | wc -x";
		shell.parseAndEvaluate(args, stdout);
	}

	// Test with pipeline where the first app throws exception
	@Test(expected = CatException.class)
	public void testPipePrevException() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "cat nonexistent.txt | wc";
		shell.parseAndEvaluate(args, stdout);
	}

	// Test with pipeline where both apps throw exception
	// Expected behavior: exception thrown by first app should be caught
	@Test(expected = HeadException.class)
	public void testPipeBothException() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "head nonexistent.txt | wc -ap";
		shell.parseAndEvaluate(args, stdout);
	}

	// Test with two pipelines, with wc being the last cmd
	@Test
	public void testDoublePipeWcLast() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "grep IS sortAppTestCapitalSpecialChars.txt | tail -n 2 | wc -w -m";
		String expected = "27 6 \n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}

	// Test with two pipelines, with wc being the last cmd
	@Test
	public void testDoublePipeWcMiddle() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "grep IS sortAppTestCapitalSpecialChars.txt | tail -n 2 | wc -m -l";
		String expected = "27 2 \n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}

	// Test with command substitution
	@Test
	public void testCmdSubstitution() throws AbstractApplicationException, ShellException {
		// Negative case
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "wc `cat test.txt`";
		String expected = "wc: line: No such file\n" + "wc: 1line: No such file\n" + "wc: 2line: No such file\n"
				+ "wc: 3line: No such file\n" + "wc: 4: No such file\n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}

	@Test
	public void testDoubleCmdSubstitution() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "wc `cat cmdSubFile3.txt` `head cmdSubFile2.txt` test.txt";
		String expected = "27 8 4 test.txt\n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}

	@Test
	public void testDoubleCmdSubstitutionFilename() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "wc `cat cmdSubFile3.txt` `head cmdSubFile.txt`";
		String expected = "38 7 sortAppTestCapitalNumbers.txt\n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}

	@Test
	public void testCmdSubstitutionAndPipe() throws AbstractApplicationException, ShellException {
		stdout = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "wc `cat test.txt` | sort";
		String expected = "wc: 1line: No such file\n" + "wc: 2line: No such file\n" + "wc: 3line: No such file\n"
				+ "wc: 4: No such file\n" + "wc: line: No such file\n";
		shell.parseAndEvaluate(args, stdout);
		assertEquals(expected, stdout.toString());
	}

}
