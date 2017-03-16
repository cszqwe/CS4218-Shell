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
import sg.edu.nus.comp.cs4218.impl.app.PwdApplication;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;

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

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		is = null;
		os = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPwdBasic() {
		String args[] = {};
		try {
			pwdApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(pwd, os.toString());
		
	}
	
	@Test
	public void testPwdWithAdditionalArgs() {
		String args[] = {"unnecessary args"};
		// expected behavior: pwd works as long as the command "pwd" is valid, and does not care about args
		try {
			pwdApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(pwd, os.toString());
	}
	
	@Test
	public void testPwdWithNullArgs() {
		String args[] = null;
		// expected behavior: pwd works as long as the command "pwd" is valid, and does not care about args
		try {
			pwdApp.run(args, is, os);
		} catch (AbstractApplicationException e) {
			
		}
		assertEquals(pwd, os.toString());
	}

}
