package sg.edu.nus.comp.cs4218test.impl.app;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.app.SortApplication;

/**
 * Test suite for the sort application.
 */

public class SortApplicationTest {

	private static final String EXCEPTION_NOT = "Should not throw exception: ";
	private static final String OUTPUT_STREAM = "OutputStreamFile";
	private static final String EXCEPTION_SHOULD = "Should throw exception";
	private File testDir;
	private final String testFilePath = "testDir" + File.separator + "firstTest.txt";
	private final String testString = "First" + System.lineSeparator() + "sample" + System.lineSeparator() + "50"
			+ System.lineSeparator() + "5" + System.lineSeparator() + "&%*@" + System.lineSeparator() + "Sample"
			+ System.lineSeparator() + "10" + System.lineSeparator() + "1" + System.lineSeparator() + "random"
			+ System.lineSeparator() + "rand0m" + System.lineSeparator() + "CAPITAL";
	private final String testStringSortedNoNumberFilter = "&%*@" + System.lineSeparator() + "1" + System.lineSeparator()
			+ "10" + System.lineSeparator() + "5" + System.lineSeparator() + "50" + System.lineSeparator() + "CAPITAL"
			+ System.lineSeparator() + "First" + System.lineSeparator() + "Sample" + System.lineSeparator() + "rand0m"
			+ System.lineSeparator() + "random" + System.lineSeparator() + "sample";
	private final String testStringSortedWithNumberFilter = "&%*@" + System.lineSeparator() + "CAPITAL"
			+ System.lineSeparator() + "First" + System.lineSeparator() + "Sample" + System.lineSeparator() + "rand0m"
			+ System.lineSeparator() + "random" + System.lineSeparator() + "sample" + System.lineSeparator() + "1"
			+ System.lineSeparator() + "5" + System.lineSeparator() + "10" + System.lineSeparator() + "50";

	private InputStream stdin;
	private OutputStream stdout;
	SortApplication sortApp;

	@Before
	public void setUp() throws Exception {
		sortApp = new SortApplication();
		testDir = new File("testDir");
		testDir.mkdir();
		stdin = System.in;
		stdout = new ByteArrayOutputStream();
		File testFile = new File(testFilePath);
		testFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
		writer.write(testString);
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
	public void testNullParameters() {
		String[] args = null;
		try {
			sortApp.run(args, stdin, stdout);
			fail(EXCEPTION_SHOULD);
		} catch (SortException e) {
			assertEquals("sort: Insufficient Parameters", e.getMessage());
		}
	}

	@Test
	public void testInsufficientParameters() {
		String[] args = {};

		try {
			sortApp.run(args, null, stdout);
			fail(EXCEPTION_SHOULD);
		} catch (SortException e) {
			assertEquals("sort: Insufficient Parameters", e.getMessage());
		}
	}

	@Test
	public void testDirectoryPath() {
		String[] args = { "testDir" };
		try {
			sortApp.run(args, stdin, stdout);
			fail(EXCEPTION_SHOULD);
		} catch (SortException e) {
			assertEquals("sort: testDir: Is a directory", e.getMessage());
		}
	}

	@Test
	public void testInvalidPath() {
		String[] args = { "testDi" };
		try {
			sortApp.run(args, stdin, stdout);
			fail(EXCEPTION_SHOULD);
		} catch (SortException e) {
			assertEquals("sort: testDi: No such file or directory", e.getMessage());
		}
	}

	@Test
	public void testWritingToFileWithoutNumberSort() throws IOException {
		String[] args = { testFilePath };
		BufferedReader reader = null;
		File testFile = new File(OUTPUT_STREAM);
		testFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(testFile);
		StringBuilder strBuilder = new StringBuilder();
		String line;

		try {
			sortApp.run(args, stdin, outputStream);
			reader = new BufferedReader(new FileReader(testFile));

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}

			assertEquals(testStringSortedNoNumberFilter, strBuilder.toString());
		} catch (Exception e) {
			fail("Should not have exception: " + e.getMessage());
		} finally {
			reader.close();
			outputStream.close();
			testFile.delete();
		}
	}

