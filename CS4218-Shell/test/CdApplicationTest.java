import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.impl.app.CdApplication;

public class CdApplicationTest {
	static String origPwd;
	static CdApplication cdApp;
	static OutputStream os;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cdApp = new CdApplication();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		origPwd = Environment.currentDirectory;
		os = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		Environment.currentDirectory = origPwd;
	}

	@Test
	public void testCd() {
		String args[] = {"cd Test_folder"};
		try {
			cdApp.run(args, null, os);
			assertEquals(origPwd + "\\cd Test_folder", Environment.currentDirectory);
		} catch (AbstractApplicationException e) {
			
		}
		
	}
	
	@Test
	public void testCdNonexistentDir() {
		String args[] = {"nonexistent"};
		try {
			cdApp.run(args, null, null);
			assertEquals(origPwd, Environment.currentDirectory);
			
		} catch (AbstractApplicationException e) {
			
		}
	}
	
	@Test
	public void testCdNestedDir() {
		String args[] = {"cd Test_folder\\nested-folder"};
		try {
			cdApp.run(args, null, os);
			assertEquals(origPwd + "\\cd Test_folder\\nested-folder", Environment.currentDirectory);
		} catch (AbstractApplicationException e) {
			
		}
	}
	
	@Test
	public void testMultipleArgsValid() {
		String args[] = {"cd Test_folder", "asdf"};
		try {
			cdApp.run(args, null, os);
			assertEquals(origPwd + "\\cd Test_folder", Environment.currentDirectory);
		} catch (AbstractApplicationException e) {
			
		}
	}
	
	@Test
	public void testMultipleArgsNonexistent() {
		String args[] = {"Nonexistent", "asdf"};
		try {
			cdApp.run(args, null, os);
			assertEquals(origPwd, Environment.currentDirectory);
		} catch (AbstractApplicationException e) {
			
		}
	}

}
