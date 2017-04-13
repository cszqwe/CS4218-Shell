package sg.edu.nus.comp.cs4218test.impl.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.HeadException;
import sg.edu.nus.comp.cs4218.exception.InvalidFileOrDirException;
import sg.edu.nus.comp.cs4218.exception.TailException;
import sg.edu.nus.comp.cs4218.impl.app.HeadApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test suite for the head application.
 */

public class HeadApplicationTest {

	private static final String THIRD = "Third";
	private static final String SECOND = "Second";
	private static final String EIGHTH = "Eighth";
	private static final String TENTH = "Tenth";
	private static final String NINETH = "Nineth";
	private static final String SEVENTH = "Seventh";
	private static final String SIXTH = "Sixth";
	private static final String FIFTH = "Fifth";
	private static final String FOURTH = "Fourth";
	private static final String FIRST = "First";
	private static final String EXCEPTION = "Should throw exception";
	private static final String OUT_STREAM_FILE = "OutputStreamFile";
	private final String filePath = "testDir" + File.separator + "firstTest.txt";
	private final String fileString = FIRST + System.lineSeparator() + SECOND + System.lineSeparator() + THIRD
			+ System.lineSeparator() + FOURTH + System.lineSeparator() + FIFTH + System.lineSeparator() + SIXTH
			+ System.lineSeparator() + SEVENTH + System.lineSeparator() + EIGHTH + System.lineSeparator() + NINETH
			+ System.lineSeparator() + TENTH + System.lineSeparator() + "Eleventh" + System.lineSeparator() + "Twelveth"
			+ System.lineSeparator() + "Thirteenth";
	private final String secondFileString = "Fourteen" + System.lineSeparator() + "Fifteen" + System.lineSeparator()
			+ "Sixteen" + System.lineSeparator() + "Seventeen" + System.lineSeparator() + "Eighteen";

	private File testDir;
	private InputStream stdin;
	private OutputStream stdout;

	@Before
	public void setUp() throws Exception {
		stdout = new ByteArrayOutputStream();
		testDir = new File("testDir");
		testDir.mkdir();
		File testFile = new File(filePath);
		testFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
		writer.write(fileString);
		writer.close();
	}

	@After
	public void tearDown() throws Exception {
		for (File file : testDir.listFiles()) {
			file.delete();
		}
		testDir.delete();
	}

