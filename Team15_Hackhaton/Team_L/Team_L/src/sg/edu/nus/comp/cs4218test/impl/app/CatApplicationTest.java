package sg.edu.nus.comp.cs4218test.impl.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.app.CatApplication;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test suite for the cat application.
 */

public class CatApplicationTest {

	private static final String EXCEPTION_SHOULD = "Should throw exception";
	private static final String TEST_DIR = "testDir";
	private File firstDir, secondDir;
	private final String firstFilePath = TEST_DIR + File.separator + "firstTest.txt";
	private final String secondFilePath = TEST_DIR + File.separator + "secondDir" + File.separator + "secondTest.txt";
	private final String firstString = "First" + System.lineSeparator() + "Sample";
	private final String secondString = "Second" + System.lineSeparator() + "Sample";

	@Before
	public void setUp() throws Exception {
		firstDir = new File(TEST_DIR);
		firstDir.mkdir();
		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
		writer.write(firstString);
		writer.close();

		secondDir = new File(firstDir, "secondDir");
		secondDir.mkdir();
		File testFile2 = new File(secondFilePath);
		testFile2.createNewFile();
		writer = new BufferedWriter(new FileWriter(testFile2));
		writer.write(secondString);
		writer.close();
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
	public void testNullInputStreamException() {
		CatApplication catApp = new CatApplication();
		String[] args = {};
		try {
			catApp.run(args, null, System.out);
			fail(EXCEPTION_SHOULD);
		} catch (CatException e) {
			assertEquals("cat: Null Pointer Exception", e.getMessage());
		}
	}

	@Test
	public void testNullOutputStreamException() {
		CatApplication catApp = new CatApplication();
		String[] args = {};
		try {
			catApp.run(args, System.in, null);
			fail(EXCEPTION_SHOULD);
		} catch (CatException e) {
			assertEquals("cat: Null Pointer Exception", e.getMessage());
		}
	}

	@Test
	public void testFileDoesNotExist() throws IOException {
		CatApplication catApp = new CatApplication();
		String[] args = { "Non Existent File" };
		try {
			catApp.run(args, null, null);
			fail(EXCEPTION_SHOULD);
		} catch (CatException e) {
			assertEquals("cat: Could not read file", e.getMessage());
		}
	}

	@Test
	public void testDirectoryNotFileException() throws IOException {
		CatApplication catApp = new CatApplication();
		String[] args = { TEST_DIR };
		try {
			catApp.run(args, System.in, System.out);
			fail(EXCEPTION_SHOULD);
		} catch (CatException e) {
			assertEquals("cat: This is a directory", e.getMessage());
		}
	}

	@Test
	public void testOutputToFile() throws IOException {
		CatApplication catApp = new CatApplication();
		String[] args = { secondFilePath };
		BufferedReader reader = null;
		File testFile = new File("OutputStreamFile");
		testFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(testFile);
		StringBuilder strBuilder = new StringBuilder();
		String line;

		try {
			catApp.run(args, null, outputStream);
			reader = new BufferedReader(new FileReader(testFile));

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}

			assertEquals(secondString, strBuilder.toString());
		} catch (CatException e) {
			fail("Should not catch exception :" + e.getMessage());
		} finally {
			reader.close();
			outputStream.close();
			testFile.delete();
		}
	}

	@Test
	public void testInputStreamFromFile() throws IOException {
		CatApplication catApp = new CatApplication();
		BufferedReader reader = null;
		File testFile = new File("OutputStreamFile");
		testFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(testFile);
		StringBuilder strBuilder = new StringBuilder();
		String line;
		InputStream stubInputStream = new FileInputStream(firstFilePath);

		try {
			catApp.run(null, stubInputStream, outputStream);
			reader = new BufferedReader(new FileReader(testFile));

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}

			assertEquals(firstString, strBuilder.toString());
		} catch (CatException e) {
			fail("Should not catch exception :" + e.getMessage());
		} finally {
			reader.close();
			stubInputStream.close();
			outputStream.close();
			testFile.delete();
		}
	}
}
