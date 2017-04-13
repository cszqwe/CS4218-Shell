package sg.edu.nus.comp.cs4218test.impl.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.InvalidFileOrDirException;
import sg.edu.nus.comp.cs4218.exception.TailException;
import sg.edu.nus.comp.cs4218.impl.app.TailApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

/**
 * Test suite for the tail application.
 *
 */
public class TailApplicationTest {

	private final String fileString = "First" + System.lineSeparator() + "Second" + System.lineSeparator() + "Third"
			+ System.lineSeparator() + "Fourth" + System.lineSeparator() + "Fifth" + System.lineSeparator() + "Sixth"
			+ System.lineSeparator() + "Seventh" + System.lineSeparator() + "Eighth" + System.lineSeparator() + "Nineth"
			+ System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh" + System.lineSeparator()
			+ "Twelveth" + System.lineSeparator() + "Thirteenth";

	private final String secondFileString = "Fourteen" + System.lineSeparator() + "Fifteen" + System.lineSeparator()
			+ "Sixteen" + System.lineSeparator() + "Seventeen" + System.lineSeparator() + "Eighteen";
	private File testDir;
	private String filePath;
	private InputStream stdin;
	private OutputStream stdout;

	@Before
	public void setUp() throws Exception {
		filePath = Environment.currentDirectory + File.separator + "testDir" + File.separator + "firstTest.txt";
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
	public void testLastFiveLines() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "5", filePath };

		String expectedString = "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh"
				+ System.lineSeparator() + "Twelveth" + System.lineSeparator() + "Thirteenth" + System.lineSeparator();
		tailApp.run(args, null, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test
	public void testLargeOptionsNumberExpectedWholeFile() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "50000", filePath };

		String expectedString = "First" + System.lineSeparator() + "Second" + System.lineSeparator() + "Third"
				+ System.lineSeparator() + "Fourth" + System.lineSeparator() + "Fifth" + System.lineSeparator()
				+ "Sixth" + System.lineSeparator() + "Seventh" + System.lineSeparator() + "Eighth"
				+ System.lineSeparator() + "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator()
				+ "Eleventh" + System.lineSeparator() + "Twelveth" + System.lineSeparator() + "Thirteenth"
				+ System.lineSeparator();

		tailApp.run(args, null, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test
	public void testNoOptionsExpectedLastTenLines() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { filePath };

		String expectedString = "Fourth" + System.lineSeparator() + "Fifth" + System.lineSeparator() + "Sixth"
				+ System.lineSeparator() + "Seventh" + System.lineSeparator() + "Eighth" + System.lineSeparator()
				+ "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh"
				+ System.lineSeparator() + "Twelveth" + System.lineSeparator() + "Thirteenth" + System.lineSeparator();

		tailApp.run(args, null, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test
	public void testWithOptionsNumberWithZero() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "0", filePath };

		tailApp.run(args, null, stdout);
		assertEquals("", stdout.toString());
	}

	@Test(expected = TailException.class)
	public void testInvalidOptions() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-sd", "12", filePath };
		File outputTestFile = new File("OutputStreamFile");
		outputTestFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(outputTestFile);

