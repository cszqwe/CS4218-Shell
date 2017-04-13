package sg.edu.nus.comp.cs4218test.impl;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class ShellImplTest {
	private static final String TEST_DIR = "testDir";
	private static final String EXCEPTION_PREFIX = "Should not throw exception: ";
	private final String firstFilePath = TEST_DIR + File.separator + "testFile1.txt";
	private final String secondFilePath = TEST_DIR + File.separator + "testFile2.txt";
	private final String thirdFilePath = TEST_DIR + File.separator + "testFile3.txt";

	private final String firstFileString = "First" + System.lineSeparator() + "Second" + System.lineSeparator()
			+ "Third" + System.lineSeparator() + "Fourth" + System.lineSeparator() + "Fifth" + System.lineSeparator()
			+ "Sixth" + System.lineSeparator() + "Seventh" + System.lineSeparator() + "Eighth" + System.lineSeparator()
			+ "Nineth" + System.lineSeparator() + "Tenth" + System.lineSeparator() + "Eleventh" + System.lineSeparator()
			+ "Twelveth" + System.lineSeparator() + "Thirteenth";

	private final String secondFileString = "Second" + System.lineSeparator() + "Sample";
	private final String thirdFileString = "Third" + System.lineSeparator() + "Sample" + System.lineSeparator()
			+ "SAMPLESS";

	private File firstDir;
	private String initFilePath;
	private OutputStream stdout;
	private Shell shell;

	@Before
	public void setUp() throws Exception {
		shell = new ShellImpl();
		firstDir = new File(TEST_DIR);
		firstDir.mkdir();
		initFilePath = Environment.currentDirectory;

		Environment.currentDirectory =  TEST_DIR;

		File testFile = new File(firstFilePath);
		testFile.createNewFile();
		BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
		writer.write(firstFileString);
		writer.close();

		File testFile2 = new File(secondFilePath);
		testFile2.createNewFile();
		writer = new BufferedWriter(new FileWriter(testFile2));
		writer.write(secondFileString);
		writer.close();

		File testFile3 = new File(thirdFilePath);
		testFile2.createNewFile();
		writer = new BufferedWriter(new FileWriter(testFile3));
		writer.write(thirdFileString);
		writer.close();

		stdout = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {

		for (File file : firstDir.listFiles()) {
			file.delete();
		}
		firstDir.delete();

		Environment.currentDirectory  = initFilePath;
	}

	@Test
	public void testGlobbing() {
		String args = "cat " + "*";
		try {
			shell.parseAndEvaluate(args, stdout);

		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	// @Test
	// public void testOutRedirectionWithOutNotExists(){
	// String args = "cat " + "testDir" + File.separator + "testFile1.txt" + ">
	// output.txt";
	// BufferedReader br = null;
	// StringBuilder sb = new StringBuilder();
	// String line;
	//
	// try {
	//
	// shell.parseAndEvaluate(args, stdout);
	// br = new BufferedReader(new FileReader(outputFilePath));
	// if ((line = br.readLine()) != null) {
	// sb.append(line);
	// }
	//
	// while ((line = br.readLine()) != null) {
	// sb.append(System.lineSeparator());
	// sb.append(line);
	// }
	// assertEquals(firstFileString + secondFileString, sb.toString());
	// } catch (AbstractApplicationException e) {
	// fail("Should Not Throw Exception: " + e.getMessage());
	// } catch (ShellException e) {
	// fail("Should Not Throw Exception: " + e.getMessage());
	// } catch (IOException e){
	// fail("Should Not Throw Exception: " + e.getMessage());
	// }
	// }

	@Test
	public void testInRedirection() {
		String args = "cat < testDir" + File.separator + "testFile1.txt";
		try {
			shell.parseAndEvaluate(args, stdout);
			assertEquals(firstFileString, stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

}
