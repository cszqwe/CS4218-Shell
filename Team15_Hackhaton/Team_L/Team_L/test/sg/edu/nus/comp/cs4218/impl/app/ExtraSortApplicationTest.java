package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.HeadException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class ExtraSortApplicationTest {

	SortApplication ssa;
	String args[];
	String fileName;
	InputStream stdin;
	InputStream numericStdin;
	InputStream emptyStdin;
	String numericFile;
	String testMethodsFile;
	String emptyFile;
	String reorderStr1 = "test.txt";
	String reorderStr2 = "test1.txt";
	OutputStream os;

	String defaultString = "Boisterous he on understood attachment as entreaties ye devonshire.\n"
			+ "In mile an form snug were been sell.\n" + "Hastened admitted joy nor absolute gay its.\n"
			+ "Extremely ham any his departure for contained curiosity defective.\n"
			+ "Way now instrument had eat diminution melancholy expression sentiments stimulated.\n"
			+ "One built fat you out manor books.\n"
			+ "Mrs interested now his affronting inquietude contrasted cultivated.\n"
			+ "Lasting showing expense greater on colonel no.\n\n" +

			"Prepared do an dissuade be so whatever steepest.\n" + "Yet her beyond looked either day wished nay.\n"
			+ "By doubtful disposed do juvenile an.\n" + "Now curiosity you explained immediate why behaviour.\n"
			+ "An dispatched impossible of of melancholy favourable.\n"
			+ "Our quiet not heart along scale sense timed.\n"
			+ "Consider may dwelling old him her surprise finished families graceful.\n"
			+ "Gave led past poor met fine was new.\n\n" +

			"Is at purse tried jokes china ready decay an.\n" + "Small its shy way had woody downs power.\n"
			+ "To denoting admitted speaking learning my exercise so in.\n" + "Procured shutters mr it feelings.\n"
			+ "To or three offer house begin taken am at.\n"
			+ "As dissuade cheerful overcame so of friendly he indulged unpacked.\n"
			+ "Alteration connection to so as collecting me.\n"
			+ "Difficult in delivered extensive at direction allowance.\n"
			+ "Alteration put use diminution can considered sentiments interested discretion.\n"
			+ "An seeing feebly stairs am branch income me unable.\n\n" +

			"No comfort do written conduct at prevent manners on.\n"
			+ "Celebrated contrasted discretion him sympathize her collecting occasional.\n"
			+ "Do answered bachelor occasion in of offended no concerns.\n" + "Supply worthy warmth branch of no ye.\n"
			+ "Voice tried known to as my to.\n" + "Though wished merits or be.\n"
			+ "Alone visit use these smart rooms ham.\n" + "No waiting in on enjoyed placing it inquiry.\n\n" +

			"Fat new smallness few supposing suspicion two.\n" + "Course sir people worthy horses add entire suffer.\n"
			+ "How one dull get busy dare far.\n" + "At principle perfectly by sweetness do.\n"
			+ "As mr started arrival subject by believe.\n"
			+ "Strictly numerous outlived kindness whatever on we no on addition.\n\n" +

			"Are sentiments apartments decisively the especially alteration.\n"
			+ "Thrown shy denote ten ladies though ask saw.\n" + "Or by to he going think order event music.\n"
			+ "Incommode so intention defective at convinced.\n"
			+ "Led income months itself and houses you. After nor you leave might share court balls.";

	String sortedString = "\n\n\n\n\nAlone visit use these smart rooms ham.\n"
			+ "Alteration connection to so as collecting me.\n"
			+ "Alteration put use diminution can considered sentiments interested discretion.\n"
			+ "An dispatched impossible of of melancholy favourable.\n"
			+ "An seeing feebly stairs am branch income me unable.\n"
			+ "Are sentiments apartments decisively the especially alteration.\n"
			+ "As dissuade cheerful overcame so of friendly he indulged unpacked.\n"
			+ "As mr started arrival subject by believe.\n" + "At principle perfectly by sweetness do.\n"
			+ "Boisterous he on understood attachment as entreaties ye devonshire.\n"
			+ "By doubtful disposed do juvenile an.\n"
			+ "Celebrated contrasted discretion him sympathize her collecting occasional.\n"
			+ "Consider may dwelling old him her surprise finished families graceful.\n"
			+ "Course sir people worthy horses add entire suffer.\n"
			+ "Difficult in delivered extensive at direction allowance.\n"
			+ "Do answered bachelor occasion in of offended no concerns.\n"
			+ "Extremely ham any his departure for contained curiosity defective.\n"
			+ "Fat new smallness few supposing suspicion two.\n" + "Gave led past poor met fine was new.\n"
			+ "Hastened admitted joy nor absolute gay its.\n" + "How one dull get busy dare far.\n"
			+ "In mile an form snug were been sell.\n" + "Incommode so intention defective at convinced.\n"
			+ "Is at purse tried jokes china ready decay an.\n" + "Lasting showing expense greater on colonel no.\n"
			+ "Led income months itself and houses you. After nor you leave might share court balls.\n"
			+ "Mrs interested now his affronting inquietude contrasted cultivated.\n"
			+ "No comfort do written conduct at prevent manners on.\n"
			+ "No waiting in on enjoyed placing it inquiry.\n"
			+ "Now curiosity you explained immediate why behaviour.\n" + "One built fat you out manor books.\n"
			+ "Or by to he going think order event music.\n" + "Our quiet not heart along scale sense timed.\n"
			+ "Prepared do an dissuade be so whatever steepest.\n" + "Procured shutters mr it feelings.\n"
			+ "Small its shy way had woody downs power.\n"
			+ "Strictly numerous outlived kindness whatever on we no on addition.\n"
			+ "Supply worthy warmth branch of no ye.\n" + "Though wished merits or be.\n"
			+ "Thrown shy denote ten ladies though ask saw.\n"
			+ "To denoting admitted speaking learning my exercise so in.\n"
			+ "To or three offer house begin taken am at.\n" + "Voice tried known to as my to.\n"
			+ "Way now instrument had eat diminution melancholy expression sentiments stimulated.\n"
			+ "Yet her beyond looked either day wished nay.\n";

	@Before
	public void setUp() throws FileNotFoundException {
		ssa = new SortApplication();
		args = new String[2];
		fileName = "test/sg/edu/nus/comp/cs4218/impl/app/testdoc.txt";
		stdin = new FileInputStream("test/sg/edu/nus/comp/cs4218/impl/app/testdoc.txt");
		numericStdin = new FileInputStream("test/sg/edu/nus/comp/cs4218/impl/app/TestSortNumeric.txt");
		numericFile = "test/sg/edu/nus/comp/cs4218/impl/app/TestSortNumeric.txt";
		testMethodsFile = "test/sg/edu/nus/comp/cs4218/impl/app/TestSortMethods.txt";
		emptyFile = "test/sg/edu/nus/comp/cs4218/impl/app/emptydoc.txt";
		emptyStdin = new FileInputStream("test/sg/edu/nus/comp/cs4218/impl/app/emptydoc.txt");
		os = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		// reset is and os
		os = new ByteArrayOutputStream();
	}

	@Test
	public void testSort() throws SortException {
		args[0] = "-n";
		args[1] = fileName;
		ssa.run(args, null, os);
		assertEquals(sortedString, os.toString());

	}

	@Test
	public void sortAllNumericInputStream() throws SortException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream pStream = new PrintStream(baos);
		System.setOut(pStream);
		ssa.run(null, numericStdin, System.out);
		System.out.flush();
		assertEquals("1\n10\n2\n", baos.toString());
	}

	@Test
	public void sortAllNumericFile() throws SortException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream pStream = new PrintStream(baos);
		System.setOut(pStream);
		args = new String[2];
		args[1] = numericFile;
		args[0] = "-n"; // -n should come first, not behind
		ssa.run(args, null, System.out);
		System.out.flush();
		assertEquals("1\n2\n10\n", baos.toString());
	}

	@Test
	public void sortAllInputStream() throws SortException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream pStream = new PrintStream(baos);
		System.setOut(pStream);
		ssa.run(null, stdin, System.out);
		System.out.flush();
		assertEquals(sortedString, baos.toString());
	}

	@Test
	public void sortAllFile() throws SortException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream pStream = new PrintStream(baos);
		System.setOut(pStream);
		args = new String[1];
		args[0] = fileName;
		ssa.run(args, null, System.out);
		System.out.flush();
		assertEquals(sortedString, baos.toString());
	}

	@Test
	public void testEmptyStream() throws SortException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream pStream = new PrintStream(baos);
		System.setOut(pStream);
		ssa.run(null, emptyStdin, System.out);
		System.out.flush();
		assertEquals("\n", baos.toString());
	}

	@Test
	public void testEmptyFile() throws SortException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		final PrintStream pStream = new PrintStream(baos);
		System.setOut(pStream);
		args = new String[1];
		args[0] = emptyFile;
		ssa.run(args, null, System.out);
		System.out.flush();
		assertEquals("", baos.toString().trim());
	}

	@Test
	public void testNumericOpt() throws SortException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		;
		try {
			args = new String[3];
			args[0] = "-n";
			args[1] = testMethodsFile;
			args[2] = numericFile;
			ssa.run(args, stdin, baos);
			System.out.println("Successfully runs");
		} catch (Exception e) {
			System.out.println("Exception");
		}
		assertEquals("\n+\n1\n1\n2\n2\n5\n10\nA\nB\na\nb\n", baos.toString());

	}

	/**
	 * Below are unit tests on Sort interface
	 */

	@Test
	public void testSortStringsSimple() {
		String input = "sort sortAppTestSimple.txt";
		String expected = "a\naaa\naaaa\nabc\nbaaa\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleNumerically() {
		String input = "sort -n sortAppTestSimple.txt";
		String expected = "a\naaa\naaaa\nabc\nbaaa\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsCapital() {
		String input = "sort sortAppTestCapital.txt";
		String expected = "BATMAN\nSEVENELEVEN\nSUPER\nSUPERMAN\nSUPERMARKET\nSUPPER\n";
		assertEquals(expected, ssa.sortStringsCapital(input));
	}

	@Test
	public void testSortStringsCapitalNumerically() {
		String input = "sort -n sortAppTestCapital.txt";
		String expected = "BATMAN\nSEVENELEVEN\nSUPER\nSUPERMAN\nSUPERMARKET\nSUPPER\n";
		assertEquals(expected, ssa.sortStringsCapital(input));
	}

	@Test
	public void testSortStringsNumbers() {
		String input = "sort sortAppTestNumbers.txt";
		String expected = "001\n01\n1\n10\n2\n210\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsNumbersNumerically() {
		String input = "sort -n sortAppTestNumbers.txt";
		String expected = "001\n01\n1\n2\n10\n210\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSpecialChars() {
		String input = "sort sortAppTestSpecialChars.txt";
		String expected = "\n \n  \n!?@#>\n%)*^%\n%^!\n??\n????\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSpecialCharsNumerically() {
		String input = "sort -n sortAppTestSpecialChars.txt";
		String expected = "\n \n  \n!?@#>\n%)*^%\n%^!\n??\n????\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleCapital() {
		String input = "sort sortAppTestSimpleCapital.txt";
		String expected = "AA\nAAAA\nAAAAA\naBCd\naaaa\nbcD\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleCapitalNumerically() {
		String input = "sort -n sortAppTestSimpleCapital.txt";
		String expected = "AA\nAAAA\nAAAAA\naBCd\naaaa\nbcD\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleNumbers() {
		String input = "sort sortAppTestSimpleNumbers.txt";
		String expected = "03\n1\n10th\n1ine\n3rd\nline\nline1\nline10\nline3\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleNumbersNumerically() {
		String input = "sort -n sortAppTestSimpleNumbers.txt";
		String expected = "1\n1ine\n03\n3rd\n10th\nline\nline1\nline10\nline3\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleSpecialChars() {
		String input = "sort sortAppTestSimpleSpecialChars.txt";
		String expected = "\n \n? why\n?!%^@\nport^\nsore throat\nsort!\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleSpecialCharsNumerically() {
		String input = "sort -n sortAppTestSimpleSpecialChars.txt";
		String expected = "\n \n? why\n?!%^@\nport^\nsore throat\nsort!\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsCapitalNumbers() {
		String input = "sort sortAppTestCapitalNumbers.txt";
		String expected = "50\n500\n6X\n6X\nSTRING10\nSTRING5\nSTRING50\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsCapitalNumbersNumerically() {
		String input = "sort -n sortAppTestCapitalNumbers.txt";
		String expected = "6X\n6X\n50\n500\nSTRING10\nSTRING5\nSTRING50\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsCapitalSpecialChars() {
		String input = "sort sortAppTestCapitalSpecialChars.txt";
		String expected = "\n\n  \n!(@THIGH\n@THIGH\nTHIGH\nTHIS IS SPA\nTHIS IS SPARTA!\nTHIS-IS-MADNESS\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsCapitalSpecialCharsNumerically() {
		String input = "sort -n sortAppTestCapitalSpecialChars.txt";
		String expected = "\n\n  \n!(@THIGH\n@THIGH\nTHIGH\nTHIS IS SPA\nTHIS IS SPARTA!\nTHIS-IS-MADNESS\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsNumbersSpecialChars() {
		String input = "sort sortAppTestNumbersSpecialChars.txt";
		String expected = "$(@&34\n$(@&7\n<   >\n<-0->\n12$(@&34\n2$(@&34\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsNumbersSpecialCharsNumerically() {
		String input = "sort -n sortAppTestNumbersSpecialChars.txt";
		String expected = "$(@&34\n$(@&7\n<   >\n<-0->\n2$(@&34\n12$(@&34\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleCapitalNumbers() {
		String input = "sort sortAppTestSimpleCapitalNumbers.txt";
		String expected = "21stCentury\n30TH\n30th\n3rdPlace\nM24Chaffee\nM40Rifle\nM4Sherman\nm4\nmnm\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleCapitalNumbersNumerically() {
		String input = "sort -n sortAppTestSimpleCapitalNumbers.txt";
		String expected = "3rdPlace\n21stCentury\n30TH\n30th\nM24Chaffee\nM40Rifle\nM4Sherman\nm4\nmnm\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleCapitalSpecialChars() {
		String input = "sort sortAppTestSimpleCapitalSpecialChars.txt";
		String expected = " Hello, World!\n!! Hello\nHELLO WORLD\nHella lot of tests!\nHello World!\nHello World!\nHello World@!\nHello world!\nHello, World!\nHello,World!\nhello?\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleCapitalSpecialCharsNumerically() {
		String input = "sort -n sortAppTestSimpleCapitalSpecialChars.txt";
		String expected = " Hello, World!\n!! Hello\nHELLO WORLD\nHella lot of tests!\nHello World!\nHello World!\nHello World@!\nHello world!\nHello, World!\nHello,World!\nhello?\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleNumbersSpecialChars() {
		String input = "sort sortAppTestSimpleNumbersSpecialChars.txt";
		String expected = "!arg==\"-n\"\n&arg\n4=3\n40=3\n6=3\narg==\"-n\"\narg=3\nargs[40]\nargs[7]\narray.get(2)\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsSimpleNumbersSpecialCharsNumerically() {
		String input = "sort -n sortAppTestSimpleNumbersSpecialChars.txt";
		String expected = "!arg==\"-n\"\n&arg\n4=3\n6=3\n40=3\narg==\"-n\"\narg=3\nargs[40]\nargs[7]\narray.get(2)\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsCapitalNumbersSpecialChars() {
		String input = "sort sortAppTestCapitalNumbersSpecialChars.txt";
		String expected = "!0AA\n!AAA\n%AAA\n10AAA\n2A\n2AAA\n2BAA\nAAA10\nAAA2\nAAAA2\nAAB2\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsCapitalNumbersSpecialCharsNumerically() {
		String input = "sort -n sortAppTestCapitalNumbersSpecialChars.txt";
		String expected = "!0AA\n!AAA\n%AAA\n2A\n2AAA\n2BAA\n10AAA\nAAA10\nAAA2\nAAAA2\nAAB2\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsAll() {
		String input = "sort sortAppTestAll.txt";
		String expected = "\n  \n! hit\n!@#%\n//WARNING//\n/4run\n/ru28!*#\n/run script\n3 damage dealt\n30 damage dealt\n6 damage\n6 damage dealt\nGain 30 more exp\nGain0205's message:\nGained [30] gold\nGained [6] exp\ncome here\ncommand unknown\ngain 30 more exp\nok\nokay\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	@Test
	public void testSortStringsAllNumerically() {
		String input = "sort -n sortAppTestAll.txt";
		String expected = "\n  \n! hit\n!@#%\n//WARNING//\n/4run\n/ru28!*#\n/run script\n3 damage dealt\n6 damage\n6 damage dealt\n30 damage dealt\nGain 30 more exp\nGain0205's message:\nGained [30] gold\nGained [6] exp\ncome here\ncommand unknown\ngain 30 more exp\nok\nokay\n";
		assertEquals(expected, ssa.sortStringsSimple(input));
	}

	// Integration testing

	@Test
	public void testSortPipeFromSort() throws AbstractApplicationException, ShellException {
		ShellImpl shell = new ShellImpl();
		String args = "sort -n sortAppTestAll.txt | tail -n 5";
		String expected = "come here\ncommand unknown\ngain 30 more exp\nok\nokay\n";
		shell.parseAndEvaluate(args, os);
		assertEquals(expected, os.toString());
	}

	@Test
	public void testSortPipeToSort() throws AbstractApplicationException, ShellException {
		ShellImpl shell = new ShellImpl();
		String args = "date | sort -n";
		String expected = java.util.Calendar.getInstance().getTime().toString() + "\n";
		shell.parseAndEvaluate(args, os);
		assertEquals(expected, os.toString());
	}

	@Test
	public void testSortCmdSubstitution() throws AbstractApplicationException, ShellException {
		ShellImpl shell = new ShellImpl();
		String args = "sort -n `cat cmdSubFile.txt`"; // cmdSubFile contains a
														// filename broken by
														// lines, which allows
														// sort to take in the
														// filename as arg
		String expected = "6X\n6X\n50\n500\nSTRING10\nSTRING5\nSTRING50\n";
		shell.parseAndEvaluate(args, os);
		assertEquals(expected, os.toString());
	}

	@Test
	public void testSortDoublePipeSortLast() throws AbstractApplicationException, ShellException {
		ShellImpl shell = new ShellImpl();
		String args = "cat test.txt | head -n 2 | sort -n";
		String expected = "line 1\nline 2\n";
		shell.parseAndEvaluate(args, os);
		assertEquals(expected, os.toString());
	}

	@Test
	public void testSortDoubleCmdSubstitution() throws AbstractApplicationException, ShellException {
		ShellImpl shell = new ShellImpl();
		String args = "sort `cat cmdSubFile.txt` `head cmdSubFile4.txt`";
		String expected = "001\n01\n1\n10\n2\n210\n50\n500\n6X\n6X\nSTRING10\nSTRING5\nSTRING50\n";
		shell.parseAndEvaluate(args, os);
		assertEquals(expected, os.toString());
	}

}