		tailApp.run(args, null, outputStream);
		outputStream.close();
		outputTestFile.delete();
	}

	@Test(expected = TailException.class)
	public void testInvalidOptionsNumberWithNegative() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "-12", filePath };
		File outputTestFile = new File("OutputStreamFile");
		outputTestFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(outputTestFile);

		tailApp.run(args, null, outputStream);
		outputStream.close();
		outputTestFile.delete();
	}

	@Test(expected = TailException.class)
	public void testInvalidOptionsNumberWithChar() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "qwer", filePath };
		File outputTestFile = new File("OutputStreamFile");
		outputTestFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(outputTestFile);

		tailApp.run(args, null, outputStream);
		outputStream.close();
		outputTestFile.delete();
	}

	@Test(expected = TailException.class)
	public void testInvalidFilePath() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10", "abcd" };

		tailApp.run(args, null, stdout);
	}

	@Test(expected = TailException.class)
	public void testNoFilePathNoStdin() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10" };
		File outputTestFile = new File("OutputStreamFile");
		outputTestFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(outputTestFile);

		tailApp.run(args, stdin, outputStream);
		outputStream.close();
		outputTestFile.delete();
	}

	@Test
	public void testNoFilePathWithStdin() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10" };

		String expectedString = "Fourth" + System.lineSeparator() + "Fifth" + System.lineSeparator() + "Sixth"
				+ System.lineSeparator() + "Seventh" + System.lineSeparator() + "Eighth" + System.lineSeparator()
				+ "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh"
				+ System.lineSeparator() + "Twelveth" + System.lineSeparator() + "Thirteenth" + System.lineSeparator();

		stdin = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));
		tailApp.run(args, stdin, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test
	public void testSameWithFilePathWithStdin() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10", filePath };

		String expectedString = "Fourth" + System.lineSeparator() + "Fifth" + System.lineSeparator() + "Sixth"
				+ System.lineSeparator() + "Seventh" + System.lineSeparator() + "Eighth" + System.lineSeparator()
				+ "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh"
				+ System.lineSeparator() + "Twelveth" + System.lineSeparator() + "Thirteenth" + System.lineSeparator();

		stdin = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));
		tailApp.run(args, stdin, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test
	public void testDiffWithFilePathWithStdin() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10", filePath };

		String expectedString = "Fourth" + System.lineSeparator() + "Fifth" + System.lineSeparator() + "Sixth"
				+ System.lineSeparator() + "Seventh" + System.lineSeparator() + "Eighth" + System.lineSeparator()
				+ "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh"
				+ System.lineSeparator() + "Twelveth" + System.lineSeparator() + "Thirteenth" + System.lineSeparator();

		stdin = new ByteArrayInputStream(secondFileString.getBytes(StandardCharsets.UTF_8));
		tailApp.run(args, stdin, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test(expected = TailException.class)
	public void testWithFilePathWithInvalidMultipleOptions() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10", "-n", filePath };

		tailApp.run(args, null, stdout);
	}

	@Test(expected = TailException.class)
	public void testWithStdinWithInvalidMultipleOptions() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10", "-n" };
		stdin = new ByteArrayInputStream(secondFileString.getBytes(StandardCharsets.UTF_8));

		tailApp.run(args, stdin, stdout);
	}

	@Test
	public void testWithFilePathWithValidMultipleOptions() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10", "-n", "5", filePath };

		String expectedString = "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh"
				+ System.lineSeparator() + "Twelveth" + System.lineSeparator() + "Thirteenth" + System.lineSeparator();

		tailApp.run(args, null, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test
	public void testWithStdinWithValidMultipleOptions() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10", "-n", "5" };
		stdin = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));

		String expectedString = "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh"
				+ System.lineSeparator() + "Twelveth" + System.lineSeparator() + "Thirteenth" + System.lineSeparator();

		tailApp.run(args, stdin, stdout);
		assertEquals(expectedString, stdout.toString());
	}

	@Test(expected = TailException.class)
	public void testWithFilePathWithInvalidMultipleOptionsNegativeNumber() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10", "-n", "-5", filePath };

		tailApp.run(args, null, stdout);
	}

	@Test(expected = TailException.class)
	public void testWithStdinWithInvalidMultipleOptionsNegativeNumber() throws IOException, TailException {
		TailApplication tailApp = new TailApplication();
		String[] args = { "-n", "10", "-n", "-5" };
		stdin = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));

		tailApp.run(args, null, stdout);
	}

	@Test(expected = TailException.class)
	public void testNumberStrCheckInvalidCharStr() throws TailException {
		TailApplication tailApp = new TailApplication();
		tailApp.numberStrCheck("qwe");
	}

	@Test(expected = TailException.class)
	public void testNumberStrCheckInvalidNegNumber() throws TailException {
		TailApplication tailApp = new TailApplication();
		tailApp.numberStrCheck("-1");
	}

	@Test
	public void testNumberStrCheckValidNumber() throws TailException {
		TailApplication tailApp = new TailApplication();
		int res = tailApp.numberStrCheck("12");
		assertEquals(12, res);
	}

	@Test
	public void testPrintTailForFileValidFile() throws TailException {
		TailApplication tailApp = new TailApplication();
		tailApp.printTailForFile(filePath, 5, stdout);
		String expectedString = "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh"
				+ System.lineSeparator() + "Twelveth" + System.lineSeparator() + "Thirteenth" + System.lineSeparator();
		assertEquals(stdout.toString(), expectedString);
	}

	@Test(expected = TailException.class)
	public void testPrintTailForFileInvalidFile() throws TailException {
		TailApplication tailApp = new TailApplication();
		tailApp.printTailForFile(filePath + "z", 5, stdout);
	}

	@Test
	public void testPrintTailValidFile() throws TailException, IOException, InvalidFileOrDirException {
		TailApplication tailApp = new TailApplication();
		BufferedReader reader = new BufferedReader(new FileReader(Environment.checkFileOrDir(filePath)));
		tailApp.printTail(5, stdout, reader);
		String expectedString = "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh"
				+ System.lineSeparator() + "Twelveth" + System.lineSeparator() + "Thirteenth" + System.lineSeparator();
		assertEquals(stdout.toString(), expectedString);
	}

	@Test(expected = InvalidFileOrDirException.class)
	public void testPrintTailInvalidFile() throws TailException, IOException, InvalidFileOrDirException {
		TailApplication tailApp = new TailApplication();
		BufferedReader reader = new BufferedReader(new FileReader(Environment.checkFileOrDir(filePath + "z")));
		tailApp.printTail(5, stdout, reader);
	}
}
