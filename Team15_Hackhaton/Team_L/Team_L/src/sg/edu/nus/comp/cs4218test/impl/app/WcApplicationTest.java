package sg.edu.nus.comp.cs4218test.impl.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.WcException;
import sg.edu.nus.comp.cs4218.impl.app.WcApplication;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class WcApplicationTest {
	private File testDir;
	private String filePath;
	private String invalidFilePath;
	private String fileString;
	private InputStream stdin;
	private OutputStream stdout;
	private int charCount;
	private int wordCount;
	private int newLineCount;
	private String allCount;
	private WcApplication app;

	@Before
	public void setUp() throws Exception {
		filePath = Environment.currentDirectory + File.separator + "testDir" + File.separator + "firstTest.txt";
		invalidFilePath = Environment.currentDirectory + File.separator + "testDir" + File.separator + "firstTt.txt";
		testDir = new File("testDir");
		testDir.mkdir();
		File testFile = new File(filePath);
		testFile.createNewFile();
		fileString = "123 456 789 aaa bbb ccc ddd" + System.lineSeparator() + System.lineSeparator() + "aaa 00000";
		BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
		writer.write(fileString);
		writer.close();
		app = new WcApplication();

		stdin = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));
		stdout = new ByteArrayOutputStream();
		charCount = fileString.length();
		wordCount = 9;
		newLineCount = 2;
		allCount = charCount + " " + wordCount + " " + newLineCount;
	}

	@After
	public void tearDown() throws Exception {
		for (File file : testDir.listFiles()) {
			file.delete();
		}
		testDir.delete();
	}

	@Test
	public void testFinalCommandInStdinOptionMW() {
		WcApplication wcApp = new WcApplication();
		int[] arr = { 1, 1, 0 };
		String res = wcApp.finalCommandInStdin(arr, fileString);
		String expected = charCount + " " + wordCount + " ";
		assertEquals(expected, res);
	}

	@Test
	public void testFinalCommandInStdinOptionMWL() {
		WcApplication wcApp = new WcApplication();
		int[] arr = { 1, 1, 1 };
		String res = wcApp.finalCommandInStdin(arr, fileString);
		String expected = allCount + " ";
		assertEquals(expected, res);
	}

	@Test
	public void testFinalCommandInFileOptionMW() {
		WcApplication wcApp = new WcApplication();
		int[] arr = { 1, 1, 0 };
		String res = wcApp.finalCommandInFile(arr, fileString);
		String expected = charCount + " " + wordCount + " ";
		assertEquals(expected, res);
	}

	@Test
	public void testGetFileContentInStr() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader((filePath)));
		String res = WcApplication.getFileContentInStr(reader);
		assertEquals(res, fileString);
	}

	@Test
	public void testFinalCommandInFileOptionMWL() {
		WcApplication wcApp = new WcApplication();
		int[] arr = { 1, 1, 1 };
		String res = wcApp.finalCommandInFile(arr, fileString);
		String expected = allCount + " ";
		assertEquals(expected, res);
	}

	@Test
	public void testCommandProcessingIsFileSeperatedArgs() throws WcException {
		WcApplication wcApp = new WcApplication();
		String[] args = { "-m", "-w", filePath };
		int[] arr;
		int[] expected = { 1, 1, 0 };
		arr = wcApp.commandProcessing(args, true);
		assertEquals(arr[0], expected[0]);
		assertEquals(arr[1], expected[1]);
		assertEquals(arr[2], expected[2]);
	}

	@Test
	public void testCommandProcessingIsStdinSeperatedArgs() throws WcException {
		WcApplication wcApp = new WcApplication();
		String[] args = { "-m", "-w" };
		int[] arr;
		int[] expected = { 1, 1, 0 };
		arr = wcApp.commandProcessing(args, false);
		assertEquals(arr[0], expected[0]);
		assertEquals(arr[1], expected[1]);
		assertEquals(arr[2], expected[2]);
	}

	@Test
	public void testCommandProcessingIsFileChainedArgs() throws WcException {
		WcApplication wcApp = new WcApplication();
		String[] args = { "-lm", "-wl", filePath };
		int[] arr;
		int[] expected = { 1, 1, 2 };
		arr = wcApp.commandProcessing(args, true);
		assertEquals(arr[0], expected[0]);
		assertEquals(arr[1], expected[1]);
		assertEquals(arr[2], expected[2]);
	}

	@Test
	public void testCommandProcessingIsStdinChainedArgs() throws WcException {
		WcApplication wcApp = new WcApplication();
		String[] args = { "-mm", "-wl" };
		int[] arr;
		int[] expected = { 2, 1, 1 };
		arr = wcApp.commandProcessing(args, false);
		assertEquals(arr[0], expected[0]);
		assertEquals(arr[1], expected[1]);
		assertEquals(arr[2], expected[2]);
	}

	@Test(expected = WcException.class)
	public void testCommandProcessingIsFileInvalidChainedArgs() throws WcException {
		WcApplication wcApp = new WcApplication();
		String[] args = { "-lm", "-wx", filePath };
		wcApp.commandProcessing(args, true);
	}

	@Test(expected = WcException.class)
	public void testCommandProcessingIsFileInvalidSeperatedArgs() throws WcException {
		WcApplication wcApp = new WcApplication();
		String[] args = { "-m", "-x", filePath };
		wcApp.commandProcessing(args, true);
	}

	@Test(expected = WcException.class)
	public void testCommandProcessingIsFileMissingOptionIndicator() throws WcException {
		WcApplication wcApp = new WcApplication();
		String[] args = { "m", "x", filePath };
		wcApp.commandProcessing(args, true);
	}

	@Test(expected = WcException.class)
	public void testReturnFileContentInInvalidFile() throws WcException {
		WcApplication wcApp = new WcApplication();
		wcApp.returnFileContentInFile(invalidFilePath);
	}

	@Test
	public void testReturnFileContentInFile() throws WcException {
		WcApplication wcApp = new WcApplication();
		String result = wcApp.returnFileContentInFile(filePath);
		String expected = fileString;
		assertEquals(result, expected);
	}

	@Test
	public void testWcWithNullOut() {
		WcApplication wcApp = new WcApplication();

		String[] args = { filePath };
		try {
			wcApp.run(args, null, null);
			fail("Should throw exception");
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("OutputStream not provided"));
		}
	}

	@Test
	public void testWcWithNoFile() {
		WcApplication wcApp = new WcApplication();

		String[] args = {};
		try {
			wcApp.run(args, null, stdout);
			fail("Should throw exception");
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("Incomplete command"));
		}
	}

	@Test
	public void testWcWithNoFileNullStdin() {
		WcApplication wcApp = new WcApplication();

		String[] args = {};
		try {
			wcApp.run(args, null, stdout);
			fail("Should throw exception");
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("Incomplete command"));
		}
	}

	@Test
	public void testWcWithFileNoOptions() {
		WcApplication wcApp = new WcApplication();

		String[] args = { filePath };
		try {
			wcApp.run(args, null, stdout);
			assertTrue(stdout.toString()
					.equals(charCount + " " + wordCount + " " + newLineCount + System.lineSeparator()));
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception");
		}
	}

	@Test
	public void testWcWithMultipleFileNoOptions() {
		WcApplication wcApp = new WcApplication();

		String[] args = { filePath, filePath };
		try {
			wcApp.run(args, null, stdout);
			fail("Should throw exception");
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("Illegal option"));
		}
	}

	@Test
	public void testWcWithInvalidFileNoOptions() {
		WcApplication wcApp = new WcApplication();

		String[] args = { invalidFilePath };
		try {
			wcApp.run(args, null, stdout);
			fail("Should throw exception");
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("No such file or directory"));
		}
	}

	@Test
	public void testWcWithFileInvalidOption() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-x", "-m", filePath };
		try {
			wcApp.run(args, null, stdout);
			fail("Should throw exception");
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("Illegal option"));
		}
	}

	@Test
	public void testWcWithFileInvalidOptionInChain() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-lxx", filePath };
		try {
			wcApp.run(args, null, stdout);
			fail("Should throw exception");
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("Illegal option"));
		}
	}

	@Test
	public void testWcWithStdinNoOptions() {
		WcApplication wcApp = new WcApplication();

		String[] args = {};
		try {
			wcApp.run(args, stdin, stdout);
			assertTrue(stdout.toString()
					.equals(charCount + " " + wordCount + " " + newLineCount + System.lineSeparator()));
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithOptionM() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-m", filePath };
		try {
			wcApp.run(args, null, stdout);
			assertEquals(charCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithOptionM() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-m" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(charCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithOptionMForNonAscii() {
		WcApplication wcApp = new WcApplication();
		stdin = new ByteArrayInputStream("我的家".getBytes(StandardCharsets.UTF_8));

		String[] args = { "-m" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(3 + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithOptionW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-w", filePath };
		try {
			wcApp.run(args, null, stdout);
			assertEquals(wordCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithOptionW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-w" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(wordCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithOptionL() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-l", filePath };
		try {
			wcApp.run(args, null, stdout);
			assertEquals(newLineCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithOptionL() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-l" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(newLineCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithOptionMW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-mw", filePath };
		try {
			wcApp.run(args, null, stdout);
			assertEquals(charCount + " " + wordCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithOptionMW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-mw" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(charCount + " " + wordCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithDuplicateOptionMWW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-mww", filePath };
		try {
			wcApp.run(args, null, stdout);
			assertEquals(charCount + " " + wordCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithDuplicateOptionMWW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-mww" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(charCount + " " + wordCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithOptionML() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-ml", filePath };
		try {
			wcApp.run(args, null, stdout);
			assertEquals(charCount + " " + newLineCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithOptionML() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-ml" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(charCount + " " + newLineCount + " " + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithOptionWML() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-wml", filePath };
		try {
			wcApp.run(args, null, stdout);
			assertEquals(charCount + " " + wordCount + " " + newLineCount + " " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithOptionWML() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-wml" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(charCount + " " + wordCount + " " + newLineCount + " " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithOptionMLW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-mlw", filePath };
		try {
			wcApp.run(args, null, stdout);
			assertEquals(charCount + " " + wordCount + " " + newLineCount + " " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithOptionMLW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-mlw" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(charCount + " " + wordCount + " " + newLineCount + " " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithOptionMWL() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-mwl", filePath };
		try {
			wcApp.run(args, null, stdout);
			assertEquals(charCount + " " + wordCount + " " + newLineCount + " " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithOptionMWL() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-mwl" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(charCount + " " + wordCount + " " + newLineCount + " " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithOptionSeparatedMLW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-m", "-l", "-w", filePath };
		try {
			wcApp.run(args, null, stdout);
			assertEquals(charCount + " " + wordCount + " " + newLineCount + " " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithStdinWithOptionSeparatedMLW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-m", "-l", "-w" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(charCount + " " + wordCount + " " + newLineCount + " " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithFileWithStdinWithOptionSeparatedMLW() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-m", "-l", "-w", filePath };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(charCount + " " + wordCount + " " + newLineCount + " " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithOptionSeparatedMLWNoFilePathWithStdin() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-m", "-l", "-w" };
		try {
			wcApp.run(args, stdin, stdout);
			assertEquals(charCount + " " + wordCount + " " + newLineCount + " " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWcWithOptionSeparatedMLWNoFilePathNullStdin() {
		WcApplication wcApp = new WcApplication();

		String[] args = { "-m", "-l", "-w" };
		try {
			wcApp.run(args, null, stdout);
		} catch (AbstractApplicationException e) {
			assertTrue(e.getMessage().contains("InputStream not provided"));
		}
	}

	@Test
	public void printAllCountsInFile() {
		String expected = allCount;
		String result = app.printAllCountsInFile(fileString);
		assertEquals(expected, result);
	}

	@Test
	public void printCharacterCountInStdin() {
		String expected = Integer.toString(charCount);
		String result = app.printCharacterCountInStdin(fileString);
		assertEquals(expected, result);
	}

	@Test
	public void printWordCountInStdin() {
		String expected = Integer.toString(wordCount);
		String result = app.printWordCountInStdin(fileString);
		assertEquals(expected, result);
	}

	@Test
	public void printNewlineCountInStdin() {
		String expected = Integer.toString(newLineCount);
		String result = app.printNewlineCountInStdin(fileString);
		assertEquals(expected, result);
	}

	@Test
	public void printAllCountsInStdin() {
		String expected = allCount;
		String result = app.printAllCountsInStdin(fileString);
		assertEquals(expected, result);
	}
}
