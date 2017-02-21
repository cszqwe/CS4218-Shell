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

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPwd() {
		
		String CURRENTDIR = "Workspace\\CS4218-Shell";
		Environment.currentDirectory = CURRENTDIR;
		InputStream is = null;
		OutputStream os = new ByteArrayOutputStream();
		PwdApplication pwdApp = new PwdApplication();
		String args[] = {};
		try {
			pwdApp.run(args, is, os);
			String output = os.toString();
			assertEquals(output, CURRENTDIR);
			
		} catch (AbstractApplicationException e) {
			
		}
	}

}