	@Test
	public void testWithNumberSort() throws IOException {
		String[] args = { "-n", testFilePath };
		BufferedReader reader = null;
		File testFile = new File(OUTPUT_STREAM);
		testFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(testFile);
		StringBuilder strBuilder = new StringBuilder();
		String line;

		try {
			sortApp.run(args, stdin, outputStream);
			reader = new BufferedReader(new FileReader(testFile));

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}

			assertEquals(testStringSortedWithNumberFilter, strBuilder.toString());
		} catch (Exception e) {
			fail("Should not have exception: " + e.getMessage());
		} finally {
			reader.close();
			outputStream.close();
			testFile.delete();
		}
	}

	@Test
	public void testInputFromStdinFile() throws IOException {
		String[] args = { "-n" };
		String expectedOutput = 1 + System.lineSeparator() + 10;
		BufferedReader reader = null;
		File testFile = new File(OUTPUT_STREAM);
		testFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(testFile);
		StringBuilder strBuilder = new StringBuilder();
		String line;

		String inputString = 10 + System.lineSeparator() + 1;
		File testInputFile = new File("input");
		testInputFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(testInputFile));
		writer.write(inputString);
		writer.close();

		InputStream inputStream = new FileInputStream(testInputFile);

		try {
			sortApp.run(args, inputStream, outputStream);
			reader = new BufferedReader(new FileReader(testFile));

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}
			assertEquals(expectedOutput, strBuilder.toString());
		} catch (SortException e) {
			fail("Should not have exception: " + e.getMessage());
		} finally {
			reader.close();
			outputStream.close();
			inputStream.close();
			testFile.delete();
			testInputFile.delete();
		}
	}

	/*
	 * Test cases for checkIfFileIsReadable
	 */
	@Test
	public void testCheckIfFileIsReadableWithNullArg() {
		try {
			sortApp.checkIfFileIsReadable(null);
		} catch (SortException e) {
			assertEquals("sort: null: No such file or directory", e.getMessage());
		}
	}

	@Test
	public void testCheckIfFileIsReadableWithEmptyStr() {
		try {
			sortApp.checkIfFileIsReadable("");
		} catch (SortException e) {
			assertEquals("sort: : No such file or directory", e.getMessage());
		}
	}

	@Test
	public void testCheckIfFileIsReadableWithValidFile() {
		try {
			assertTrue(sortApp.checkIfFileIsReadable(testFilePath));
		} catch (SortException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test
	public void testCheckIfFileIsReadableWithInvalidFile() {
		try {
			sortApp.checkIfFileIsReadable("invalid.txt");
		} catch (SortException e) {
			assertEquals("sort: invalid.txt: No such file or directory", e.getMessage());
		}
	}

	/*
	 * Test cases for extractInt
	 */
	@Test
	public void testExtractIntWithNull() {
		assertEquals(-1, sortApp.extractInt(null));
	}

	@Test
	public void testExtractIntWithEmptyStr() {
		assertEquals(-1, sortApp.extractInt(""));
	}

	@Test
	public void testExtractIntWithStrWithInt() {
		assertEquals(123, sortApp.extractInt("123sbb"));
	}

	@Test
	public void testExtractIntWithStrWithoutInteger() {
		assertEquals(-1, sortApp.extractInt("helloworld"));
	}

	/*
	 * Test cases for writeToOutputStream
	 */
	@Test
	public void testWriteToOutputStreamWithNullStr() {
		try {
			sortApp.writeToOutputStream(null, stdout);
			assertEquals("sort: Null Arguments\n", stdout.toString());
		} catch (SortException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test
	public void testWriteToOutputStreamWithEmptyStr() {
		try {
			sortApp.writeToOutputStream("", stdout);
			assertEquals("", stdout.toString());
		} catch (SortException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	@Test
	public void testWriteToOutputStreamWithNullOutputStream() {
		try {
			sortApp.writeToOutputStream("", null);
		} catch (SortException e) {
			assertEquals("sort: Null output stream", e.getMessage());
		}
	}

	@Test
	public void testWriteToOutputStreamWithValidArg() {
		try {
			sortApp.writeToOutputStream("valid arg!!$%^&*( 100 ", stdout);
			assertEquals("valid arg!!$%^&*( 100 ", stdout.toString());
		} catch (SortException e) {
			fail(EXCEPTION_NOT + e.getMessage());
		}
	}

	/*
	 * Test cases for sortStringsSimple
	 */
	@Test
	public void testSortStringsSimpleWithNull() {
		assertEquals("Null argument", sortApp.sortStringsSimple(null));
	}

	@Test
	public void testSortStringsSimpleWithPatternFound() {
		assertEquals("random" + System.lineSeparator() + "sample" + System.lineSeparator(),
				sortApp.sortStringsSimple(testString));
	}

	@Test
	public void testSortStringsSimpleWithPatternNotFound() {
		assertEquals("", sortApp.sortStringsSimple("WORLD\nHELLO\n"));
	}

	/*
	 * Test cases for sortStringsCapital
	 */
	@Test
	public void testSortStringsCapitalWithNull() {
		assertEquals("Null argument", sortApp.sortStringsCapital(null));
	}

	@Test
	public void testSortStringsCapitalWithPatternFound() {
		assertEquals("CAPITAL" + System.lineSeparator(), sortApp.sortStringsCapital(testString));
	}

	@Test
	public void testSortStringsCapitalWithPatternNotFound() {
		assertEquals("", sortApp.sortStringsCapital("world\nhello\n"));
	}

	/*
	 * Test cases for sortNumbers
	 */
	@Test
	public void testSortNumbersWithNull() {
		assertEquals("Null argument", sortApp.sortNumbers(null));
	}

	@Test
	public void testSortNumbersWithPatternFound() {
		assertEquals("1" + System.lineSeparator() + "10" + System.lineSeparator() + "5" + System.lineSeparator() + "50"
				+ System.lineSeparator(), sortApp.sortNumbers(testString));
	}

	@Test
	public void testSortNumbersWithPatternNotFound() {
		assertEquals("", sortApp.sortNumbers("world\nhello\n"));
	}

	/*
	 * Test cases for sortSpecialChars
	 */
	@Test
	public void testSortSpecialCharsWithNull() {
		assertEquals("Null argument", sortApp.sortSpecialChars(null));
	}

	@Test
	public void testSortSpecialCharsWithPatternFound() {
		assertEquals("&%*@" + System.lineSeparator(), sortApp.sortSpecialChars(testString));
	}

	@Test
	public void testSortSpecialCharsWithPatternNotFound() {
		assertEquals("", sortApp.sortSpecialChars("world\nhello\n"));
	}

	/*
	 * Test cases for sortSimpleCapital
	 */
	@Test
	public void testSortSimpleCapitalWithNull() {
		assertEquals("Null argument", sortApp.sortSimpleCapital(null));
	}

	@Test
	public void testSortSimpleCapitalWithPatternFound() {
		assertEquals("CAPITAL" + System.lineSeparator() + "First" + System.lineSeparator() + "Sample"
				+ System.lineSeparator() + "random" + System.lineSeparator() + "sample" + System.lineSeparator(),
				sortApp.sortSimpleCapital(testString));
	}

	@Test
	public void testSortSimpleCapitalWithPatternNotFound() {
		assertEquals("", sortApp.sortSimpleCapital("@#$\n100\n \n"));
	}

	/*
	 * Test cases for sortSimpleNumbers
	 */
	@Test
	public void testSortSimpleNumbersWithNull() {
		assertEquals("Null argument", sortApp.sortSimpleNumbers(null));
	}

	@Test
	public void testSortSimpleNumbersWithPatternFound() {
		assertEquals("1" + System.lineSeparator() + "10" + System.lineSeparator() + "5" + System.lineSeparator() + "50"
				+ System.lineSeparator() + "rand0m" + System.lineSeparator() + "random" + System.lineSeparator()
				+ "sample" + System.lineSeparator(), sortApp.sortSimpleNumbers(testString));
	}

	@Test
	public void testSortSimpleNumbersWithPatternNotFound() {
		assertEquals("", sortApp.sortSimpleNumbers("Hello\n@#@#\n \n"));
	}

	/*
	 * Test cases for sortSimpleSpecialChars
	 */
	@Test
	public void testSortSimpleSpecialCharsWithNull() {
		assertEquals("Null argument", sortApp.sortSimpleSpecialChars(null));
	}

	@Test
	public void testSortSimpleSpecialCharsWithPatternFound() {
		assertEquals(
				"&%*@" + System.lineSeparator() + "random" + System.lineSeparator() + "sample" + System.lineSeparator(),
				sortApp.sortSimpleSpecialChars(testString));
	}

	@Test
	public void testSortSimpleSpecialCharsWithPatternNotFound() {
		assertEquals("", sortApp.sortSimpleSpecialChars("111$\nHELLO"));
	}

	/*
	 * Test cases for sortCapitalNumbers
	 */
	@Test
	public void testSortCapitalNumbersWithNull() {
		assertEquals("Null argument", sortApp.sortCapitalNumbers(null));
	}

	@Test
	public void testSortCapitalNumbersWithPatternFound() {
		assertEquals(
				"1" + System.lineSeparator() + "10" + System.lineSeparator() + "5" + System.lineSeparator() + "50"
						+ System.lineSeparator() + "CAPITAL" + System.lineSeparator(),
				sortApp.sortCapitalNumbers(testString));
	}

	@Test
	public void testSortCapitalNumbersWithPatternNotFound() {
		assertEquals("", sortApp.sortCapitalNumbers("&*^()$\nhello"));
	}

	/*
	 * Test cases for sortCapitalSpecialChars
	 */
	@Test
	public void testSortCapitalSpecialCharsWithNull() {
		assertEquals("Null argument", sortApp.sortCapitalSpecialChars(null));
	}

	@Test
	public void testSortCapitalSpecialCharsWithPatternFound() {
		assertEquals("&%*@" + System.lineSeparator() + "CAPITAL" + System.lineSeparator(),
				sortApp.sortCapitalSpecialChars(testString));
	}

	@Test
	public void testSortCapitalSpecialCharsWithPatternNotFound() {
		assertEquals("", sortApp.sortCapitalSpecialChars("111\nhello"));
	}

	/*
	 * Test cases for sortNumbersSpecialChars
	 */
	@Test
	public void testSortNumberSpecialCharsWithNull() {
		assertEquals("Null argument", sortApp.sortNumbersSpecialChars(null));
	}

	@Test
	public void testSortNumberSpecialCharsWithPatternFound() {
		assertEquals(
				"&%*@" + System.lineSeparator() + "1" + System.lineSeparator() + "10" + System.lineSeparator() + "5"
						+ System.lineSeparator() + "50" + System.lineSeparator(),
				sortApp.sortNumbersSpecialChars(testString));
	}

	@Test
	public void testSortNumberSpecialCharsWithPatternNotFound() {
		assertEquals("", sortApp.sortNumbersSpecialChars("WORLD\nhello"));
	}

	/*
	 * Test cases for sortSimpleCapitalNumber
	 */
	@Test
	public void testSortSimpleCapitalNumberWithNull() {
		assertEquals("Null argument", sortApp.sortSimpleCapitalNumber(null));
	}

	@Test
	public void testSortSimpleCapitalNumberWithPatternFound() {
		assertEquals(
				"1" + System.lineSeparator() + "10" + System.lineSeparator() + "5" + System.lineSeparator() + "50"
						+ System.lineSeparator() + "CAPITAL" + System.lineSeparator() + "First" + System.lineSeparator()
						+ "Sample" + System.lineSeparator() + "rand0m" + System.lineSeparator() + "random"
						+ System.lineSeparator() + "sample" + System.lineSeparator(),
				sortApp.sortSimpleCapitalNumber(testString));
	}

	@Test
	public void testSortSimpleCapitalNumberWithPatternNotFound() {
		assertEquals("", sortApp.sortSimpleCapitalNumber("!!\n@#"));
	}

	/*
	 * Test cases for sortSimpleCapitalSpecialChars
	 */
	@Test
	public void testSortSimpleCapitalSpecialCharsWithNull() {
		assertEquals("Null argument", sortApp.sortSimpleCapitalSpecialChars(null));
	}

	@Test
	public void testSortSimpleCapitalSpecialCharsWithPatternFound() {
		assertEquals("&%*@" + System.lineSeparator() + "CAPITAL" + System.lineSeparator() + "First"
				+ System.lineSeparator() + "Sample" + System.lineSeparator() + "random" + System.lineSeparator()
				+ "sample" + System.lineSeparator(), sortApp.sortSimpleCapitalSpecialChars(testString));
	}

	@Test
	public void testSortSimpleCapitalSpecialCharsWithPatternNotFound() {
		assertEquals("", sortApp.sortSimpleCapitalSpecialChars("99\n100\n"));
	}

	/*
	 * Test cases for sortSimpleNumbersSpecialChars
	 */
	@Test
	public void testSortSimpleNumbersSpecialCharsWithNull() {
		assertEquals("Null argument", sortApp.sortSimpleNumbersSpecialChars(null));
	}

	@Test
	public void testSortSimpleNumbersSpecialCharsWithPatternFound() {
		assertEquals(
				"&%*@" + System.lineSeparator() + "1" + System.lineSeparator() + "10" + System.lineSeparator() + "5"
						+ System.lineSeparator() + "50" + System.lineSeparator() + "rand0m" + System.lineSeparator()
						+ "random" + System.lineSeparator() + "sample" + System.lineSeparator(),
				sortApp.sortSimpleNumbersSpecialChars(testString));
	}

	@Test
	public void testSortSimpleNumbersSpecialCharsWithPatternNotFound() {
		assertEquals("", sortApp.sortSimpleNumbersSpecialChars("WORLD\nBIG\n"));
	}

	/*
	 * Test cases for sortCapitalNumbersSpecialChars
	 */
	@Test
	public void testSortCapitalNumbersSpecialCharsWithNull() {
		assertEquals("Null argument", sortApp.sortCapitalNumbersSpecialChars(null));
	}

	@Test
	public void testSortCapitalNumbersSpecialCharsWithPatternFound() {
		assertEquals(
				"&%*@" + System.lineSeparator() + "1" + System.lineSeparator() + "10" + System.lineSeparator() + "5"
						+ System.lineSeparator() + "50" + System.lineSeparator() + "CAPITAL" + System.lineSeparator(),
				sortApp.sortCapitalNumbersSpecialChars(testString));
	}

	@Test
	public void testSortCapitalNumbersSpecialCharsWithPatternNotFound() {
		assertEquals("", sortApp.sortCapitalNumbersSpecialChars("world\nsmall\n"));
	}

	/*
	 * Test cases for sortAll
	 */
	@Test
	public void testSortAllWithNull() {
		assertEquals("Null argument", sortApp.sortAll(null));
	}

	@Test
	public void testSortAllWithPatternFound() {
		assertEquals("&%*@" + System.lineSeparator() + "1" + System.lineSeparator() + "10" + System.lineSeparator()
				+ "5" + System.lineSeparator() + "50" + System.lineSeparator() + "CAPITAL" + System.lineSeparator()
				+ "First" + System.lineSeparator() + "Sample" + System.lineSeparator() + "rand0m"
				+ System.lineSeparator() + "random" + System.lineSeparator() + "sample" + System.lineSeparator(),
				sortApp.sortAll(testString));
	}

	@Test
	public void testSortAllWithPatternNotFound() {
		assertEquals("", sortApp.sortAll("\n"));
	}
}