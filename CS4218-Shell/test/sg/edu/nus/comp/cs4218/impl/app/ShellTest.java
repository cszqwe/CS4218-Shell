package sg.edu.nus.comp.cs4218.impl.app;
import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

public class ShellTest {
	static ShellImpl shell;
	static OutputStream os;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shell = new ShellImpl();
	}

	
	@Test
	//Test the basic call command functions
	public void testCallCommand() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "cat test.txt";
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}

	@Test
	//Test the fail case of calling command functions
	public void testNotValidCommand() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "notvalidcommand aaa aaa";
		Exception exc = new Exception();
		try{
			shell.parseAndEvaluate(cmdline, os);
	
		}catch (Exception e){
			exc = e;
		}
		assertEquals("shell: notvalidcommand: Invalid app.",exc.getMessage());
	}
	
	@Test
	//Test the double quoting function
	public void testDoubleQuoting() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "echo \"asd\"asd";
		String expected = "asdasd\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}

	@Test
	//Test the single quoting function
	public void testSingleQuoting() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "echo \'asd\'asd";
		String expected = "asdasd\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
		os = new ByteArrayOutputStream();
		cmdline = "echo \'\"asd\"\'asd";
		expected = "\"asd\"asd\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}
	
	@Test
	//Test the command subsititution function
	public void testCommandSubsititution() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		//A normal one
		String cmdline = "echo `echo \'asd\'asd`";
		String expected = "asdasd\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
		os = new ByteArrayOutputStream();
		//A one commented by single quote
		cmdline = "echo \'`echo asd`\'asd";
		expected = "`echo asd`asd\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
		os = new ByteArrayOutputStream();
		cmdline = "echo \"This is space:`echo \" \"`.\" ";
		expected = "This is space: .\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
		os = new ByteArrayOutputStream();
		cmdline = "echo 'This is space:`echo \" \"`.' ";
		expected = "This is space:`echo \" \"`.\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}
	
	@Test
	//Test the fail case of calling command functions
	public void testSemicolon() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		//A normal one
		String cmdline = "echo asdasd ; echo asd";
		String[] expected = {"echo asdasd ", " echo asd"};
		String[] result = ShellImpl.processSemi(cmdline);
		assertEquals(expected[0], result[0]);
		assertEquals(expected[1], result[1]);
	}

	@Test
	//Test the fail case of calling command functions
	public void testPipeline() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "cat test.txt | cat | cat | cat | cat | cat";
		String expected = "line 1\r\nline 2\r\nline 3\r\nline 4";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
		os = new ByteArrayOutputStream();
		cmdline = "cat test.txt | head -n 2 | tail -n 1";
		expected = "line 2\n";
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}
	
	@Test
	//Test the fail case of calling command functions
	public void testPipeTwoCommands() throws AbstractApplicationException, ShellException {
		assertEquals("2\n", shell.pipeTwoCommands("echo 1 | echo 2"));
	}
	
	@Test
	//Test the fail case of calling command functions
	public void testPipeMultipleCommands() throws AbstractApplicationException, ShellException {
		assertEquals("3\n", shell.pipeMultipleCommands("echo 1 | echo 2| echo 3"));
	}
	
	@Test
	//Test the fail case of calling command functions
	public void testPipeWithException() throws AbstractApplicationException, ShellException {
		assertEquals("shell: ech: Invalid app.", shell.pipeWithException("echo 1 | echo 2| ech 3 |echo 4 "));
	}
	
	@Test
	//Test the fail case of calling command functions
	public void testGlobNoPath() throws AbstractApplicationException, ShellException {
		assertEquals("globbingTests is no directory.", shell.globNoPaths("cat globbingTests/*"));
	}
	
	@Test
	//Test the fail case of calling command functions
	public void testGlobOneFile(){
		assertEquals("line 1\r\nline 2\r\nline 3\r\nline 4", shell.globOneFile("cat globbingTest/test.txt*"));
	}
	
	@Test
	//Test the fail case of calling command functions
	public void testGlobMultipleFile(){
		assertEquals(127, shell.globFilesDirectories("cat globbingTest/*").length());
	}

	@Test
	//Test the fail case of calling command functions
	public void testGlobWithException(){
		assertEquals("globbingTests is no directory.", shell.globWithException("cat globbingTests/*"));
	}

	@Test
	//Test the fail case of calling command functions
	public void testRedirectInput(){
		assertEquals("line 1\r\nline 2\r\nline 3\r\nline 4", shell.redirectInput("cat < test.txt")) ;
	}

	@Test
	//Test the fail case of calling command functions
	public void testRedirectOutput(){
		assertEquals("",shell.redirectOutput( "cat < test.txt > testredirectout.txt"));
	}

	@Test
	//Test the fail case of calling command functions
	public void testrediRectInputWithNoFile(){
		assertEquals("shell: ",shell.redirectInputWithNoFile( "cat <"));
	}

	@Test
	//Test the fail case of calling command functions
	public void testrediRectOutputWithNoFile(){
		assertEquals("shell: ",shell.redirectOutputWithNoFile( "cat >"));
	}

	@Test
	//Test the fail case of calling command functions
	public void testrediRectIntputWithException(){
		assertEquals("shell: ",shell.redirectInputWithException( "cat <"));
	}
		
	@Test
	//Test the fail case of calling command functions
	public void testrediRectOutputWithException(){
		assertEquals("shell: ",shell.redirectOutputWithException( "cat >"));
	}
	
	@Test
	//Test the fail case of calling command functions
	public void performCommandSubstitution(){
		assertEquals("line 1\r\nline 2\r\nline 3\r\nline 4",shell.performCommandSubstitution("cat `echo test.txt`"));
	}
	
	@Test
	//Test the fail case of calling command functions
	public void performCommandSubstitutionWithException(){
		assertEquals("shell: eco: Invalid app.",shell.performCommandSubstitutionWithException("cat `eco test.txt`"));
	}
	
	
}
