package sg.edu.nus.comp.cs4218test.impl;

import static org.junit.Assert.*;

import org.junit.*;

import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.Parser;

/**
 * Test suite for the Parser.
 *
 */
public class ParserTest {

	private static final String COMMAND_TYPE_CALL = "COMMAND_TYPE_CALL";
	private static final String EXCEPTION_PREFIX = "Should not throw exception: ";
	private String args;

	@Test
	public void testWithPipe() {
		args = "cat File.txt | echo";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals("COMMAND_TYPE_PIPE", parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testWithSeq() {
		args = "cat File.txt ; cat file2.txt";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals("COMMAND_TYPE_SEQ", parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testNormal() {
		args = "cat file.txt";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals(COMMAND_TYPE_CALL, parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testLongSpaceBetweenPipe() {
		args = "cat File.txt                                      |                                           echo";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals("COMMAND_TYPE_PIPE", parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testDoublePipe() {
		args = "cat || file.txt";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals("COMMAND_TYPE_PIPE", parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testValidCommandWithSingleQuote() {
		args = "echo 'echo something'";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals(COMMAND_TYPE_CALL, parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testValidCommandWithDoubleQuote() {
		args = "echo \"echo something\"";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals(COMMAND_TYPE_CALL, parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testValidCommandWithBackQuote() {
		args = "echo `echo something`";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals(COMMAND_TYPE_CALL, parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testValidCommandWithBackSingleQuote() {
		args = "echo '`echo something`'";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals(COMMAND_TYPE_CALL, parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testValidCommandWithBackDoubleQuote() {
		args = "echo \"`echo something`\"";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals(COMMAND_TYPE_CALL, parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testValidCommandWithSingleDoubleQuote() {
		args = "echo \"'echo something'\"";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals(COMMAND_TYPE_CALL, parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testValidCommandWithSingleDoubleBackQuote() {
		args = "echo '`\"echo something\"`'";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals(COMMAND_TYPE_CALL, parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testValidCommandWithQuote() {
		args = "echo \"'echo something'\"";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
			assertEquals(COMMAND_TYPE_CALL, parser.getType());
		} catch (ShellException e) {
			fail(EXCEPTION_PREFIX + e.getMessage());
		}
	}

	@Test
	public void testInvalidCommandWithInvalidApp() {
		args = "fakeCommand something";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
		} catch (ShellException e) {
			assertEquals("shell: fakeCommand: Invalid app.", e.getMessage());
		}
	}

	@Test
	public void testInvalidCommandWithSingleBackQuoteInsideBackQuote() {
		args = "echo `some `echo some`";
		Parser parser = new Parser(args);
		try {
			parser.evaluate();
		} catch (ShellException e) {
			assertEquals("shell: Invalid syntax encountered.", e.getMessage());
		}
	}

	@Test
	public void testCheckInvalidNestedBackQuote() {
		args = "echo `some `echo some``";
		try {
			Parser.checkInvalidNestedBackQuote(args);
		} catch (ShellException e) {
			assertEquals("shell: Invalid syntax encountered.", e.getMessage());
		}
	}
}
