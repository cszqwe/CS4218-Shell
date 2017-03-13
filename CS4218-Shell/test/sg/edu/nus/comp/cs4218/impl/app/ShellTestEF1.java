package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class ShellTestEF1 {
	static ShellImpl shell;
	static OutputStream os;
	
	@Test
	//Test the globbing function.
	public void testGlobbing() throws AbstractApplicationException, ShellException {
		assertEquals(ShellImpl.processGlobbing("cat globbingTest\\*"), "cat globbingTest\test1.txt globbingTest\test2.txt ");
		os = new ByteArrayOutputStream();
		//A normal one
		String cmdline = "cat globbingTest\\*";
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4line 1\r\nline 2\r\nline 3\r\nline 4\r\nline 5\r\nline 6\r\nline 7\r\nline 8\r\nline 9\r\nline 10";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);		
	}

	@Test
	//Test the redirect function
	public void testRedirection() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline1 = "cat test.txt > redirectionTest.txt";
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4";
		String cmdline = "echo < redirectionTest.txt";
		shell.parseAndEvaluate(cmdline1, os);
		os = new ByteArrayOutputStream();
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
	}
	
}
