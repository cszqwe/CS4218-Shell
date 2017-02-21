package sg.edu.nus.comp.cs4218.impl.app;
import static org.junit.Assert.*;

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
			assertEquals(origPwd + "\\cd Test_folder", Environment.currentDirectory);
		} catch (AbstractApplicationException e) {
			
		}
		
	}
	
	@Test
	public void testCdInvalidDir() {
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
			cdApp.run(args, null, null);
			assertEquals(origPwd + "\\cd Test_folder\\nested-folder", Environment.currentDirectory);
		} catch (AbstractApplicationException e) {
			
		}
	}
	
	@Test
	public void testMultipleArgsValid() {
		String args[] = {"cd Test_folder", "asdf"};
		try {
			cdApp.run(args, null, null);
			assertEquals(origPwd + "\\cd Test_folder", Environment.currentDirectory);
		} catch (AbstractApplicationException e) {
			
		}
	}
	
	@Test
	public void testMultipleArgsInvalid() {
		String args[] = {"Nonexistent", "asdf"};
		try {
			cdApp.run(args, null, null);
			assertEquals(origPwd, Environment.currentDirectory);
		} catch (AbstractApplicationException e) {
			
		}
	}
	
	@Test
	public void testFileNotDir() {
		String args[] = {"Test.txt"};
		try {
			cdApp.run(args, null, null);
			assertEquals(origPwd, Environment.currentDirectory);
		} catch (AbstractApplicationException e) {
			
		}
	}

}
