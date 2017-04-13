package sg.edu.nus.comp.cs4218test.impl.cmd;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.SeqCommand;

public class SeqCommandTest {

	private static final String EXCEPTION_PREFIX = "Should not throw exception: ";
	private final String firstFilePath = "testDir" + File.separator + "firstTest.txt";
	private final String secondFilePath = "testDir" + File.separator + "secondDir" + File.separator + "secondTest.txt";
	private final String firstFileString = "First" + System.lineSeparator() + "Sample";
	private final String secondFileString = "Second" + System.lineSeparator() + "Sample";

	private File firstDir, secondDir;
	private InputStream stdin;
	private OutputStream stdout;

	@Before
	public void setUp() throws Exception {
		firstDir = new File("testDir");
		firstDir.mkdir();
		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
		writer.write(firstFileString);
		writer.close();

		secondDir = new File(firstDir, "secondDir");
		secondDir.mkdir();
		File testFile2 = new File(secondFilePath);
		testFile2.createNewFile();
		writer = new BufferedWriter(new FileWriter(testFile2));
		writer.write(secondFileString);
		writer.close();

		stdout = new ByteArrayOutputStream();
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
	public void testTwoCat() {
		SeqCommand seqCommand = new SeqCommand("cat " + firstFilePath + ";" + "cat " + secondFilePath);
		try {
			seqCommand.evaluate(stdin, stdout);
			assertEquals(firstFileString + secondFileString, stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testTwoEcho() {
		SeqCommand seqCommand = new SeqCommand("echo abc;echo ccc");
		try {
			seqCommand.evaluate(stdin, stdout);
			assertEquals("abc" + System.lineSeparator() + "ccc" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testEchoCat() {
		SeqCommand seqCommand = new SeqCommand("echo " + "abc" + ";" + "cat " + firstFilePath);
		try {
			seqCommand.evaluate(stdin, stdout);
			assertEquals("abc" + System.lineSeparator() + firstFileString, stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testHeadTail() {
		SeqCommand seqCommand = new SeqCommand("head " + firstFilePath + ";" + "tail " + secondFilePath);
		try {
			seqCommand.evaluate(stdin, stdout);
			assertEquals(firstFileString + System.lineSeparator() + secondFileString + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testGrepEcho() {
		SeqCommand seqCommand = new SeqCommand("grep First " + firstFilePath + ";" + "tail " + secondFilePath);
		try {
			seqCommand.evaluate(stdin, stdout);
			assertEquals("First" + System.lineSeparator() + secondFileString + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

}
