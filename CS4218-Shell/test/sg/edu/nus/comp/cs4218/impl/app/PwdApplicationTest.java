package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.PwdApplication;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.PwdException;
import sg.edu.nus.comp.cs4218.exception.ShellException;

public class PwdApplicationTest {
	static PwdApplication pwdApp;
	static InputStream is;
	static OutputStream os;
	static String pwd;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pwdApp = new PwdApplication();
		is = null;
		os = new ByteArrayOutputStream();
		pwd = Environment.currentDirectory; // for backup
	}

	@Before
	public void setUp() throws Exception {
		Environment.currentDirectory = pwd; // reset
		is = null;
		os = new ByteArrayOutputStream();
	}

	@Test
	public void testPwdBasic() {
		String args[] = {};
		try {
			pwdApp.run(args, is, os);
		} catch (PwdException e) {

		}
		assertEquals(pwd + System.lineSeparator(), os.toString());

	}

	@Test
	public void testPwdWithAdditionalArgs() {
		String args[] = { "unnecessary args" };
		// expected behavior: pwd works as long as the command "pwd" is valid,
		// and does not care about args
		try {
			pwdApp.run(args, is, os);
		} catch (PwdException e) {

		}
		assertEquals(pwd + System.lineSeparator(), os.toString());
	}

	@Test
	public void testPwdWithNullArgs() {
		String args[] = null;
		// expected behavior: pwd works as long as the command "pwd" is valid,
		// and does not care about args
		try {
			pwdApp.run(args, is, os);
		} catch (PwdException e) {

		}
		assertEquals(pwd + System.lineSeparator(), os.toString());
	}

	@Test
	public void testPwdWithNullStdout() {
		String args[] = {};
		String expected = "pwd: Null Pointer Exception";
		Exception exc = new Exception();
		try {
			pwdApp.run(args, is, null);
		} catch (PwdException e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}

	// Integration test
	@Test
	public void testPwdCmdPipe() throws AbstractApplicationException, ShellException {
		Environment.currentDirectory = "CS4218_team15_2017\\CS4218-Shell"; // because
																			// pwd
																			// may
																			// differ
																			// for
																			// other
																			// computers
		ShellImpl shell = new ShellImpl();
		String args = "pwd | wc";
		shell.parseAndEvaluate(args, os);
		assertEquals("31 1 1 \n", os.toString());
	}

	@Test
	public void testPwdCmdSubstitutionWithCd() throws AbstractApplicationException, ShellException {
		// our implementation of cd does not support absolute path inputs
		// hence fails although this is a valid piping
		ShellImpl shell = new ShellImpl();
		String args = "cd `pwd`";
		shell.parseAndEvaluate(args, os);
		assertEquals(pwd, Environment.currentDirectory);
	}

}
