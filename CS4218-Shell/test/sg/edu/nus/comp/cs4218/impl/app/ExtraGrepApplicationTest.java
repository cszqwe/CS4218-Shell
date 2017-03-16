package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;

/*
 * Assumptions: 
 * 1) run function will call the correct functions with the correct inputs in the correct order separated by a space
 * 2) Run function will take inputs directly from shell unordered
 * 3) Args for run: unordered consisting of pattern and files
 * 4) Args for grepFromOneFile: pattern, file
 * 5) Args for grepFromMultipleFiles: pattern, file, file, ...
 * 6) Args for grepFromStdin: pattern (Stdin will be parsed from run)
 */

public class ExtraGrepApplicationTest {
	private static final String REGEXMULTIOUT = "Hello Hello\nABC Hello\nello milo";
	private static final String NOMATCHFILE = "Pattern Not Found In File!";
	private static final String REGEXPATTERNOUT = "Hello Hello\nABC Hello";
	private static final String REGEXPATTERN = ".*ell";
	private static final String ABCSINGLEFILEOUT = "ABC Hello\nABCDEFGHI";
	private static final String ABCPATTERN = "ABC";
	private static final String HIEPATTERN = "hie";
	private static final String NOMATCHSTDIN = "Pattern Not Found In Stdin!";
	private GrepApplication grepApp;
	private String[] args;
	private FileInputStream stdin;
	private String fileName;
	private String fileName2;
	private String fileName3;
	private String directory;
	private String invalidFile;
	private ByteArrayOutputStream baos;
	PrintStream print;

	@Before
	public void setUp() throws FileNotFoundException {
		grepApp = new GrepApplication();
		stdin = new FileInputStream("test/sg/edu/nus/comp/cs4218/impl/app/greptestdoc.txt");
		fileName = "test/sg/edu/nus/comp/cs4218/impl/app/greptestdoc.txt";
		fileName2 = "test/sg/edu/nus/comp/cs4218/impl/app/greptestdoc2.txt";
		fileName3 = "test/sg/edu/nus/comp/cs4218/impl/app/testdoc.txt";
		invalidFile = "test/sg/edu/nus/comp/cs4218/impl/app/abjkcsnakjc.txt";
		directory = "test/sg/edu/nus/comp/cs4218/impl/app/";
		baos = new ByteArrayOutputStream();
		print = new PrintStream(baos);
		System.setOut(print);
	}

	@Test(expected = GrepException.class)
	public void grepStdInNoMatchesFromRun() throws AbstractApplicationException {
		args = new String[1];
		args[0] = HIEPATTERN;
		grepApp.run(args, null, System.out);
		System.out.flush();
		assertEquals(NOMATCHSTDIN + "\n", baos.toString());
	}


	@Test
	public void grepStdInMatchesFromRun() throws AbstractApplicationException {
		args = new String[1];
		args[0] = ABCPATTERN;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals(ABCSINGLEFILEOUT + "\n", baos.toString());
	}

	@Test
	public void grepStdInRegexMatchesFromRun() throws AbstractApplicationException {
		args = new String[1];
		args[0] = REGEXPATTERN;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals(REGEXPATTERNOUT + "\n", baos.toString());
	}


	@Test
	public void grepSingleFileNoMatchesFromRun() throws AbstractApplicationException {
		args = new String[2];
		args[0] = HIEPATTERN;
		args[1] = fileName;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals("", baos.toString());
	}

	@Test
	public void grepSingleFileMultipleMatchesInALineFromRun() throws AbstractApplicationException {
		args = new String[2];
		args[0] = "h";
		args[1] = fileName3;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals("Boisterous he on understood attachment as entreaties ye devonshire.\n"
				+ "Extremely ham any his departure for contained curiosity defective.\n"
				+ "Way now instrument had eat diminution melancholy expression sentiments stimulated.\n"
				+ "Mrs interested now his affronting inquietude contrasted cultivated.\n"
				+ "Lasting showing expense greater on colonel no.\n"
				+ "Prepared do an dissuade be so whatever steepest.\n"
				+ "Yet her beyond looked either day wished nay.\n"
				+ "Now curiosity you explained immediate why behaviour.\n"
				+ "An dispatched impossible of of melancholy favourable.\n"
				+ "Our quiet not heart along scale sense timed.\n"
				+ "Consider may dwelling old him her surprise finished families graceful.\n"
				+ "Is at purse tried jokes china ready decay an.\n" + "Small its shy way had woody downs power.\n"
				+ "Procured shutters mr it feelings.\n" + "To or three offer house begin taken am at.\n"
				+ "As dissuade cheerful overcame so of friendly he indulged unpacked.\n"
				+ "An seeing feebly stairs am branch income me unable.\n"
				+ "Celebrated contrasted discretion him sympathize her collecting occasional.\n"
				+ "Do answered bachelor occasion in of offended no concerns.\n"
				+ "Supply worthy warmth branch of no ye.\n" + "Though wished merits or be.\n"
				+ "Alone visit use these smart rooms ham.\n" + "Course sir people worthy horses add entire suffer.\n"
				+ "Strictly numerous outlived kindness whatever on we no on addition.\n"
				+ "Are sentiments apartments decisively the especially alteration.\n"
				+ "Thrown shy denote ten ladies though ask saw.\n" + "Or by to he going think order event music.\n"
				+ "Led income months itself and houses you. After nor you leave might share court balls.\n",
				baos.toString());
	}

