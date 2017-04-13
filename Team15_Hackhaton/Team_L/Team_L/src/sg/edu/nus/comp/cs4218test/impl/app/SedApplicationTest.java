package sg.edu.nus.comp.cs4218test.impl.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.exception.SedException;
import sg.edu.nus.comp.cs4218.impl.app.SedApplication;

import java.io.*;

import static org.junit.Assert.*;

public class SedApplicationTest {

    private static final String REGEX = "s/a/b/";
	private static final String FIRSTS = "Firsts";
	private static final String OUTPUT_STREAM = "OutputStreamFile";
	private static final String EXCEPTION_SHOULD = "Should throw exception";
	private static final String EXCEPTION_NOT = "Should not throw exception";
	private static final String EXCEPTION_INVALID = "sed: Invalid replacement expression.";
    private final String firstFilePath = "testDir" + File.separator + "firstTest.txt";
	private final String firstString = FIRSTS + System.lineSeparator() + "Samples";
	
    private File firstDir;

    @Before
    public void setUp() throws Exception {
        firstDir = new File("testDir");
        firstDir.mkdir();
        File testFile = new File(firstFilePath);
        testFile.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(testFile));
        writer.write(firstString);
        writer.close();
    }

    @After
    public void tearDown() throws Exception {
        for (File file : firstDir.listFiles()) {
            file.delete();
        }
        firstDir.delete();
    }

    @Test
    public void testNullArgsException() {
        SedApplication sedApp = new SedApplication();
        try {
            sedApp.run(null, System.in, System.out);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals("sed: No arguments are provided.", e.getMessage());
        }
    }

    @Test
    public void testEmptyArgsException() {
        SedApplication sedApp = new SedApplication();
        String[] args = {};
        try {
            sedApp.run(args, System.in, System.out);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals("sed: No arguments are provided.", e.getMessage());
        }
    }

    @Test
    public void testInvalidArgsException() {
        SedApplication sedApp = new SedApplication();
		String[] args = { REGEX, firstFilePath, "too many args"};
        try {
            sedApp.run(args, null, System.out);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals("sed: Invalid number of arguments are provided.", e.getMessage());
        }
    }

    @Test
    public void testNullStdinWithNoFileException() {
        SedApplication sedApp = new SedApplication();
        String[] args = {REGEX};
        try {
            sedApp.run(args, null, System.out);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals("sed: No valid input is specified.", e.getMessage());
        }
    }

    @Test
    public void testNullStoutException() {
        SedApplication sedApp = new SedApplication();
        String[] args = {REGEX, firstFilePath};
        try {
            sedApp.run(args, null, null);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals("sed: No valid output is specified.", e.getMessage());
        }
    }

    @Test
    public void testInvalidFileException() {
        SedApplication sedApp = new SedApplication();
        String[] args = {REGEX, "fakeFile"};
        try {
            sedApp.run(args, null, System.out);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals("sed: No such file or directory.", e.getMessage());
        }
    }

    @Test
    public void testNullStdinWithFileNoException() {
        SedApplication sedApp = new SedApplication();
        String[] args = {REGEX, firstFilePath};
        try {
            sedApp.run(args, null, System.out);
            assertTrue(true);
        } catch (SedException e) {
            fail(EXCEPTION_NOT);
        }
    }

    @Test
    public void testStdinWithNoFileNoException() throws IOException {
        SedApplication sedApp = new SedApplication();
        String[] args = {REGEX};
        InputStream stubInputStream = new FileInputStream(firstFilePath);

        try {
            sedApp.run(args, stubInputStream, System.out);
            assertTrue(true);
        } catch (SedException e) {
            fail(EXCEPTION_NOT);
        } finally {
            if (stubInputStream != null) {
            	stubInputStream.close();
            }
        }
    }

    @Test
    public void testInvalidReplacementExpressionStartWithNotSException() {
        SedApplication sedApp = new SedApplication();
        String[] args = {"a/a/b", firstFilePath};

        try {
            sedApp.run(args, null, System.out);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals(EXCEPTION_INVALID, e.getMessage());
        }
    }

    @Test
    public void testInvalidReplacementExpressionDifferentSymbolsUsedException() {
        SedApplication sedApp = new SedApplication();
        String[] args = {"s/a^b", firstFilePath};

        try {
            sedApp.run(args, null, System.out);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals(EXCEPTION_INVALID, e.getMessage());
        }
    }

    // Also tests for too many arguments
    @Test
    public void testInvalidReplacementExpressionSymbolsInRegexOrReplacementException() {
        SedApplication sedApp = new SedApplication();
        String[] args = {"s%a%z%z%b", firstFilePath};

        try {
            sedApp.run(args, null, System.out);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals(EXCEPTION_INVALID, e.getMessage());
        }
    }

    @Test
    public void testInvalidReplacementExpressionTooLittleArgumentsException() {
        SedApplication sedApp = new SedApplication();
        String[] args = {"s/b", firstFilePath};

        try {
            sedApp.run(args, null, System.out);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals(EXCEPTION_INVALID, e.getMessage());
        }
    }

    @Test
    public void testInvalidReplacementExpressionInvalidReaplceAllArgumentsException() {
        SedApplication sedApp = new SedApplication();
        String[] args = {"s/a/b/f", firstFilePath};

        try {
            sedApp.run(args, null, System.out);
            fail(EXCEPTION_SHOULD);
        } catch (SedException e) {
            assertEquals("sed: Invalid replacement expression for replace all.", e.getMessage());
        }
    }

    @Test
    public void testValidStdoutToFile() throws IOException {
        SedApplication sedApp = new SedApplication();
        String[] args = {REGEX, firstFilePath};
        BufferedReader reader = null;
		File testFile = new File(OUTPUT_STREAM);
        testFile.createNewFile();
        OutputStream outputStream = new FileOutputStream(testFile);
        StringBuilder strBuilder = new StringBuilder();
        String line;
        String expectedResult = (FIRSTS + System.lineSeparator() + "Sbmples");

        try {
            sedApp.run(args, null, outputStream);
            reader = new BufferedReader(new FileReader(testFile));

            if ((line = reader.readLine()) != null) {
                strBuilder.append(line);
            }

            while ((line = reader.readLine()) != null) {
                strBuilder.append(System.lineSeparator());
                strBuilder.append(line);
            }

            assertEquals(expectedResult, strBuilder.toString());
        } catch (SedException e) {
            fail(EXCEPTION_NOT);
        } finally {
        	if (reader != null) {
            	reader.close();
            }
            if (outputStream != null) {
            	outputStream.close();
            }
            testFile.delete();
        }
    }

    @Test
    public void testValidInputStreamFromFile() throws IOException {
        SedApplication sedApp = new SedApplication();
        String[] args = {REGEX};
        BufferedReader reader = null;
        File testFile = new File(OUTPUT_STREAM);

        testFile.createNewFile();

        OutputStream outputStream = null;
        InputStream stubInputStream = null;
        outputStream = new FileOutputStream(testFile);
        stubInputStream = new FileInputStream(firstFilePath);


        StringBuilder strBuilder = new StringBuilder();
        String line;
        String expectedResult = (FIRSTS + System.lineSeparator() + "Sbmples");

        try {
            sedApp.run(args, stubInputStream, outputStream);
            reader = new BufferedReader(new FileReader(testFile));

            if ((line = reader.readLine()) != null) {
                strBuilder.append(line);
            }

            while ((line = reader.readLine()) != null) {
                strBuilder.append(System.lineSeparator());
                strBuilder.append(line);
            }

            assertEquals(expectedResult, strBuilder.toString());
        } catch (SedException e) {
            fail(EXCEPTION_NOT);
        } finally {
        	if (reader != null) {
            	reader.close();
            }
            if (outputStream != null) {
            	outputStream.close();
            }
            if (stubInputStream != null) {
            	stubInputStream.close();
            }
            testFile.delete();
        }
    }
    
    @Test
    public void testValidAllReplacement() throws IOException {
        SedApplication sedApp = new SedApplication();
        String[] args = {"s/s/x/g"};
        BufferedReader reader = null;
        File testFile = new File(OUTPUT_STREAM);
        testFile.createNewFile();
        OutputStream outputStream = new FileOutputStream(testFile);
        StringBuilder strBuilder = new StringBuilder();
        String line;
        InputStream stubInputStream = new FileInputStream(firstFilePath);
        String expectedResult = ("Firxtx" + System.lineSeparator() + "Samplex");

        try {
            sedApp.run(args, stubInputStream, outputStream);
            reader = new BufferedReader(new FileReader(testFile));

            if ((line = reader.readLine()) != null) {
                strBuilder.append(line);
            }

            while ((line = reader.readLine()) != null) {
                strBuilder.append(System.lineSeparator());
                strBuilder.append(line);
            }

            assertEquals(expectedResult, strBuilder.toString());
        } catch (Exception e) {
            fail(EXCEPTION_NOT);
        } finally {
        	if (reader != null) {
            	reader.close();
            }
            if (outputStream != null) {
            	outputStream.close();
            }
            if (stubInputStream != null) {
            	stubInputStream.close();
            }
            testFile.delete();
        }
    }

    @Test
    public void testValidOtherSymbolRequireEscape() throws IOException {
        SedApplication sedApp = new SedApplication();
        String[] args = {"s^a^b^"};
        BufferedReader reader = null;
        File testFile = new File(OUTPUT_STREAM);
        testFile.createNewFile();
        OutputStream outputStream = new FileOutputStream(testFile);
        StringBuilder strBuilder = new StringBuilder();
        String line;
        InputStream stubInputStream = new FileInputStream(firstFilePath);
        String expectedResult = (FIRSTS + System.lineSeparator() + "Sbmples");

        try {
            sedApp.run(args, stubInputStream, outputStream);
            reader = new BufferedReader(new FileReader(testFile));

            if ((line = reader.readLine()) != null) {
                strBuilder.append(line);
            }

            while ((line = reader.readLine()) != null) {
                strBuilder.append(System.lineSeparator());
                strBuilder.append(line);
            }

            assertEquals(expectedResult, strBuilder.toString());
        } catch (SedException e) {
            e.printStackTrace();
            fail(EXCEPTION_NOT);
        } finally {
            if (reader != null) {
            	reader.close();
            }
            if (outputStream != null) {
            	outputStream.close();
            }
            if (stubInputStream != null) {
            	stubInputStream.close();
            }
            testFile.delete();
        }   
    }
     
}
