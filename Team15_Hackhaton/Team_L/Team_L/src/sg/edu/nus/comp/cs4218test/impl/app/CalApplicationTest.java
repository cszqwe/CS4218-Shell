package sg.edu.nus.comp.cs4218test.impl.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.exception.CalException;
import sg.edu.nus.comp.cs4218.impl.app.CalApplication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test suite for the cal application.
 */

public class CalApplicationTest {

	private ArrayList<ArrayList<String>> actualResult = new ArrayList<>();
	private final ArrayList<ArrayList<String>> expectedResult = new ArrayList<>();
	private OutputStream stdout;

	@Before
	public void setup() {
		stdout = new ByteArrayOutputStream();
	}

	@After
	public void teardown() throws IOException {
		stdout.flush();
	}

	@Test
	public void testMonthValidityCheckBelowAcceptable() {
		CalApplication calApp = new CalApplication();

		try {
			calApp.checkValidMonth(0);
			fail("Should throw exception");
		} catch (CalException e) {
			assertEquals("cal: Invalid month input", e.getMessage());
		}
	}

	@Test
	public void testMonthValidityCheckNormalAcceptable() {
		CalApplication calApp = new CalApplication();

		try {
			boolean monthStatus = calApp.checkValidMonth(5);
			assertEquals(true, monthStatus);
		} catch (CalException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testMonthValidityCheckBeyondAcceptable() {
		CalApplication calApp = new CalApplication();

		try {
			calApp.checkValidMonth(13);
			fail("Should throw exception");
		} catch (CalException e) {
			assertEquals("cal: Invalid month input", e.getMessage());
		}
	}

	@Test
	public void testYearValidityCheckBelowAcceptable() {
		CalApplication calApp = new CalApplication();

		try {
			calApp.checkValidYear(1751);
			fail("Should throw exception");
		} catch (CalException e) {
			assertEquals("cal: Invalid year input", e.getMessage());
		}
	}

	@Test
	public void testYearValidityCheckNormalAcceptable() {
		CalApplication calApp = new CalApplication();

		try {
			boolean yearStatus = calApp.checkValidYear(2000);
			assertEquals(true, yearStatus);
		} catch (CalException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testDayOfWeekSundayZero() {
		int actualResult = CalApplication.dayOfWeek(2, 5, 2017);
		assertEquals(0, actualResult);
	}

	@Test
	public void testDayOfWeekMondayOne() {
		int actualResult = CalApplication.dayOfWeek(2, 13, 2017);
		assertEquals(1, actualResult);
	}

	@Test
	public void testDayOfWeekSaturdaySix() {
		int actualResult = CalApplication.dayOfWeek(2, 18, 2017);
		assertEquals(6, actualResult);
	}

	@Test
	public void testLeapYearCheckFourHundreds() {
		boolean isLeapYear = CalApplication.isLeapYear(2000);
		assertTrue(isLeapYear);
	}

	@Test
	public void testLeapYearCheckHundredAndFourDivisible() {
		boolean isLeapYear = CalApplication.isLeapYear(1900);
		assertFalse(isLeapYear);
	}

	@Test
	public void testLeapYearCheckNormalFourDivisible() {
		boolean isLeapYear = CalApplication.isLeapYear(1944);
		assertTrue(isLeapYear);
	}

	@Test
	public void testGetCalArrayForMonthStartZero() {
		actualResult = new ArrayList<ArrayList<String>>();
		actualResult = CalApplication.getCalArrayForMonth(5, 0, 2017);

		ArrayList<String> weekArray = new ArrayList<String>();
		weekArray.add(" 1 ");
		weekArray.add(" 2 ");
		weekArray.add(" 3 ");
		weekArray.add(" 4 ");
		weekArray.add(" 5 ");
		weekArray.add(" 6 ");
		weekArray.add(" 7 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add(" 8 ");
		weekArray.add(" 9 ");
		weekArray.add("10 ");
		weekArray.add("11 ");
		weekArray.add("12 ");
		weekArray.add("13 ");
		weekArray.add("14 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add("15 ");
		weekArray.add("16 ");
		weekArray.add("17 ");
		weekArray.add("18 ");
		weekArray.add("19 ");
		weekArray.add("20 ");
		weekArray.add("21 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add("22 ");
		weekArray.add("23 ");
		weekArray.add("24 ");
		weekArray.add("25 ");
		weekArray.add("26 ");
		weekArray.add("27 ");
		weekArray.add("28 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add("29 ");
		weekArray.add("30 ");
		weekArray.add("31 ");
		expectedResult.add(weekArray);

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testGetCalArrayForMonthStartOne() {
		actualResult = new ArrayList<ArrayList<String>>();
		actualResult = CalApplication.getCalArrayForMonth(5, 1, 2017);

		ArrayList<String> weekArray = new ArrayList<String>();
		weekArray.add("   ");
		weekArray.add(" 1 ");
		weekArray.add(" 2 ");
		weekArray.add(" 3 ");
		weekArray.add(" 4 ");
		weekArray.add(" 5 ");
		weekArray.add(" 6 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add(" 7 ");
		weekArray.add(" 8 ");
		weekArray.add(" 9 ");
		weekArray.add("10 ");
		weekArray.add("11 ");
		weekArray.add("12 ");
		weekArray.add("13 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add("14 ");
		weekArray.add("15 ");
		weekArray.add("16 ");
		weekArray.add("17 ");
		weekArray.add("18 ");
		weekArray.add("19 ");
		weekArray.add("20 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add("21 ");
		weekArray.add("22 ");
		weekArray.add("23 ");
		weekArray.add("24 ");
		weekArray.add("25 ");
		weekArray.add("26 ");
		weekArray.add("27 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add("28 ");
		weekArray.add("29 ");
		weekArray.add("30 ");
		weekArray.add("31 ");
		expectedResult.add(weekArray);

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testGetCalArrayForLeapYearFeb() {
		actualResult = new ArrayList<ArrayList<String>>();
		actualResult = CalApplication.getCalArrayForMonth(2, 1, 2016);

		ArrayList<String> weekArray = new ArrayList<String>();
		weekArray.add("   ");
		weekArray.add(" 1 ");
		weekArray.add(" 2 ");
		weekArray.add(" 3 ");
		weekArray.add(" 4 ");
		weekArray.add(" 5 ");
		weekArray.add(" 6 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add(" 7 ");
		weekArray.add(" 8 ");
		weekArray.add(" 9 ");
		weekArray.add("10 ");
		weekArray.add("11 ");
		weekArray.add("12 ");
		weekArray.add("13 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add("14 ");
		weekArray.add("15 ");
		weekArray.add("16 ");
		weekArray.add("17 ");
		weekArray.add("18 ");
		weekArray.add("19 ");
		weekArray.add("20 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add("21 ");
		weekArray.add("22 ");
		weekArray.add("23 ");
		weekArray.add("24 ");
		weekArray.add("25 ");
		weekArray.add("26 ");
		weekArray.add("27 ");
		expectedResult.add(weekArray);
		weekArray = new ArrayList<String>();
		weekArray.add("28 ");
		weekArray.add("29 ");
		expectedResult.add(weekArray);

		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void testPrintCalWithMondayFirst() {
		CalApplication calApp = new CalApplication();
		String actualAnswer = calApp.printCalWithMondayFirst("1,2017");
		String expectedAnswer = "   January 2017" + System.lineSeparator() + "Mo Tu We Th Fr Sa Su"
				+ System.lineSeparator() + "                   1 " + System.lineSeparator() + " 2  3  4  5  6  7  8 "
				+ System.lineSeparator() + " 9 10 11 12 13 14 15 " + System.lineSeparator() + "16 17 18 19 20 21 22 "
				+ System.lineSeparator() + "23 24 25 26 27 28 29 " + System.lineSeparator() + "30 31 "
				+ System.lineSeparator();

		assertEquals(expectedAnswer, actualAnswer);
	}

	@Test
	public void testPrintCalWithSundayFirst() {
		CalApplication calApp = new CalApplication();
		String actualAnswer = calApp.printCal("1,2017");

		String expectedAnswer = "   January 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa"
				+ System.lineSeparator() + " 1  2  3  4  5  6  7 " + System.lineSeparator() + " 8  9 10 11 12 13 14 "
				+ System.lineSeparator() + "15 16 17 18 19 20 21 " + System.lineSeparator() + "22 23 24 25 26 27 28 "
				+ System.lineSeparator() + "29 30 31 " + System.lineSeparator();

		assertEquals(expectedAnswer, actualAnswer);
	}

	@Test
	public void testCreateGridCalendarMondayFirst() {
		CalApplication calApp = new CalApplication();
		String actualAnswer = calApp.create3by4Cal("0,2016");
		String expectedAnswer = "     January 2016          February 2016          March 2016     "
				+ System.lineSeparator() + "Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   "
				+ System.lineSeparator() + "             1  2  3    1  2  3  4  5  6  7       1  2  3  4  5  6   "
				+ System.lineSeparator() + " 4  5  6  7  8  9 10    8  9 10 11 12 13 14    7  8  9 10 11 12 13   "
				+ System.lineSeparator() + "11 12 13 14 15 16 17   15 16 17 18 19 20 21   14 15 16 17 18 19 20   "
				+ System.lineSeparator() + "18 19 20 21 22 23 24   22 23 24 25 26 27 28   21 22 23 24 25 26 27   "
				+ System.lineSeparator() + "25 26 27 28 29 30 31   29                     28 29 30 31            "
				+ System.lineSeparator() + "     April 2016              May 2016              June 2016     "
				+ System.lineSeparator() + "Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   "
				+ System.lineSeparator() + "             1  2  3                      1          1  2  3  4  5   "
				+ System.lineSeparator() + " 4  5  6  7  8  9 10    2  3  4  5  6  7  8    6  7  8  9 10 11 12   "
				+ System.lineSeparator() + "11 12 13 14 15 16 17    9 10 11 12 13 14 15   13 14 15 16 17 18 19   "
				+ System.lineSeparator() + "18 19 20 21 22 23 24   16 17 18 19 20 21 22   20 21 22 23 24 25 26   "
				+ System.lineSeparator() + "25 26 27 28 29 30      23 24 25 26 27 28 29   27 28 29 30            "
				+ System.lineSeparator() + "                       30 31                                         "
				+ System.lineSeparator() + "     July 2016          August 2016          September 2016     "
				+ System.lineSeparator() + "Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   "
				+ System.lineSeparator() + "             1  2  3    1  2  3  4  5  6  7             1  2  3  4   "
				+ System.lineSeparator() + " 4  5  6  7  8  9 10    8  9 10 11 12 13 14    5  6  7  8  9 10 11   "
				+ System.lineSeparator() + "11 12 13 14 15 16 17   15 16 17 18 19 20 21   12 13 14 15 16 17 18   "
				+ System.lineSeparator() + "18 19 20 21 22 23 24   22 23 24 25 26 27 28   19 20 21 22 23 24 25   "
				+ System.lineSeparator() + "25 26 27 28 29 30 31   29 30 31               26 27 28 29 30         "
				+ System.lineSeparator() + "     October 2016          November 2016          December 2016     "
				+ System.lineSeparator() + "Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   "
				+ System.lineSeparator() + "                1  2       1  2  3  4  5  6             1  2  3  4   "
				+ System.lineSeparator() + " 3  4  5  6  7  8  9    7  8  9 10 11 12 13    5  6  7  8  9 10 11   "
				+ System.lineSeparator() + "10 11 12 13 14 15 16   14 15 16 17 18 19 20   12 13 14 15 16 17 18   "
				+ System.lineSeparator() + "17 18 19 20 21 22 23   21 22 23 24 25 26 27   19 20 21 22 23 24 25   "
				+ System.lineSeparator() + "24 25 26 27 28 29 30   28 29 30               26 27 28 29 30 31      "
				+ System.lineSeparator() + "31                                                                   "
				+ System.lineSeparator();

		assertEquals(expectedAnswer, actualAnswer);
	}

	@Test(expected = CalException.class)
	public void testArgsIsNull() throws CalException {
		CalApplication calApp = new CalApplication();
		stdout = new ByteArrayOutputStream();
		calApp.run(null, null, stdout);
	}

	@Test(expected = CalException.class)
	public void testStdoutIsNull() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = { "2", "2017" };
		calApp.run(args, null, null);
	}

	@Test(expected = CalException.class)
	public void multipleMFromRun() throws CalException {
		CalApplication calApp = new CalApplication();
		stdout = new ByteArrayOutputStream();
		String[] args = { "-m", "-m" };
		calApp.run(args, null, stdout);
	}

	@Test(expected = CalException.class)
	public void testInvalidYrInArg() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = { "2", "2" };
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
	}

	@Test(expected = CalException.class)
	public void testInvalidMonthInArg() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = { "0", "2017" };
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
	}

	@Test(expected = CalException.class)
	public void testInvalidMonthWithCharInArg() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = { "da", "2017" };
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
	}

	@Test(expected = CalException.class)
	public void testInvalidYrWithCharInArg() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = { "1", "asd@" };
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
	}

	@Test(expected = CalException.class)
	public void testInvalidMonthAndYrInArg() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = { "0", "3" };
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
	}

	@Test
	public void testNoMonthYrInArg() throws CalException {
		// only works at current month. Current month is March.
		CalApplication calApp = new CalApplication();
		String[] args = {};
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
		String expectedAnswer = "   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa"
				+ System.lineSeparator() + "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 "
				+ System.lineSeparator() + "12 13 14 15 16 17 18 " + System.lineSeparator() + "19 20 21 22 23 24 25 "
				+ System.lineSeparator() + "26 27 28 29 30 31 " + System.lineSeparator();

		assertEquals(expectedAnswer, stdout.toString());
	}

	@Test
	public void testMonthYrInArg() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = { "3", "2017" };
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
		String expectedAnswer = "   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa"
				+ System.lineSeparator() + "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 "
				+ System.lineSeparator() + "12 13 14 15 16 17 18 " + System.lineSeparator() + "19 20 21 22 23 24 25 "
				+ System.lineSeparator() + "26 27 28 29 30 31 " + System.lineSeparator();

		assertEquals(expectedAnswer, stdout.toString());
	}

	@Test
	public void testMonthYrMonFirstInArg() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = { "-m", "1", "2017" };
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
		String expectedAnswer = "   January 2017" + System.lineSeparator() + "Mo Tu We Th Fr Sa Su"
				+ System.lineSeparator() + "                   1 " + System.lineSeparator() + " 2  3  4  5  6  7  8 "
				+ System.lineSeparator() + " 9 10 11 12 13 14 15 " + System.lineSeparator() + "16 17 18 19 20 21 22 "
				+ System.lineSeparator() + "23 24 25 26 27 28 29 " + System.lineSeparator() + "30 31 "
				+ System.lineSeparator();

		assertEquals(expectedAnswer, stdout.toString());
	}

	@Test
	public void testNoMonthYrMonFirstInArg() throws CalException {
		// only works at current month. Current month is March.
		CalApplication calApp = new CalApplication();
		String[] args = { "-m" };
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
		String expectedAnswer = "   March 2017" + System.lineSeparator() + "Mo Tu We Th Fr Sa Su"
				+ System.lineSeparator() + "       1  2  3  4  5 " + System.lineSeparator() + " 6  7  8  9 10 11 12 "
				+ System.lineSeparator() + "13 14 15 16 17 18 19 " + System.lineSeparator() + "20 21 22 23 24 25 26 "
				+ System.lineSeparator() + "27 28 29 30 31 " + System.lineSeparator();
		assertEquals(expectedAnswer, stdout.toString());
	}

	@Test
	public void testYrMonFirstInArg() throws CalException {
		// only works at current month. Current month is March.
		CalApplication calApp = new CalApplication();
		String[] args = { "-m", "2016" };
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
		String expectedAnswer = "     January 2016          February 2016          March 2016     "
				+ System.lineSeparator() + "Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   "
				+ System.lineSeparator() + "             1  2  3    1  2  3  4  5  6  7       1  2  3  4  5  6   "
				+ System.lineSeparator() + " 4  5  6  7  8  9 10    8  9 10 11 12 13 14    7  8  9 10 11 12 13   "
				+ System.lineSeparator() + "11 12 13 14 15 16 17   15 16 17 18 19 20 21   14 15 16 17 18 19 20   "
				+ System.lineSeparator() + "18 19 20 21 22 23 24   22 23 24 25 26 27 28   21 22 23 24 25 26 27   "
				+ System.lineSeparator() + "25 26 27 28 29 30 31   29                     28 29 30 31            "
				+ System.lineSeparator() + "     April 2016              May 2016              June 2016     "
				+ System.lineSeparator() + "Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   "
				+ System.lineSeparator() + "             1  2  3                      1          1  2  3  4  5   "
				+ System.lineSeparator() + " 4  5  6  7  8  9 10    2  3  4  5  6  7  8    6  7  8  9 10 11 12   "
				+ System.lineSeparator() + "11 12 13 14 15 16 17    9 10 11 12 13 14 15   13 14 15 16 17 18 19   "
				+ System.lineSeparator() + "18 19 20 21 22 23 24   16 17 18 19 20 21 22   20 21 22 23 24 25 26   "
				+ System.lineSeparator() + "25 26 27 28 29 30      23 24 25 26 27 28 29   27 28 29 30            "
				+ System.lineSeparator() + "                       30 31                                         "
				+ System.lineSeparator() + "     July 2016          August 2016          September 2016     "
				+ System.lineSeparator() + "Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   "
				+ System.lineSeparator() + "             1  2  3    1  2  3  4  5  6  7             1  2  3  4   "
				+ System.lineSeparator() + " 4  5  6  7  8  9 10    8  9 10 11 12 13 14    5  6  7  8  9 10 11   "
				+ System.lineSeparator() + "11 12 13 14 15 16 17   15 16 17 18 19 20 21   12 13 14 15 16 17 18   "
				+ System.lineSeparator() + "18 19 20 21 22 23 24   22 23 24 25 26 27 28   19 20 21 22 23 24 25   "
				+ System.lineSeparator() + "25 26 27 28 29 30 31   29 30 31               26 27 28 29 30         "
				+ System.lineSeparator() + "     October 2016          November 2016          December 2016     "
				+ System.lineSeparator() + "Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   Mo Tu We Th Fr Sa Su   "
				+ System.lineSeparator() + "                1  2       1  2  3  4  5  6             1  2  3  4   "
				+ System.lineSeparator() + " 3  4  5  6  7  8  9    7  8  9 10 11 12 13    5  6  7  8  9 10 11   "
				+ System.lineSeparator() + "10 11 12 13 14 15 16   14 15 16 17 18 19 20   12 13 14 15 16 17 18   "
				+ System.lineSeparator() + "17 18 19 20 21 22 23   21 22 23 24 25 26 27   19 20 21 22 23 24 25   "
				+ System.lineSeparator() + "24 25 26 27 28 29 30   28 29 30               26 27 28 29 30 31      "
				+ System.lineSeparator() + "31                                                                   "
				+ System.lineSeparator();
		assertEquals(expectedAnswer, stdout.toString());
	}

	@Test
	public void testYrFirstInArg() throws CalException {
		// only works at current month. Current month is March.
		CalApplication calApp = new CalApplication();
		String[] args = { "2016" };
		stdout = new ByteArrayOutputStream();
		calApp.run(args, null, stdout);
		String expectedAnswer = "     January 2016          February 2016          March 2016     "
				+ System.lineSeparator() + "Su Mo Tu We Th Fr Sa   Su Mo Tu We Th Fr Sa   Su Mo Tu We Th Fr Sa   "
				+ System.lineSeparator() + "                1  2       1  2  3  4  5  6          1  2  3  4  5   "
				+ System.lineSeparator() + " 3  4  5  6  7  8  9    7  8  9 10 11 12 13    6  7  8  9 10 11 12   "
				+ System.lineSeparator() + "10 11 12 13 14 15 16   14 15 16 17 18 19 20   13 14 15 16 17 18 19   "
				+ System.lineSeparator() + "17 18 19 20 21 22 23   21 22 23 24 25 26 27   20 21 22 23 24 25 26   "
				+ System.lineSeparator() + "24 25 26 27 28 29 30   28 29                  27 28 29 30 31         "
				+ System.lineSeparator() + "31                                                                   "
				+ System.lineSeparator() + "     April 2016              May 2016              June 2016     "
				+ System.lineSeparator() + "Su Mo Tu We Th Fr Sa   Su Mo Tu We Th Fr Sa   Su Mo Tu We Th Fr Sa   "
				+ System.lineSeparator() + "                1  2    1  2  3  4  5  6  7             1  2  3  4   "
				+ System.lineSeparator() + " 3  4  5  6  7  8  9    8  9 10 11 12 13 14    5  6  7  8  9 10 11   "
				+ System.lineSeparator() + "10 11 12 13 14 15 16   15 16 17 18 19 20 21   12 13 14 15 16 17 18   "
				+ System.lineSeparator() + "17 18 19 20 21 22 23   22 23 24 25 26 27 28   19 20 21 22 23 24 25   "
				+ System.lineSeparator() + "24 25 26 27 28 29 30   29 30 31               26 27 28 29 30         "
				+ System.lineSeparator() + "     July 2016          August 2016          September 2016     "
				+ System.lineSeparator() + "Su Mo Tu We Th Fr Sa   Su Mo Tu We Th Fr Sa   Su Mo Tu We Th Fr Sa   "
				+ System.lineSeparator() + "                1  2       1  2  3  4  5  6                1  2  3   "
				+ System.lineSeparator() + " 3  4  5  6  7  8  9    7  8  9 10 11 12 13    4  5  6  7  8  9 10   "
				+ System.lineSeparator() + "10 11 12 13 14 15 16   14 15 16 17 18 19 20   11 12 13 14 15 16 17   "
				+ System.lineSeparator() + "17 18 19 20 21 22 23   21 22 23 24 25 26 27   18 19 20 21 22 23 24   "
				+ System.lineSeparator() + "24 25 26 27 28 29 30   28 29 30 31            25 26 27 28 29 30      "
				+ System.lineSeparator() + "31                                                                   "
				+ System.lineSeparator() + "     October 2016          November 2016          December 2016     "
				+ System.lineSeparator() + "Su Mo Tu We Th Fr Sa   Su Mo Tu We Th Fr Sa   Su Mo Tu We Th Fr Sa   "
				+ System.lineSeparator() + "                   1          1  2  3  4  5                1  2  3   "
				+ System.lineSeparator() + " 2  3  4  5  6  7  8    6  7  8  9 10 11 12    4  5  6  7  8  9 10   "
				+ System.lineSeparator() + " 9 10 11 12 13 14 15   13 14 15 16 17 18 19   11 12 13 14 15 16 17   "
				+ System.lineSeparator() + "16 17 18 19 20 21 22   20 21 22 23 24 25 26   18 19 20 21 22 23 24   "
				+ System.lineSeparator() + "23 24 25 26 27 28 29   27 28 29 30            25 26 27 28 29 30 31   "
				+ System.lineSeparator() + "30 31                                                                "
				+ System.lineSeparator();
		assertEquals(expectedAnswer, stdout.toString());
	}

	/*** ---------------------TDD Tests--------------------- */
	@Test
	public void printCalendarWithoutInputsFromRun() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = new String[0];
		calApp.run(args, null, stdout);
		System.out.flush();
		assertEquals("   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa" + System.lineSeparator()
				+ "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 " + System.lineSeparator()
				+ "12 13 14 15 16 17 18 " + System.lineSeparator() + "19 20 21 22 23 24 25 " + System.lineSeparator()
				+ "26 27 28 29 30 31 " + System.lineSeparator(), stdout.toString());
	}

	@Test
	public void printCalendarMonthYearFromRun() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = new String[2];
		args[0] = "03";
		args[1] = "2017";
		calApp.run(args, null, stdout);
		System.out.flush();
		assertEquals("   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa" + System.lineSeparator()
				+ "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 " + System.lineSeparator()
				+ "12 13 14 15 16 17 18 " + System.lineSeparator() + "19 20 21 22 23 24 25 " + System.lineSeparator()
				+ "26 27 28 29 30 31 " + System.lineSeparator(), stdout.toString());
	}

	@Test
	public void printCalendarMonthYearFromRunSingleDigit() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = new String[2];
		args[0] = "3";
		args[1] = "2017";
		calApp.run(args, null, stdout);
		System.out.flush();
		assertEquals("   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa" + System.lineSeparator()
				+ "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 " + System.lineSeparator()
				+ "12 13 14 15 16 17 18 " + System.lineSeparator() + "19 20 21 22 23 24 25 " + System.lineSeparator()
				+ "26 27 28 29 30 31 " + System.lineSeparator(), stdout.toString());
	}

	// run method will add 0 in front of the month
	@Test
	public void printCalendarMonthYear() {
		CalApplication calApp = new CalApplication();
		String[] args = new String[2];
		args[0] = "03";
		args[1] = "2017";
		String result = calApp.printCalForMonthYear(args[0] + "," + args[1]);
		assertEquals("   March 2017" + System.lineSeparator() + "Su Mo Tu We Th Fr Sa" + System.lineSeparator()
				+ "          1  2  3  4 " + System.lineSeparator() + " 5  6  7  8  9 10 11 " + System.lineSeparator()
				+ "12 13 14 15 16 17 18 " + System.lineSeparator() + "19 20 21 22 23 24 25 " + System.lineSeparator()
				+ "26 27 28 29 30 31 " + System.lineSeparator(), result);
	}

	@Test
	public void printCalendarMonthYearMondayFromRun() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = new String[3];
		args[0] = "-m";
		args[1] = "03";
		args[2] = "2017";
		calApp.run(args, null, stdout);
		System.out.flush();
		assertEquals(
				"  " + " March 2017" + System.lineSeparator() + "Mo Tu We Th Fr Sa Su" + System.lineSeparator()
						+ "       1  2  3  4  5 " + System.lineSeparator() + " 6  7  8  9 10 11 12 "
						+ System.lineSeparator() + "13 14 15 16 17 18 19 " + System.lineSeparator()
						+ "20 21 22 23 24 25 26 " + System.lineSeparator() + "27 28 29 30 31 " + System.lineSeparator(),
				stdout.toString());
	}

	@Test
	public void printCalendarMonthYearMondayFromRunSingleDigit() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = new String[3];
		args[0] = "-m";
		args[1] = "3";
		args[2] = "2017";
		calApp.run(args, null, stdout);
		System.out.flush();
		assertEquals(
				"  " + " March 2017" + System.lineSeparator() + "Mo Tu We Th Fr Sa Su" + System.lineSeparator()
						+ "       1  2  3  4  5 " + System.lineSeparator() + " 6  7  8  9 10 11 12 "
						+ System.lineSeparator() + "13 14 15 16 17 18 19 " + System.lineSeparator()
						+ "20 21 22 23 24 25 26 " + System.lineSeparator() + "27 28 29 30 31 " + System.lineSeparator(),
				stdout.toString());
	}

	@Test
	public void printCalendarMonthYearMonday() {
		CalApplication calApp = new CalApplication();
		String[] args = new String[3];
		args[0] = "03";
		args[1] = "2017";
		assertEquals(
				"  " + " March 2017" + System.lineSeparator() + "Mo Tu We Th Fr Sa Su" + System.lineSeparator()
						+ "       1  2  3  4  5 " + System.lineSeparator() + " 6  7  8  9 10 11 12 "
						+ System.lineSeparator() + "13 14 15 16 17 18 19 " + System.lineSeparator()
						+ "20 21 22 23 24 25 26 " + System.lineSeparator() + "27 28 29 30 31 " + System.lineSeparator(),
				calApp.printCalForMonthYearMondayFirst(args[0] + "," + args[1]));
	}

	@Test(expected = CalException.class)
	public void multipleYearFromRun() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = new String[2];
		args[0] = "1991";
		args[1] = "2007";
		calApp.run(args, null, stdout);
		System.out.flush();
	}

	@Test(expected = CalException.class)
	public void multipleMonthFromRun() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = new String[2];
		args[0] = "11";
		args[1] = "12";
		calApp.run(args, null, stdout);
		System.out.flush();
	}

	@Test(expected = CalException.class)
	public void multipleMonthStringFromRun() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = new String[2];
		args[0] = "may";
		args[1] = "april";
		calApp.run(args, null, stdout);
		System.out.flush();
	}

	@Test(expected = CalException.class)
	public void invalidMonthFromRun() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = new String[1];
		args[0] = "19";
		calApp.run(args, null, stdout);
		System.out.flush();
	}

	@Test(expected = CalException.class)
	public void invalidYear1FromRun() throws CalException {
		CalApplication calApp = new CalApplication();
		String[] args = new String[1];
		args[0] = "300";
		calApp.run(args, null, stdout);
		System.out.flush();
	}

	// Unused TDD Tests as the implementation for the args is different

	// @Test
	// public void printCalendarMondayWithoutInputsFromRun() throws CalException
	// {
	// CalApplication calApp = new CalApplication();
	// String [] args = new String[1];
	// args[0] = "-m";
	// calApp.run(args, null, stdout);
	// System.out.flush();
	// assertEquals(
	// " February 2017\nMo Tu We Th Fr Sa Su\n 1 2 3 4 5 \n"
	// + "6 7 8 9 10 11 12\n13 14 15 16 17 18 19\n20 21 22 23 24 25 26\n27 28
	// \n",
	// stdout.toString());
	// }
	//
	// @Test
	// public void printCalendarMondayWithoutInputs() {
	// CalApplication calApp = new CalApplication();
	// String [] args = new String[1];
	// args[0] = "-m";
	// assertEquals(
	// " February 2017\nMo Tu We Th Fr Sa Su\n 1 2 3 4 5 \n"
	// + "6 7 8 9 10 11 12\n13 14 15 16 17 18 19\n20 21 22 23 24 25 26\n27 28 ",
	// calApp.printCalWithMondayFirst(args[0]));
	// }

	// @Test
	// public void printCalendarYearMonday() {
	// CalApplication calApp = new CalApplication();
	// String [] args = new String[2];
	// args[0] = "-m";
	// args[1] ="2017";
	// assertEquals(" " , calApp.printCalForYearMondayFirst(args[0] + "," +
	// args[1]));
	// }
	// @Test
	// public void unorderedFromRun() throws CalException {
	// args = new String[3];
	// args[0] = _2017;
	// args[1] = "-m";
	// args[2] = "03";
	// calendarApp.run(args, null, System.out);
	// System.out.flush();
	// assertEquals(MARCH2017MON + "\n", baos.toString());
	// }

	// 10000 is not an invalid year as the future year can be checked
	// @Test(expected = CalException.class)
	// public void invalidYearFromRun() throws CalException {
	// CalApplication calApp = new CalApplication();
	// String [] args = new String[1];
	// args[0] = "10000";
	// calApp.run(args, null, stdout);
	// System.out.flush();
	// }

}
