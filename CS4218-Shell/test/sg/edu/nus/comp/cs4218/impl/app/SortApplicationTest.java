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
	
	@Test
	public void testSortIsFailure() {
		String[] args = {};
		String expected = "sort: InputStream failed";
		Exception exc = new Exception();
		try {
			is = new FileInputStream("sortTestBasic.txt");
			is.close();
			sortApp.run(args, is, os);
		} catch (Exception e) {
			exc = e;
		}
		assertEquals(expected, exc.getMessage());
	}
	
	@Test
	public void testSortOsFailure() {
		String[] args = {};
		String expected = "sort: Output stream not working";
		Exception exc = new Exception();
		try {
			is = new FileInputStream("sortTestBasic.txt");
			os.close();
			sortApp.run(args, is, os);
			System.out.println("passes");
		} catch (Exception e) {
			exc = e;
			System.out.println(e);
		}
		assertEquals(expected, exc.getMessage());
	}
	
	// tests for each combination of char types
	// same files and expected results as those in interface unit test
	
	@Test
	public void testSortStringsSimple() {
		String[] args = {"sortAppTestSimple.txt"};
		String expected = "a\naaa\naaaa\nabc\nbaaa\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleNumerically() {
		String[] args = {"-n", "sortAppTestSimple.txt"};
		String expected = "a\naaa\naaaa\nabc\nbaaa\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsCapital() {
		String[] args = {"sortAppTestCapital.txt"};
		String expected = "BATMAN\nSEVENELEVEN\nSUPER\nSUPERMAN\nSUPERMARKET\nSUPPER\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsCapitalNumerically() {
		String[] args = {"-n", "sortAppTestCapital.txt"};
		String expected = "BATMAN\nSEVENELEVEN\nSUPER\nSUPERMAN\nSUPERMARKET\nSUPPER\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsNumbers() {
		String[] args = {"sortAppTestNumbers.txt"};
		String expected = "001\n01\n1\n10\n2\n210\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsNumbersNumerically() {
		String[] args = {"-n", "sortAppTestNumbers.txt"};
		String expected = "001\n01\n1\n2\n10\n210\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSpecialChars() {
		String[] args = {"sortAppTestSpecialChars.txt"};
		String expected = "\n \n  \n!?@#>\n%)*^%\n%^!\n??\n????\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSpecialCharsNumerically() {
		String[] args = {"-n", "sortAppTestSpecialChars.txt"};
		String expected = "\n \n  \n!?@#>\n%)*^%\n%^!\n??\n????\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleCapital() {
		String[] args = {"sortAppTestSimpleCapital.txt"};
		String expected = "AA\nAAAA\nAAAAA\naBCd\naaaa\nbcD\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleCapitalNumerically() {
		String[] args = {"-n", "sortAppTestSimpleCapital.txt"};
		String expected = "AA\nAAAA\nAAAAA\naBCd\naaaa\nbcD\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleNumbers() {
		String[] args = {"sortAppTestSimpleNumbers.txt"};
		String expected = "03\n1\n10th\n1ine\n3rd\nline\nline1\nline10\nline3\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleNumbersNumerically() {
		String[] args = {"-n", "sortAppTestSimpleNumbers.txt"};
		String expected = "1\n1ine\n03\n3rd\n10th\nline\nline1\nline10\nline3\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleSpecialChars() {
		String[] args = {"sortAppTestSimpleSpecialChars.txt"};
		String expected = "\n \n? why\n?!%^@\nport^\nsore throat\nsort!\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleSpecialCharsNumerically() {
		String[] args = {"-n", "sortAppTestSimpleSpecialChars.txt"};
		String expected = "\n \n? why\n?!%^@\nport^\nsore throat\nsort!\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsCapitalNumbers() {
		String[] args = {"sortAppTestCapitalNumbers.txt"};
		String expected = "50\n500\n6X\n6X\nSTRING10\nSTRING5\nSTRING50\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsCapitalNumbersNumerically() {
		String[] args = {"-n", "sortAppTestCapitalNumbers.txt"};
		String expected = "6X\n6X\n50\n500\nSTRING10\nSTRING5\nSTRING50\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsCapitalSpecialChars() {
		String[] args = {"sortAppTestCapitalSpecialChars.txt"};
		String expected = "\n\n  \n!(@THIGH\n@THIGH\nTHIGH\nTHIS IS SPA\nTHIS IS SPARTA!\nTHIS-IS-MADNESS\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsCapitalSpecialCharsNumerically() {
		String[] args = {"-n", "sortAppTestCapitalSpecialChars.txt"};
		String expected = "\n\n  \n!(@THIGH\n@THIGH\nTHIGH\nTHIS IS SPA\nTHIS IS SPARTA!\nTHIS-IS-MADNESS\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsNumbersSpecialChars() {
		String[] args = {"sortAppTestNumbersSpecialChars.txt"};
		String expected = "$(@&34\n$(@&7\n<   >\n<-0->\n12$(@&34\n2$(@&34\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsNumbersSpecialCharsNumerically() {
		String[] args = {"-n", "sortAppTestNumbersSpecialChars.txt"};
		String expected = "$(@&34\n$(@&7\n<   >\n<-0->\n2$(@&34\n12$(@&34\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleCapitalNumbers() {
		String[] args = {"sortAppTestSimpleCapitalNumbers.txt"};
		String expected = "21stCentury\n30TH\n30th\n3rdPlace\nM24Chaffee\nM40Rifle\nM4Sherman\nm4\nmnm\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleCapitalNumbersNumerically() {
		String[] args = {"-n", "sortAppTestSimpleCapitalNumbers.txt"};
		String expected = "3rdPlace\n21stCentury\n30TH\n30th\nM24Chaffee\nM40Rifle\nM4Sherman\nm4\nmnm\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleCapitalSpecialChars() {
		String[] args = {"sortAppTestSimpleCapitalSpecialChars.txt"};
		String expected = " Hello, World!\n!! Hello\nHELLO WORLD\nHella lot of tests!\nHello World!\nHello World!\nHello World@!\nHello world!\nHello, World!\nHello,World!\nhello?\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleCapitalSpecialCharsNumerically() {
		String[] args = {"-n", "sortAppTestSimpleCapitalSpecialChars.txt"};
		String expected = " Hello, World!\n!! Hello\nHELLO WORLD\nHella lot of tests!\nHello World!\nHello World!\nHello World@!\nHello world!\nHello, World!\nHello,World!\nhello?\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleNumbersSpecialChars() {
		String[] args = {"sortAppTestSimpleNumbersSpecialChars.txt"};
		String expected = "!arg==\"-n\"\n&arg\n4=3\n40=3\n6=3\narg==\"-n\"\narg=3\nargs[40]\nargs[7]\narray.get(2)\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsSimpleNumbersSpecialCharsNumerically() {
		String[] args = {"-n", "sortAppTestSimpleNumbersSpecialChars.txt"};
		String expected = "!arg==\"-n\"\n&arg\n4=3\n6=3\n40=3\narg==\"-n\"\narg=3\nargs[40]\nargs[7]\narray.get(2)\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsCapitalNumbersSpecialChars() {
		String[] args = {"sortAppTestCapitalNumbersSpecialChars.txt"};
		String expected = "!0AA\n!AAA\n%AAA\n10AAA\n2A\n2AAA\n2BAA\nAAA10\nAAA2\nAAAA2\nAAB2\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsCapitalNumbersSpecialCharsNumerically() {
		String[] args = {"-n", "sortAppTestCapitalNumbersSpecialChars.txt"};
		String expected = "!0AA\n!AAA\n%AAA\n2A\n2AAA\n2BAA\n10AAA\nAAA10\nAAA2\nAAAA2\nAAB2\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsAll() {
		String[] args = {"sortAppTestAll.txt"};
		String expected = "\n  \n! hit\n!@#%\n//WARNING//\n/4run\n/ru28!*#\n/run script\n3 damage dealt\n30 damage dealt\n6 damage\n6 damage dealt\nGain 30 more exp\nGain0205's message:\nGained [30] gold\nGained [6] exp\ncome here\ncommand unknown\ngain 30 more exp\nok\nokay\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testSortStringsAllNumerically() {
		String[] args = {"-n", "sortAppTestAll.txt"};
		String expected = "\n  \n! hit\n!@#%\n//WARNING//\n/4run\n/ru28!*#\n/run script\n3 damage dealt\n6 damage\n6 damage dealt\n30 damage dealt\nGain 30 more exp\nGain0205's message:\nGained [30] gold\nGained [6] exp\ncome here\ncommand unknown\ngain 30 more exp\nok\nokay\n";
		try {
			sortApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	

}
