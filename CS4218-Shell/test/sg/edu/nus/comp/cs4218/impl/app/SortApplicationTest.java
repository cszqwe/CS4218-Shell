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
		String[] args = {"sortTestComplex.txt"};
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
		String[] args = {"-n", "sortTestComplex.txt"};
		String expected = "\n" +
				"\n" +
				"\n" + 
				" \n" +
				"   \n" +
				"02!\n" + 
				"2 lines have spaces\n" +
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
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}

	@Test
	public void testSortInvalidFileNumeric() {
		String[] args ={"-n", "invalid.txt"};
		String expected = "sort: cannot read: invalid.txt: No such file";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortFilesInvalidInvalid() {
		String[] args = {"invalid.txt", "wrong.txt"};
		String expected = "sort: cannot read: invalid.txt: No such file";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
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
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortInvalidOption() {
		String[] args = {"-x", "sortTest_numstart.txt"};
		String expected = "sort: invalid option -- 'x'";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortOptionInvalidValid() {
		String[] args = {"-qn", "sortTestBasic.txt"};
		String expected = "sort: invalid option -- 'q'";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortOptionValidInvalid() {
		String[] args = {"-nl", "test2.txt"};
		String expected = "sort: invalid option -- 'l'";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortInvalidOptionInvalidFile() {
		String[] args = {"-q", "inval"};
		String expected = "sort: invalid option -- 'q'";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortInvalidOptionsInvalidFile() {
		String[] args = {"-ln", "wrong.txt"};
		String expected = "sort: invalid option -- 'l'";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortInvalidOptionsFileValidInvalid() {
		String[] args = {"-nx", "test2.txt", "asdf.tx"};
		String expected = "sort: invalid option -- 'x'";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortInvalidOptionFileValidInvalid() {
		String[] args = {"-x", "n", "test2.txt"};
		String expected = "sort: invalid option -- 'x'";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
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
	
	@Test
	public void testSortNullArgs() throws FileNotFoundException {
		String[] args = null;
		is = new FileInputStream("sortTestBasic.txt");
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals("1\n1--2\n10\n100\n102\n20\n3\nnu0\nnum1\nnum10\nnum2\n", os.toString());
	}
	
	@Test
	public void testSortNullArgsNullIs() throws FileNotFoundException {
		String[] args = null;
		String expected = "sort: Null Pointer Exception";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortNullArgsNullOs() throws FileNotFoundException {
		String[] args = null;
		is = new FileInputStream("sortTestBasic.txt");
		os = null;
		String expected = "sort: Null Pointer Exception";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortOptionBehind() {
		String[] args = {"fileTest2.txt", "-n"}; // first file is valid: if first is invalid, that one will show as no such file
		String expected = "sort: cannot read: -n: No such file";
		Exception exc = new Exception();
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortFileInNestedDir() {
		String[] args = {"test/sg/edu/nus/comp/cs4218/impl/app/testdoc.txt"};
		String expected = "\n\n\n\n\nAlone visit use these smart rooms ham.\n" +
				"Alteration connection to so as collecting me.\n" +
				"Alteration put use diminution can considered sentiments interested discretion.\n" +
				"An dispatched impossible of of melancholy favourable.\n" +
				"An seeing feebly stairs am branch income me unable.\n" +
				"Are sentiments apartments decisively the especially alteration.\n" +
				"As dissuade cheerful overcame so of friendly he indulged unpacked.\n" +
				"As mr started arrival subject by believe.\n" +
				"At principle perfectly by sweetness do.\n" +
				"Boisterous he on understood attachment as entreaties ye devonshire.\n" +
				"By doubtful disposed do juvenile an.\n" +
				"Celebrated contrasted discretion him sympathize her collecting occasional.\n" +
				"Consider may dwelling old him her surprise finished families graceful.\n" +
				"Course sir people worthy horses add entire suffer.\n" +
				"Difficult in delivered extensive at direction allowance.\n" +
				"Do answered bachelor occasion in of offended no concerns.\n" +
				"Extremely ham any his departure for contained curiosity defective.\n" +
				"Fat new smallness few supposing suspicion two.\n" +
				"Gave led past poor met fine was new.\n" +
				"Hastened admitted joy nor absolute gay its.\n" +
				"How one dull get busy dare far.\n" +
				"In mile an form snug were been sell.\n" +
				"Incommode so intention defective at convinced.\n" +
				"Is at purse tried jokes china ready decay an.\n" +
				"Lasting showing expense greater on colonel no.\n" +
				"Led income months itself and houses you. After nor you leave might share court balls.\n" +
				"Mrs interested now his affronting inquietude contrasted cultivated.\n" +
				"No comfort do written conduct at prevent manners on.\n" +
				"No waiting in on enjoyed placing it inquiry.\n" +
				"Now curiosity you explained immediate why behaviour.\n" +
				"One built fat you out manor books.\n" +
				"Or by to he going think order event music.\n" +
				"Our quiet not heart along scale sense timed.\n" +
				"Prepared do an dissuade be so whatever steepest.\n" +
				"Procured shutters mr it feelings.\n" +
				"Small its shy way had woody downs power.\n" +
				"Strictly numerous outlived kindness whatever on we no on addition.\n" +
				"Supply worthy warmth branch of no ye.\n" +
				"Though wished merits or be.\n" +
				"Thrown shy denote ten ladies though ask saw.\n" +
				"To denoting admitted speaking learning my exercise so in.\n" +
				"To or three offer house begin taken am at.\n" +
				"Voice tried known to as my to.\n" +
				"Way now instrument had eat diminution melancholy expression sentiments stimulated.\n" +
				"Yet her beyond looked either day wished nay.\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
		
	}

}