	@Test
	public void grepSingleFileMatchesFromRun() throws AbstractApplicationException {
		args = new String[2];
		args[0] = ABCPATTERN;
		args[1] = fileName;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals(ABCSINGLEFILEOUT + "\n", baos.toString());
	}

	@Test
	public void grepSingleFileRegexMatchesFromRun() throws AbstractApplicationException {
		args = new String[2];
		args[0] = REGEXPATTERN;
		args[1] = fileName;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals(REGEXPATTERNOUT + "\n", baos.toString());
	}


	@Test
	public void grepMultipleFileNoMatchesFromRun() throws AbstractApplicationException {
		args = new String[3];
		args[0] = HIEPATTERN;
		args[1] = fileName;
		args[2] = fileName2;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals("", baos.toString());
	}


	@Test
	public void grepMultipleFileMatchesFromRun() throws AbstractApplicationException {
		args = new String[3];
		args[0] = "DEF";
		args[1] = fileName;
		args[2] = fileName2;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals("ABCDEFGHI\nDEF" + "\n", baos.toString());
	}

	@Test
	public void grepMultipleFileRegexMatchesFromRun() throws AbstractApplicationException {
		args = new String[3];
		args[0] = REGEXPATTERN;
		args[1] = fileName;
		args[2] = fileName2;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals(REGEXMULTIOUT + "\n", baos.toString());
	}

	@Test
	public void grepUnorderedInput1FromRun() throws AbstractApplicationException {
		args = new String[3];
		args[0] = fileName;
		args[1] = REGEXPATTERN;
		args[2] = fileName2;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals("grep: .*ell: No such file\n", baos.toString());
	}

	@Test
	public void grepUnorderedInput2FromRun() throws AbstractApplicationException {
		args = new String[3];
		args[0] = fileName;
		args[1] = fileName2;
		args[2] = REGEXPATTERN;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals("grep: .*ell: No such file\n", baos.toString());
	}

	@Test
	public void grepMultiFileDirectoryFromRun() throws AbstractApplicationException {
		args = new String[4];
		args[1] = fileName;
		args[2] = fileName2;
		args[0] = REGEXPATTERN;
		args[3] = directory;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals(REGEXMULTIOUT + "\n" + "grep: test/sg/edu/nus/comp/cs4218/impl/app/: No such file\n", baos.toString());
	}

	@Test
	public void grepDirectoryWithFileFromRun() throws AbstractApplicationException {
		args = new String[3];
		args[0] = REGEXPATTERN;
		args[1] = fileName;
		args[2] = directory;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
		assertEquals(REGEXPATTERNOUT + "\n" + "grep: test/sg/edu/nus/comp/cs4218/impl/app/: No such file\n", baos.toString());
	}

	@Test(expected = GrepException.class)
	public void grepnoStdinFromRun() throws AbstractApplicationException {
		args = new String[1];
		args[0] = fileName;
		grepApp.run(args, null, System.out);
		System.out.flush();
	}

	@Test
	public void grepNoPatternMultipleFileFromRun() throws AbstractApplicationException {
		args = new String[2];
		args[0] = fileName;
		args[1] = fileName2;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
	}

	@Test(expected = GrepException.class)
	public void grepNoPatternStdInFromRun() throws AbstractApplicationException {
		args = new String[0];
		grepApp.run(args, stdin, System.out);
		System.out.flush();
	}

	@Test(expected = GrepException.class)
	public void grepArgsFromRun() throws AbstractApplicationException {
		args = new String[0];
		grepApp.run(null, stdin, System.out);
		System.out.flush();
	}

	
	@Test
	public void grepDirectoryFromRun() throws AbstractApplicationException {
		args = new String[2];
		args[0] = REGEXPATTERN;
		args[1] = directory;
		grepApp.run(args, stdin, System.out);
		assertEquals(baos.toString(), baos.toString());
	}


	@Test(expected = GrepException.class)
	public void invalidRegexFromRun() throws AbstractApplicationException {
		args = new String[3];
		args[0] = "[";
		args[1] = fileName2;
		args[2] = fileName;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
	}

	@Test(expected = GrepException.class)
	public void invalidRegexFileFromRun() throws AbstractApplicationException {
		args = new String[3];
		args[0] = "[";
		args[1] = fileName2;
		args[2] = fileName;
		grepApp.run(args, stdin, System.out);
		System.out.flush();
	}

	@Test(expected = GrepException.class)
	public void invalidOutputStream() throws AbstractApplicationException {
		args = new String[3];
		args[0] = REGEXPATTERN;
		args[1] = fileName2;
		args[2] = fileName;
		grepApp.run(args, stdin, null);
		System.out.flush();
	}

	
}
