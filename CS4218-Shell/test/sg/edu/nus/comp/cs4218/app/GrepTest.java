package sg.edu.nus.comp.cs4218.app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.app.GrepApplication;

public class GrepTest {
	static GrepApplication grepApp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		grepApp = new GrepApplication();
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
	
	// stdin: "moose\nrat\ncat\ncrocodile\nseahorse\nmouse\nmongoose\nelk"
	@Test
	public void testGrepFromStdinBasic() {
		String input = "grep se";
		String expected = "moose\nseahorse\nmouse\nmongoose\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}

	// stdin: "line 4\nline 2\nline 3\nline 1"
	@Test
	public void testGrepFromStdinAllMatch() {
		String input = "grep line";
		String expected = "line 4\nline 2\nline 3\nline 1\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: ""
	@Test
	public void testGrepFromStdinEmpty() {
		String input = "grep line";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "Asia\nAfrica\nAmerica\nEurope\nOceania"
	@Test
	public void testGrepFromStdinNotFound() {
		String input = "grep greenland";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "Dog\nCat\nDragon\nMcDonald\nDeer\nDoe"
	@Test
	public void testGrepFromStdinFoundCaseMatch() {
		String input = "grep Do";
		String expected = "Dog\nMcDocald\nDoe\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "Rudolf\nIdol\ncobra\nScreen door\nGift"
	@Test
	public void testGrepFromStdinFoundCaseMismatch() {
		String input = "grep Do";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "NUS\nMuseum\nUSA\nscience\nTeach us\nUSB"
	@Test
	public void testGrepFromStdinFoundCaseMatchAndMismatch() {
		String input = "grep US";
		String expected = "NUS\nUSA\nUSB\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "\n\n\n\n"
	@Test
	public void testGrepFromStdinOnlyNewlines() {
		String input = "grep search";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "\n\n\n\n\n"
	@Test
	public void testGrepFromStdinNewlinesSearchNewlines() {
		String input = "grep \"\n\"";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "Scabbard\nBastion\nsmartphone\nTextbook\nbulb"
	@Test
	public void testGrepFromStdinMultipleOccurences() {
		String input = "grep b";
		String expected = "Scabbard\nTextbook\nbulb\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "CS4218\nCS3201\nCS2106\nCS4218\nCS2103T"
	@Test
	public void testGrepFromStdinDuplicateLinesMatch() {
		String input = "grep 21";
		String expected = "CS4218\nCS2106\nCS4218\nCS2103T\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "line 1\nline 5\nline 10\nline 15\nline 20"
	@Test
	public void testGrepFromStdinNumeric() {
		String input = "grep 1";
		String expected = "line 1\nline 10\nline 15\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "Do it!\n!CONGRATULATIONS!\n#Gxh#A@%\n"
	@Test
	public void testGrepFromStdinSpecialChars() {
		String input = "grep !";
		String expected = "Do it!\n!CONGRATULATIONS!\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// stdin: "R2-D2\nC-3PO\nR2-d5\nCAR2-door8\nr2D2\nDnDe5"
	@Test
	public void testGrepFromStdinMixture() {
		String input = "grep R2-d";
		String expected = "R2-d2\nCAR2-door8\n";
		assertEquals(expected, grepApp.grepFromStdin(input));
	}
	
	// Assumption: run() will do the validity check for files, 
	// hence nonexistent/unreadable files are not tested
	
	// file.txt contents: "moose\nrat\ncat\ncrocodile\nseahorse\nmouse\nmongoose\nelk"
	@Test
	public void testGrepFromOneFileBasic() {
		String input = "grep se file.txt";
		String expected = "moose\nseahorse\nmouse\nmongoose\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
	
	// file.txt contents: "line 4\nline 2\nline 3\nline 1"
	@Test
	public void testGrepFromOneFileAllMatch() {
		String input = "grep line file.txt";
		String expected = "line 4\nline 2\nline 3\nline 1\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// file.txt contents: ""
	@Test
	public void testGrepFromOneFileEmpty() {
		String input = "grep line file.txt";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// file.txt contents: "Asia\nAfrica\nAmerica\nEurope\nOceania"
	@Test
	public void testGrepFromOneFileNotFound() {
		String input = "grep greenland file.txt";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// stdfile.txt contentsin: "Dog\nCat\nDragon\nMcDonald\nDeer\nDoe"
	@Test
	public void testGrepFromOneFileFoundCaseMatch() {
		String input = "grep Do file.txt";
		String expected = "Dog\nMcDocald\nDoe\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// file.txt contents: "Rudolf\nIdol\ncobra\nScreen door\nGift"
	@Test
	public void testGrepFromOneFileFoundCaseMismatch() {
		String input = "grep Do file.txt";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// file.txt contents: "NUS\nMuseum\nUSA\nscience\nTeach us\nUSB"
	@Test
	public void testGrepFromOneFileFoundCaseMatchAndMismatch() {
		String input = "grep US file.txt";
		String expected = "NUS\nUSA\nUSB\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// file.txt contents: "\n\n\n\n"
	@Test
	public void testGrepFromOneFileOnlyNewlines() {
		String input = "grep search file.txt";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// file.txt contents: "\n\n\n\n\n"
	@Test
	public void testGrepFromOneFileNewlinesSearchNewlines() {
		String input = "grep \"\n\" file.txt";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// file.txt contents: "Scabbard\nBastion\nsmartphone\nTextbook\nbulb"
	@Test
	public void testGrepFromOneFileMultipleOccurences() {
		String input = "grep b file.txt";
		String expected = "Scabbard\nTextbook\nbulb\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// file.txt contents: "line 1\nline 5\nline 10\nline 15\nline 20"
	@Test
	public void testGrepFromOneFileNumeric() {
		String input = "grep 1 file.txt";
		String expected = "line 1\nline 10\nline 15\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// file.txt contents: "Do it!\n!CONGRATULATIONS!\n#Gxh#A@%\n"
	@Test
	public void testGrepFromOneFileSpecialChars() {
		String input = "grep ! file.txt";
		String expected = "Do it!\n!CONGRATULATIONS!\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
		
	// file.txt contents: "R2-D2\nC-3PO\nR2-d5\nCAR2-door8\nr2D2\nDnDe5"
	@Test
	public void testGrepFromOneFileMixture() {
		String input = "grep R2-d file.txt";
		String expected = "R2-d2\nCAR2-door8\n";
		assertEquals(expected, grepApp.grepFromOneFile(input));
	}
	
	// since string match functionality itself is already tested above, repeating for multiple files would be redundant
	// tests for grep for multiple files will focus on the various ways files may come in.
	// also, since grep is simply a line by line substring matching, tests will not deal with trying to test with many files
	// instead for tests the files will be limited to 2
	
	// file1.txt contents: "odd 1\neven 2\n"
	// file2.txt contents: "odd 3\neven 4\n"
	@Test
	public void testGrepFromMultipleFilesMatchInBoth() {
		String input = "grep odd file1.txt file2.txt";
		String expected = "odd 1\nodd 3\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "can\nbar\ntan\nwar\nfar\n"
	// file2.txt contents: "cap\nbat\nhat\nmap\n"
	@Test
	public void testGrepFromMultipleFilesMatchInFirstOnly() {
		String input = "grep ar file1.txt file2.txt";
		String expected = "bar\nwar\nfar\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "can\nbar\ntan\nwar\nfar\n"
		// file2.txt contents: "cap\nbat\nhat\nmap\n"
	@Test
	public void testGrepFromMultipleFilesMatchInSecondOnly() {
		String input = "grep ap file1.txt file2.txt";
		String expected = "cap\nmap\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "line 1\nline 2\n"
	// file2.txt contents: "row 3\nrow 4\n"
	@Test
	public void testGrepFromMultipleFilesNoMatch() {
		String input = "grep a file1.txt file2.txt";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "line 1\nline 2\n"
	// file2.txt contents: "row 3\nrow 4\n"
	@Test
	public void testGrepFromMultipleFilesNoOverlap() {
		String input = "grep a file1.txt file2.txt";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "moose\nmouse\ngoose\n"
	// file2.txt contents: "mouse\nrat\nguinea pig\n"
	@Test
	public void testGrepFromMultipleFilesMatchInOverlap() {
		String input = "grep use file1.txt file2.txt";
		String expected = "mouse\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "moose\nmouse\ngoose\n"
	// file2.txt contents: "mouse\nrat\nguinea pig\n"
	@Test
	public void testGrepFromMultipleFilesMatchNotInOverlap() { // same files as above
		String input = "grep ose file1.txt file2.txt"; // different pattern
		String expected = "moose\ngoose\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "moose\nmouse\ngoose\n"
	// file2.txt contents: "mouse\nrat\nguinea pig\n"
	@Test
	public void testGrepFromMultipleFilesOverlapNoMatch() { // same files as above
		String input = "grep mango file1.txt file2.txt"; // different pattern
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "apple\nbanana\ncan\n"
	// file2.txt contents: "apple\nbanana\ncan\n"
	@Test
	public void testGrepFromMultipleFilesIdenticalMatch() {
		String input = "grep an file1.txt file2.txt"; // different pattern
		String expected = "banana\ncan\n\nbanana\ncan\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "apple\nbanana\ncan\n"
	// file2.txt contents: "apple\nbanana\ncan\n"
	@Test
	public void testGrepFromMultipleFilesIdenticalNoMatch() { // same files as above
		String input = "grep art file1.txt file2.txt"; // different pattern
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "abba\nqwerty\nZxcAsdQwe\n"
	// file2.txt contents: "poi\nPOI\njoking\nBoink\n"
	@Test
	public void testGrepFromMultipleFilesNoCommon() {
		String input = "grep oi file1.txt file2.txt"; // different pattern
		String expected = "poi\nBoink\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: ""
	// file2.txt contents: "apple\nbanana\ncan\n"
	@Test
	public void testGrepFromMultipleFilesFirstEmpty() {
		String input = "grep an file1.txt file2.txt";
		String expected = "banana\ncan\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: "apple\nbanana\ncan\n"
	// file2.txt contents: ""
	@Test
	public void testGrepFromMultipleFilesSecondEmpty() {
		String input = "grep an file1.txt file2.txt"; 
		String expected = "banana\ncan\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// file1.txt contents: ""
	// file2.txt contents: ""
	@Test
	public void testGrepFromMultipleFilesBothEmpty() {
		String input = "grep an file1.txt file2.txt";
		String expected = "\n";
		assertEquals(expected, grepApp.grepFromMultipleFiles(input));
	}
	
	// Assumption: if pattern is invalid, the invoked method will return empty string to run(),
	// to indicate that given pattern was invalid, upon which run() will throw an exception
	
	@Test
	public void testGrepInvalidPatternInStdin() {
		String input = "grep [0-9]++";
		String expected = "";
		assertEquals(expected, grepApp.grepInvalidPatternInStdin(input));
	}
	
	@Test
	public void testGrepInvalidPatternInFile() {
		String input = "grep [0-9]++ file.txt";
		String expected = "";
		assertEquals(expected, grepApp.grepInvalidPatternInFile(input));
	}

}
