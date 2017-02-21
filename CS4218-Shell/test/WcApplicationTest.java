import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.app.WcApplication;
import sg.edu.nus.comp.cs4218.exception.WcException;

public class WcApplicationTest {
	
	static WcApplication wcApp;
	static InputStream is;
	static OutputStream os;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		wcApp = new WcApplication();
		os = new ByteArrayOutputStream();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		os = new ByteArrayOutputStream();
	}

	@Test
	public void testWc() {
		String[] args = {"test2.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("87 24 12 test2.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcEmptyFile() {
		String[] args = {"wcTestEmpty.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("0 0 0 wcTestEmpty.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcOnlyNewlines() {
		String[] args = {"wcTest only newlines.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("5 0 5 wcTest only newlines.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcOnlySpaces() {
		String[] args = {"wcTest_onlyspaces.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("6 0 1 wcTest_onlyspaces.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

}
