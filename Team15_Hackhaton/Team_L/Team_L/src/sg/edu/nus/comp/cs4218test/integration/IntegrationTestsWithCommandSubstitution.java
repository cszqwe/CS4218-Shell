package sg.edu.nus.comp.cs4218test.integration;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.cmd.CallCommand;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrationTestsWithCommandSubstitution {
	private static final String EXCEPTION_PREFIX = "Should not throw exception: ";
	private static final String OUTPUT_STREAM = "OutputStreamFile";

	private final String firstFilePath = "testDir" + File.separator + "firstTest.txt";
	private final String secondFilePath = "testDir" + File.separator + "secondDir" + File.separator + "secondTest.txt";
	private final String sedString = "s/a/b/ " + secondFilePath;
	private final String secondString = "Second" + System.lineSeparator() + "Sample" + System.lineSeparator() + "with"
			+ System.lineSeparator() + "many" + System.lineSeparator() + "lines";
	private final SimpleDateFormat DEFAULT_DF = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

	private String directory;
	private File firstDir, secondDir;
	private InputStream inputStream;
	private OutputStream outputStream;

	@Before
	public void setUp() throws Exception {
		directory = Environment.currentDirectory;

		firstDir = new File("testDir");
		firstDir.mkdir();
		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
		writer.write(secondFilePath);
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

		Environment.setCurrentDirectory(Environment.currentDirectory, directory);
	}

	@Test
	public void testAValidCallSortCat() throws IOException {
		CallCommand callCommand = new CallCommand("sort `cat " + firstFilePath + "`");

		BufferedReader reader = null;

		File testFile = new File(OUTPUT_STREAM);
		testFile.createNewFile();
		try {
			outputStream = new FileOutputStream(testFile);
			inputStream = new FileInputStream(firstFilePath);
			callCommand.evaluate(inputStream, outputStream);
			reader = new BufferedReader(new FileReader(testFile));
			StringBuilder strBuilder = new StringBuilder();
			String line;
			String expectedResult = ("Sample" + System.lineSeparator() + "Second" + System.lineSeparator() + "lines"
					+ System.lineSeparator() + "many" + System.lineSeparator() + "with");

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}
			assertEquals(expectedResult, strBuilder.toString());
		} catch (Exception e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			testFile.delete();
		}
	}

	@Test
	public void testXInvalidCallSortCatNoSuchFile() {
		CallCommand callCommand = new CallCommand("sort `cat fakeFile`");
		try {
			callCommand.evaluate(inputStream, outputStream);
		} catch (Exception e) {
			assertEquals("cat: Could not read file", e.getMessage());
		}
	}

	@Test
	public void testYInvalidInnerAppBeingSubstituted() {
		CallCommand callCommand = new CallCommand("sort `fakeapp " + firstFilePath + "`");
		try {
			callCommand.evaluate(inputStream, outputStream);
		} catch (Exception e) {
			assertEquals("shell: fakeapp: Invalid app.", e.getMessage());
		}
	}

	@Test
	public void testBValidEchoSed() throws IOException {
		CallCommand callCommand = new CallCommand("echo `sed s/ir/a/g " + firstFilePath + "`");
		File testFile = new File(OUTPUT_STREAM);
		BufferedReader reader = null;
		try {
			testFile.createNewFile();

			outputStream = new FileOutputStream(testFile);
			callCommand.evaluate(inputStream, outputStream);

			reader = new BufferedReader(new FileReader(testFile));
			StringBuilder strBuilder = new StringBuilder();
			String line;
			String expectedResult = ("testDa" + File.separator + "secondDa" + File.separator + "secondTest.txt");

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}

			assertEquals(expectedResult, strBuilder.toString());
		} catch (AbstractApplicationException | ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			testFile.delete();
		}
	}

	@Test
	public void testZValidCdPwd() throws IOException {
		CallCommand callCommand = new CallCommand("pwd `cd testDir`");
		File testFile = new File(OUTPUT_STREAM);
		BufferedReader reader = null;
		try {
			testFile.createNewFile();

			outputStream = new FileOutputStream(testFile);
			callCommand.evaluate(inputStream, outputStream);

			reader = new BufferedReader(new FileReader(testFile));
			StringBuilder strBuilder = new StringBuilder();
			String line;
			String expectedResult = (Environment.currentDirectory);
			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}
			assertEquals(expectedResult, strBuilder.toString());
		} catch (AbstractApplicationException | ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			testFile.delete();
		}
	}

	@Test
	public void testEValidSedEcho() throws IOException {
		CallCommand callCommand = new CallCommand("sed `echo " + sedString + "`");
		File testFile = new File(OUTPUT_STREAM);
		BufferedReader reader = null;
		try {
			testFile.createNewFile();

			outputStream = new FileOutputStream(testFile);
			callCommand.evaluate(inputStream, outputStream);

			reader = new BufferedReader(new FileReader(testFile));
			StringBuilder strBuilder = new StringBuilder();
			String line;
			String expectedResult = "Second" + System.lineSeparator() + "Sbmple" + System.lineSeparator() + "with"
					+ System.lineSeparator() + "mbny" + System.lineSeparator() + "lines";
			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}
			assertEquals(expectedResult, strBuilder.toString());
		} catch (AbstractApplicationException | ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			testFile.delete();
		}
	}

	@Test
	public void testFValidWcHead() throws IOException {
		CallCommand callCommand = new CallCommand("wc -l `head " + firstFilePath + "`");
		String expectedResult = "4 ";

		BufferedReader reader = null;
		File testFile = new File(OUTPUT_STREAM);
		testFile.createNewFile();
		OutputStream os = new FileOutputStream(testFile);
		StringBuilder strBuilder = new StringBuilder();
		String line;
		InputStream stubInputStream = new FileInputStream(secondFilePath);

		try {
			callCommand.evaluate(stubInputStream, os);

			reader = new BufferedReader(new FileReader(testFile));
			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}
			assertEquals(expectedResult, strBuilder.toString());
		} catch (AbstractApplicationException | ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
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

	@Test
	public void testGValidEchoDate() throws IOException {
		CallCommand callCommand = new CallCommand("echo `date`");
		File testFile = new File(OUTPUT_STREAM);
		BufferedReader reader = null;

		StringBuilder strBuilder = new StringBuilder();
		String line;

		Calendar cal = Calendar.getInstance();

		try {
			testFile.createNewFile();

			outputStream = new FileOutputStream(testFile);
			callCommand.evaluate(inputStream, outputStream);

			reader = new BufferedReader(new FileReader(testFile));

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}

			assertEquals(DEFAULT_DF.format(cal.getTime()), strBuilder.toString());
		} catch (AbstractApplicationException | ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			testFile.delete();
		}
	}

	@Test
	public void testValidMultipleCmdSubstitution() throws IOException {
		PipeCommand pipeCommand = new PipeCommand("echo `cal` | echo `date`");
		File testFile = new File(OUTPUT_STREAM);
		BufferedReader reader = null;

		StringBuilder strBuilder = new StringBuilder();
		String line;

		Calendar cal = Calendar.getInstance();

		try {
			testFile.createNewFile();

			outputStream = new FileOutputStream(testFile);
			pipeCommand.evaluate(inputStream, outputStream);

			reader = new BufferedReader(new FileReader(testFile));

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}

			assertEquals(DEFAULT_DF.format(cal.getTime()), strBuilder.toString());
		} catch (AbstractApplicationException | ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			testFile.delete();
		}
	}

	@Test
	public void testInvalidMultipleCmdSubstitution() throws IOException {
		PipeCommand pipeCommand = new PipeCommand("echo `cal `echo another`` | echo `date`");
		File testFile = new File(OUTPUT_STREAM);
		BufferedReader reader = null;

		StringBuilder strBuilder = new StringBuilder();
		String line;

		Calendar cal = Calendar.getInstance();

		try {
			testFile.createNewFile();

			outputStream = new FileOutputStream(testFile);
			pipeCommand.evaluate(inputStream, outputStream);

			reader = new BufferedReader(new FileReader(testFile));

			if ((line = reader.readLine()) != null) {
				strBuilder.append(line);
			}

			while ((line = reader.readLine()) != null) {
				strBuilder.append(System.lineSeparator());
				strBuilder.append(line);
			}

			assertEquals(DEFAULT_DF.format(cal.getTime()), strBuilder.toString());
		} catch (AbstractApplicationException | ShellException e) {
			assertEquals("shell: Invalid syntax encountered.", e.getMessage());
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			testFile.delete();
		}
	}
	
	@Test
	public void testInvalidWcEcho() throws IOException {
		ShellImpl shell = new ShellImpl();
		outputStream = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate("wc -a `echo abc`", outputStream);
		} catch (AbstractApplicationException | ShellException e) {
			assertTrue(e.getMessage().contains("No such file or directory"));
		} finally {

			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}

		}
	}
	
	@Test
	public void testInvalidHeadCat() throws IOException {
		ShellImpl shell = new ShellImpl();
		outputStream = new ByteArrayOutputStream();

		try {
			shell.parseAndEvaluate("head `cat " +secondFilePath+"`", outputStream);
		} catch (AbstractApplicationException | ShellException e) {

			assertTrue(e.getMessage().contains("No such file or directory"));
		} finally {

			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}

		}
	}

}