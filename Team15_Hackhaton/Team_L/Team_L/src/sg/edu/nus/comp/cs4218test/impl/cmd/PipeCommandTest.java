package sg.edu.nus.comp.cs4218test.impl.cmd;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.cmd.PipeCommand;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class PipeCommandTest {
	private static final String SECOND = "Second";
	private static final String SAMPLE = "Sample";
	private static final String FIRST = "First";
	private static final String EXCEPTION_PREFIX = "Should not throw exception: ";
	private final String firstFilePath = "testDir" + File.separator + "firstTest.txt";
	private final String secondFilePath = "testDir" + File.separator + "secondDir" + File.separator + "secondTest.txt";
	private final String firstFileString = FIRST + System.lineSeparator() + SAMPLE;
	private final String secondFileString = SECOND + System.lineSeparator() + SAMPLE;
	private InputStream stdin;

	private File firstDir, secondDir;

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
	public void testPipeCalCal() {
		PipeCommand pipeCommand = new PipeCommand("cal | cal");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa" + System.lineSeparator()
					+ "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 "
					+ System.lineSeparator() + "12 13 14 15 16 17 18 " + System.lineSeparator()
					+ "19 20 21 22 23 24 25 " + System.lineSeparator() + "26 27 28 29 30 31 " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCalCat() {
		PipeCommand pipeCommand = new PipeCommand("cal | cat " + firstFilePath);
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(FIRST + System.lineSeparator() + SAMPLE, stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCalEchoWithoutParameter() {
		PipeCommand pipeCommand = new PipeCommand("cal | echo ");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCalEchoWithParameter() {
		PipeCommand pipeCommand = new PipeCommand("cal | echo test");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("test" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCalGrepResultFound() {
		PipeCommand pipeCommand = new PipeCommand("cal | grep March");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("March 2017" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCalGrepResultNotFound() {
		PipeCommand pipeCommand = new PipeCommand("cal | grep January");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("", stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCalHeadWithoutParameter() {
		PipeCommand pipeCommand = new PipeCommand("cal | head");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa" + System.lineSeparator()
					+ "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 "
					+ System.lineSeparator() + "12 13 14 15 16 17 18 " + System.lineSeparator()
					+ "19 20 21 22 23 24 25 " + System.lineSeparator() + "26 27 28 29 30 31 " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCalHeadWithParameter() {
		PipeCommand pipeCommand = new PipeCommand("cal | head " + firstFilePath);
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(FIRST + System.lineSeparator() + SAMPLE + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCalPwd() {
		PipeCommand pipeCommand = new PipeCommand("cal | pwd");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(Environment.currentDirectory + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCalSort() {
		PipeCommand pipeCommand = new PipeCommand("cal | sort");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("          1  2  3  4 " + System.lineSeparator() + "   March 2017" + System.lineSeparator()
					+ " 5  6  7  8  9 10 11 " + System.lineSeparator() + "12 13 14 15 16 17 18 "
					+ System.lineSeparator() + "19 20 21 22 23 24 25 " + System.lineSeparator() + "26 27 28 29 30 31 "
					+ System.lineSeparator() + "Su Mo Tu We Th Fr Sa" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCatCat() {
		PipeCommand pipeCommand = new PipeCommand("cat " + firstFilePath + " | cat " + secondFilePath);
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(SECOND + System.lineSeparator() + SAMPLE, stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCatCal() {
		PipeCommand pipeCommand = new PipeCommand("cat " + firstFilePath + " | cal");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa" + System.lineSeparator()
					+ "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 "
					+ System.lineSeparator() + "12 13 14 15 16 17 18 " + System.lineSeparator()
					+ "19 20 21 22 23 24 25 " + System.lineSeparator() + "26 27 28 29 30 31 " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCatEchoWithoutParameter() {
		PipeCommand pipeCommand = new PipeCommand("cat " + firstFilePath + " | echo");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCatEchoWithParameter() {
		PipeCommand pipeCommand = new PipeCommand("cat " + firstFilePath + " | echo something");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("something" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCatGrepResultFound() {
		PipeCommand pipeCommand = new PipeCommand("cat " + firstFilePath + " | grep Sample");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(SAMPLE + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCatGrepResultNotFound() {
		PipeCommand pipeCommand = new PipeCommand("cat " + firstFilePath + " | grep Example");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("", stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCatHeadWithoutParameter() {
		PipeCommand pipeCommand = new PipeCommand("cat " + firstFilePath + " | head");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(FIRST + System.lineSeparator() + SAMPLE + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCatHeadWithParameter() {
		PipeCommand pipeCommand = new PipeCommand("cat " + firstFilePath + " | head " + secondFilePath);
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(SECOND + System.lineSeparator() + SAMPLE + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCatPwd() {
		PipeCommand pipeCommand = new PipeCommand("cat " + firstFilePath + " | pwd");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(Environment.currentDirectory + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeCatSort() {
		PipeCommand pipeCommand = new PipeCommand("cat " + secondFilePath + " | sort");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(SAMPLE + System.lineSeparator() + SECOND + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoEchoWithParameter() {
		PipeCommand pipeCommand = new PipeCommand("echo some | echo thing");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("thing" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoEchoWithoutParameter() {
		PipeCommand pipeCommand = new PipeCommand("echo some | echo");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoCal() {
		PipeCommand pipeCommand = new PipeCommand("echo some | cal");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa" + System.lineSeparator()
					+ "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 "
					+ System.lineSeparator() + "12 13 14 15 16 17 18 " + System.lineSeparator()
					+ "19 20 21 22 23 24 25 " + System.lineSeparator() + "26 27 28 29 30 31 " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoCatWithoutParameter() {
		PipeCommand pipeCommand = new PipeCommand("echo some | cat");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("some" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoCatWithParameter() {
		PipeCommand pipeCommand = new PipeCommand("echo some | cat " + firstFilePath);
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(FIRST + System.lineSeparator() + SAMPLE, stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoGrepResultFound() {
		PipeCommand pipeCommand = new PipeCommand("echo \"abc\\nab\\nfff\" | grep ab ");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("abc\\nab\\nfff" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoGrepResultNotFound() {
		PipeCommand pipeCommand = new PipeCommand("echo \"abc\\nab\\nfff\" | grep zz ");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("", stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoHeadWithoutParameter() {
		PipeCommand pipeCommand = new PipeCommand("echo no parameter | head");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("no parameter" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoHeadWithParameter() {
		PipeCommand pipeCommand = new PipeCommand("echo no parameter | head " + firstFilePath);
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(FIRST + System.lineSeparator() + SAMPLE + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoPwd() {
		PipeCommand pipeCommand = new PipeCommand("echo no parameter | pwd");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(Environment.currentDirectory + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeEchoSort() {
		PipeCommand pipeCommand = new PipeCommand("echo hello world | sort");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("hello world" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepGrepResultFound() {
		PipeCommand pipeCommand = new PipeCommand("grep Sample " + firstFilePath + " | grep Sample");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(SAMPLE + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepGrepResultNotFound() {
		PipeCommand pipeCommand = new PipeCommand("grep Sample " + firstFilePath + " | grep Example");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("", stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepCal() {
		PipeCommand pipeCommand = new PipeCommand("grep Sample " + firstFilePath + " | cal");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa" + System.lineSeparator()
					+ "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 "
					+ System.lineSeparator() + "12 13 14 15 16 17 18 " + System.lineSeparator()
					+ "19 20 21 22 23 24 25 " + System.lineSeparator() + "26 27 28 29 30 31 " + System.lineSeparator(),
					stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepCatWithoutParameter() {
		PipeCommand pipeCommand = new PipeCommand("grep Sample " + firstFilePath + " | cat");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(SAMPLE + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepCatWithParameter() {
		PipeCommand pipeCommand = new PipeCommand("grep Sample " + firstFilePath + " | cat " + secondFilePath);
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(SECOND + System.lineSeparator() + SAMPLE, stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepEchoWithoutParameter() {
		PipeCommand pipeCommand = new PipeCommand("grep First " + firstFilePath + " | echo");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepEchoWithParameter() {
		PipeCommand pipeCommand = new PipeCommand("grep First " + firstFilePath + " | echo hello world");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals("hello world" + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepHeadWithoutParameter() {
		PipeCommand pipeCommand = new PipeCommand("grep First " + firstFilePath + " | head");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(FIRST + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepHeadWithParameter() {
		PipeCommand pipeCommand = new PipeCommand("grep First " + firstFilePath + " | head " + secondFilePath);
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(SECOND + System.lineSeparator() + SAMPLE + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepPwd() {
		PipeCommand pipeCommand = new PipeCommand("grep First " + firstFilePath + " | pwd");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(Environment.currentDirectory + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeGrepSort() {
		PipeCommand pipeCommand = new PipeCommand("grep First " + firstFilePath + " | sort");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(FIRST + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testPipeHeadTail() {
		PipeCommand pipeCommand = new PipeCommand("head " + firstFilePath + " | tail -n 1");
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		try {
			pipeCommand.evaluate(stdin, stdout);
			assertEquals(SAMPLE + System.lineSeparator(), stdout.toString());
		} catch (AbstractApplicationException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

}
