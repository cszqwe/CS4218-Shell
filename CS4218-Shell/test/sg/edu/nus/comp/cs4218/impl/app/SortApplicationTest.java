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
	}

	@Test
	public void testSortBasic() {
		String[] args = {"sortTestBasic.txt"};
		try {
			sortApp.run(args, is, os);
			assertEquals("1\n10\n100\n102\n1--2\n20\n3\nnu0\nnum1\nnum10\nnum2\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSortBasicNumeric() {
		String[] args = {"-n", "sortTestBasic.txt"};
		try {
			sortApp.run(args, is, os);
			assertEquals("1\n1--2\n3\n10\n20\n100\n102\nnu0\nnum1\nnum10\nnum2\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSortUnchanged() {
		String[] args = {"test.txt"};
		try {
			sortApp.run(args, is, os);
			assertEquals("Line 1\nLine 2\nLine 3\nLine 4\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSortUnchangedNumeric() {
		String[] args = {"-n", "test.txt"};
		try {
			sortApp.run(args, is, os);
			assertEquals("Line 1\nLine 2\nLine 3\nLine 4\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSortNumStart() {
		String[] args = {"sortTest_numstart.txt"};
		try {
			sortApp.run(args, is, os);
			assertEquals("1st\n20th\n2nd\n", os.toString());
		} catch (Exception e) {
			
		}
	}

	@Test
	public void testSortNumStartNumeric() {
		String[] args = {"-n", "sortTest_numstart.txt"};
		try {
			sortApp.run(args, is, os);
			assertEquals("1st\n2nd\n20th\n", os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSortComplex() {
		String[] args = {"sortTest2.txt"};
		String expected = "\n\n\n \n   \n0021\n02!\n20000@_@\n2103\n2103T\n2103TEST\n2103'Y'\n21>x<\n230\n2 lines have spaces\n"
				+ "CS21\nCS2103\nCS2103T\nCS2103TEST\nCS230\none one, one three\nremember spaces\nREMEMBER SPACES";
		try {
			sortApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSortComplexNumeric() {
		String[] args = {"sortTest2.txt"};
		String expected = "\n\n\n \n   \n02!\n2 lines have spaces\n0021\n21>x<\n230\n2103\2103T\n2103TEST\n2103'Y'\n20000@_@\n"
				+ "CS21\nCS2103\nCS2103T\nCS210eTEST\nCS230\none one, one three\nremember spaces\nREMEMBER SPACES";
		try {
			sortApp.run(args, is, os);
			assertEquals(expected, os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSortMultipleFiles() {
		String[] args = {"test.txt", "sortTest_numstart.txt"};
		try {
			sortApp.run(args, is, os);
			assertEquals("1st\n20th\n2nd\nline1\nline2\nline3\nline4\n", os.toString());
		} catch (Exception e) {
			
		}
		
		os = new ByteArrayOutputStream();
		args = new String[]{"-n", "test.txt", "sortTest_numstart.txt"};
		try {
			sortApp.run(args, is, os);
			assertEquals("1st\n2nd\n20th\nline1\nline2\nline3\nline4\n", os.toString());
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testSortInvalidFile() {
		String[] args = {"invalid.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: cannot read: invalid.txt: No such file", e.getMessage());
		}

		args = new String[]{"-n", "invalid.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: cannot read: invalid.txt: No such file", e.getMessage());
		}
	}
	
	@Test
	public void testSortInvalidMultipleFiles() {
		String[] args = {"invalid.txt", "wrong.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: cannot read: invalid.txt: No such file", e.getMessage());
		}

		args = new String[]{"invalid.txt", "sortTestBasic.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: cannot read: invalid.txt: No such file", e.getMessage());
		}

		args = new String[]{"sortTestBasic.txt", "invalid.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: cannot read: invalid.txt: No such file", e.getMessage());
		}
	}
	
	@Test
	public void testSortInvalidOptions() {
		String[] args = {"-x", "sortTest_numstart.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: invalid option -- 'x'", e.getMessage());
		}

		args = new String[]{"-qn", "sortTestBasic.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: invalid option -- 'q'", e.getMessage());
		}

		args = new String[]{"-nl", "test2.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: invalid option -- 'l'", e.getMessage());
		}
	}
	
	@Test
	public void testSortInvalidOptionsInvalidFiles() {
		String[] args = {"-q", "inval"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: invalid option -- 'q'", e.getMessage());
		}

		args = new String[]{"-ln", "wrong.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: invalid option -- 'l'", e.getMessage());
		}

		args = new String[]{"-nx", "test2.txt", "asdf.tx"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: invalid option -- 'x'", e.getMessage());
		}

		args = new String[]{"-x", "n", "test2.txt"};
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			assertEquals("sort: invalid option -- 'x'", e.getMessage());
		}
	}

}
