package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class ShellTestEF1 {
	static ShellImpl shell;
	static OutputStream os;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shell = new ShellImpl();
	}

	@Test
	//Test the globbing function.
	public void testGlobbing() throws AbstractApplicationException, ShellException {
		assertEquals(ShellImpl.processGlobbing("cat globbingTest\\*"), "cat globbingTest/test.txt globbingTest/test2.txt");
		assertEquals(ShellImpl.processGlobbing("cat globbingTest\\test*"), "cat globbingTest/test.txt globbingTest/test2.txt");
		assertEquals(ShellImpl.processGlobbing("cat globbingTest\\test2*"), "cat globbingTest/test2.txt");
		os = new ByteArrayOutputStream();
		//A normal one
		String cmdline = "cat globbingTest\\*";
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4line 1\r\nline 2\r\nline 3\r\nline 4\r\nline 5\r\nline 6\r\nline 7\r\nline 8\r\nline 9\r\nline 10\r\nline 11\r\nline 12";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());		
	}

	@Test
	//Test the redirect function
	public void testRedirection() throws AbstractApplicationException, ShellException {
		assertEquals(ShellImpl.processRedirectInput("cat < test.txt"), "cat");
		try{
			ShellImpl.processRedirectInput("cat < test.txt < test2.txt");			
		}catch (Exception e){
			assertEquals(e.getMessage(), "shell: More than one redirect input");
		}
		try{
			ShellImpl.processRedirectInput("cat < test3.txt");			
		}catch (Exception e){
			assertEquals(e.getMessage(), "shell: Could not read file");
		}
		os = new ByteArrayOutputStream();
		String cmdlineS = "cat < test.txt";
		shell.parseAndEvaluate(cmdlineS, os);
		assertEquals("line 1\r\nline 2\r\nline 3\r\nline 4", os.toString());		
		os = new ByteArrayOutputStream();
		String cmdline1 = "cat test.txt > redirectionTest.txt";
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4";
		String cmdline = "cat < redirectionTest.txt";
		shell.parseAndEvaluate(cmdline1, os);
		os = new ByteArrayOutputStream();
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(os.toString(), expected);
		String cmdline2 = "echo ThisIsATestFile > echoReOut.txt";
		shell.parseAndEvaluate(cmdline2, os);
		String cmdline3 = "cat echoReOut.txt";
		os = new ByteArrayOutputStream();
		shell.parseAndEvaluate(cmdline3, os);
		assertEquals(os.toString(), "ThisIsATestFile\n");
		
	}
	
}
