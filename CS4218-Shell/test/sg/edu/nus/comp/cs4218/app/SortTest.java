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
	
	// Although SortApplication should know whether any numbers are included when deciding which interface to employ so that
	// methods not dealing with any numbers (e.g. sortStringCapital) will never be invoked with commands including option -n,
	// below tests will still test for numeric sort
	
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

	// file.txt contents:
	//  burger
	//  bumper
	//  bandage
	//  car
	//  arms
	@Test
	public void testSortStringsSimple() {
		String input = "sort file.txt";
		String expected = "arms\nbandage\nbumper\nburger\ncar\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  burger
	//  bumper
	//  bandage
	//  car
	//  arms
	@Test
	public void testSortStringsSimpleNumerically() {
		String input = "sort -n file.txt";
		String expected = "arms\nbandage\nbumper\nburger\ncar\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  XP
	//  WINDOWS
	//  VISTA
	//  MICROSOFT
	@Test
	public void testSortStringsCapital() {
		String input = "sort file.txt";
		String expected = "MICROSOFT\nVISTA\nWINDOWS\nXP\n";
		assertEquals(expected, sortApp.sortStringsCapital(input));
	}
	
	// file.txt contents:
	//  XP
	//  WINDOWS
	//  VISTA
	//  MICROSOFT
	@Test
	public void testSortStringsCapitalNumerically() {
		String input = "sort -n file.txt";
		String expected = "MICROSOFT\nVISTA\nWINDOWS\nXP\n";
		assertEquals(expected, sortApp.sortStringsCapital(input));
	}
	
	// file.txt contents:
	//  102
	//  12
	//  20
	//  2017
	//  2
	@Test
	public void testSortStringsNumbers() {
		String input = "sort file.txt";
		String expected = "102\n12\n2\n20\n2017\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  102
	//  12
	//  20
	//  2017
	//  2
	@Test
	public void testSortStringsNumbersNumerically() {
		String input = "sort -n file.txt";
		String expected = "2\n12\n20\n102\n2017\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  @~@
	//  >_<
	//  @#$%,
	//  @ @;
	//  `*.&'
	@Test
	public void testSortStringsSpecialChars() {
		String input = "sort file.txt";
		String expected = "`*.&'\n>_<\n@~@\n@ @;\n@#$%,\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  @~@
	//  >_<
	//  @#$%,
	//  @ @;
	//  `*.&'
	@Test
	public void testSortStringsSpecialCharsNumerically() {
		String input = "sort -n file.txt";
		String expected = "`*.&'\n>_<\n@~@\n@ @;\n@#$%,\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  UN
	//  V
	//  underthesea
	//  Unlimited
	//  vault
	@Test
	public void testSortStringsSimpleCapital() {
		String input = "sort file.txt";
		String expected = "UN\nUnlimited\nunderthesea\nV\nvault\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  UN
	//  V
	//  underthesea
	//  Unlimited
	//  vault
	@Test
	public void testSortStringsSimpleCapitalNumerically() {
		String input = "sort -n file.txt";
		String expected = "UN\nUnlimited\nunderthesea\nV\nvault\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  obj7
	//  10th
	//  object5
	//  5th
	//  object20
	@Test
	public void testSortStringsSimpleNumbers() {
		String input = "sort file.txt";
		String expected = "10th\n5th\nobj7\nobject20\nobject5";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  obj7
	//  10th
	//  object5
	//  5th
	//  object20
	@Test
	public void testSortStringsSimpleNumbersNumerically() {
		String input = "sort -n file.txt";
		String expected = "5th\n10th\nobj7\nobject20\nobject5 ";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  br*g*t
	//  #i)
	//  bre@k
	//  #..
	//  continue
	//  
	@Test
	public void testSortStringsSimpleSpecialChars() {
		String input = "sort file.txt";
		String expected = "#..\n#i)\nbr*g*t\nbre@k\ncontinue";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  br*g*t
	//  #i)
	//  bre@k
	//  #..
	//  continue
	//  
	@Test
	public void testSortStringsSimpleSpecialCharsNumerically() {
		String input = "sort -n file.txt";
		String expected = "#..\n#i)\nbr*g*t\nbre@k\ncontinue";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  NUSY12SEM27
	//  NTU
	//  1YEAR
	//  100
	//  NUSY3SEM1
	//  2YR
	@Test
	public void testSortStringsCapitalNumbers() {
		String input = "sort file.txt";
		String expected = "100\n1YEAR\n2YR\nNTU\nNUSY12SEM27\nNUSY3SEM1\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  NUSY12SEM27
	//  NTU
	//  1YEAR
	//  100
	//  NUSY3SEM1
	//  2YR
	@Test
	public void testSortStringsCapitalNumbersNumerically() {
		String input = "sort -n file.txt";
		String expected = "1YEAR\n2YR\n100\nNTU\nNUSY12SEM27\nNUSY3SEM1\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  E!
	//  WAAAGH!!
	//  ? OH NO
	//  ?!#_<
	//  T^T
	//  TNT()
	@Test
	public void testSortStringsCapitalSpecialChars() {
		String input = "sort file.txt";
		String expected = "?!#_<\n? OH NO\nE!\nT^T\nTNT()\nWAAAGH!!\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  E!
	//  WAAAGH!!
	//  ? OH NO
	//  ?!#_<
	//  T^T
	//  TNT()
	@Test
	public void testSortStringsCapitalSpecialCharsNumerically() {
		String input = "sort -n file.txt";
		String expected = "?!#_<\n? OH NO\nE!\nT^T\nTNT()\nWAAAGH!!\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  3!=6
	//  3^4 = 81
	//  20 - 24 = -4;
	//  <30>
	//  <7#4>
	@Test
	public void testSortStringsNumbersSpecialChars() {
		String input = "sort file.txt";
		String expected = "<30>\n<7#4>\n20 - 24 = -4;\n3^4 = 81\n3!=6\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  3!=6
	//  3^4 = 81
	//  20 - 24 = -4;
	//  <30>
	//  <7#4>
	@Test
	public void testSortStringsNumbersSpecialCharsNumerically() {
		String input = "sort -n file.txt";
		String expected = "<30>\n<7#4>\n3^4 = 81\n3!=6\n20 - 24 = -4;\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  M4Sherman
	//  2ndGenTanks
	//  M24Chaffee
	//  M5Stuart
	//  10
	@Test
	public void testSortStringsSimpleCapitalNumbers() {
		String input = "sort file.txt";
		String expected = "10\n2ndGenTanks\nM24Chaffee\nM4Sherman\nM5Stuart\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  M4Sherman
	//  2ndGenTanks
	//  M24Chaffee
	//  M5Stuart
	//  10
	@Test
	public void testSortStringsSimpleCapitalNumbersNumerically() {
		String input = "sort -n file.txt";
		String expected = "2ndGenTanks\n10\nM24Chaffee\nM4Sherman\nM5Stuart\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  GOTO
	//  go out & play
	//  Go
	//   
	//  g
	@Test
	public void testSortStringsSimpleCapitalSpecialChars() {
		String input = "sort file.txt";
		String expected = " \ng\ngo out & play\nGOTO\nGo\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  GOTO
	//  go out & play
	//  Go
	//   
	//  g
	@Test
	public void testSortStringsSimpleCapitalSpecialCharsNumerically() {
		String input = "sort -n file.txt";
		String expected = " \ng\ngo out & play\nGOTO\nGo\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  if(val=10);
	//  if (val=20);
	//  if (val=5)
	//  if null
	//  if2thenvar=10
	@Test
	public void testSortStringsSimpleNumbersSpecialChars() {
		String input = "sort file.txt";
		String expected = "if (val=5)\nif (val=20);\nif null\nif(val=10);\nif2thenvar=10\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  if(val=10);
	//  if (val=20);
	//  if (val=5)
	//  if null
	//  if2thenvar=10
	@Test
	public void testSortStringsSimpleNumbersSpecialCharsNumerically() {
		String input = "sort -n file.txt";
		String expected = "if (val=5)\nif (val=20);\nif null\nif(val=10);\nif2thenvar=10\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  <TITLE>
	//  R2-D2
	//  10 STATIONS
	//  RAILWAY STATION
	//  2#mark
	//  R@G
	@Test
	public void testSortStringsCapitalNumbersSpecialChars() {
		String input = "sort file.txt";
		String expected = "<TITLE>\n10 STATIONS\n2#mark\nR@G\nR2-D2\nRAILWAY STATION\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  <TITLE>
	//  R2-D2
	//  10 STATIONS
	//  RAILWAY STATION
	//  2#mark
	//  R@G
	@Test
	public void testSortStringsCapitalNumbersSpecialCharsNumerically() {
		String input = "sort -n file.txt";
		String expected = "<TITLE>\n2#mark\n10 STATIONS\nR@G\nR2-D2\nRAILWAY STATION\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  L33T
	//  LT15
	//  literals
	//  10 litres
	//  3rd Place
	//  #4
	//  (Last test)
	@Test
	public void testSortStringsAll() {
		String input = "sort file.txt";
		String expected = "#4\n(Last test)\n10 litres\n3rd place\nL33T\nLT15\nliterals\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file.txt contents:
	//  L33T
	//  LT15
	//  literals
	//  10 litres
	//  3rd Place
	//  #4
	//  (Last test) 
	@Test
	public void testSortStringsAllNumerically() {
		String input = "sort -n file.txt";
		String expected = "#4\n(Last test)\n3rd place\n10 litres\nL33T\nLT15\nliterals\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	

}
