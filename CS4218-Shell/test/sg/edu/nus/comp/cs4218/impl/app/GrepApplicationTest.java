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

import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;

public class GrepApplicationTest {
	
	static GrepApplication grepApp;
	static InputStream is;
	static OutputStream os;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		grepApp = new GrepApplication();
		is = null;
		os = new ByteArrayOutputStream();
	}

	@Test
	public void testGrepBasic() {
		String[] args = {"10", "sortTestBasic.txt"};
		String expected = "10\n100\n102\nnum10\n";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepNotFound() {
		String[] args = {"string", "test2.txt"};
		String expected = "";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepCase() {
		String[] args = {"line", "test2.txt"};
		String expected = "line 1\nline 2\nline 3\nline 4\nline 5\nline 6\nline 7\nline 8\nline 9\nline 10\nline 11\nline 12\n";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepCaseNotFound() {
		String[] args = {"Line", "test2.txt"};
		String expected = "";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString().trim());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepMultipleFiles() {
		String[] args = {"1", "test.txt", "test2.txt"};
		String expected = "line 1\nline 1\nline 10\nline 11\nline 12\n";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepMultipleFilesOneNotFound() {
		String[] args = {"12", "test2.txt", "test.txt"};
		String expected = "line 12\n";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepMultipleFilesNotFound() {
		String[] args = {"asdf", "test2.txt", "sortTestBasic.txt"};
		String expected = "";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepRegexFindMultipleSpaces() {
		String[] args = {"\\s\\s*", "sortTestComplex.txt"};
		String expected = "one one, one three\n \nremember spaces\nREMEMBER SPACES\n2 lines have spaces\n   \n";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepInvalidFile() {
		String[] args = {"line", "tes.txt"};
		String expected = "grep: tes.txt: No such file\n";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepInvalidFiles() {
		String[] args = {"line", "test.txt", "asdf.txt"};
		String expected = "line 1\nline 2\nline 3\nline 4\ngrep: asdf.txt: No such file\n";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepInvalidValidFiles() {
		String[] args = {"1", "qwerty.txt", "test2.txt"};
		String expected = "grep: qwerty.txt: No such file\nline 1\nline 10\nline 11\nline 12\n";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testGrepInvalidValidInvalidFiles() {
		String[] args = {"1", "invalid", "test2.txt", "asdf.txt"};
		String expected = "grep: invalid: No such file\nline 1\nline 10\nline 11\nline 12\ngrep: asdf.txt: No such file\n";
		os = new ByteArrayOutputStream();
		try {
			grepApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			assertEquals("grep: invalid: No such file\ngrep: asdf.txt: No such file", e.getMessage());
		}
	}
}
