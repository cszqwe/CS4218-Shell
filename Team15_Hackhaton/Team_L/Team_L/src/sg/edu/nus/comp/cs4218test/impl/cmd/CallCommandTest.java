package sg.edu.nus.comp.cs4218test.impl.cmd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test suite for calling commands.
 */

public class CallCommandTest {

	private final String firstFilePath = "testDir" + File.separator + "firstTest.txt";
	private final String secondFilePath = "testDir" + File.separator + "secondDir" + File.separator + "secondTest.txt";
	private final String firstString = "Firsts" + System.lineSeparator() + "Samples";
	private final String secondString = "Firsts" + System.lineSeparator() + "Samples";

	private File firstDir, secondDir;
	InputStream inputStream;
	OutputStream outputStream;

	@Before
	public void setUp() throws Exception {
		firstDir = new File("testDir");
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
	public void testInvalidApplication() {
		CallCommand callCommand = new CallCommand("fakeCommand");
		try {
			callCommand.evaluate(inputStream, outputStream);
		} catch (AbstractApplicationException | ShellException e) {
			assertEquals("shell: fakeCommand: Invalid app.", e.getMessage());
		}
	}

	// Handling should be by the app itself
	@Test
	public void testValidCommandNoOutputStreamProvided() {
		CallCommand callCommand = new CallCommand("echo " + firstFilePath);
		try {
			callCommand.evaluate(null, null);
		} catch (Exception e) {
			assertEquals("echo: OutputStream not provided", e.getMessage());
		}
	}

	// Handling should be by the app itself
	@Test
	public void testValidCommandNoInputStreamProvided() {
		CallCommand callCommand = new CallCommand("head -n 15");
		try {
			callCommand.evaluate(null, System.out);
		} catch (AbstractApplicationException | ShellException e) {
			assertEquals("head: InputStream not provided", e.getMessage());
		}
	}

	@Test
	public void testValidArgsOutputToSystemOut() {
		CallCommand callCommand = new CallCommand("sed s/a/b/ " + firstFilePath);
		try {
			callCommand.evaluate(inputStream, System.out);
		} catch (AbstractApplicationException | ShellException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testValidArgsOutputToFile() throws IOException {
		CallCommand callCommand = new CallCommand("tail -n 15 " + firstFilePath);
		BufferedReader reader = null;
		File testFile = new File("OutputStreamFile");
		testFile.createNewFile();
		OutputStream outputStream = new FileOutputStream(testFile);
		StringBuilder strBuilder = new StringBuilder();
		String line;

		try {
			callCommand.evaluate(inputStream, outputStream);
			reader = new BufferedReader(new FileReader(testFile));

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}

			assertEquals(firstString, strBuilder.toString());
		} catch (AbstractApplicationException | ShellException e) {
			fail("Should not throw exception: " + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			testFile.delete();
		}
	}

	@Test
	public void testValidArgsInputFromFile() throws IOException {
		CallCommand callCommand = new CallCommand("sort -n");
		BufferedReader reader = null;
		File testFile = new File("OutputStreamFile");
		testFile.createNewFile();
		OutputStream outputStream = null;
		InputStream stubInputStream = null;
		outputStream = new FileOutputStream(testFile);
		stubInputStream = new FileInputStream(firstFilePath);
		StringBuilder strBuilder = new StringBuilder();
		String line;

		try {
			callCommand.evaluate(stubInputStream, outputStream);
			reader = new BufferedReader(new FileReader(testFile));

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}

			assertEquals(firstString, strBuilder.toString());
		} catch (AbstractApplicationException | ShellException e) {
			fail("Should not throw exception: " + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			if (stubInputStream != null) {
				stubInputStream.close();
			}
			testFile.delete();
		}
	}

}