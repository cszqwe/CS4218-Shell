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
		String[] args = {"wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 wcTest.txt\n", os.toString());
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
			assertEquals("8 0 5 wcTest only newlines.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcOnlySpaces() {
		String[] args = {"wcTest_onlyspaces.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("5 0 1 wcTest_onlyspaces.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountChars() {
		String[] args = {"-m", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountWords() {
		String[] args = {"-w", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("181 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountLines() {
		String[] args = {"-l", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountTwoOptions() {
		String[] args = {"-m", "-w", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-w"; args[1] = "-l";
		try {
			wcApp.run(args, null, os);
			assertEquals("181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-m"; args[1] = "-l";
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountTwoOptionsReverse() {
		String[] args = {"-w", "-m", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-l"; args[1] = "-w";
		try {
			wcApp.run(args, null, os);
			assertEquals("181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-l"; args[1] = "-m";
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcCountWordsChars() { // in different orders of input
		String[] args = {"-w", "-m", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcAllOptions() {
		String[] args = {"-m", "-w", "-l", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcAllOptionsDiffOrder() {
		String[] args = {"-w", "-l", "-m", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-l"; args[1] = "-m"; args[2] = "-w";
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-l"; args[1] = "-w"; args[2] = "-m";
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcOptionsOverlapping() {
		String[] args = {"-m", "-m", "-l", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-w"; args[1] = "-l"; args[2] = "-w";
		try {
			wcApp.run(args, null, os);
			assertEquals("181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-w"; args[1] = "-w"; args[2] = "-w";
		try {
			wcApp.run(args, null, os);
			assertEquals("181 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcMulticharOptionsBasic() {
		String[] args = {"-mw", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-wl";
		try {
			wcApp.run(args, null, os);
			assertEquals("181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-ml";
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-lm";
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-www";
		try {
			wcApp.run(args, null, os);
			assertEquals("181 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-mwl";
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-wlm";
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testWcMulticharOptionsOverlapping() {
		String[] args = {"-m", "-wm", "wcTest.txt"};
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-ml"; args[1] = "-mm";
		try {
			wcApp.run(args, null, os);
			assertEquals("978 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args[0] = "-wwm"; args[1] = "-mllm";
		try {
			wcApp.run(args, null, os);
			assertEquals("978 181 32 wcTest.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testWcMultipleFiles() {
		String[] args = {"wcTest.txt", "test2.txt"};
		try {
			wcApp.run(args, is, os);
			assertEquals("978 181 32 wcTest.txt\n97 24 12 test2.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testWcOptionsMultipleFiles() {
		String[] args = {"-m", "wcTest.txt", "test2.txt"};
		try {
			wcApp.run(args, is, os);
			assertEquals("978 wcTest.txt\n97 test2.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-ml", "wcTest.txt", "test.txt"};
		try {
			wcApp.run(args, is, os);
			assertEquals("978 32 wcTest.txt\n30 4 test.txt\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-l", "-wl", "test2.txt", "wcTest.txt"};
		try {
			wcApp.run(args, is, os);
			assertEquals("24 12 test2.txt\n181 32 wcTest.txt\n", os.toString());
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
		
		args[0] = "wcTest.txt"; args[1] = "m"; args[2] = "wcTest_onlyspaces.txt";
		os = new ByteArrayOutputStream();
		try {
			wcApp.run(args, is, os);
			assertEquals("978 181 32 wcTest.txt\nwc: m: No such file\n5 0 1 wcTest_onlyspaces.txt\n", os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testWcOptionsInvalid() {
		String[] args = {"-s", "wcTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- s", e.getMessage());
		}
		
		args = new String[]{"-a", "-m", "wcTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
		
		args = new String[]{"-m", "-t", "wcTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- t", e.getMessage());
		}
		
		args = new String[]{"-m", "-a", "-s", "-m", "wcTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
	}
	
	@Test
	public void testWcMulticharOptionsInvalid() {
		String[] args = {"-asd", "wcTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
		
		args = new String[]{"-maw", "-lst", "wcTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
		
		args = new String[]{"-ml", "-lw", "-tasd", "wcTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- t", e.getMessage());
		}
	}
	
	@Test
	public void testWcInvalidOptionsMultipleFiles() {
		String[] args = {"-a", "wcTest.txt", "test2.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- a", e.getMessage());
		}
		
		args = new String[]{"-lam", "wcTest.txt", "test2.txt", "test.txt"};
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
		
		args = new String[]{"-z", "testfile", "wcTest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- z", e.getMessage());
		}
		
		args = new String[]{"-m", "-wl", "-r", "wcTest.txt", "wrongtest.txt"};
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("wc: invalid option -- r", e.getMessage());
		}
	}

}
