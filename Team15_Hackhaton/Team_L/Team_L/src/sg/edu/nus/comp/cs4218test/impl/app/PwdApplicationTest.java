package sg.edu.nus.comp.cs4218test.impl.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.impl.app.PwdApplication;

/**
 * Test suite for the pwd application.
 */

public class PwdApplicationTest {

	private static final String EXCEPTION_PREFIX = "Should not have exception: ";
	private static final String FIRST_TEST = "firstTest.txt";
	private static final String TEST_DIR = "testDir";
	private File firstDir, secondDir;

	@Before
	public void setUp() throws Exception {
		firstDir = new File(TEST_DIR);
		firstDir.mkdir();
		secondDir = new File(firstDir, "secondDir");
		secondDir.mkdir();
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
	public void testNullArgs() {
		PwdApplication pwdApp = new PwdApplication();
		try {
			pwdApp.run(null, System.in, System.out);
		} catch (AbstractApplicationException e) {
			assertEquals("pwd: Null arguments", e.getMessage());
		}
	}

	@Test
	public void testNullOutputStream() {
		PwdApplication pwdApp = new PwdApplication();
		String[] args = { "pwd" };
		try {
			pwdApp.run(args, System.in, null);
		} catch (AbstractApplicationException e) {
			assertEquals("pwd: OutputStream not provided", e.getMessage());
		}
	}

	@Test
	public void testOutputStreamToFile() throws IOException {
		PwdApplication pwdApp = new PwdApplication();
		String[] args = {};

		String firstFilePath = TEST_DIR + File.separator + FIRST_TEST;
		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		OutputStream stdout = new FileOutputStream(testFile);
		BufferedReader reader = null;

		try {
			pwdApp.run(args, System.in, stdout);
			reader = new BufferedReader(new FileReader(testFile));
			assertEquals(Environment.currentDirectory, reader.readLine());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			reader.close();
			stdout.close();
			testFile.delete();
		}
	}

	@Test
	public void testNullInputStream() throws IOException {
		PwdApplication pwdApp = new PwdApplication();
		String[] args = { "pwd" };

		String firstFilePath = TEST_DIR + File.separator + FIRST_TEST;
		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		OutputStream stdout = new FileOutputStream(testFile);
		BufferedReader reader = null;

		try {
			pwdApp.run(args, null, stdout);
			reader = new BufferedReader(new FileReader(testFile));
			assertEquals(Environment.currentDirectory, reader.readLine());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			reader.close();
			stdout.close();
			testFile.delete();
		}
	}

	@Test
	public void testMoreNumberdOfArgs() throws IOException {
		PwdApplication pwdApp = new PwdApplication();
		String[] args = { "More than", "one argument" };

		String firstFilePath = TEST_DIR + File.separator + FIRST_TEST;
		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		OutputStream stdout = new FileOutputStream(testFile);
		BufferedReader reader = null;

		try {
			pwdApp.run(args, null, stdout);
			reader = new BufferedReader(new FileReader(testFile));
			assertEquals(Environment.currentDirectory, reader.readLine());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			reader.close();
			stdout.close();
			testFile.delete();
		}
	}

	@Test
	public void testMalformedArgs() throws IOException {
		PwdApplication pwdApp = new PwdApplication();
		String[] args = { "cat $$#@&&" };

		String firstFilePath = TEST_DIR + File.separator + FIRST_TEST;
		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		OutputStream stdout = new FileOutputStream(testFile);
		BufferedReader reader = null;

		try {
			pwdApp.run(args, null, stdout);
			reader = new BufferedReader(new FileReader(testFile));
			assertEquals(Environment.currentDirectory, reader.readLine());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			reader.close();
			stdout.close();
			testFile.delete();
		}
	}

	@Test
	public void testEmptyArgs() throws IOException {
		PwdApplication pwdApp = new PwdApplication();
		String[] args = {};

		String firstFilePath = TEST_DIR + File.separator + FIRST_TEST;
		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		OutputStream stdout = new FileOutputStream(testFile);
		BufferedReader reader = null;

		try {
			pwdApp.run(args, null, stdout);
			reader = new BufferedReader(new FileReader(testFile));
			assertEquals(Environment.currentDirectory, reader.readLine());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			reader.close();
			stdout.close();
			testFile.delete();
		}
	}
}
