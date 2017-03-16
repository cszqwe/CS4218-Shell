package sg.edu.nus.comp.cs4218.impl.app;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.CdApplication;

public class CdApplicationTest {
	static String origPwd;
	static CdApplication cdApp;
	OutputStream os;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cdApp = new CdApplication();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Environment.currentDirectory = origPwd;
	}

	@Before
	public void setUp() throws Exception {
		origPwd = Environment.currentDirectory; // for assertion and backup
		os = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		Environment.currentDirectory = origPwd; // reset current dir
	}

	@Test
	public void testCd() {
		String args[] = {"cd Test_folder"};
		try {
			cdApp.run(args, null, null);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(origPwd + "\\cd Test_folder", Environment.currentDirectory);
		
	}
	
	@Test
	public void testCdInvalidDir() {
		String args[] = {"nonexistent"};
		try {
			cdApp.run(args, null, null);
			
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(origPwd, Environment.currentDirectory);
	}
	
	@Test
	public void testCdNestedDir() {
		String args[] = {"cd Test_folder\\nested-folder"};
		try {
			cdApp.run(args, null, null);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(origPwd + "\\cd Test_folder\\nested-folder", Environment.currentDirectory);
	}
	
	@Test
	public void testCdMultipleArgsValid() {
		String args[] = {"cd Test_folder", "asdf"};
		try {
			cdApp.run(args, null, null);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(origPwd + "\\cd Test_folder", Environment.currentDirectory);
	}
	
	@Test
	public void testCdMultipleArgsInvalid() {
		String args[] = {"Nonexistent", "asdf"};
		try {
			cdApp.run(args, null, null);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(origPwd, Environment.currentDirectory);
	}
	
	@Test
	public void testCdFileNotDir() {
		String args[] = {"Test.txt"};
		try {
			cdApp.run(args, null, null);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(origPwd, Environment.currentDirectory);
	}
	
	@Test
	public void testCdNoArgs() {
		String[] args = {};
		try {
			cdApp.run(args, null, null);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(origPwd, Environment.currentDirectory);
	}
	
	@Test
	public void testCdNullArgs() {
		String[] args = null;
		try {
			cdApp.run(args, null, null);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(origPwd, Environment.currentDirectory);
	}
	
	@Test
	public void testCdEmptyArgs() {
		String[] args = {""};
		try {
			cdApp.run(args, null, null);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(origPwd, Environment.currentDirectory);
	}
	
	// Integration tests
	
	@Test
	public void testCdCmdPipe() throws AbstractApplicationException, ShellException {
		// Negative test case: while cd does not throw exception, it does not read from stdin
		ShellImpl shell = new ShellImpl();
		String args = "echo Files | cd";
		shell.parseAndEvaluate(args, os);
		assertEquals(origPwd, Environment.currentDirectory);
	}
	
	@Test
	public void testCdCmdSubstitution() throws AbstractApplicationException, ShellException {
		// Positive test case: unlike pipe, cmd substitution passes the contents as args, allowing cd to change directory
		ShellImpl shell = new ShellImpl();
		String args = "cd `echo Files`";
		shell.parseAndEvaluate(args, os);
		assertEquals(origPwd + "\\Files", Environment.currentDirectory);
	}
	
	@Test
	public void testCdDoublePipeCdMiddle() throws AbstractApplicationException, ShellException {
		// Negative test case: while cd does not throw exception, the contents of stdout in first app do not get transferred to stdin of third app
		ShellImpl shell = new ShellImpl();
		String args = "cat test.txt | cd | wc -m -l";
		shell.parseAndEvaluate(args, os);
		assertEquals("0 0 \n", os.toString());
	}
	
	

}
