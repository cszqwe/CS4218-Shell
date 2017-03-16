package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.exception.HeadException;
import sg.edu.nus.comp.cs4218.exception.SedException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

/**
 * Unit Test For Sed Application
 *
 */
public class ExtraSedApplicationTest {
	private static final String FS = File.separator;
	private static final String NEWLINE = "\n";
	private static final String TWO_LINE_FILE_PATH = "files" + FS + "sedTestFiles" + FS + "two-lines.txt";
	private static final String EMPTY_FILE_PATH = "files" + FS + "sedTestFiles" + FS + "empty.txt";
	private static final String NUMBER_FILE_PATH = "files" + FS + "sedTestFiles" + FS + "number.txt";
	private static final String HELLO_WORLD_FILE_PATH = "files" + FS + "sedTestFiles" + FS + "hello world.txt";

	static ShellImpl shell;
	static OutputStream os;
	
	
	private static SedApplication sed;
	private OutputStream stdout;
	private InputStream stdin;
	private static InputStream twoLineFileInputStream;
	private static InputStream emptyFileInputStream;
	private static InputStream numberFileInputStream;
	private static InputStream hellowWorldFileInputStream;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sed = new SedApplication();

	}

	@AfterClass
	public static void reset() {
	}

	@Before
	public void setUp() throws Exception {
		shell = new ShellImpl();
		stdout = new ByteArrayOutputStream();
		twoLineFileInputStream = new FileInputStream(new File(TWO_LINE_FILE_PATH));
		emptyFileInputStream = new FileInputStream(new File(EMPTY_FILE_PATH));
		numberFileInputStream = new FileInputStream(new File(NUMBER_FILE_PATH));
		hellowWorldFileInputStream = new FileInputStream(new File(HELLO_WORLD_FILE_PATH));
	}

	@Test(expected = SedException.class)
	public void testSedWithNullArgument() throws SedException {
		String args[] = null;
		stdin = hellowWorldFileInputStream;
		sed.run(args, stdin, stdout);
		// "error on sed command - fails to throw exception with null args";
	}



	@Test(expected = SedException.class)
	public void testSedWithSingleArgument() throws SedException {
		String args[] = { "arg1" };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - fails to throw exception with insuffcient
		// args";
	}


	@Test(expected = SedException.class)
	public void testSedWithNullStdinAndNonFileArg() throws SedException {
		String args[] = { "s|a|b|" };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - fails to throw exception with null stdin and
		// non file arg";
	}

	@Test
	public void testSedWithNullStdinAndNonExistentFile() throws SedException {
		String args[] = { "s|a|b|", "non-existent.txt" };
		stdin = null;
		sed.run(args, stdin, stdout);
		// String msg =
		// "error on sed command - fails to throw exception with null stdin and
		// non-existent file ";
	}

	@Test 
	public void testSedWithEmptyFile() throws SedException {
		String args[] = { "s|a|b|", EMPTY_FILE_PATH };
		stdin = null;
		String expected = NEWLINE;
		stdout = new ByteArrayOutputStream();
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with empty file";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithTwoLineFile() throws SedException {
		String args[] = { "s|a|b|", TWO_LINE_FILE_PATH };
		stdin = null;
		String expected = "Hey, good to know <you>!" + NEWLINE + "This is b small file consists of {1+1+0} lines."
				+ NEWLINE + "/* Hope this helps */ # no new line here" + NEWLINE;
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with two line file";
		assertEquals(msg, expected, stdout.toString());
	}


	@Test
	public void testSedWithEmptyFileInputStream() throws SedException {
		String args[] = { "s|a|b|" };
		stdin = emptyFileInputStream;
		String expected = NEWLINE;
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with empty file input stream";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithNumberFileInputStream() throws SedException {
		String args[] = { "s*3*76*" };
		stdin = numberFileInputStream;
		String expected = "01276456789\n";
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with two line file input stream";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithTwoLineFileInputStream() throws SedException {
		// mock current directory to a fake non-root one
		String args[] = { "s|a|b|" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, good to know <you>!" + NEWLINE + "This is b small file consists of {1+1+0} lines."
				+ NEWLINE + "/* Hope this helps */ # no new line here" + NEWLINE;
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with two line file input stream";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithTwoLineFileInputStreamAndEmptyFile() throws SedException {
		String args[] = { "s|a|b|", EMPTY_FILE_PATH };
		stdin = twoLineFileInputStream;
		String expected = "\n";
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with two line file inputstream and empty file";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithExtraArgs1() throws SedException {
		String args[] = { "s|a|b|", EMPTY_FILE_PATH, "-l" };
		stdin = null;
		String expected = NEWLINE;
		stdout = new ByteArrayOutputStream();
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with extra redundant arg";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithExtraArgs2() throws SedException {
		String args[] = { "s|0|1|", NUMBER_FILE_PATH, TWO_LINE_FILE_PATH, "-l" };
		stdin = null;
		String expected = "1123456789\n";
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with extra redundant arg";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test(expected = SedException.class)
	public void testSedWithInvalidReplacementOnIllegalFormat1() throws SedException {
		String args[] = { "|0|1|", NUMBER_FILE_PATH };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - incorrect output with invalid replacement
		// that has illegal format";
	}

	@Test(expected = SedException.class)
	public void testSedWithInvalidReplacementOnIllegalFormat2() throws SedException {
		// mock current directory to a fake non-root one
		String args[] = { "s|0|1|gg", NUMBER_FILE_PATH };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - incorrect output with invalid replacement
		// that has illegal format";
	}

	@Test(expected = SedException.class)
	public void testSedWithInvalidReplacementOnIllegalFormat3() throws SedException {
		String args[] = { "s-0|1|g", NUMBER_FILE_PATH };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - incorrect output with invalid replacement
		// that has illegal format";
	}

	@Test(expected = SedException.class)
	public void testSedWithInvalidReplacementOnIllegalFormat4() throws SedException {
		String args[] = { "s-0|1g", NUMBER_FILE_PATH };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - incorrect output with invalid replacement
		// that has illegal format";
	}

	@Test(expected = SedException.class)
	public void testSedWithInvalidReplacementOnIllegalFormat5() throws SedException {
		String args[] = { "s|0| m| |g", NUMBER_FILE_PATH };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - incorrect output with invalid replacement
		// that has illegal format";
	}

	@Test(expected = SedException.class)
	public void testSedWithInvalidReplacementOnIllegalRegex1() throws SedException {
		String args[] = { "s|0| m| |g", NUMBER_FILE_PATH };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - incorrect output with invalid replacement
		// that has illegal regular expression";
	}

	@Test(expected = SedException.class)
	public void testSedWithInvalidReplacementOnIllegalFormat6() throws SedException {
		String args[] = { "s||0||1||", NUMBER_FILE_PATH };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - incorrect output with invalid replacement
		// that has illegal format";
	}

	@Test(expected = SedException.class)
	public void testSedWithInvalidReplacementOnIllegalFormat7() throws SedException {
		// mock current directory to a fake non-root one
		String args[] = { "s|", NUMBER_FILE_PATH };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - incorrect output with invalid replacement
		// that has illegal format";
	}

	@Test(expected = SedException.class)
	public void testSedWithInvalidReplacementOnIllegalFormat8() throws SedException {
		String args[] = { "m|1|2|", NUMBER_FILE_PATH };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - incorrect output with invalid replacement
		// that has illegal format";
	}

	@Test(expected = SedException.class)
	public void testSedWithInvalidReplacementOnIllegalRegrex() throws SedException {
		String args[] = { "s|[|1|", NUMBER_FILE_PATH };
		stdin = null;
		sed.run(args, stdin, stdout);
		// "error on sed command - incorrect output with invalid replacement
		// that has illegal regrex";
	}

	@Test
	public void testSedWithGlobalReplacement() throws SedException {
		String args[] = { "s|l|*|g" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, good to know <you>!" + NEWLINE + "This is a sma** fi*e consists of {1+1+0} *ines."
				+ NEWLINE + "/* Hope this he*ps */ # no new *ine here";
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with global replacement";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithDifferentSeparator1() throws SedException {
		String args[] = { "ssls*sg" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, good to know <you>!" + NEWLINE + "This is a sma** fi*e consists of {1+1+0} *ines."
				+ NEWLINE + "/* Hope this he*ps */ # no new *ine here";
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with different separator";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithDifferentSeparator2() throws SedException {
		String args[] = { "s/l/*/g" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, good to know <you>!" + NEWLINE + "This is a sma** fi*e consists of {1+1+0} *ines."
				+ NEWLINE + "/* Hope this he*ps */ # no new *ine here";
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with different separator";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithDifferentSeparator3() throws SedException {
		String args[] = { "s,l,*,g" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, good to know <you>!" + NEWLINE + "This is a sma** fi*e consists of {1+1+0} *ines."
				+ NEWLINE + "/* Hope this he*ps */ # no new *ine here";
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with different separator";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithEmptyReplacment() throws SedException {
		String args[] = { "s|l||" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, good to know <you>!" + NEWLINE + "This is a smal file consists of {1+1+0} lines."
				+ NEWLINE + "/* Hope this heps */ # no new line here" + NEWLINE;
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with empty replacement";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithComplexReplacement1() throws SedException {
		String args[] = { "s|no| *&/s\\$|" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, good to k *&/s$w <you>!" + NEWLINE + "This is a small file consists of {1+1+0} lines."
				+ NEWLINE + "/* Hope this helps */ #  *&/s$ new line here\n";
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with complex replacement";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithComplexReplacement2() throws SedException {
		String args[] = { "s|o|[^]|" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, g[^]od to know <you>!" + NEWLINE + "This is a small file c[^]nsists of {1+1+0} lines."
				+ NEWLINE + "/* H[^]pe this helps */ # no new line here" + NEWLINE;
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with complex replacement";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithComplexReplacement3() throws SedException {
		String args[] = { "s|o   |% #$%^&|" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, good to know <you>!" + NEWLINE + "This is a small file consists of {1+1+0} lines."
				+ NEWLINE + "/* Hope this helps */ # no new line here" + NEWLINE;
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with complex replacement";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test(expected = SedException.class)
	public void testSedWithEmtpyRegexpAndEmptyReplacement() throws SedException {
		String args[] = { "s|||" };
		stdin = twoLineFileInputStream;
		sed.run(args, stdin, stdout);
		// String msg =
		// "error on sed command - fail to throw exception with empty regular
		// expression and empty replacement";
	}

	@Test(expected = SedException.class)
	public void testSedWithEmtpyRegexp() throws SedException {
		String args[] = { "s||m|g" };
		stdin = twoLineFileInputStream;
		sed.run(args, stdin, stdout);
		// String msg =
		// "error on sed command - fail to throw exception with empty regular
		// expression";
	}

	@Test
	public void testSedWithComplexRegexp1() throws SedException {
		String args[] = { "s|This|r|g" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, good to know <you>!" + "\n" + "r is a small file consists of {1+1+0} lines."
				+ "\n" + "/* Hope this helps */ # no new line here";
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with complex regular expression";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithComplexRegexp2() throws SedException {
		String args[] = { "s|o{2,3}d*|r|" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, gr to know <you>!" + NEWLINE + "This is a small file consists of {1+1+0} lines."
				+ NEWLINE + "/* Hope this helps */ # no new line here"+ NEWLINE;
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with complex regular expression";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithComplexRegexp3() throws SedException {
		String args[] = { "s|[^a-zA-Z ]|-|g" };
		stdin = twoLineFileInputStream;
		String expected = "Hey- good to know -you---This is a small file consists of ------- lines---- Hope this helps -- - no new line here";
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with complex regular expression";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testSedWithComplexRegexp4() throws SedException {
		String args[] = { "s-good|know-r-" };
		stdin = twoLineFileInputStream;
		String expected = "Hey, r to know <you>!" + NEWLINE + "This is a small file consists of {1+1+0} lines."
				+ NEWLINE + "/* Hope this helps */ # no new line here" + NEWLINE;
		sed.run(args, stdin, stdout);
		String msg = "error on sed command - incorrect output with complex regular expression";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testInvalidInputStream() throws SedException, IOException {
		String args[] = { "s|a|b|" };
		stdin = twoLineFileInputStream;
		InputStream stdinc = new FileInputStream(new File(EMPTY_FILE_PATH));
		stdinc.close();
		String expected ="sed: Stdin is not readable";
		sed.run(args, stdinc, stdout);
		String msg = "error on sed command - incorrect output with complex regular expression";
		assertEquals(msg, expected, stdout.toString());
	}
	
	@Test
	public void testInvalidFileStream() throws SedException, IOException {
		String args[] = { "s|a|b|", "nosuchfile.sad" };
		stdin = twoLineFileInputStream;
		InputStream stdinc = new FileInputStream(new File(EMPTY_FILE_PATH));
		stdinc.close();
		String expected = "sed: File is not readable";
		sed.run(args, stdinc, stdout);
		String msg = "sed: File is not readable";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test
	public void testInvalidInputStreamGlobal() throws SedException, IOException {
		String args[] = { "s|a|b|g" };
		stdin = twoLineFileInputStream;
		InputStream stdinc = new FileInputStream(new File(EMPTY_FILE_PATH));
		stdinc.close();
		String expected = "sed: Stdin is not readable";
		sed.run(args, stdinc, stdout);
		String msg = "error on sed command - incorrect output with complex regular expression";
		assertEquals(msg, expected, stdout.toString());
	}
	
	@Test
	public void testInvalidFileStreamGlobal() throws SedException, IOException {
		String args[] = { "s|a|b|g", "nosuchfile.sad" };
		stdin = twoLineFileInputStream;
		InputStream stdinc = new FileInputStream(new File(EMPTY_FILE_PATH));
		stdinc.close();
		String expected = "sed: File is not readable";
		sed.run(args, stdinc, stdout);
		String msg = "sed: File is not readable";
		assertEquals(msg, expected, stdout.toString());
	}

	@Test (expected = SedException.class)
	public void testInvalidOutputStream() throws SedException, IOException {
		String args[] = { "s|a|b|g" };
		stdin = twoLineFileInputStream;
		InputStream stdinc = new FileInputStream(new File(EMPTY_FILE_PATH));
		stdinc.close();
		sed.run(args, stdin, null);
	}

	@Test (expected = SedException.class)
	public void testInvalidInput() throws SedException, IOException {
		String args[] = { "s" };
		stdin = twoLineFileInputStream;
		InputStream stdinc = new FileInputStream(new File(EMPTY_FILE_PATH));
		stdinc.close();
		sed.run(args, stdin, null);
	}	
	
	@Test
	//Test the case of calling command functions
	public void commandSubTest1() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "sed s/l/L/ `echo test.txt`";
		String expected = "Line 1\r\nLine 2\r\nLine 3\r\nLine 4\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}

	@Test
	//Test the case of calling command functions
	public void commandSubTest2() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "head test.txt | sed `echo s/l/L/`";
		String expected = "Line 1\nLine 2\nLine 3\nLine 4\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}

	@Test (expected = CatException.class)
	//Test the fail case of calling command functions, when command subsititution failed, the whole thing would generate an exception
	public void commandSubTestFail() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "grep `echo line | cat test5.txt` test.txt | sed `echo s/l/L/`";
		String expected = "line 1\nline 2\nline 3\nline 4\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}

	@Test
	//Test the case of pipe
	public void pipeTest1() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "sed s/[l]/L/g test.txt | cat";
		String expected = "Line 1\r\nLine 2\r\nLine 3\r\nLine 4";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}

	@Test
	//Test the case of pipe
	public void pipeTest2() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "head -n 2 test.txt | sed s/[l]/L/g";
		String expected = "Line 1\nLine 2";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}	

	@Test (expected = HeadException.class)
	//Test the case of pipe with exception
	public void pipeTestFail() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "head -n 2 tesasdt.txt | grep line | sed test";
		String expected = "line 1\nline 2\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}	
	
	@Test
	//Test the case of calling command functions
	public void complicatedCommandSubTest1() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "grep `echo line` `echo test.txt` | sed s/[l]/L/g";
		String expected = "Line 1\nLine 2\nLine 3\nLine 4";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}

	@Test
	//Test the case of calling command functions
	public void complicatedCommandSubTest2() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "grep `echo line | head` `echo test.txt` | sed s/[l]/L/g";
		String expected = "Line 1\nLine 2\nLine 3\nLine 4";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}

	@Test (expected = SortException.class)
	//Test the fail case of calling command functions, when command subsititution failed, the whole thing would generate an exception
	public void ComplicatedommandSubTestFail() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "grep `echo line | cat test.txt` `sort test5.txt` | sed s/[l]/L/g";
		String expected = "line 1\nline 2\nline 3\nline 4\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}
	
	@Test
	//Test the case of calling command functions
	public void complicatedPipeTest1() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "grep line test.txt | tail -n 2 | grep 'line 4' | sed s/[l]/L/g";
		String expected = "Line 4";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}

	@Test
	//Test the case of calling command functions
	public void complicatedPipeSubTest2() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "grep 'line 2' test.txt | grep 'line' | grep 'li' | sed s/[l]/L/";
		String expected = "Line 2\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}

	@Test (expected = GrepException.class)
	//Test the fail case of calling command functions, when command subsititution failed, the whole thing would generate an exception
	public void ComplicatedPipeTestFail() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "grep `echo line | cat test.txt` `sort test.txt` | grep | grep line2| sed s";
		String expected = "line 1\nline 2\nline 3\nline 4\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}

	@Test
	//Test the fail case of calling command functions, when command subsititution failed, the whole thing would generate an exception
	public void quoteTest1() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "sed s/[l]/L/g test test.txt";
		String expected = "sed: File is not readable";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}
	
	@Test
	//Test the fail case of calling command functions, when command subsititution failed, the whole thing would generate an exception
	public void quoteTest2() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "sed s/[l]/L/g 'test.txt'";
		String expected = "Line 1\r\nLine 2\r\nLine 3\r\nLine 4";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}	
	
	@Test (expected = SedException.class)
	//Test the fail case of calling command functions, when command subsititution failed, the whole thing would generate an exception
	public void quoteFail() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "'sed' 'line 2' test.txt | 'grep' [";
		String expected = "line 2\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}
	
	@Test
	//Test the fail case of calling command functions, when command subsititution failed, the whole thing would generate an exception
	public void redirTest1() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "sed s/[l]/L/g < test.txt > sedL.txt";
		String cmdline2 = "sed s/[L]/l/g < sedL.txt";
		String expected = "line 1\nline 2\nline 3\nline 4";
		shell.parseAndEvaluate(cmdline, os);
		os = new ByteArrayOutputStream();
		shell.parseAndEvaluate(cmdline2, os);
		assertEquals(os.toString(), expected);
	}
	
	@Test
	//Test the fail case of calling command functions, when command subsititution failed, the whole thing would generate an exception
	public void redirTest2() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "grep 'line 2' test.txt > line2.txt";
		String cmdline2 = "cat test.txt | sed s/[L]/l/g < line2.txt";
		String expected = "line 2";
		shell.parseAndEvaluate(cmdline, os);
		os = new ByteArrayOutputStream();
		shell.parseAndEvaluate(cmdline2, os);
		assertEquals(os.toString(), expected);
	}
	
	@Test
	//Test the fail case of calling command functions, when command subsititution failed, the whole thing would generate an exception
	public void redirTest3() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "grep 'line 2' test.txt > line2.txt";
		String cmdline2 = "cat test.txt | grep line test.txt < line2.txt | sed s/[l]/L/";
		String expected = "Line 1\nLine 2\nLine 3\nLine 4\n";
		shell.parseAndEvaluate(cmdline, os);
		os = new ByteArrayOutputStream();
		shell.parseAndEvaluate(cmdline2, os);
		assertEquals(os.toString(), expected);
	}
	
	
	@Test (expected = ShellException.class)
	//Test the fail case of calling command functions, when command subsititution failed, the whole thing would generate an exception
	public void redirTestFail() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "grep 'line 2' test.txt > line2.txt";
		String cmdline2 = "sed s/[L]/l/g < line5.txt";
		String expected = "line 2\n";
		shell.parseAndEvaluate(cmdline, os);
		os = new ByteArrayOutputStream();
		shell.parseAndEvaluate(cmdline2, os);
		assertEquals(os.toString(), expected);
	}	
}
