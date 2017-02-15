import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.HeadException;
import sg.edu.nus.comp.cs4218.impl.app.HeadApplication;

public class HeadApplicationTest {
	static HeadApplication headApp;
	static InputStream is;
	static OutputStream os;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		headApp = new HeadApplication();
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
	public void testHeadOptionAndFile() {
		String args[] = "-n 3 test.txt".split(" ");
		String expected ="line 1\nline 2\nline 3\n";
		try {
			headApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testHeadOptionExceedAndFile() {
		String args[] = "-n 1000 test.txt".split(" ");
		// 4 lines in test.txt, should print all 4 lines
		String expected ="line 1\nline 2\nline 3\nline 4\n";
		try {
			headApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testHeadFileOnly() {
		String args[] = "test2.txt".split(" ");
		// 12 lines in test2.txt, should only print 10 lines
		String expected ="line 1\nline 2\nline 3\nline 4\nline 5\nline 6\nline 7\nline 8\nline 9\nline 10\n";
		try {
			headApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// What's wrong with this?
	@Test
	public void testHeadEmptyInput() {
		String args[] = "".split(" ");
		// Should  throw HeadException
		String expected ="line 1\nline 2\nline 3\nline 4\nline 5\nline 6\nline 7\nline 8\nline 9\nline 10\n";
		try {
			headApp.run(args, is, os);
			String output = os.toString();

		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			assertEquals("head: File not found", e.getMessage());
		}
	}

}
