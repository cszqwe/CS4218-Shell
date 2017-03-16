package sg.edu.nus.comp.cs4218.app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.app.SortApplication;

public class SortTest {
	
	static SortApplication sortApp;
	
	// test case decisions:
	// Test with cases where the differences are in varying positions of strings
	// e.g. first characters differ, start diverging from second char, first few chars identical but later different, no similarity, etc
	// so that as many variations are tested without having to test exhaustively
	
	// corner cases being tested are:
	// 1. empty string
	// 2. identical strings
	// 3. strings identical up to middle, then differing
	// 4. strings identical up to middle, then one ending
	// 5. strings different up to middle, then identical
	// 6. strings with different numbers at beginning
	// 7. strings with different numbers in middle or end
	// 8. strings identical but with offset (caused by additional char(s) at beginning)
	// 9. numbers with same numeric value but different strings (e.g. 2, 02, 002...)
	// 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sortApp = new SortApplication();
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
	public void testSortStringsSimple() {
		String input = "sort sortAppTestSimple.txt";
		String expected = "a\naaa\naaaa\nabc\nbaaa\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleNumerically() {
		String input = "sort -n sortAppTestSimple.txt";
		String expected = "a\naaa\naaaa\nabc\nbaaa\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsCapital() {
		String input = "sort sortAppTestCapital.txt";
		String expected = "BATMAN\nSEVENELEVEN\nSUPER\nSUPERMAN\nSUPERMARKET\nSUPPER\n";
		assertEquals(expected, sortApp.sortStringsCapital(input));
	}
	
	@Test
	public void testSortStringsCapitalNumerically() {
		String input = "sort -n sortAppTestCapital.txt";
		String expected = "BATMAN\nSEVENELEVEN\nSUPER\nSUPERMAN\nSUPERMARKET\nSUPPER\n";
		assertEquals(expected, sortApp.sortStringsCapital(input));
	}
	
