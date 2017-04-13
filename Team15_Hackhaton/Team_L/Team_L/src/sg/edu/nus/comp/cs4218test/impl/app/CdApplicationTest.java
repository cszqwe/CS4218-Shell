package sg.edu.nus.comp.cs4218test.impl.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CdException;
import sg.edu.nus.comp.cs4218.exception.InvalidFileOrDirException;
import sg.edu.nus.comp.cs4218.impl.app.CdApplication;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Test suite for the cd application.
 *
 */
public class CdApplicationTest {

	private static final String CHILD_DIR_ONE = "childDir1";
	private static final String DIR = "dir";
	File parentDir;
	File firstChildDir;
	File secondChildDir;
	File thirdChildDir;
	InputStream stdin;
	OutputStream stdout;
	String initFilePath;

	@Before
	public void setup() throws IOException {
		stdin = System.in;
		stdout = System.out;
		parentDir = new File(DIR);
		parentDir.mkdir();

		firstChildDir = new File(parentDir, CHILD_DIR_ONE);
		firstChildDir.mkdir();
		secondChildDir = new File(parentDir, "childDir2");
		secondChildDir.mkdir();
		thirdChildDir = new File(parentDir, "childDir3");
		thirdChildDir.mkdir();
		// Environment.setCurrentDirectory("dir");
		initFilePath = Environment.currentDirectory;
	}

	@After
	public void teardown() throws IOException, InvalidFileOrDirException {

		firstChildDir.delete();
		secondChildDir.delete();
		thirdChildDir.delete();
		parentDir.delete();
		String afterFilePath = Environment.currentDirectory;
		Environment.setCurrentDirectory(afterFilePath, initFilePath);
	}

	@Test
	public void testCdNormal() {
		try {
			String[] args = { DIR };
			CdApplication cdApp = new CdApplication();
			cdApp.run(args, stdin, stdout);
			assertTrue((initFilePath + File.separator + DIR).equals(Environment.currentDirectory));
		} catch (CdException e) {
			fail("Should Not Throw Exception: " + e.getMessage());
		}
	}

	@Test
	public void testCdFileNotExist() {
		try {
			String[] args = { "childDir12" };
			CdApplication cdApp = new CdApplication();
			cdApp.run(args, stdin, stdout);
		} catch (CdException e) {
			assertEquals("cd: " + Environment.currentDirectory + File.separator + "childDir12: "
					+ "No such file or directory", e.getMessage());
		}
	}

	@Test
	public void testCdTwoRuns() {
		try {
			String[] args = { DIR + File.separator + CHILD_DIR_ONE };
			CdApplication cdApp = new CdApplication();
			cdApp.run(args, stdin, stdout);
			args[0] = "childDir2";
			cdApp.run(args, stdin, stdout);

		} catch (CdException e) {
			assertEquals("cd: " + Environment.currentDirectory + File.separator + "childDir2: "
					+ "No such file or directory", e.getMessage());
		}
	}

	@Test
	public void testCdWholeFilePath() {
		try {
			String[] args = { initFilePath + File.separator + DIR + File.separator + CHILD_DIR_ONE };
			CdApplication cdApp = new CdApplication();
			cdApp.run(args, stdin, stdout);
			assertTrue((initFilePath + File.separator + DIR + File.separator + CHILD_DIR_ONE)
					.equals(Environment.currentDirectory));

		} catch (CdException e) {
			fail("Should Not Throw Exception: " + e.getMessage());
		}
	}

	@Test
	public void testCdWrongFilePath() {
		try {
			String[] args = { initFilePath + File.separator + "childDir122" };
			CdApplication cdApp = new CdApplication();
			cdApp.run(args, stdin, stdout);

		} catch (CdException e) {
			assertEquals("cd: " + Environment.currentDirectory + File.separator + "childDir122: "
					+ "No such file or directory", e.getMessage());
		}
	}

	@Test
	public void testCdNullArgs() {
		try {
			CdApplication cdApp = new CdApplication();
			cdApp.run(null, stdin, stdout);

		} catch (CdException e) {
			assertTrue(e.getMessage().contains("Null arguments"));
		}
	}

	@Test
	public void testCdNoArgs() {
		try {
			String[] args = new String[0];
			CdApplication cdApp = new CdApplication();
			cdApp.run(args, stdin, stdout);

		} catch (CdException e) {
			assertTrue(e.getMessage().contains("No directory provided"));
		}
	}

	@Test
	public void testCdMultArgs() {
		try {
			String[] args = { initFilePath + File.separator + "childDir2",
					initFilePath + File.separator + CHILD_DIR_ONE };
			CdApplication cdApp = new CdApplication();
			cdApp.run(args, stdin, stdout);

		} catch (CdException e) {
			assertTrue(e.getMessage().contains("Invalid directory"));
		}
	}

}
