package sg.edu.nus.comp.cs4218test.impl.app;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.*;

import sg.edu.nus.comp.cs4218.exception.EchoException;
import sg.edu.nus.comp.cs4218.impl.app.EchoApplication;

/**
 * Test suite for the echo application.
 *
 */

public class EchoApplicationTest {
	EchoApplication testEcho;
	InputStream stdin;
	OutputStream stdout;
	String[] stringArray;

	@Before
	public void setup() {
		testEcho = new EchoApplication();
		stdout = new ByteArrayOutputStream();
	}

	@Test
	public void testNullArgsEchoException() {
		try {
			testEcho.run(null, stdin, stdout);
			fail();
		} catch (EchoException e) {
			assertTrue(e.getMessage().contains("Null arguments"));
		}
	}

	@Test
	public void testNullOutputEchoException() {
		try {
			String[] stringArray = new String[] { "aa", "bb", "bv" };
			testEcho.run(stringArray, null, null);
		} catch (EchoException e) {
			assertTrue(e.getMessage().contains("OutputStream not provided"));
		}
	}

	@Test
	public void testAllNullParaEchoException() {
		try {
			testEcho.run(null, null, null);
		} catch (EchoException e) {
			assertTrue(e.getMessage().contains("Null arguments"));
		}
	}

	@Test(expected = NullPointerException.class)
	public void testNullItemsInStringArrayExpectNullPointer() {
		stringArray = new String[] { null, null, null };
		try {
			testEcho.run(stringArray, stdin, stdout);
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testEmptyArgString() {
		try {
			stringArray = new String[] {};

			testEcho.run(stringArray, null, stdout);

			assertEquals(System.lineSeparator(), stdout.toString());

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testEmptyString() {
		try {
			stringArray = new String[] { "" };

			testEcho.run(stringArray, null, stdout);

			assertEquals(System.lineSeparator(), stdout.toString());

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testSingleWhiteSpaceString() {
		try {
			stringArray = new String[] { " " };

			testEcho.run(stringArray, null, stdout);

			assertEquals(" "+ System.lineSeparator(), stdout.toString());

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleWhiteSpaceString() {
		try {
			stringArray = new String[] { "     " };

			testEcho.run(stringArray, null, stdout);

			assertEquals("     "+ System.lineSeparator(), stdout.toString());

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleWhiteSpaceArgsString() {
		try {
			stringArray = new String[] { "     " , "   " };

			testEcho.run(stringArray, null, stdout);

			assertEquals("         " +System.lineSeparator(), stdout.toString());

		} catch (EchoException e) {
			fail();
		}
	}

	@Test
	public void testSingleAlphabetString() {
		try {
			stringArray = new String[] { "A" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("A"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleAlphabetString() {
		try {
			stringArray = new String[] { "abc" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("abc"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}

	@Test
	public void testMultipleAlphabetArgsString() {
		try {
			stringArray = new String[] { "abc", "bb", "cc" };

			testEcho.run(stringArray, null, stdout);

			assertEquals("abc bb cc"+ System.lineSeparator(), stdout.toString());
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testSingleDigitString() {
		try {
			stringArray = new String[] { "1" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("1"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleDigitString() {
		try {
			stringArray = new String[] { "1000001111" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("1000001111"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleDigitArgsString() {
		try {
			stringArray = new String[] { "1", "2", "100" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("1 2 100"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testSingleSpecialCharString() {
		try {
			stringArray = new String[] { "#" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("#"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleSpecialCharString() {
		try {
			stringArray = new String[] { "#!@#$%^&*()_+" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("#!@#$%^&*()_+"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleSpecialCharArgsString() {
		try {
			stringArray = new String[] { "#", "!@#", "$%^&*()", "+-~" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("# !@# $%^&*() +-~"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testAlphabetDigitString() {
		try {
			stringArray = new String[] { "a12B" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("a12B"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleAlphabetDigitString() {
		try {
			stringArray = new String[] { "a33", "w33", "cbbbZZZZ", "hello world55" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("a33 w33 cbbbZZZZ hello world55"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testAlphabetSpecialCharString() {
		try {
			stringArray = new String[] { "asC<>!@~" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("asC<>!@~"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleAlphabetSpecialCharString() {
		try {
			stringArray = new String[] { "a*^&$", "CHGJ!@#", "ZZ,.////", ":?><:kl" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("a*^&$ CHGJ!@# ZZ,.//// :?><:kl" + System.lineSeparator() ));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testDigitSpecialCharString() {
		try {
			stringArray = new String[] { "102930<>!@~" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("102930<>!@~"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleDigitSpecialCharString() {
		try {
			stringArray = new String[] { "000*^&$", "020!@#", "98765,.////", ":?><:1234567890" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("000*^&$ 020!@# 98765,.//// :?><:1234567890"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testAlphabetDigitSpecialCharString() {
		try {
			stringArray = new String[] { "102ASDOUsfsjdf930<>!@~" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("102ASDOUsfsjdf930<>!@~"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleAlphabetDigitSpecialCharString() {
		try {
			stringArray = new String[] { "000SDYY*^&$", "020!@dfdaaaa#", "98765testset,.////", ":?><:123sdfdf    4567890" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("000SDYY*^&$ 020!@dfdaaaa# 98765testset,.//// :?><:123sdfdf    4567890"+ System.lineSeparator()));
		} catch (EchoException e) {
			fail();
		}
	}

	@Test
	public void testOneSingleQuotedString() {
		try {
			stringArray = new String[] { "'abc" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("'abc"+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleSingleQuotedString() {
		try {
			stringArray = new String[] { "'ab''''''c" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("'ab''''''c"+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleSingleQuotedArgsString() {
		try {
			stringArray = new String[] { "'abc'", "'2233'", "'aA1''''7B'", "'single" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("'abc' '2233' 'aA1''''7B' 'single"+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testOneBackQuotedString() {
		try {
			stringArray = new String[] { "ab`c" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("ab`c"+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleBackQuotedString() {
		try {
			stringArray = new String[] { "a``b`c" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("a``b`c"+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleBackQuotedArgsString() {
		try {
			stringArray = new String[] { "`abc`", "`2233`", "`aA1```7B`", "`single" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("`abc` `2233` `aA1```7B` `single"+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testSingleBackQuotedString() {
		try {
			stringArray = new String[] { "a'b`c" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("a'b`c"+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleSingleBackQuotedString() {
		try {
			stringArray = new String[] { "a'`'`'`''`b`````''''2394c" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("a'`'`'`''`b`````''''2394c"+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleSingleBackQuotedArgsString() {
		try {
			stringArray = new String[] { "a'`'`'`''`b`````''''2394c", "#$%``''" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals("a'`'`'`''`b`````''''2394c #$%``''"+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testAllTypesInOneString() {
		try {
			stringArray = new String[] { " '`!1Aa" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals(" '`!1Aa" + System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleAllTypesInOneString() {
		try {
			stringArray = new String[] { " '123`! 1A'ZPSa`sd`>$%^]]]<" };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals(" '123`! 1A'ZPSa`sd`>$%^]]]<"+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
	
	@Test
	public void testMultipleAllTypesInOneArgsString() {
		try {
			stringArray = new String[] { " '123`! 1A'ZPSa`sd`>$%^]]]<", "IIE   ", " ` ' '' ()*** " };

			testEcho.run(stringArray, null, stdout);

			assertTrue(stdout.toString().equals(" '123`! 1A'ZPSa`sd`>$%^]]]< IIE     ` ' '' ()*** "+ System.lineSeparator()));

		} catch (EchoException e) {
			fail();
		}
	}
}