	@Test
	public void testSortStringsNumbers() {
		String input = "sort sortAppTestNumbers.txt";
		String expected = "001\n01\n1\n10\n2\n210\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsNumbersNumerically() {
		String input = "sort -n sortAppTestNumbers.txt";
		String expected = "001\n01\n1\n2\n10\n210\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSpecialChars() {
		String input = "sort sortAppTestSpecialChars.txt";
		String expected = "\n \n  \n!?@#>\n%)*^%\n%^!\n??\n????\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSpecialCharsNumerically() {
		String input = "sort -n sortAppTestSpecialChars.txt";
		String expected = "\n \n  \n!?@#>\n%)*^%\n%^!\n??\n????\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleCapital() {
		String input = "sort sortAppTestSimpleCapital.txt";
		String expected = "AA\nAAAA\nAAAAA\naBCd\naaaa\nbcD\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleCapitalNumerically() {
		String input = "sort -n sortAppTestSimpleCapital.txt";
		String expected = "AA\nAAAA\nAAAAA\naBCd\naaaa\nbcD\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleNumbers() {
		String input = "sort sortAppTestSimpleNumbers.txt";
		String expected = "03\n1\n10th\n1ine\n3rd\nline\nline1\nline10\nline3\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleNumbersNumerically() {
		String input = "sort -n sortAppTestSimpleNumbers.txt";
		String expected = "1\n1ine\n03\n3rd\n10th\nline\nline1\nline10\nline3\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleSpecialChars() {
		String input = "sort sortAppTestSimpleSpecialChars.txt";
		String expected = "\n \n? why\n?!%^@\nport^\nsore throat\nsort!\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleSpecialCharsNumerically() {
		String input = "sort -n sortAppTestSimpleSpecialChars.txt";
		String expected = "\n \n? why\n?!%^@\nport^\nsore throat\nsort!\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsCapitalNumbers() {
		String input = "sort sortAppTestCapitalNumbers.txt";
		String expected = "50\n500\n6X\n6X\nSTRING10\nSTRING5\nSTRING50\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsCapitalNumbersNumerically() {
		String input = "sort -n sortAppTestCapitalNumbers.txt";
		String expected = "6X\n6X\n50\n500\nSTRING10\nSTRING5\nSTRING50\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsCapitalSpecialChars() {
		String input = "sort sortAppTestCapitalSpecialChars.txt";
		String expected = "\n\n  \n!(@THIGH\n@THIGH\nTHIGH\nTHIS IS SPA\nTHIS IS SPARTA!\nTHIS-IS-MADNESS\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsCapitalSpecialCharsNumerically() {
		String input = "sort -n sortAppTestCapitalSpecialChars.txt";
		String expected = "\n\n  \n!(@THIGH\n@THIGH\nTHIGH\nTHIS IS SPA\nTHIS IS SPARTA!\nTHIS-IS-MADNESS\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsNumbersSpecialChars() {
		String input = "sort sortAppTestNumbersSpecialChars.txt";
		String expected = "$(@&34\n$(@&7\n<   >\n<-0->\n12$(@&34\n2$(@&34\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsNumbersSpecialCharsNumerically() {
		String input = "sort -n sortAppTestNumbersSpecialChars.txt";
		String expected = "$(@&34\n$(@&7\n<   >\n<-0->\n2$(@&34\n12$(@&34\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleCapitalNumbers() {
		String input = "sort sortAppTestSimpleCapitalNumbers.txt";
		String expected = "21stCentury\n30TH\n30th\n3rdPlace\nM24Chaffee\nM40Rifle\nM4Sherman\nm4\nmnm\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleCapitalNumbersNumerically() {
		String input = "sort -n sortAppTestSimpleCapitalNumbers.txt";
		String expected = "3rdPlace\n21stCentury\n30TH\n30th\nM24Chaffee\nM40Rifle\nM4Sherman\nm4\nmnm\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleCapitalSpecialChars() {
		String input = "sort sortAppTestSimpleCapitalSpecialChars.txt";
		String expected = " Hello, World!\n!! Hello\nHELLO WORLD\nHella lot of tests!\nHello World!\nHello World!\nHello World@!\nHello world!\nHello, World!\nHello,World!\nhello?\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleCapitalSpecialCharsNumerically() {
		String input = "sort -n sortAppTestSimpleCapitalSpecialChars.txt";
		String expected = " Hello, World!\n!! Hello\nHELLO WORLD\nHella lot of tests!\nHello World!\nHello World!\nHello World@!\nHello world!\nHello, World!\nHello,World!\nhello?\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleNumbersSpecialChars() {
		String input = "sort sortAppTestSimpleNumbersSpecialChars.txt";
		String expected = "!arg==\"-n\"\n&arg\n4=3\n40=3\n6=3\narg==\"-n\"\narg=3\nargs[40]\nargs[7]\narray.get(2)\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsSimpleNumbersSpecialCharsNumerically() {
		String input = "sort -n sortAppTestSimpleNumbersSpecialChars.txt";
		String expected = "!arg==\"-n\"\n&arg\n4=3\n6=3\n40=3\narg==\"-n\"\narg=3\nargs[40]\nargs[7]\narray.get(2)\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsCapitalNumbersSpecialChars() {
		String input = "sort sortAppTestCapitalNumbersSpecialChars.txt";
		String expected = "!0AA\n!AAA\n%AAA\n10AAA\n2A\n2AAA\n2BAA\nAAA10\nAAA2\nAAAA2\nAAB2\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsCapitalNumbersSpecialCharsNumerically() {
		String input = "sort -n sortAppTestCapitalNumbersSpecialChars.txt";
		String expected = "!0AA\n!AAA\n%AAA\n2A\n2AAA\n2BAA\n10AAA\nAAA10\nAAA2\nAAAA2\nAAB2\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsAll() {
		String input = "sort sortAppTestAll.txt";
		String expected = "\n  \n! hit\n!@#%\n//WARNING//\n/4run\n/ru28!*#\n/run script\n3 damage dealt\n30 damage dealt\n6 damage\n6 damage dealt\nGain 30 more exp\nGain0205's message:\nGained [30] gold\nGained [6] exp\ncome here\ncommand unknown\ngain 30 more exp\nok\nokay\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	@Test
	public void testSortStringsAllNumerically() {
		String input = "sort -n sortAppTestAll.txt";
		String expected = "\n  \n! hit\n!@#%\n//WARNING//\n/4run\n/ru28!*#\n/run script\n3 damage dealt\n6 damage\n6 damage dealt\n30 damage dealt\nGain 30 more exp\nGain0205's message:\nGained [30] gold\nGained [6] exp\ncome here\ncommand unknown\ngain 30 more exp\nok\nokay\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	

}
