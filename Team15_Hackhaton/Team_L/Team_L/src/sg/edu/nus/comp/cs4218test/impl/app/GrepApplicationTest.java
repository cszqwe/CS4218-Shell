package sg.edu.nus.comp.cs4218test.impl.app;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.*;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;

/**
 * Test suite for the grep application.
 *
 */
public class GrepApplicationTest {

	private File testDir;
	private File testDir2;
	private String testDir2StrWithBackslash = Environment.currentDirectory + File.separator + "testDir2/";
	private String testDir2StrWithoutBackslash = Environment.currentDirectory + File.separator + "testDir2";
	private File testFile;
	private String[] filePaths = { "firstText.txt", "secondText.txt" };
	private GrepApplication testGrep;
	private InputStream stdin;
	private OutputStream stdout;
	private String[][] fileStringArrs = {
			{ "abc", "ab1c", "1 1 1 1", "1", "1 abc", "!/123", "%&abc", "abc!!abc", "abc!1abc", "aBC1!", "aBc",
					"a-zA-Z" },
			{ "abc", "ab1c", "3 2 1 0", "4", "0 xAByz", "!/123", "!abc", "xyz!%xyz", "xYZc!", "zYx" } };

	@Before
	public void setup() {
		stdin = System.in;
		stdout = new ByteArrayOutputStream();
		testGrep = new GrepApplication();
		testDir = new File("testDir");
		testDir2 = new File("testDir2");
		testDir.mkdir();
		testDir2.mkdir();

		try {
			for (int i = 0; i < filePaths.length; i++) {
				filePaths[i] = Environment.currentDirectory + File.separator + "testDir" + File.separator
						+ filePaths[i];
				testFile = new File(filePaths[i]);
				testFile.createNewFile();
				StringBuilder firstSB = new StringBuilder();
				String[] stringArr = fileStringArrs[i];

				for (String s : stringArr) {
					firstSB.append(s);
					firstSB.append(System.lineSeparator());
				}
				BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
				writer.write(firstSB.toString());
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@After
	public void tearDown() {
		for (File file : testDir.listFiles()) {
			file.delete();
		}
		testDir.delete();
	}

	/*
	 * Test cases for run/grep method
	 */
	@Test
	public void testGrepNullArgs() {
		try {
			testGrep.run(null, stdin, stdout);
		} catch (GrepException e) {
			assertEquals(e.getMessage(), "grep: Insufficient Parameters");
		}
	}

	@Test
	public void testGrepDirWithBackslash() {
		String[] args = { "abc", testDir2StrWithBackslash };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("grep: testDir2/: Is a directory" + System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepDirWithoutBackslash() {
		String[] args = { "abc", testDir2StrWithoutBackslash };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("grep: testDir2: Is a directory" +System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepAlphabetArgs() {
		String[] args = { "abc", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("abc" +System.lineSeparator()+"1 abc" +System.lineSeparator()+"%&abc" +System.lineSeparator() +"abc!!abc" +System.lineSeparator()+ "abc!1abc"+System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepIntegerArgs() {
		String[] args = { "123", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("!/123" +System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepSpecialCharArgs() {
		String[] args = { "%&", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("%&abc" +System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithSpecialCharIntegerArgsNotFound() {
		String[] args = { "\\\\d", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("", stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithSpecialCharIntegerArgsFound() {
		String[] args = { "!a", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("abc!!abc" +System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithSpecialCharLetterArgsNotFound() {
		String[] args = { "a-zxA-Z", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("", stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithSpecialCharLetterArgsFound() {
		String[] args = { "a-zA-Z", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("a-zA-Z" +System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithSpecialCharLetterDigitArgsNotFound() {
		String[] args = { "c1!", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("", stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithSpecialCharLetterDigitArgsFound() {
		String[] args = { "C1!", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("aBC1!" + System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithIntegerRegexArgs() {
		String[] args = { "\\d", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("ab1c" +System.lineSeparator()+"1 1 1 1" +System.lineSeparator()+"1" +System.lineSeparator()+"1 abc" +System.lineSeparator()+"!/123" +System.lineSeparator()+"abc!1abc" +System.lineSeparator()+"aBC1!" +System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithLetterRegexArgs() {
		String[] args = { "[a-zA-Z]", filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("abc" +System.lineSeparator()+"ab1c"+System.lineSeparator()+"1 abc"+System.lineSeparator()+"%&abc"+System.lineSeparator()+ "abc!!abc" +System.lineSeparator() +"abc!1abc"+ System.lineSeparator()+"aBC1!"+System.lineSeparator()+"aBc"+System.lineSeparator()+"a-zA-Z"+System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithSpecialCharsRegexArgs() {
		String[] args = { "[^A-Za-z0-9]", filePaths[0] };
		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("1 1 1 1" +System.lineSeparator()+"1 abc"+System.lineSeparator()+"!/123" +System.lineSeparator()+ "%&abc" +System.lineSeparator()+ "abc!!abc"+ System.lineSeparator()+ "abc!1abc" + System.lineSeparator()+ "aBC1!" +System.lineSeparator()+"a-zA-Z" +System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithMultipleFilesArgsFound() {
		String[] args = { "abc", filePaths[0], filePaths[1] };
		try {
			testGrep.run(args, stdin, stdout);
			assertEquals(
					"firstText.txt:abc" +System.lineSeparator() +"firstText.txt:1 abc" +System.lineSeparator()+"firstText.txt:%&abc" +System.lineSeparator()+"firstText.txt:abc!!abc" +System.lineSeparator() +"firstText.txt:abc!1abc" + System.lineSeparator()+ "secondText.txt:abc" +System.lineSeparator()+ "secondText.txt:!abc" +System.lineSeparator(),
					stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepWithMultipleFilesArgsNotFound() {
		String[] args = { "xBB", filePaths[0], filePaths[1] };
		try {
			testGrep.run(args, stdin, stdout);
			assertEquals("", stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testGrepNullOutput() {
		String[] args = { "[a-z]+", filePaths[0] };

		try {
			testGrep.run(args, stdin, null);
			fail("Should not pass as output stream is null");

		} catch (GrepException e) {
			assertEquals(e.getMessage(), "grep: Null OutputStream");
		}
	}

	@Test
	public void testGrepInvalidFilePathPatternArgs() {
		String[] args = { filePaths[0], filePaths[0] };

		try {
			testGrep.run(args, stdin, stdout);
		} catch (GrepException e) {
			assertEquals(e.getMessage(), "grep: Invalid Pattern");
		}
	}

	/*
	 * Test cases for grepFromStdin method
	 */
	@Test
	public void testGrepFromStdinWithNullArgs() {
		assertEquals("grep: Null Arguments" +System.lineSeparator(), testGrep.grepFromStdin(null));
	}

	@Test
	public void testGrepFromStdinWithEmptyStrParameters() {
		assertEquals("grep: Insufficient Parameters" +System.lineSeparator(), testGrep.grepFromStdin(""));
	}

	@Test
	public void testGrepFromStdinWithSingleParameter() {
		assertEquals("grep: Insufficient Parameters" +System.lineSeparator(), testGrep.grepFromStdin("check"));
	}

	@Test
	public void testGrepFromStdinWithTwoParametersResultNotFound() {
		assertEquals("", testGrep.grepFromStdin("check some"));
	}

	@Test
	public void testGrepFromStdinWithTwoParametersResultFound() {
		assertEquals("checkSome" +System.lineSeparator(), testGrep.grepFromStdin("check checkSome"));
	}

	@Test
	public void testGrepFromStdinWithMultipleParametersResultNotFound() {
		assertEquals("", testGrep.grepFromStdin("check looks like chec but not ceck"));
	}

	@Test
	public void testGrepFromStdinWithMultipleParametersResultFound() {
		assertEquals("this is check" + System.lineSeparator(), testGrep.grepFromStdin("check this is check"));
	}

	@Test
	public void testGrepFromStdinWithRegexResultNotFound() {
		assertEquals("", testGrep.grepFromStdin("\\d density d"));
	}

	@Test
	public void testGrepFromStdinWithRegexResultFound() {
		assertEquals("3 results are found" +System.lineSeparator(), testGrep.grepFromStdin("\\d 3 results are found"));
	}

	/*
	 * Test cases for grepFromOneFile method
	 */
	@Test
	public void testGrepFromOneFileWithNullArgs() {
		assertEquals("grep: Null Arguments" +System.lineSeparator(), testGrep.grepFromOneFile(null));
	}

	@Test
	public void testGrepFromOneFileWithEmptyStrParameters() {
		assertEquals("grep: Insufficient Parameters" +System.lineSeparator(), testGrep.grepFromOneFile(""));
	}

	@Test
	public void testGrepFromOneFileWithSingleParameter() {
		assertEquals("grep: Insufficient Parameters" +System.lineSeparator(), testGrep.grepFromOneFile("check"));
	}

	@Test
	public void testGrepFromOneFileWithInvalidFile() {
		assertEquals("grep: check.txt: No such file or directory " +System.lineSeparator(), testGrep.grepFromOneFile("check check.txt"));
	}

	@Test
	public void testGrepFromOneFileWithInvalidDirectory() {
		assertEquals("grep: check/txt: No such file or directory " +System.lineSeparator(), testGrep.grepFromOneFile("check check/txt"));
	}

	@Test
	public void testGrepFromOneFileResultNotFound() {
		assertEquals("", testGrep.grepFromOneFile("zzz " + filePaths[0]));
	}

	@Test
	public void testGrepFromOneFileResultFound() {
		assertEquals("a-zA-Z" +System.lineSeparator(), testGrep.grepFromOneFile("A-Z " + filePaths[0]));
	}

	@Test
	public void testGrepFromOneFileWithMultipleFiles() {
		assertEquals("grep: Too many parameters" +System.lineSeparator(), testGrep.grepFromOneFile("A-Z firstText.txt secondText.txt"));
	}

	@Test
	public void testGrepFromOneFileWithBlackslashDir() {
		assertEquals("grep: testDir2/: Is a directory" +System.lineSeparator(), testGrep.grepFromOneFile("abc " + testDir2StrWithBackslash));
	}

	@Test
	public void testGrepFromOneFileWithWithoutBackslashDir() {
		assertEquals("grep: testDir2: Is a directory" +System.lineSeparator(),
				testGrep.grepFromOneFile("abc " + testDir2StrWithoutBackslash));
	}

	/*
	 * Test cases for grepFromMultipleFiles method
	 */
	@Test
	public void testGrepFromMultipleFilesWithNullArgs() {
		assertEquals("grep: Null Arguments" +System.lineSeparator(), testGrep.grepFromMultipleFiles(null));
	}

	@Test
	public void testGrepFromMultipleFilesWithEmptyStrParameters() {
		assertEquals("grep: Insufficient Parameters" +System.lineSeparator(), testGrep.grepFromMultipleFiles(""));
	}

	@Test
	public void testGrepFromMultipleFilesWithSingleParameter() {
		assertEquals("grep: Insufficient Parameters" +System.lineSeparator(), testGrep.grepFromMultipleFiles("check"));
	}

	@Test
	public void testGrepFromMultipleFilesWithTwoParameters() {
		assertEquals("grep: Insufficient Parameters" +System.lineSeparator(), testGrep.grepFromMultipleFiles("check input.txt"));
	}

	@Test
	public void testGrepFromMultipleFilesWithFirstInvalidFile() {
		assertEquals("grep: invalid.txt: No such file or directory " +System.lineSeparator() + "firstText.txt:a-zA-Z" + System.lineSeparator(),
				testGrep.grepFromMultipleFiles("a-zA-Z invalid.txt " + filePaths[0]));
	}

	@Test
	public void testGrepFromMultipleFilesWithSecondInvalidFile() {
		assertEquals("firstText.txt:a-zA-Z" +System.lineSeparator()  + "grep: invalid.txt: No such file or directory " + System.lineSeparator(),
				testGrep.grepFromMultipleFiles("a-zA-Z " + filePaths[0] + " invalid.txt"));
	}

	@Test
	public void testGrepFromMultipleFilesWithBothInvalidFile() {
		assertEquals("grep: invalid.txt: No such file or directory " +System.lineSeparator() + "grep: invalid2.txt: No such file or directory " +System.lineSeparator(),
				testGrep.grepFromMultipleFiles("check invalid.txt invalid2.txt"));
	}

	@Test
	public void testGrepFromMultipleFilesWithBothValidFilesResultFound() {
		assertEquals("firstText.txt:!/123" +System.lineSeparator()+ "secondText.txt:!/123" +System.lineSeparator(),
				testGrep.grepFromMultipleFiles("!/123 " + filePaths[0] + " " + filePaths[1]));
	}

	@Test
	public void testGrepFromMultipleFilesWithBothValidFilesResultNotFound() {
		assertEquals("", testGrep.grepFromMultipleFiles("!/1231231231 " + filePaths[0] + " " + filePaths[1]));
	}

	@Test
	public void testGrepFromMultipleFileWithFirstDir() {
		assertEquals("grep: testDir2/: Is a directory" +System.lineSeparator() +"firstText.txt:a-zA-Z" +System.lineSeparator(),
				testGrep.grepFromMultipleFiles("a-zA-Z " + testDir2StrWithBackslash + " " + filePaths[0]));
	}

	@Test
	public void testGrepFromMultipleFileWithSecondDir() {
		assertEquals("firstText.txt:a-zA-Z" +System.lineSeparator() + "grep: testDir2/: Is a directory" +System.lineSeparator(),
				testGrep.grepFromMultipleFiles("a-zA-Z " + filePaths[0] + " " + testDir2StrWithBackslash));
	}

	@Test
	public void testGrepFromMultipleFileWithBothDir() {
		assertEquals("grep: testDir2: Is a directory" +System.lineSeparator() + "grep: testDir2/: Is a directory" +System.lineSeparator(), testGrep
				.grepFromMultipleFiles("a-zA-Z " + testDir2StrWithoutBackslash + " " + testDir2StrWithBackslash));
	}

	@Test
	public void testGrepFromMultipleFileWithValidDirValidFile() {
		assertEquals("grep: testDir2: Is a directory" +System.lineSeparator() + "grep: invalid.txt: No such file or directory " +System.lineSeparator(),
				testGrep.grepFromMultipleFiles("a-zA-Z " + testDir2StrWithoutBackslash + " invalid.txt"));
	}

	/*
	 * Test cases for grepInvalidPatternInStdin
	 */
	@Test
	public void testGrepInvalidPatternInStdinWithNullArg() {
		assertEquals(null, testGrep.grepInvalidPatternInStdin(null));
	}
	
	@Test
	public void testGrepInvalidPatternInStdinWithEmptyStr() {
		assertEquals(null, testGrep.grepInvalidPatternInStdin(""));
	}
	
	@Test
	public void testGrepInvalidPatternInStdinWithValidPattern() {
		assertEquals(null, testGrep.grepInvalidPatternInStdin("this is valid pattern"));
	}
	
	@Test
	public void testGrepInvalidPatternInStdinWithInvalidPattern() {
		assertEquals("Dangling meta character '*' near index 0" +System.lineSeparator()+"*" +System.lineSeparator()+"^", testGrep.grepInvalidPatternInStdin("*"));
	}
	
	/*
	 * Test cases for grepInvalidPatternInFile
	 */
	@Test
	public void testGrepInvalidPatternInFileWithNullArg() {
		assertEquals(null, testGrep.grepInvalidPatternInFile(null));
	}
	
	@Test
	public void testGrepInvalidPatternInFileWithEmptyStr() {
		assertEquals(null, testGrep.grepInvalidPatternInFile(""));
	}
	
	@Test
	public void testGrepInvalidPatternInFileWithValidPattern() {
		assertEquals(null, testGrep.grepInvalidPatternInFile("this is valid pattern"));
	}
	
	@Test
	public void testGrepInvalidPatternInFileWithInvalidPattern() {
		assertEquals("Dangling meta character '*' near index 0" +System.lineSeparator()+"*" +System.lineSeparator() +"^", testGrep.grepInvalidPatternInFile("*"));
	}
	
	/*
	 * Test cases for writeToOutputStream
	 */
	@Test
	public void testWriteToOutputStreamWithNullStr() {
		try {
			testGrep.writeToOutputStream(null, stdout);
			assertEquals("grep: Null Arguments" +System.lineSeparator(), stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWriteToOutputStreamWithEmptyStr() {
		try {
			testGrep.writeToOutputStream("", stdout);
			assertEquals("", stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testWriteToOutputStreamWithNullOutputStream() {
		try {
			testGrep.writeToOutputStream("", null);
		} catch (GrepException e) {
			assertEquals("grep: Null output stream", e.getMessage());
		}
	}

	@Test
	public void testWriteToOutputStreamWithValidArg() {
		try {
			testGrep.writeToOutputStream("valid arg!!$%^&*( 100 ", stdout);
			assertEquals("valid arg!!$%^&*( 100 ", stdout.toString());
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	/*
	 * Test cases for checkIfFileIsReadable
	 */
	@Test
	public void testCheckIfFileIsReadableWithNullArg() {
		try {
			testGrep.checkIfFileIsReadable(null);
		} catch (GrepException e) {
			assertEquals("grep: null: No such file or directory" +System.lineSeparator(), e.getMessage());
		}
	}

	@Test
	public void testCheckIfFileIsReadableWithEmptyStr() {
		try {
			testGrep.checkIfFileIsReadable("");
		} catch (GrepException e) {
			assertEquals("grep: : No such file or directory" +System.lineSeparator(), e.getMessage());
		}
	}

	@Test
	public void testCheckIfFileIsReadableWithValidFile() {
		try {
			assertTrue(testGrep.checkIfFileIsReadable(filePaths[0]));
		} catch (GrepException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testCheckIfFileIsReadableWithInvalidFile() {
		try {
			testGrep.checkIfFileIsReadable("invalid.txt");
		} catch (GrepException e) {
			assertEquals("grep: invalid.txt: No such file or directory " +System.lineSeparator(), e.getMessage());
		}
	}

	/*
	 * Test cases for composeArgs
	 */
	@Test
	public void testComposeArgsWithNullArg() {
		assertEquals(null, testGrep.composeArgs(null));
	}

	@Test
	public void testComposeArgsWithEmptyArr() {
		String[] arr = {};
		assertEquals(null, testGrep.composeArgs(arr));
	}

	@Test
	public void testComposeArgsWithValidArr() {
		String[] arr = { "hello", "world" };
		assertEquals("hello world", testGrep.composeArgs(arr));
	}

	/*
	 * Test cases for decomposeStr
	 */
	@Test
	public void testDecomposeStrWithNullArg() {
		assertArrayEquals(null, testGrep.decomposeStr(null));
	}

	@Test
	public void testDecomposeStrWithEmptyStr() {
		String[] expectedOutput = { "" };
		assertArrayEquals(expectedOutput, testGrep.decomposeStr(""));
	}

	@Test
	public void testDecomposeStrWithValidArr() {
		String[] expectedOutput = { "hello", "world" };
		assertArrayEquals(expectedOutput, testGrep.decomposeStr("hello world"));
	}
}
