package sg.edu.nus.comp.cs4218.impl.app;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CalException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class CalApplicationTest {
	static CalApplication calApp;
	static InputStream is;
	static OutputStream os;
	
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static Date date = new Date();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		calApp = new CalApplication();
		is = null;
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		os = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// Cal -m month year
	@Test
	public void testCalMondayFirstWithMonthAndYear() {
		String args[] = "-m 6 1993".split(" ");
		String expected =	"     June 1993      \n" +
							"Mo Tu We Th Fr Sa Su\n" +
							"   1  2  3  4  5  6 \n" + 
							"7  8  9  10 11 12 13\n" +
							"14 15 16 17 18 19 20\n" +
							"21 22 23 24 25 26 27\n" +
							"28 29 30            ";            
		try {
			calApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// Cal month year
	@Test
	public void testCalSundayFirstWithMonthAndYear() {
		String args[] = "6 1993".split(" ");
		String expected =	"     June 1993      \n" +
							"Su Mo Tu We Th Fr Sa\n" +
							"      1  2  3  4  5 \n" + 
							"6  7  8  9  10 11 12\n" +
							"13 14 15 16 17 18 19\n" +
							"20 21 22 23 24 25 26\n" +
							"27 28 29 30         ";            
		try {
			calApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Cal -m year
	@Test
	public void testCalMondayFirstWithYearOnly() {
		String args[] = "-m 1993".split(" ");
		String expected =	""
				+ "      January               February               March        \n"
				+ "Mo Tu We Th Fr Sa Su  Mo Tu We Th Fr Sa Su  Mo Tu We Th Fr Sa Su\n"
				+ "            1  2  3   1  2  3  4  5  6  7   1  2  3  4  5  6  7 \n"
				+ "4  5  6  7  8  9  10  8  9  10 11 12 13 14  8  9  10 11 12 13 14\n"
				+ "11 12 13 14 15 16 17  15 16 17 18 19 20 21  15 16 17 18 19 20 21\n"
				+ "18 19 20 21 22 23 24  22 23 24 25 26 27 28  22 23 24 25 26 27 28\n"
				+ "25 26 27 28 29 30 31                                            \n\n\n"
				+ "       April                  May                   June        \n"
				+ "Mo Tu We Th Fr Sa Su  Mo Tu We Th Fr Sa Su  Mo Tu We Th Fr Sa Su\n"
				+ "         1  2  3  4                  1  2      1  2  3  4  5  6 \n"
				+ "5  6  7  8  9  10 11  3  4  5  6  7  8  9   7  8  9  10 11 12 13\n"
				+ "12 13 14 15 16 17 18  10 11 12 13 14 15 16  14 15 16 17 18 19 20\n"
				+ "19 20 21 22 23 24 25  17 18 19 20 21 22 23  21 22 23 24 25 26 27\n"
				+ "26 27 28 29 30        24 25 26 27 28 29 30  28 29 30            \n"
				+ "                      31                                        \n\n\n"
				+ "        July                 August              September      \n"
				+ "Mo Tu We Th Fr Sa Su  Mo Tu We Th Fr Sa Su  Mo Tu We Th Fr Sa Su\n"
				+ "         1  2  3  4                     1         1  2  3  4  5 \n"
				+ "5  6  7  8  9  10 11  2  3  4  5  6  7  8   6  7  8  9  10 11 12\n"
				+ "12 13 14 15 16 17 18  9  10 11 12 13 14 15  13 14 15 16 17 18 19\n"
				+ "19 20 21 22 23 24 25  16 17 18 19 20 21 22  20 21 22 23 24 25 26\n"
				+ "26 27 28 29 30 31     23 24 25 26 27 28 29  27 28 29 30         \n"
				+ "                      30 31                                     \n\n\n"
				+ "      October               November              December      \n"
				+ "Mo Tu We Th Fr Sa Su  Mo Tu We Th Fr Sa Su  Mo Tu We Th Fr Sa Su\n"
				+ "            1  2  3   1  2  3  4  5  6  7         1  2  3  4  5 \n"
				+ "4  5  6  7  8  9  10  8  9  10 11 12 13 14  6  7  8  9  10 11 12\n"
				+ "11 12 13 14 15 16 17  15 16 17 18 19 20 21  13 14 15 16 17 18 19\n"
				+ "18 19 20 21 22 23 24  22 23 24 25 26 27 28  20 21 22 23 24 25 26\n"
				+ "25 26 27 28 29 30 31  29 30                 27 28 29 30 31      \n"; 
		try {
			calApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Cal year
	@Test
	public void testCalSundayFirstWithYearOnly() {
		String args[] = "1993".split(" ");
		String expected =	""
				+ "      January               February               March        \n"
				+ "Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa\n"
				+ "               1  2      1  2  3  4  5  6      1  2  3  4  5  6 \n"
				+ "3  4  5  6  7  8  9   7  8  9  10 11 12 13  7  8  9  10 11 12 13\n"
				+ "10 11 12 13 14 15 16  14 15 16 17 18 19 20  14 15 16 17 18 19 20\n"
				+ "17 18 19 20 21 22 23  21 22 23 24 25 26 27  21 22 23 24 25 26 27\n"
				+ "24 25 26 27 28 29 30  28                    28 29 30 31         \n"
				+ "31                                                              \n\n\n"
				+ "       April                  May                   June        \n"
				+ "Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa\n"
				+ "            1  2  3                     1         1  2  3  4  5 \n"
				+ "4  5  6  7  8  9  10  2  3  4  5  6  7  8   6  7  8  9  10 11 12\n"
				+ "11 12 13 14 15 16 17  9  10 11 12 13 14 15  13 14 15 16 17 18 19\n"
				+ "18 19 20 21 22 23 24  16 17 18 19 20 21 22  20 21 22 23 24 25 26\n"
				+ "25 26 27 28 29 30     23 24 25 26 27 28 29  27 28 29 30         \n"
				+ "                      30 31                                     \n\n\n"
				+ "        July                 August              September      \n"
				+ "Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa\n"
				+ "            1  2  3   1  2  3  4  5  6  7            1  2  3  4 \n"
				+ "4  5  6  7  8  9  10  8  9  10 11 12 13 14  5  6  7  8  9  10 11\n"
				+ "11 12 13 14 15 16 17  15 16 17 18 19 20 21  12 13 14 15 16 17 18\n"
				+ "18 19 20 21 22 23 24  22 23 24 25 26 27 28  19 20 21 22 23 24 25\n"
				+ "25 26 27 28 29 30 31  29 30 31              26 27 28 29 30      \n\n\n"
				+ "      October               November              December      \n"
				+ "Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa\n"
				+ "               1  2      1  2  3  4  5  6            1  2  3  4 \n"
				+ "3  4  5  6  7  8  9   7  8  9  10 11 12 13  5  6  7  8  9  10 11\n"
				+ "10 11 12 13 14 15 16  14 15 16 17 18 19 20  12 13 14 15 16 17 18\n"
				+ "17 18 19 20 21 22 23  21 22 23 24 25 26 27  19 20 21 22 23 24 25\n"
				+ "24 25 26 27 28 29 30  28 29 30              26 27 28 29 30 31   \n"
				+ "31                                                              \n"; 
		try {
			calApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Cal -m
	@Test
	public void testCalMondayFirst() {
		String args[] = "-m".split(" ");
		String expected =	"     March 2017     \n" +
							"Mo Tu We Th Fr Sa Su\n" +
							"      1  2  3  4  5 \n" + 
							"6  7  8  9  10 11 12\n" +
							"13 14 15 16 17 18 19\n" +
							"20 21 22 23 24 25 26\n" +
							"27 28 29 30 31      ";            
		try {
			calApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Cal
	@Test
	public void testCalSundayFirst() {
		String args[] = new String[0];
		String expected =	"     March 2017     \n" +
							"Su Mo Tu We Th Fr Sa\n" +
							"         1  2  3  4 \n" + 
							"5  6  7  8  9  10 11\n" +
							"12 13 14 15 16 17 18\n" +
							"19 20 21 22 23 24 25\n" +
							"26 27 28 29 30 31   ";            
		try {
			calApp.run(args, is, os);
			String output = os.toString();
			assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Failing test cases
	
	// cal a
	@Test
	public void testCalSundayFirstInvalidYear() {
		String args[] = "a".split(" ");      
		try {
			calApp.run(args, is, os);
			fail("CalException should be thrown!");
			// String output = os.toString();
			// assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// cal -m z
	@Test
	public void testCalMondayFirstInvalidYear() {
		String args[] = "-m asd".split(" ");      
		try {
			calApp.run(args, is, os);
			fail("CalException should be thrown!");
			// String output = os.toString();
			// assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// cal a 1999
	@Test
	public void testCalMondayFirstInvalidM() {
		String args[] = "asd 2000".split(" ");      
		try {
			calApp.run(args, is, os);
			fail("Exception should be thrown!");
			// String output = os.toString();
			// assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// cal 0 1999
	@Test
	public void testCalSundayFirstMonthAndYearInvalidMonth() {
		String args[] = "0 2000".split(" ");      
		try {
			calApp.run(args, is, os);
			fail("Exception should be thrown!");
			// String output = os.toString();
			// assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// cal 6 zx
	@Test
	public void testCalSundayFirstMonthAndYearInvalidYear() {
		String args[] = "6 dfv".split(" ");      
		try {
			calApp.run(args, is, os);
			fail("Exception should be thrown!");
			// String output = os.toString();
			// assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// cal a b
	@Test
	public void testCalSundayFirstMonthAndYearInvalidMonthAndYear() {
		String args[] = "fg dfv".split(" ");      
		try {
			calApp.run(args, is, os);
			fail("Exception should be thrown!");
			// String output = os.toString();
			// assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// cal -m 6 abc
	@Test
	public void testCalMondayFirstMonthAndYearInvalidYear() {
		String args[] = "-m 3 rgfds".split(" ");      
		try {
			calApp.run(args, is, os);
			fail("Exception should be thrown!");
			// String output = os.toString();
			// assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// cal -m 0 2000
	@Test
	public void testCalMondayFirstMonthAndYearInvalidMonth() {
		String args[] = "-m 32 2006".split(" ");      
		try {
			calApp.run(args, is, os);
			fail("Exception should be thrown!");
			// String output = os.toString();
			// assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// cal a 4 2000
	@Test
	public void testCalMondayFirstMonthAndYearInvalidM() {
		String args[] = "2asd 3 2006".split(" ");      
		try {
			calApp.run(args, is, os);
			fail("Exception should be thrown!");
			// String output = os.toString();
			// assertEquals(expected, output);
		} catch (AbstractApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

