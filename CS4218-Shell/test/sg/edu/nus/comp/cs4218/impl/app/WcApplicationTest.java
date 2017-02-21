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
		String[] args = {"fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcEmptyFile() {
		String[] args = {"fileTestEmpty.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("0 0 0 fileTestEmpty.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcOnlyNewlines() {
		String[] args = {"fileTest only newlines.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("8 0 5 fileTest only newlines.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcOnlySpaces() {
		String[] args = {"fileTest_onlyspaces.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("5 0 1 fileTest_onlyspaces.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountChars() {
		String[] args = {"-m", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountWords() {
		String[] args = {"-w", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("181 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountLines() {
		String[] args = {"-l", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountTwoOptions() {
		String[] args = {"-m", "-w", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-w", "-l", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-m", "-l", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountTwoOptionsReverse() {
		String[] args = {"-w", "-m", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-l", "-w", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-l", "-m", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountWordsChars() { // in different orders of input
		String[] args = {"-w", "-m", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcAllOptions() {
		String[] args = {"-m", "-w", "-l", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcAllOptionsDiffOrder() {
		String[] args = {"-w", "-l", "-m", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-l", "-m", "-w", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-l", "-w", "-m", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcOptionsOverlapping() {
		String[] args = {"-m", "-m", "-l", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-w", "-l", "-w", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-w", "-w", "-w", "-w", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("181 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcMulticharOptionsBasic() {
		String[] args = {"-mw", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-wl", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-ml", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-lm", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-www", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("181 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-mwl", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-wlm", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcMulticharOptionsOverlapping() {
		String[] args = {"-m", "-wm", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-ml", "-mm", "-l", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-wwm", "-mllm", "-w", "-wl", "fileTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testWcMultipleFiles() {
		String[] args = {"fileTest.txt", "test2.txt"};
		try {
			wcApp.run(args, is, os);
			assertEquals("978 181 32 fileTest.txt\n97 24 12 test2.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testWcOptionsMultipleFiles() {
		String[] args = {"-m", "fileTest.txt", "test2.txt"};
		try {
			wcApp.run(args, is, os);
			assertEquals("978 fileTest.txt\n97 test2.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-ml", "fileTest.txt", "test.txt"};
		try {
			wcApp.run(args, is, os);
			assertEquals("978 32 fileTest.txt\n30 4 test.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-l", "-wl", "test2.txt", "fileTest.txt"};
		try {
			wcApp.run(args, is, os);
			assertEquals("24 12 test2.txt\n181 32 fileTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testWcInvalidFile() {
		String[] args = {"asdf.txt"};
		try {
			wcApp.run(args, is, os);
			assertEquals("wc: asdf.txt: No such file\n", os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testWcMultipleFilesInvalidFile() {
		String[] args = {"asdf.txt", "querty.txt", "montypython"};
		try {
			wcApp.run(args, is, os);
			assertEquals("wc: asdf.txt: No such file\nwc: querty.txt: No such file\nwc: montypython: No such file\n", os.toString());
		} catch (Exception e) {
			
		}
		
		args[0] = "fileTest.txt"; args[1] = "m"; args[2] = "fileTest_onlyspaces.txt";
		os = new ByteArrayOutputStream();
		try {
			wcApp.run(args, is, os);
			assertEquals("978 181 32 fileTest.txt\nwc: m: No such file\n5 0 1 fileTest_onlyspaces.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testWcOptionsInvalid() {
		String[] args = {"-s", "fileTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- s", e.getMessage());
		}
		
		args = new String[]{"-a", "-m", "fileTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
		
		args = new String[]{"-m", "-t", "fileTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- t", e.getMessage());
		}
		
		args = new String[]{"-m", "-a", "-s", "-m", "fileTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
	}
	
	@Test
	public void testWcMulticharOptionsInvalid() {
		String[] args = {"-asd", "fileTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
		
		args = new String[]{"-maw", "-lst", "fileTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
		
		args = new String[]{"-ml", "-lw", "-tasd", "fileTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- t", e.getMessage());
		}
	}
	
	@Test
	public void testWcInvalidOptionsMultipleFiles() {
		String[] args = {"-a", "fileTest.txt", "test2.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
		
		args = new String[]{"-lam", "fileTest.txt", "test2.txt", "test.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
	}
	
	@Test
	public void testWcInvalidOptionsInvalidFiles() {
		String[] args = {"-x", "test2"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- x", e.getMessage());
		}
		
		args = new String[]{"-lm", "-t", "m", "test.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- t", e.getMessage());
		}
		
		args = new String[]{"-z", "testfile", "fileTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- z", e.getMessage());
		}
		
		args = new String[]{"-m", "-wl", "-r", "fileTest.txt", "wrongtest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- r", e.getMessage());
		}
	}

}
