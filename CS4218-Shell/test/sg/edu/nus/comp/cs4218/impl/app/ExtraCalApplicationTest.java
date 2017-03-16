package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CalException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.GrepException;
import sg.edu.nus.comp.cs4218.exception.HeadException;
import sg.edu.nus.comp.cs4218.exception.SedException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.SortException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.CalApplication;

/* Assumption: 1) run function will call the correct functions with the correct inputs in the correct order separated by a space
 * 2) Assume year starts from 1000
 * 3) run function will take inputs directly from shell unordered
 * 4) Args for run: unordered consisting of -m, month and year
 * 5) Args for printCall: null
 * 6) Args for printCalWithMondayFirst: -m
 * 7) Args for printCalForYear: year
 * 8) Args for printCalForYearMondayFirst: -m year
 * 9) Args for printCalForMonthYear: month year
 * 10) Args for printCalForMonthYearMondayFirst: -m month year
 */

public class ExtraCalApplicationTest {

	private CalApplication calendarApp;
	private String[] args;
	ByteArrayOutputStream baos;
	PrintStream print;
	static ShellImpl shell;
	static OutputStream os;
	
	@Before
	public void setUp() {
		shell = new ShellImpl();
		os = new ByteArrayOutputStream();
		calendarApp = new CalApplication();
		baos = new ByteArrayOutputStream();
		print = new PrintStream(baos);
		System.setOut(print);
	}



	@Test(expected = CalException.class)
	public void multipleMFromRun() throws CalException {
		args = new String[2];
		args[0] = "-m";
		args[1] = "-m";
		calendarApp.run(args, null, System.out);
		System.out.flush();
		assertEquals(
				"   February 2017\nSu Mo Tu We Th Fr Sa\n"
						+ "         1  2  3  4 \n5  6  7  8  9  10 11\n12 13 14 15 16 17 18\n19 20 21 22 23 24 25\n26 27 28 ",
				baos.toString());
	}

	@Test(expected = CalException.class)
	public void multipleYearFromRun() throws CalException {
		args = new String[2];
		args[0] = "1991";
		args[1] = "2007";
		calendarApp.run(args, null, System.out);
		System.out.flush();
	}

	@Test(expected = CalException.class)
	public void multipleMonthStringFromRun() throws CalException {
		args = new String[2];
		args[0] = "may";
		args[1] = "april";
		calendarApp.run(args, null, System.out);
		System.out.flush();
	}


	@Test(expected = CalException.class)
	public void invalidYearFromRun() throws CalException {
		args = new String[1];
		args[0] = "asd";
		calendarApp.run(args, null, System.out);
		System.out.flush();
	}

	@Test(expected = CalException.class)
	public void invalidYear1FromRun() throws CalException {
		args = new String[1];
		args[0] = "ffsf";
		calendarApp.run(args, null, System.out);
		System.out.flush();
	}
	
	
	@Test
	//Test the case of pipe
	public void pipeRedirOutputTest1() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "cal -m 1993 > call.txt | cat call.txt";
		String expected =	"                              1993\n"
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
		
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}

	@Test
	//Test the case of pipe
	public void pipeTest2() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "cal -m 1993 | cat";
		String expected =	"                              1993\n"
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
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}	

	@Test
	//Test the case of pipe
	public void pipeTest3() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "cal -m 1993 | cat | cat |cat";
		String expected =	"                              1993\n"
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
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}	

	
	@Test
	//Test the case of calling command functions
	public void complicatedCommandSubTest1() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "cal `echo -m 1993` | cat | cat |cat";
		String expected =	"                              1993\n"
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
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}

	@Test
	//Test the case of calling command functions
	public void complicatedCommandSubTest2() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "cal `echo -m 1993 | cat` | cat |cat";
		String expected =	"                              1993\n"
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
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}

	
	@Test
	//Test the case of calling command functions
	public void complicatedPipeTest1() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		String cmdline = "cal `echo -m 1993 | cat` | cat |cat | cat | cat | cat";
		String expected =	"                              1993\n"
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
		shell.parseAndEvaluate(cmdline, os);
		assertEquals(expected, os.toString());
	}


}