	@Test
	public void testNullArgs() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		try {
			headApp.run(null, stdin, stdout);
			fail(EXCEPTION);
		} catch (HeadException e) {
			assertEquals("head: Null arguments", e.getMessage());
		}
	}

	@Test
	public void testNullOutputStream() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = {};
		try {
			headApp.run(args, stdin, null);
			fail(EXCEPTION);
		} catch (HeadException e) {
			assertEquals("head: OutputStream not provided", e.getMessage());
		}
	}

	@Test
	public void testNullInputStreamNoArgs() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = {};
		try {
			headApp.run(args, null, System.out);
			fail(EXCEPTION);
		} catch (HeadException e) {
			assertEquals("head: InputStream not provided", e.getMessage());
		}
	}

	@Test
	public void testNullInputStreamWithArgs() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "5" };
		try {
			headApp.run(args, null, System.out);
			fail(EXCEPTION);
		} catch (HeadException e) {
			assertEquals("head: InputStream not provided", e.getMessage());
		}
	}

	@Test
	public void testInvalidArgs() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "5", "10", "15" };
		try {
			headApp.run(args, null, System.out);
			fail(EXCEPTION);
		} catch (HeadException e) {
			assertEquals("head: Invalid options", e.getMessage());
		}
	}

	@Test
	public void testInvalidFile() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "fakePath" };
		try {
			headApp.run(args, stdin, System.out);
			fail(EXCEPTION);
		} catch (HeadException e) {
			assertEquals("head: fakePath: No such file or directory", e.getMessage());
		}

	}

	@Test
	public void testPrintFromFileNJustBigger() throws IOException, AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "14", filePath };

		headApp.run(args, null, stdout);
		assertEquals(fileString + System.lineSeparator(), stdout.toString());
	}

	@Test
	public void testPrintFromFileOneLine() throws IOException, AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "1", filePath };

		headApp.run(args, null, stdout);
		assertEquals(FIRST + System.lineSeparator(), stdout.toString());
	}

	@Test
	public void testNoOptionsExpectFirstTenLines() throws IOException, AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { filePath };

		String expectedString = FIRST + System.lineSeparator() + SECOND + System.lineSeparator() + THIRD
				+ System.lineSeparator() + FOURTH + System.lineSeparator() + FIFTH + System.lineSeparator() + SIXTH
				+ System.lineSeparator() + SEVENTH + System.lineSeparator() + EIGHTH + System.lineSeparator() + NINETH
				+ System.lineSeparator() + TENTH + System.lineSeparator();

		headApp.run(args, null, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test(expected = HeadException.class)
	public void testPrintFromFileNNegative() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "-1", filePath };

		headApp.run(args, stdin, System.out);
	}

	@Test
	public void testInputFromStream() throws IOException, AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		InputStream stubInputStream = new FileInputStream(filePath);
		String[] args = { "-n", "5" };

		String expectedString = FIRST + System.lineSeparator() + SECOND + System.lineSeparator() + THIRD
				+ System.lineSeparator() + FOURTH + System.lineSeparator() + FIFTH + System.lineSeparator();
		headApp.run(args, stubInputStream, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test
	public void testWithOptionsNumberWithZero() throws IOException, AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "0", filePath };

		headApp.run(args, null, stdout);
		assertEquals("", stdout.toString());
	}

	@Test(expected = HeadException.class)
	public void testInvalidOptions() throws IOException, AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-sd", "12", filePath };
		File outputTestFile = new File(OUT_STREAM_FILE);
		outputTestFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(outputTestFile);

		headApp.run(args, null, outputStream);
		outputStream.close();
		outputTestFile.delete();
	}

	@Test(expected = HeadException.class)
	public void testInvalidOptionsNumberWithNegative() throws IOException, AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "-12", filePath };
		File outputTestFile = new File(OUT_STREAM_FILE);
		outputTestFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(outputTestFile);

		headApp.run(args, null, outputStream);
		outputStream.close();
		outputTestFile.delete();
	}

	@Test(expected = HeadException.class)
	public void testInvalidOptionsNumberWithChar() throws IOException, AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "qwer", filePath };
		File outputTestFile = new File(OUT_STREAM_FILE);
		outputTestFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(outputTestFile);

		headApp.run(args, null, outputStream);
		outputStream.close();
		outputTestFile.delete();
	}

	@Test(expected = HeadException.class)
	public void testInvalidFilePath() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10", "abcd" };

		headApp.run(args, null, stdout);
	}

	@Test(expected = HeadException.class)
	public void testNoFilePathNoStdin() throws IOException, AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10" };
		File outputTestFile = new File(OUT_STREAM_FILE);
		outputTestFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(outputTestFile);

		headApp.run(args, stdin, outputStream);
		outputStream.close();
		outputTestFile.delete();
	}

	@Test
	public void testNoFilePathWithStdin() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10" };

		String expectedString = FIRST + System.lineSeparator() + SECOND + System.lineSeparator() + THIRD
				+ System.lineSeparator() + FOURTH + System.lineSeparator() + FIFTH + System.lineSeparator() + SIXTH
				+ System.lineSeparator() + SEVENTH + System.lineSeparator() + EIGHTH + System.lineSeparator() + NINETH
				+ System.lineSeparator() + TENTH + System.lineSeparator();

		stdin = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));
		headApp.run(args, stdin, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test
	public void testSameWithFilePathWithStdin() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10", filePath };

		String expectedString = FIRST + System.lineSeparator() + SECOND + System.lineSeparator() + THIRD
				+ System.lineSeparator() + FOURTH + System.lineSeparator() + FIFTH + System.lineSeparator() + SIXTH
				+ System.lineSeparator() + SEVENTH + System.lineSeparator() + EIGHTH + System.lineSeparator() + NINETH
				+ System.lineSeparator() + TENTH + System.lineSeparator();

		stdin = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));
		headApp.run(args, stdin, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test
	public void testDiffWithFilePathWithStdin() throws AbstractApplicationException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10", filePath };

		String expectedString = FIRST + System.lineSeparator() + SECOND + System.lineSeparator() + THIRD
				+ System.lineSeparator() + FOURTH + System.lineSeparator() + FIFTH + System.lineSeparator() + SIXTH
				+ System.lineSeparator() + SEVENTH + System.lineSeparator() + EIGHTH + System.lineSeparator() + NINETH
				+ System.lineSeparator() + TENTH + System.lineSeparator();

		stdin = new ByteArrayInputStream(secondFileString.getBytes(StandardCharsets.UTF_8));
		headApp.run(args, stdin, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test(expected = HeadException.class)
	public void testWithFilePathWithInvalidMultipleOptions() throws HeadException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10", "-n", filePath };

		headApp.run(args, null, stdout);
	}

	@Test(expected = HeadException.class)
	public void testWithStdinWithInvalidMultipleOptions() throws HeadException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10", "-n" };
		stdin = new ByteArrayInputStream(secondFileString.getBytes(StandardCharsets.UTF_8));

		headApp.run(args, stdin, stdout);
	}

	@Test
	public void testWithFilePathWithValidMultipleOptions() throws HeadException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10", "-n", "5", filePath };

		String expectedString = FIRST + System.lineSeparator() + SECOND + System.lineSeparator() + THIRD
				+ System.lineSeparator() + FOURTH + System.lineSeparator() + FIFTH + System.lineSeparator();

		headApp.run(args, null, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test
	public void testWithStdinWithValidMultipleOptions() throws HeadException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10", "-n", "5" };
		stdin = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));

		String expectedString = FIRST + System.lineSeparator() + SECOND + System.lineSeparator() + THIRD
				+ System.lineSeparator() + FOURTH + System.lineSeparator() + FIFTH + System.lineSeparator();

		headApp.run(args, stdin, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test(expected = HeadException.class)
	public void testWithFilePathWithInvalidMultipleOptionsNegativeNumber() throws HeadException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10", "-n", "-5", filePath };

		headApp.run(args, null, stdout);
	}

	@Test(expected = HeadException.class)
	public void testWithStdinWithInvalidMultipleOptionsNegativeNumber() throws HeadException {
		HeadApplication headApp = new HeadApplication();
		String[] args = { "-n", "10", "-n", "-5" };
		stdin = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));

		headApp.run(args, null, stdout);
	}

	@Test(expected = HeadException.class)
	public void testNumberStrCheckInvalidCharStr() throws HeadException {
		HeadApplication headApp = new HeadApplication();
		headApp.numberStrCheck("qwe");
	}

	@Test(expected = HeadException.class)
	public void testNumberStrCheckInvalidNegNumber() throws HeadException {
		HeadApplication headApp = new HeadApplication();
		headApp.numberStrCheck("-1");
	}

	@Test
	public void testNumberStrCheckValidNumber() throws HeadException {
		HeadApplication headApp = new HeadApplication();
		int res = headApp.numberStrCheck("12");
		assertEquals(12, res);
	}

	@Test
	public void testPrintHeadForFileValidFile() throws HeadException {
		HeadApplication headApp = new HeadApplication();

		headApp.printHeadForFile(filePath, 5, stdout);
		String expectedString = FIRST + System.lineSeparator() + SECOND + System.lineSeparator() + THIRD
				+ System.lineSeparator() + FOURTH + System.lineSeparator() + FIFTH + System.lineSeparator();
		assertEquals(stdout.toString(), expectedString);
	}

	@Test(expected = HeadException.class)
	public void testPrintHeadForFileInvalidFile() throws HeadException {
		HeadApplication headApp = new HeadApplication();
		headApp.printHeadForFile(filePath + "z", 5, stdout);
	}

	@Test
	public void testPrintHeadValidFile() throws IOException, InvalidFileOrDirException, HeadException {
		HeadApplication headApp = new HeadApplication();
		BufferedReader reader = new BufferedReader(new FileReader(Environment.checkFileOrDir(filePath)));
		headApp.printHead(5, stdout, reader);
		String expectedString = FIRST + System.lineSeparator() + SECOND + System.lineSeparator() + THIRD
				+ System.lineSeparator() + FOURTH + System.lineSeparator() + FIFTH + System.lineSeparator();
		assertEquals(stdout.toString(), expectedString);
	}

	@Test(expected = InvalidFileOrDirException.class)
	public void testPrintHeadInvalidFile() throws IOException, InvalidFileOrDirException, HeadException {
		HeadApplication headApp = new HeadApplication();
		BufferedReader reader = new BufferedReader(new FileReader(Environment.checkFileOrDir(filePath + "z")));
		headApp.printHead(5, stdout, reader);
	}
}
