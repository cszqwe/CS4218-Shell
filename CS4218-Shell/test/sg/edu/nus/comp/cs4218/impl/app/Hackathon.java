package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.*;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class HackatonTests {
	private static final String EXCEPTION_NOT = "Should not throw exception";
	private static final String TEST_DIR = "testDir";

	private String initialDir = Environment.currentDirectory;
	private File testDir;
	private String currentDir;
	private String testFilePath;
	private Shell shell;
	private OutputStream stdout;

	@Before
	public void setUp() throws Exception {
		shell = new ShellImpl();
		stdout = new ByteArrayOutputStream();
		testDir = new File(TEST_DIR);		
		testDir.mkdir();
		
		currentDir = Environment.currentDirectory + File.separator + testDir;
		testFilePath = currentDir + File.separator + "test.txt";
		
		File testFile = new File(testFilePath);
		testFile.createNewFile();
		Environment.currentDirectory = currentDir;
	}

	@After
	public void tearDown() throws Exception {
		Environment.currentDirectory = initialDir;
	}

	

	
	/**
	 * 
	 * 
	 * Expected: Invalid year for cal
	 * Actual : 3 by 4 grid for year -1 / month for year -1
	 * Description : Negative Year should be invalid
	 * Reference : Project Description page 11 
	 * @throws ShellException 
	 * @throws AbstractApplicationException **
	 * 
	 */
	@Test(expected = CalException.class)
	public void testCalInvalidNegativeYear() throws AbstractApplicationException, ShellException {
		String input = "cal -m -1";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		shell.parseAndEvaluate(input, stdout);
		
	}

    /**
     * Expected: Prints the first 3 lines of the file [mac specific]
     * Actual: File not found on mac.
     * Description: all valid head command will not work on mac. Found assumption that file works on windows
     *              but does not work on Ubuntu. However, project's requirement states that file separator should
     *              use java's file.separator instead of hardcoded \\.
     * Reference: Project Description page 1
     * @throws AbstractApplicationException
     * @throws ShellException
     */
    @Test
    public void testHeadValidArgsOnMac() throws AbstractApplicationException, ShellException {
        String input = "head -n 3 test.txt";
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        String expected = "line 1\nline 2\nline 3\n";

        shell.parseAndEvaluate(input, stdout);
        assertEquals(expected, stdout.toString());
    }

    /**
     * Expected: Prints the last 5 lines of the file [mac specific]
     * Actual: File not found on mac.
     * Description: all valid tail command will not work on mac. Found assumption that file works on windows
     *              but does not work on Ubuntu. However, project's requirement states that file separator should
     *              use java's file.separator instead of hardcoded \\.
     * Reference: Project Description page 1
     * @throws AbstractApplicationException
     * @throws ShellException
     */
    @Test
    public void testTailValidArgsOnMac() throws AbstractApplicationException, ShellException {
        String input = "tail -n 5 test2.txt";
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();
        String expected = "line 8\nline 9\nline 10\nline 11\nline 12\n";

        shell.parseAndEvaluate(input, stdout);
        assertEquals(expected, stdout.toString());
    }

	/**
	 * 
	 * Expected: Current Directory + newline
	 * Actual : Current Directory only
	 * Description : the pwd will output current directory and newline
	 * Reference : Project Description page 10 : The pwd command reports the current working directory followed by a newline. 
	 * @throws ShellException 
	 * @throws AbstractApplicationException **
	 * 
	 */
	@Test
	public void testPwd() throws AbstractApplicationException, ShellException {
		String input = "pwd";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		shell.parseAndEvaluate(input, stdout);
		assertEquals(Environment.currentDirectory + System.lineSeparator(), stdout.toString());
	}
	
	/**
	 * 
	 * Expected: Calendar 2016 with 29 Feb
	 * Actual : Calendar 2016 without 29 Feb
	 * Description : The leap year is not handled by cal
	 * Reference 
	 * @throws ShellException 
	 * @throws AbstractApplicationException **
	 * 
	 */
	@Test
	public void testCal2016() throws AbstractApplicationException, ShellException {
		String input = "cal 2016";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		shell.parseAndEvaluate(input, stdout);
		String expectation ="                              2016\n      January               February               March        \nSu Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa\n               1  2      1  2  3  4  5  6         1  2  3  4  5 \n3  4  5  6  7  8  9   7  8  9  10 11 12 13  6  7  8  9  10 11 12\n10 11 12 13 14 15 16  14 15 16 17 18 19 20  13 14 15 16 17 18 19\n17 18 19 20 21 22 23  21 22 23 24 25 26 27  20 21 22 23 24 25 26\n24 25 26 27 28 29 30  28 29                 27 28 29 30 31      \n31                                                              \n\n\n       April                  May                   June        \nSu Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa\n               1  2   1  2  3  4  5  6  7            1  2  3  4 \n3  4  5  6  7  8  9   8  9  10 11 12 13 14  5  6  7  8  9  10 11\n10 11 12 13 14 15 16  15 16 17 18 19 20 21  12 13 14 15 16 17 18\n17 18 19 20 21 22 23  22 23 24 25 26 27 28  19 20 21 22 23 24 25\n24 25 26 27 28 29 30  29 30 31              26 27 28 29 30      \n\n\n        July                 August              September      \nSu Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa\n               1  2      1  2  3  4  5  6               1  2  3 \n3  4  5  6  7  8  9   7  8  9  10 11 12 13  4  5  6  7  8  9  10\n10 11 12 13 14 15 16  14 15 16 17 18 19 20  11 12 13 14 15 16 17\n17 18 19 20 21 22 23  21 22 23 24 25 26 27  18 19 20 21 22 23 24\n24 25 26 27 28 29 30  28 29 30 31           25 26 27 28 29 30   \n31                                                              \n\n\n      October               November              December      \nSu Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa  Su Mo Tu We Th Fr Sa\n                  1         1  2  3  4  5               1  2  3 \n2  3  4  5  6  7  8   6  7  8  9  10 11 12  4  5  6  7  8  9  10\n9  10 11 12 13 14 15  13 14 15 16 17 18 19  11 12 13 14 15 16 17\n16 17 18 19 20 21 22  20 21 22 23 24 25 26  18 19 20 21 22 23 24\n23 24 25 26 27 28 29  27 28 29 30           25 26 27 28 29 30 31\n30 31                                                           \n"; 
		assertEquals( expectation, stdout.toString());
	}
	
	
	
	
	/**
	 * 
	 * Expected: Unterminated regular expression
	 * Actual : Replaces 1 with s in  test.txt.
	 * Description : the 2nd parameter of sed should be terminated by / , if it is not terminated, it should report Exception
	 * Reference : Project Description page 13 :  specifies replacement rule, as follows: sed s/regexp/replacement/ 
	 * @throws ShellException 
	 * @throws AbstractApplicationException **
	 * 
	 */
	@Test(expected=SedException.class)
	public void testSedUnterminated() throws AbstractApplicationException, ShellException {
		String input = "sed s/1/s test.txt";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		shell.parseAndEvaluate(input, stdout);
	}
	

	/**
	 * 
	 * Expected: Head/Tail Exception
	 * Actual : Prints the first line of file
	 * Description : head / tail doesn't handle invalid option, the application still proceeds to print the lines
	 * Reference : Project Description page 11 :  OPTIONS head -n 15 means printing 15 lines. Print first 10 lines if not specified.  
	 * @throws ShellException 
	 * @throws AbstractApplicationException **
	 * 
	 */
	@Test(expected=HeadException.class)
	public void testHeadInvalidOption() throws AbstractApplicationException, ShellException {
		String input = "head -p 1 test.txt";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		shell.parseAndEvaluate(input, stdout);
	}
	
	
	/**
	 * 
	 * Expected: Head/Tail Exception due to invalid line number
	 * Actual : Prints the content of file
	 * Description : The negative line number of head should be handled and report exception to user
	 * Reference : Project Description page 11 : OPTIONS head -n 15 means printing 15 lines. Print first 10 lines if not specified.
	 * @throws ShellException 
	 * @throws AbstractApplicationException **
	 * 
	 */
	@Test(expected=TailException.class)
	public void testTailNegativeLineNumber() throws AbstractApplicationException, ShellException {
		String input = "tail -n -1 test.txt";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		shell.parseAndEvaluate(input, stdout);
	}

		/**
	 * 
	 * Expected: Head/Tail Exception due to invalid line number
	 * Actual : Prints the content of file
	 * Description : The negative line number of head should be handled and report exception to user
	 * Reference : Project Description page 11 : OPTIONS head -n 15 means printing 15 lines. Print first 10 lines if not specified.
	 * @throws ShellException 
	 * @throws AbstractApplicationException **
	 * 
	 */
	@Test(expected=HeadException.class)
	public void testHeadNegativeLineNumber() throws AbstractApplicationException, ShellException {
		String input = "head -n -1 test.txt";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		shell.parseAndEvaluate(input, stdout);
	}
	
	
	/**
	 * 
	 * Expected: Prints first 2 line of test1.txt
	 * Actual : Invalid usage
	 * Description : From the behavior of Unix terminal, when multiple valid options exists, the last valid options should be handled.
	 * Reference : Discussed on forum 19/2/17 4:30PM Question 2. Title: Re: Responses to clarifications about the project
     * @throws ShellException
	 * @throws AbstractApplicationException **
	 * 
	 */
	@Test
	public void testHeadMultipleValidOptions() throws AbstractApplicationException, ShellException {
		String input = "head -n 1 -n 2 test.txt";
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();

		shell.parseAndEvaluate(input, stdout);
		
		assertEquals("line 1" + "\n" + "line 2\n", stdout.toString());
	}

    /**
     *
     * Expected: Prints last 2 line of test1.txt
     * Actual : Invalid usage
     * Description : From the behavior of Unix terminal, when multiple valid options exists, the last valid options should be handled.
     * Reference : Discussed on forum 19/2/17 4:30PM Question 2. Title: Re: Responses to clarifications about the project
     * @throws ShellException
     * @throws AbstractApplicationException **
     *
     */
    @Test
    public void testTailMultipleValidOptions() throws AbstractApplicationException, ShellException {
        String input = "tail -n 1 -n 2 test.txt";
        ByteArrayOutputStream stdout = new ByteArrayOutputStream();

        shell.parseAndEvaluate(input, stdout);

        assertEquals("line 3" + "\n" + "line 4\n", stdout.toString());
    }
    
    
    /**
    *
    * Expected: Prints all files' name inside the working directory
    * Actual : Exception
    * Description : echo * should print all file's name. This bug is due to incorrect handling of single unquoted globbing symbol *.
    * Reference : project_description page 8 section 7.1.5
    * @throws ShellException
    * @throws AbstractApplicationException **
    *
    */
	@Test
	public void testUnquotedGlobbing() {
		String cmdline = "echo *";
		String expected = "test.txt test2.txt\n";
		
		try {
			shell.parseAndEvaluate(cmdline, stdout);
			assertEquals(expected, stdout.toString());
		} catch (AbstractApplicationException | ShellException e) {
			fail("Should not throw exception.");
		}
	}
	
    /**
    *
    * Expected: Prints *
    * Actual : Shell Exception
    * Description : This bug is due to incorrect handling of single quoted globbing symbol *
    * Reference : project_description page 8 section 7.1.5
    * @throws ShellException
    * @throws AbstractApplicationException **
    *
    */
	@Test
	public void testQuotedGlobbing() {
		String cmdline = "echo \"*\"";
		String expected = "*\n";
		
		try {
			shell.parseAndEvaluate(cmdline, stdout);
			assertEquals(expected, stdout.toString());
		} catch (AbstractApplicationException | ShellException e) {
			fail("Should not throw exception: " + e);
		}
	}
	
	
    /**
    *
    * Expected: Prints abc 
    * Actual : Exception thrown
    * Description :   This bug is due to incorrect handling of output redirection without whitespace
    * Reference : project_description page 8 section 7.1.6
    * @throws ShellException
    * @throws AbstractApplicationException **
    *
    */
	@Test
	public void testOutputRedirectionWithoutWhitespace() throws AbstractApplicationException, ShellException, IOException {
		String cmdline = "echo abc>aaa.txt";
		String expected = "abc";
		
		try {
			shell.parseAndEvaluate(cmdline, stdout);
			
			BufferedReader br = new BufferedReader(new FileReader("aaa.txt"));
			String output = "";
			String line;
			
			while ((line = br.readLine()) != null) {
				output = output + line;
			}
			
			br.close();
			 
			assertEquals(expected, output);
		} catch (Exception e) {
            fail();
        }
	}
	
	

    /**
    *
    * Expected: ShellException.class
    * Actual : Exception not thrown
    * Description :   This bug is due to incorrect handling of not closed quoted command
    * Reference : project_description page 6 section 7.1.2
    * @throws ShellException
    * @throws AbstractApplicationException **
    *
    */

	@Test(expected = ShellException.class)
	public void testNotEnclosedQuotedCommand() throws AbstractApplicationException, ShellException {
		String cmdline = "echo \"test";
		
		shell.parseAndEvaluate(cmdline, stdout);
	}
	
    /**
    *
    * Expected: ShellException.class
    * Actual : Exception not thrown
    * Description :  This bug is due to not handling the case where having backquote inside backquoted command
    * Reference : project_description page 6 section 7.1.2
    * @throws ShellException
    * @throws AbstractApplicationException **
    *
    */
	@Test(expected = ShellException.class)
	public void testBackquoteInsideBackquoteCommand() throws AbstractApplicationException, ShellException {
		String cmdline = "echo ```";
		
		shell.parseAndEvaluate(cmdline, stdout);
	}
	
	
	
}


