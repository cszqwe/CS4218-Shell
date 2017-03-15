package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.app.SortApplication;

public class SortApplicationTest {
	
	static SortApplication sortApp;
	static InputStream is;
	static OutputStream os;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sortApp = new SortApplication();
		is = null;
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
		// reset is and os
		is = null;
		os = new ByteArrayOutputStream();
	}

	@Test
	public void testSortBasic() {
		String[] args = {"sortTestBasic.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("1\n1--2\n10\n100\n102\n20\n3\nnu0\nnum1\nnum10\nnum2\n", os.toString());
	}

	@Test
	public void testSortBasicNumeric() {
		String[] args = {"-n", "sortTestBasic.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("1\n1--2\n3\n10\n20\n100\n102\nnu0\nnum1\nnum10\nnum2\n", os.toString());
	}

	@Test
	public void testSortUnchanged() {
		String[] args = {"test.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("line 1\nline 2\nline 3\nline 4\n", os.toString());
	}

	@Test
	public void testSortUnchangedNumeric() {
		String[] args = {"-n", "test.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("line 1\nline 2\nline 3\nline 4\n", os.toString());
	}

	@Test
	public void testSortNumStart() {
		String[] args = {"sortTest_numstart.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("1st\n20th\n2nd\n", os.toString());
	}

	@Test
	public void testSortNumStartNumeric() {
		String[] args = {"-n", "sortTest_numstart.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("1st\n2nd\n20th\n", os.toString());
	}
	
	@Test
	public void testSortComplex() {
		String[] args = {"sortTest Complex.txt"};
		String expected = "\n" +
				"\n" +
				"\n" + 
				" \n" +
				"   \n" +
				"0021\n" +
				"02!\n" + 
				"2 lines have spaces\n" +
				"20000@_@\n" +
				"21>x<\n" +
				"2103\n" +
				"2103'Y'\n" +
				"2103T\n" +
				"2103TEST\n" +
				"230\n" +
				"CS21\n" +
				"CS2103\n" +
				"CS2103T\n" +
				"CS2103TEST\n" +
				"CS230\n" +
				"REMEMBER SPACES\n" +
				"one one, one three\n" +
				"remember spaces\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortComplexNumeric() {
		String[] args = {"-n", "sortTest Complex.txt"};
		String expected = "\n" +
				"\n" +
				"\n" + 
				" \n" +
				"   \n" +
				"2 lines have spaces\n" +
				"02!\n" + 
				"0021\n" +
				"21>x<\n" +
				"230\n" +
				"2103\n" +
				"2103'Y'\n" +
				"2103T\n" +
				"2103TEST\n" +
				"20000@_@\n" +
				"CS21\n" +
				"CS2103\n" +
				"CS2103T\n" +
				"CS2103TEST\n" +
				"CS230\n" +
				"REMEMBER SPACES\n" +
				"one one, one three\n" +
				"remember spaces\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortMultipleFiles() {
		String[] args = {"test.txt", "sortTest_numstart.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("1st\n20th\n2nd\nline 1\nline 2\nline 3\nline 4\n", os.toString());
	}
	
	@Test
	public void testSortMultipleFilesNumeric() {
		String[] args = {"-n", "test.txt", "sortTest_numstart.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("1st\n2nd\n20th\nline 1\nline 2\nline 3\nline 4\n", os.toString());
	}
	
	@Test
	public void testSortInvalidFile() {
		String[] args = {"invalid.txt"};
		String expected = "sort: cannot read: invalid.txt: No such file";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}

	@Test
	public void testSortInvalidFileNumeric() {
		String[] args ={"-n", "invalid.txt"};
		String expected = "sort: cannot read: invalid.txt: No such file";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortFilesInvalidInvalid() {
		String[] args = {"invalid.txt", "wrong.txt"};
		String expected = "sort: cannot read: invalid.txt: No such file";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortFilesInvalidValid() {
		String[] args = {"invalid.txt", "sortTestBasic.txt"};
		String expected = "sort: cannot read: invalid.txt: No such file";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortFilesValidInvalid() {
		String[] args = {"sortTestBasic.txt", "invalid.txt"};
		String expected = "sort: cannot read: invalid.txt: No such file";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortInvalidOption() {
		String[] args = {"-x", "sortTest_numstart.txt"};
		String expected = "sort: invalid option -- 'x'";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortOptionInvalidValid() {
		String[] args = {"-qn", "sortTestBasic.txt"};
		String expected = "sort: invalid option -- 'q'";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortOptionValidInvalid() {
		String[] args = {"-nl", "test2.txt"};
		String expected = "sort: invalid option -- 'l'";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortInvalidOptionInvalidFile() {
		String[] args = {"-q", "inval"};
		String expected = "sort: invalid option -- 'q'";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortInvalidOptionsInvalidFile() {
		String[] args = {"-ln", "wrong.txt"};
		String expected = "sort: invalid option -- 'l'";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortInvalidOptionsFileValidInvalid() {
		String[] args = {"-nx", "test2.txt", "asdf.tx"};
		String expected = "sort: invalid option -- 'x'";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortInvalidOptionFileValidInvalid() {
		String[] args = {"-x", "n", "test2.txt"};
		String expected = "sort: invalid option -- 'x'";
		Exception exp = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testSortStdin() throws FileNotFoundException {
		String[] args = {};
		is = new FileInputStream("sortTestBasic.txt");
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("1\n1--2\n10\n100\n102\n20\n3\nnu0\nnum1\nnum10\nnum2\n", os.toString());
	}
	
	@Test
	public void testSortStdinNumeric() throws FileNotFoundException {
		String[] args = {"-n"};
		is = new FileInputStream("sortTestBasic.txt");
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("1\n1--2\n3\n10\n20\n100\n102\nnu0\nnum1\nnum10\nnum2\n", os.toString());
	}
	
	@Test
	public void testSortStdinAndFiles() throws FileNotFoundException {
		String[] args = {"-n", "sortTestBasic.txt"};
		is = new FileInputStream("test.txt");
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("1\n1--2\n3\n10\n20\n100\n102\nnu0\nnum1\nnum10\nnum2\n", os.toString());
	}

}
