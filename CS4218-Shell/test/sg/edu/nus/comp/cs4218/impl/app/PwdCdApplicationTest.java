package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.Environment;

/**
 * 
 * This test is to test how apps pwd and cd work together
 * since there is little to test with pwd alone
 * Since pwd and cd are already tested in respective tests,
 * this test focuses on pwd and cd interaction
 *
 */
public class PwdCdApplicationTest {
	static CdApplication cdApp;
	static PwdApplication pwdApp;
	static String currentDir; // for comparison and assertion
	static String pwdBackup; // backup
	static InputStream is;
	static OutputStream os;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cdApp = new CdApplication();
		pwdApp = new PwdApplication();
		
		is = null;
		os = new ByteArrayOutputStream();
		pwdBackup = Environment.currentDirectory;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Environment.currentDirectory = pwdBackup; // reset, to prevent affecting other test suites
	}

	@Before
	public void setUp() throws Exception {
		currentDir = pwdBackup;
	}

	@After
	public void tearDown() throws Exception {
		Environment.currentDirectory = pwdBackup; // reset current dir
	}

	@Test
	public void testPwdCdBasic() {
		String[] args1 = {};
		try {
			pwdApp.run(args1, is, os);
			
		} catch (Exception e) {
			
		}
		assertEquals(currentDir, os.toString());
		os = new ByteArrayOutputStream();
		
		String[] args2 = {"cd Test_folder"};
		try {
			cdApp.run(args2, is, os);
		} catch (Exception e) {
			
		}
		System.out.println(Environment.currentDirectory);
		assertEquals(pwdBackup + "\\cd Test_folder", Environment.currentDirectory);
		os = new ByteArrayOutputStream();
		
		try {
			pwdApp.run(args1, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(pwdBackup + "\\cd Test_folder", os.toString());
		os = new ByteArrayOutputStream();
	}

}
