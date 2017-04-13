package sg.edu.nus.comp.cs4218.impl.app;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTestWcApplication {

	public static boolean debug = false;

	@Test
	public void test001() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test001");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printAllCountsInFile("wc: 5: No such file\n");
		java.lang.String str6 = wcApplication0.printCharacterCountInFile("1");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("20");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "20 5 2 " + "'", str4.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "5" + "'", str8.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "2 1 1 " + "'", str10.equals("2 1 1 "));

	}

	@Test
	public void test002() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test002");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printWordCountInFile("");
		java.lang.String str9 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str11 = wcApplication0.printWordCountInFile("21");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "6 3 1 " + "'", str9.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1" + "'", str11.equals("1"));

	}

	@Test
	public void test003() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test003");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str9 = wcApplication0.printCharacterCountInStdin("19");
		java.lang.String str11 = wcApplication0.printWordCountInFile("7");
		java.lang.String str13 = wcApplication0.printWordCountInStdin("wc: 19: No such file\n");
		java.lang.String str15 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str17 = wcApplication0.printCharacterCountInFile("20 5 2 ");
		java.lang.String str19 = wcApplication0.printAllCountsInStdin("");
		// The following exception was thrown during execution in test
		// generation
		try {
			java.lang.String str24 = wcApplication0.printCountInFile("7 3 1 ", false, true, true);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "3 1 1 " + "'", str7.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "2" + "'", str9.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1" + "'", str11.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "5" + "'", str13.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "0" + "'", str15.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "7" + "'", str17.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "0 0 0 " + "'", str19.equals("0 0 0 "));

	}

	@Test
	public void test004() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test004");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str9 = wcApplication0.printCharacterCountInStdin("19");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("5");
		java.lang.String str13 = wcApplication0.printAllCountsInStdin("19");
		java.lang.String str15 = wcApplication0.printNewlineCountInStdin("wc: : No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "3 1 1 " + "'", str7.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "2" + "'", str9.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1 1 1 " + "'", str11.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "2 1 1 " + "'", str13.equals("2 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "2" + "'", str15.equals("2"));

	}

	@Test
	public void test005() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test005");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("wc: : No such file\n");
		java.lang.String str11 = wcApplication0.printCountInFile("", true, false, true);
		java.lang.String str13 = wcApplication0.printCharacterCountInFile("19 5 2 ");
		java.lang.String str15 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str17 = wcApplication0.printNewlineCountInStdin("20 5 2 ");
		java.lang.String str19 = wcApplication0.printNewlineCountInFile("5");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "19 5 2 " + "'", str6.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "wc: : No such file\n" + "'",
				str11.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "7" + "'", str13.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "0 0 0 " + "'", str15.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "1" + "'", str17.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "1" + "'", str19.equals("1"));

	}

	@Test
	public void test006() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test006");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("");
		java.lang.String str6 = wcApplication0.printNewlineCountInFile("wc: 19: No such file\n");
		java.lang.String str8 = wcApplication0.printCharacterCountInStdin("21 5 2 ");
		java.lang.String str10 = wcApplication0.printCharacterCountInStdin("wc: hi!: No such file\n");
		java.lang.String str12 = wcApplication0.printNewlineCountInFile("20");
		java.lang.String str14 = wcApplication0.printCharacterCountInStdin("7 3 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "2" + "'", str6.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "7" + "'", str8.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "22" + "'", str10.equals("22"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "6" + "'", str14.equals("6"));

	}

	@Test
	public void test007() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test007");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printWordCountInFile("3");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("0 0 0 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("0");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("2");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("wc: 2: No such file\n");
		java.lang.String str12 = wcApplication0.printNewlineCountInStdin("7");
		java.lang.String str14 = wcApplication0.printWordCountInStdin("21 5 2 ");
		java.lang.String str16 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str18 = wcApplication0.printWordCountInStdin("20 5 2 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "1" + "'", str2.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "20 5 2 " + "'", str10.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "3" + "'", str14.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "3" + "'", str16.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "3" + "'", str18.equals("3"));

	}

	@Test
	public void test008() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test008");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str10 = wcApplication0.printWordCountInFile("wc: 6: No such file\n");
		java.lang.String str12 = wcApplication0.printCharacterCountInStdin("1");
		java.lang.String str14 = wcApplication0.printWordCountInFile("7");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "5" + "'", str6.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "0 0 0 " + "'", str8.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "5" + "'", str10.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

	}

	@Test
	public void test009() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test009");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("hi!");
		java.lang.String str6 = wcApplication0.printNewlineCountInFile("1 1 1 ");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("wc: 19: No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("0");
		java.lang.String str12 = wcApplication0.printNewlineCountInStdin("3 1 1 ");
		java.lang.String str14 = wcApplication0.printWordCountInFile("1");
		java.lang.String str16 = wcApplication0.printCharacterCountInFile("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "21 5 2 " + "'", str8.equals("21 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1 1 1 " + "'", str10.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "0" + "'", str16.equals("0"));

	}

	@Test
	public void test010() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test010");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printNewlineCountInFile("2");
		java.lang.String str4 = wcApplication0.printWordCountInFile("3 1 1 ");
		java.lang.String str6 = wcApplication0.printNewlineCountInStdin("22");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "1" + "'", str2.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

	}

	@Test
	public void test011() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test011");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str9 = wcApplication0.printCountInFile("1", false, true, true);
		java.lang.String str11 = wcApplication0.printNewlineCountInFile("6");
		java.lang.String str13 = wcApplication0.printAllCountsInFile("6");
		java.lang.String str15 = wcApplication0.printAllCountsInStdin("5");
		java.lang.String str17 = wcApplication0.printWordCountInStdin("21 5 2 ");
		java.lang.String str19 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str21 = wcApplication0.printCharacterCountInFile("3 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "wc: 1: No such file\n" + "'",
				str9.equals("wc: 1: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1" + "'", str11.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1 1 1 " + "'", str13.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "1 1 1 " + "'", str15.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "3" + "'", str17.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "19" + "'", str19.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "6" + "'", str21.equals("6"));

	}

	@Test
	public void test012() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test012");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("wc: : No such file\n");
		java.lang.String str11 = wcApplication0.printCountInFile("", true, false, true);
		java.lang.String str13 = wcApplication0.printNewlineCountInStdin("hi!");
		java.lang.String str15 = wcApplication0.printCharacterCountInFile("wc: 1: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "19 5 2 " + "'", str6.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "wc: : No such file\n" + "'",
				str11.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1" + "'", str13.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "20" + "'", str15.equals("20"));

	}

	@Test
	public void test013() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test013");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printWordCountInFile("");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("6 3 1 ");
		java.lang.String str13 = wcApplication0.printAllCountsInFile("2");
		java.lang.String str15 = wcApplication0.printWordCountInStdin("");
		java.lang.String str17 = wcApplication0.printWordCountInFile("1");
		java.lang.String str19 = wcApplication0.printAllCountsInFile("20");
		java.lang.String str21 = wcApplication0.printAllCountsInStdin("1 1 1 ");
		java.lang.String str23 = wcApplication0.printAllCountsInFile("0 0 0 ");
		java.lang.String str25 = wcApplication0.printCharacterCountInFile("21 5 2 ");
		java.lang.String[] str_array28 = new java.lang.String[] { "19 5 2 ", "5" };
		java.io.InputStream inputStream29 = null;
		java.io.OutputStream outputStream30 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array28, inputStream29, outputStream30);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "0" + "'", str9.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6 3 1 " + "'", str11.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1 1 1 " + "'", str13.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "0" + "'", str15.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "1" + "'", str17.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "2 1 1 " + "'", str19.equals("2 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "6 3 1 " + "'", str21.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "6 3 1 " + "'", str23.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str25 + "' != '" + "7" + "'", str25.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array28);

	}

	@Test
	public void test014() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test014");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printWordCountInFile("");
		java.lang.String str9 = wcApplication0.printNewlineCountInStdin("wc: 1: No such file\n");
		java.lang.String str11 = wcApplication0.printCharacterCountInFile("wc: 20: No such file\n");
		java.lang.String str13 = wcApplication0.printAllCountsInStdin("wc: 2: No such file\n");
		java.lang.String str15 = wcApplication0.printNewlineCountInStdin("wc: 5: No such file\n");
		java.lang.String str17 = wcApplication0.printCharacterCountInStdin("hi!");
		java.lang.String str19 = wcApplication0.printCharacterCountInStdin("2");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "2" + "'", str9.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "21" + "'", str11.equals("21"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "20 5 2 " + "'", str13.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "2" + "'", str15.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "3" + "'", str17.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "1" + "'", str19.equals("1"));

	}

	@Test
	public void test015() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test015");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("19");
		java.lang.String str12 = wcApplication0.printCharacterCountInStdin("1 1 1 ");
		java.lang.String str14 = wcApplication0.printAllCountsInFile("6");
		java.lang.String str16 = wcApplication0.printAllCountsInFile("1");
		java.lang.String str18 = wcApplication0.printCharacterCountInFile("20 5 2 ");
		java.lang.String str20 = wcApplication0.printNewlineCountInFile("7 3 1 ");
		java.lang.String str22 = wcApplication0.printCharacterCountInStdin("wc: 3: No such file\n");
		java.lang.String str24 = wcApplication0.printWordCountInFile("6 3 1 ");
		java.lang.String str26 = wcApplication0.printAllCountsInFile("6");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6" + "'", str12.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1 1 1 " + "'", str14.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1 1 1 " + "'", str16.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "7" + "'", str18.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "1" + "'", str20.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "20" + "'", str22.equals("20"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "3" + "'", str24.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str26 + "' != '" + "1 1 1 " + "'", str26.equals("1 1 1 "));

	}

	@Test
	public void test016() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test016");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printNewlineCountInFile("wc: 1: No such file\n");
		java.lang.String str9 = wcApplication0.printCharacterCountInStdin("0 0 0 ");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("3");
		// The following exception was thrown during execution in test
		// generation
		try {
			java.lang.String str16 = wcApplication0.printCountInFile("2 1 1 ", false, false, true);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "2" + "'", str7.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "6" + "'", str9.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1 1 1 " + "'", str11.equals("1 1 1 "));

	}

	@Test
	public void test017() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test017");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("1 1 1 ");
		java.lang.String str10 = wcApplication0.printCharacterCountInFile("3 1 1 ");
		java.lang.String str12 = wcApplication0.printWordCountInFile("21 5 2 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("20 5 2 ");
		java.lang.String str16 = wcApplication0.printNewlineCountInFile("wc: hi!: No such file\n");
		java.lang.String str18 = wcApplication0.printCharacterCountInFile("5");
		java.io.InputStream inputStream19 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			java.lang.String str23 = wcApplication0.printCountInStdin(inputStream19, false, false, false);
			org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
		} catch (java.lang.NullPointerException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "6" + "'", str10.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "3" + "'", str12.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "2" + "'", str16.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "1" + "'", str18.equals("1"));

	}

	@Test
	public void test018() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test018");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printWordCountInFile("3");
		java.lang.String str4 = wcApplication0.printCharacterCountInStdin("");
		java.lang.String str6 = wcApplication0.printNewlineCountInStdin("2 1 1 ");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("3 1 1 ");
		java.lang.String str10 = wcApplication0.printWordCountInFile("19 5 2 ");
		java.lang.String str12 = wcApplication0.printCharacterCountInFile("20 5 2 ");
		java.lang.String str14 = wcApplication0.printWordCountInStdin("6 3 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "1" + "'", str2.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "6 3 1 " + "'", str8.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "3" + "'", str10.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "7" + "'", str12.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "3" + "'", str14.equals("3"));

	}

	@Test
	public void test019() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test019");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str16 = wcApplication0.printWordCountInFile("2");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "0" + "'", str14.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

	}

	@Test
	public void test020() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test020");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str12 = wcApplication0.printAllCountsInStdin("0");
		java.lang.String str14 = wcApplication0.printWordCountInStdin("wc: 5: No such file\n");
		java.lang.String str16 = wcApplication0.printCharacterCountInStdin("wc: 3: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "19 5 2 " + "'", str10.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1 1 1 " + "'", str12.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "5" + "'", str14.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "20" + "'", str16.equals("20"));

	}

	@Test
	public void test021() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test021");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("0");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("2 1 1 ");
		java.lang.String str12 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str14 = wcApplication0.printAllCountsInStdin("2");
		java.lang.String str16 = wcApplication0.printAllCountsInStdin("wc: 2: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "3" + "'", str10.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "5" + "'", str12.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1 1 1 " + "'", str14.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "20 5 2 " + "'", str16.equals("20 5 2 "));

	}

	@Test
	public void test022() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test022");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printWordCountInFile("hi!");
		java.lang.String str10 = wcApplication0.printCharacterCountInFile("7 3 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "6" + "'", str10.equals("6"));

	}

	@Test
	public void test023() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test023");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("1 1 1 ");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("21 5 2 ");
		java.lang.String str12 = wcApplication0.printNewlineCountInStdin("wc: 7: No such file\n");
		java.lang.String str14 = wcApplication0.printCharacterCountInFile("21 5 2 ");
		java.lang.String str16 = wcApplication0.printWordCountInFile("wc: 6: No such file\n");
		java.lang.String str18 = wcApplication0.printNewlineCountInFile("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "6 3 1 " + "'", str8.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "7 3 1 " + "'", str10.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "2" + "'", str12.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "7" + "'", str14.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "5" + "'", str16.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "0" + "'", str18.equals("0"));

	}

	@Test
	public void test024() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test024");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("hi!");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("");
		java.lang.String str8 = wcApplication0.printNewlineCountInStdin("0");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("wc: 7: No such file\n");
		java.lang.String str14 = wcApplication0.printAllCountsInStdin("wc: 20: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0" + "'", str6.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "20 5 2 " + "'", str12.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "21 5 2 " + "'", str14.equals("21 5 2 "));

	}

	@Test
	public void test025() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test025");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printWordCountInFile("");
		java.lang.String str9 = wcApplication0.printCharacterCountInStdin("6");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("5");
		java.lang.String str16 = wcApplication0.printCountInFile("0", true, false, false);
		java.lang.String str18 = wcApplication0.printNewlineCountInStdin("21");
		java.lang.String[] str_array23 = new java.lang.String[] { "wc: 20: No such file\n", "3 1 1 ",
				"wc: 7: No such file\n", "wc: 5: No such file\n" };
		java.io.InputStream inputStream24 = null;
		java.io.OutputStream outputStream25 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array23, inputStream24, outputStream25);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "1" + "'", str9.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1 1 1 " + "'", str11.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "wc: 0: No such file\n" + "'",
				str16.equals("wc: 0: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "1" + "'", str18.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array23);

	}

	@Test
	public void test026() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test026");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("");
		java.lang.String str6 = wcApplication0.printWordCountInFile("wc: 5: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "5" + "'", str6.equals("5"));

	}

	@Test
	public void test027() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test027");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("wc: : No such file\n");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printCharacterCountInStdin("0 0 0 ");
		java.io.InputStream inputStream11 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			java.lang.String str15 = wcApplication0.printCountInStdin(inputStream11, true, false, false);
			org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
		} catch (java.lang.NullPointerException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "19 5 2 " + "'", str6.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "6" + "'", str10.equals("6"));

	}

	@Test
	public void test028() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test028");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("19");
		java.lang.String str12 = wcApplication0.printCharacterCountInStdin("1 1 1 ");
		java.lang.String str14 = wcApplication0.printCharacterCountInFile("0 0 0 ");
		java.lang.String str16 = wcApplication0.printAllCountsInFile("wc: 6: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6" + "'", str12.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "6" + "'", str14.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "20 5 2 " + "'", str16.equals("20 5 2 "));

	}

	@Test
	public void test029() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test029");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printNewlineCountInFile("2");
		java.lang.String str4 = wcApplication0.printCharacterCountInStdin("5");
		java.lang.String str6 = wcApplication0.printCharacterCountInFile("2 1 1 ");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("7 3 1 ");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("1 1 1 ");
		java.lang.String str12 = wcApplication0.printWordCountInStdin("6 3 1 ");
		java.lang.String str14 = wcApplication0.printWordCountInStdin("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "1" + "'", str2.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "6" + "'", str6.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "6 3 1 " + "'", str8.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "6 3 1 " + "'", str10.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "3" + "'", str12.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "0" + "'", str14.equals("0"));

	}

	@Test
	public void test030() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test030");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printWordCountInFile("");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("6 3 1 ");
		java.lang.String str13 = wcApplication0.printAllCountsInFile("2");
		java.lang.String str15 = wcApplication0.printWordCountInStdin("");
		java.lang.String str17 = wcApplication0.printWordCountInFile("1");
		java.lang.String str19 = wcApplication0.printNewlineCountInFile("19 5 2 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "0" + "'", str9.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6 3 1 " + "'", str11.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1 1 1 " + "'", str13.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "0" + "'", str15.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "1" + "'", str17.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "1" + "'", str19.equals("1"));

	}

	@Test
	public void test031() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test031");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("0");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("2 1 1 ");
		java.lang.String str12 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str14 = wcApplication0.printAllCountsInStdin("2");
		java.lang.String str16 = wcApplication0.printNewlineCountInStdin("wc: 21: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "3" + "'", str10.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "5" + "'", str12.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1 1 1 " + "'", str14.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "2" + "'", str16.equals("2"));

	}

	@Test
	public void test032() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test032");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("1 1 1 ");
		java.lang.String str10 = wcApplication0.printCharacterCountInFile("3 1 1 ");
		java.lang.String str12 = wcApplication0.printWordCountInFile("21 5 2 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("20 5 2 ");
		java.lang.String str16 = wcApplication0.printAllCountsInStdin("7");
		java.lang.String str18 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str20 = wcApplication0.printAllCountsInStdin("wc: hi!: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "6" + "'", str10.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "3" + "'", str12.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1 1 1 " + "'", str16.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "3" + "'", str18.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "22 5 2 " + "'", str20.equals("22 5 2 "));

	}

	@Test
	public void test033() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test033");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str12 = wcApplication0.printCharacterCountInFile("2 1 1 ");
		java.lang.String str14 = wcApplication0.printAllCountsInFile("wc: 1: No such file\n");
		java.lang.String str16 = wcApplication0.printCharacterCountInFile("5");
		java.lang.String str18 = wcApplication0.printAllCountsInStdin("wc: 2: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "19 5 2 " + "'", str10.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6" + "'", str12.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "20 5 2 " + "'", str14.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "20 5 2 " + "'", str18.equals("20 5 2 "));

	}

	@Test
	public void test034() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test034");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printAllCountsInFile("19");
		java.lang.String str11 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str13 = wcApplication0.printAllCountsInStdin("0 0 0 ");
		java.lang.String str15 = wcApplication0.printWordCountInStdin("3");
		java.lang.String str17 = wcApplication0.printAllCountsInFile("");
		java.lang.String str19 = wcApplication0.printNewlineCountInStdin("2 1 1 ");
		java.lang.String str21 = wcApplication0.printCharacterCountInStdin("");
		java.io.InputStream inputStream22 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			java.lang.String str26 = wcApplication0.printCountInStdin(inputStream22, false, false, false);
			org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
		} catch (java.lang.NullPointerException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "2 1 1 " + "'", str9.equals("2 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "3" + "'", str11.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "6 3 1 " + "'", str13.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "1" + "'", str15.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "0 0 0 " + "'", str17.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "1" + "'", str19.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "0" + "'", str21.equals("0"));

	}

	@Test
	public void test035() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test035");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printAllCountsInFile("wc: : No such file\n");
		java.lang.String str11 = wcApplication0.printAllCountsInStdin("wc: 6: No such file\n");
		java.lang.String str13 = wcApplication0.printWordCountInStdin("0 0 0 ");
		java.lang.String[] str_array14 = new java.lang.String[] {};
		java.io.InputStream inputStream15 = null;
		java.io.OutputStream outputStream16 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array14, inputStream15, outputStream16);
			org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.WcException");
		} catch (sg.edu.nus.comp.cs4218.exception.WcException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "19 5 2 " + "'", str9.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "20 5 2 " + "'", str11.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "3" + "'", str13.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array14);

	}

	@Test
	public void test036() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test036");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("1 1 1 ");
		java.lang.String str10 = wcApplication0.printNewlineCountInFile("wc: 1: No such file\n");
		java.lang.String str12 = wcApplication0.printWordCountInStdin("0 0 0 ");
		java.lang.String str14 = wcApplication0.printCharacterCountInFile("22");
		java.lang.String str16 = wcApplication0.printAllCountsInStdin("3 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "2" + "'", str10.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "3" + "'", str12.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "2" + "'", str14.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "6 3 1 " + "'", str16.equals("6 3 1 "));

	}

	@Test
	public void test037() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test037");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printAllCountsInFile("0");
		java.lang.String str6 = wcApplication0.printWordCountInFile("wc: 20: No such file\n");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("5");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("19");
		java.lang.String str15 = wcApplication0.printCountInFile("3", false, true, true);

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "5" + "'", str6.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1 1 1 " + "'", str8.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "wc: 3: No such file\n" + "'",
				str15.equals("wc: 3: No such file\n"));

	}

	@Test
	public void test038() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test038");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printWordCountInFile("3");
		java.lang.String str4 = wcApplication0.printCharacterCountInStdin("");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("3 1 1 ");
		java.lang.String str8 = wcApplication0.printWordCountInFile("0 0 0 ");
		java.lang.String str10 = wcApplication0.printWordCountInFile("wc: 1: No such file\n");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("1 1 1 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("7 3 1 ");
		java.lang.String str16 = wcApplication0.printCharacterCountInStdin("1");
		java.lang.String str18 = wcApplication0.printWordCountInFile("20");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "1" + "'", str2.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "6 3 1 " + "'", str6.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "5" + "'", str10.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "1" + "'", str18.equals("1"));

	}

	@Test
	public void test039() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test039");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printWordCountInFile("");
		java.lang.String str9 = wcApplication0.printWordCountInStdin("2 1 1 ");
		java.lang.String str11 = wcApplication0.printNewlineCountInStdin("7");
		java.lang.String str13 = wcApplication0.printAllCountsInFile("20");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "3" + "'", str9.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1" + "'", str11.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "2 1 1 " + "'", str13.equals("2 1 1 "));

	}

	@Test
	public void test040() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test040");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str6 = wcApplication0.printCharacterCountInFile("0");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("7");
		java.lang.String str13 = wcApplication0.printCountInFile("20", true, false, true);
		java.lang.String str15 = wcApplication0.printNewlineCountInStdin("21 5 2 ");
		java.lang.String str17 = wcApplication0.printNewlineCountInStdin("wc: 6: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1 1 1 " + "'", str8.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "wc: 20: No such file\n" + "'",
				str13.equals("wc: 20: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "1" + "'", str15.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "2" + "'", str17.equals("2"));

	}

	@Test
	public void test041() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test041");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printWordCountInFile("");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("6 3 1 ");
		java.lang.String str13 = wcApplication0.printWordCountInStdin("1");
		java.lang.String str15 = wcApplication0.printAllCountsInStdin("2 1 1 ");
		java.lang.String str17 = wcApplication0.printWordCountInFile("");
		java.lang.String str19 = wcApplication0.printCharacterCountInFile("wc: 21: No such file\n");
		java.lang.String str21 = wcApplication0.printCharacterCountInFile("wc: hi!: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "0" + "'", str9.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6 3 1 " + "'", str11.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1" + "'", str13.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "6 3 1 " + "'", str15.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "0" + "'", str17.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "21" + "'", str19.equals("21"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "22" + "'", str21.equals("22"));

	}

	@Test
	public void test042() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test042");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printWordCountInFile("");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("6 3 1 ");
		java.lang.String str13 = wcApplication0.printWordCountInStdin("22 5 2 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "0" + "'", str9.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6 3 1 " + "'", str11.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "3" + "'", str13.equals("3"));

	}

	@Test
	public void test043() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test043");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str16 = wcApplication0.printAllCountsInStdin("6");
		java.lang.String str18 = wcApplication0.printAllCountsInFile("6");
		java.lang.String str20 = wcApplication0.printAllCountsInFile("3 1 1 ");
		java.lang.String str22 = wcApplication0.printAllCountsInFile("0 0 0 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "0" + "'", str14.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1 1 1 " + "'", str16.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "1 1 1 " + "'", str18.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "6 3 1 " + "'", str20.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "6 3 1 " + "'", str22.equals("6 3 1 "));

	}

	@Test
	public void test044() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test044");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printWordCountInFile("3");
		java.lang.String str4 = wcApplication0.printCharacterCountInStdin("");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("3 1 1 ");
		java.lang.String str8 = wcApplication0.printCharacterCountInStdin("1 1 1 ");
		java.lang.String str13 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str15 = wcApplication0.printCharacterCountInStdin("wc: 19: No such file\n");
		java.lang.String str17 = wcApplication0.printCharacterCountInFile("wc: 7: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "1" + "'", str2.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "6 3 1 " + "'", str6.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "6" + "'", str8.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "wc: : No such file\n" + "'",
				str13.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "21" + "'", str15.equals("21"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "20" + "'", str17.equals("20"));

	}

	@Test
	public void test045() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test045");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("1");
		java.lang.String str16 = wcApplication0.printCharacterCountInStdin("3 1 1 ");
		java.lang.String str18 = wcApplication0.printCharacterCountInFile("6");
		java.lang.String str20 = wcApplication0.printAllCountsInFile("22 5 2 ");
		java.lang.String str22 = wcApplication0.printCharacterCountInStdin("2");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "6" + "'", str16.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "1" + "'", str18.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "7 3 1 " + "'", str20.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "1" + "'", str22.equals("1"));

	}

	@Test
	public void test046() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test046");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str14 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str16 = wcApplication0.printNewlineCountInStdin("19");
		java.lang.String str18 = wcApplication0.printNewlineCountInStdin("wc: 20: No such file\n");
		java.lang.String str20 = wcApplication0.printAllCountsInFile("wc: 7: No such file\n");
		java.lang.String[] str_array24 = new java.lang.String[] { "hi!", "2 1 1 ", "22" };
		java.io.InputStream inputStream25 = null;
		java.io.OutputStream outputStream26 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array24, inputStream25, outputStream26);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "5" + "'", str12.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "19" + "'", str14.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "2" + "'", str18.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "20 5 2 " + "'", str20.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array24);

	}

	@Test
	public void test047() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test047");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("1 1 1 ");
		java.lang.String str10 = wcApplication0.printNewlineCountInFile("0 0 0 ");
		java.lang.String str12 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str14 = wcApplication0.printCharacterCountInStdin("2 1 1 ");
		java.lang.String str16 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str18 = wcApplication0.printAllCountsInFile("19");
		java.lang.String str20 = wcApplication0.printAllCountsInStdin("6");
		java.lang.String str25 = wcApplication0.printCountInFile("21", false, false, true);

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "6 3 1 " + "'", str8.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "3" + "'", str12.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "6" + "'", str14.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "2 1 1 " + "'", str18.equals("2 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "1 1 1 " + "'", str20.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str25 + "' != '" + "wc: 21: No such file\n" + "'",
				str25.equals("wc: 21: No such file\n"));

	}

	@Test
	public void test048() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test048");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("20 5 2 ");
		java.lang.String str10 = wcApplication0.printNewlineCountInStdin("3");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "7 3 1 " + "'", str8.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

	}

	@Test
	public void test049() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test049");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInStdin("6 3 1 ");
		java.lang.String str9 = wcApplication0.printAllCountsInFile("hi!");
		java.lang.String str11 = wcApplication0.printCharacterCountInStdin("7 3 1 ");
		java.lang.String str13 = wcApplication0.printCharacterCountInFile("7");
		java.lang.String str18 = wcApplication0.printCountInFile("", true, true, false);
		java.lang.String str20 = wcApplication0.printWordCountInStdin("21");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "6" + "'", str7.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "3 1 1 " + "'", str9.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6" + "'", str11.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1" + "'", str13.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "wc: : No such file\n" + "'",
				str18.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "1" + "'", str20.equals("1"));

	}

	@Test
	public void test050() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test050");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("1 1 1 ");
		java.lang.String str10 = wcApplication0.printNewlineCountInFile("0 0 0 ");
		java.lang.String str12 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str14 = wcApplication0.printCharacterCountInStdin("2 1 1 ");
		java.lang.String str16 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str18 = wcApplication0.printAllCountsInFile("3");
		java.lang.String str20 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str22 = wcApplication0.printCharacterCountInFile("2 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "6 3 1 " + "'", str8.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "3" + "'", str12.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "6" + "'", str14.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "1 1 1 " + "'", str18.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "19" + "'", str20.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "6" + "'", str22.equals("6"));

	}

	@Test
	public void test051() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test051");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("");
		java.lang.String str6 = wcApplication0.printNewlineCountInStdin("");
		java.lang.String str11 = wcApplication0.printCountInFile("19", false, true, true);
		java.lang.String str13 = wcApplication0.printAllCountsInFile("19");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0" + "'", str6.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "wc: 19: No such file\n" + "'",
				str11.equals("wc: 19: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "2 1 1 " + "'", str13.equals("2 1 1 "));

	}

	@Test
	public void test052() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test052");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str19 = wcApplication0.printCountInFile("7", false, false, true);
		java.lang.String str21 = wcApplication0.printCharacterCountInStdin("3");
		java.lang.String str23 = wcApplication0.printAllCountsInFile("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "0" + "'", str14.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "wc: 7: No such file\n" + "'",
				str19.equals("wc: 7: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "1" + "'", str21.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "0 0 0 " + "'", str23.equals("0 0 0 "));

	}

	@Test
	public void test053() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test053");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printAllCountsInFile("0");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("wc: hi!: No such file\n");
		java.io.InputStream inputStream9 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			java.lang.String str13 = wcApplication0.printCountInStdin(inputStream9, false, true, false);
			org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
		} catch (java.lang.NullPointerException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "3" + "'", str6.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "5" + "'", str8.equals("5"));

	}

	@Test
	public void test054() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test054");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printAllCountsInFile("19");
		java.lang.String str11 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str13 = wcApplication0.printAllCountsInFile("7 3 1 ");
		java.lang.String str18 = wcApplication0.printCountInFile("7", false, false, true);
		java.lang.String str20 = wcApplication0.printWordCountInFile("3 1 1 ");
		java.lang.String[] str_array22 = new java.lang.String[] { "3 1 1 " };
		java.io.InputStream inputStream23 = null;
		java.io.OutputStream outputStream24 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array22, inputStream23, outputStream24);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "2 1 1 " + "'", str9.equals("2 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "3" + "'", str11.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "6 3 1 " + "'", str13.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "wc: 7: No such file\n" + "'",
				str18.equals("wc: 7: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "3" + "'", str20.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array22);

	}

	@Test
	public void test055() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test055");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInFile("");
		java.lang.String str9 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("wc: 19: No such file\n");
		java.lang.String str13 = wcApplication0.printNewlineCountInStdin("0");
		java.lang.String str15 = wcApplication0.printNewlineCountInStdin("wc: : No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0 0 0 " + "'", str7.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "3 1 1 " + "'", str9.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "21 5 2 " + "'", str11.equals("21 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1" + "'", str13.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "2" + "'", str15.equals("2"));

	}

	@Test
	public void test056() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test056");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printWordCountInFile("");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("6 3 1 ");
		java.lang.String str13 = wcApplication0.printWordCountInStdin("1");
		java.lang.String str15 = wcApplication0.printAllCountsInFile("wc: 1: No such file\n");
		java.lang.String str17 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str22 = wcApplication0.printCountInFile("5", true, false, true);
		java.lang.String str24 = wcApplication0.printNewlineCountInFile("0 0 0 ");
		java.lang.String str26 = wcApplication0.printWordCountInStdin("wc: 22: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "0" + "'", str9.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6 3 1 " + "'", str11.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1" + "'", str13.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "20 5 2 " + "'", str15.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "5" + "'", str17.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "wc: 5: No such file\n" + "'",
				str22.equals("wc: 5: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "1" + "'", str24.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str26 + "' != '" + "5" + "'", str26.equals("5"));

	}

	@Test
	public void test057() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test057");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("1");
		java.lang.String str19 = wcApplication0.printCountInFile("", false, false, false);
		java.lang.String str21 = wcApplication0.printCharacterCountInStdin("19");
		java.lang.String str23 = wcApplication0.printNewlineCountInFile("wc: 20: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "wc: : No such file\n" + "'",
				str19.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "2" + "'", str21.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "2" + "'", str23.equals("2"));

	}

	@Test
	public void test058() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test058");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String[] str_array6 = new java.lang.String[] { "wc: hi!: No such file\n", "wc: 2: No such file\n",
				"20" };
		java.io.InputStream inputStream7 = null;
		java.io.OutputStream outputStream8 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array6, inputStream7, outputStream8);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array6);

	}

	@Test
	public void test059() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test059");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printNewlineCountInStdin("wc: 19: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "2" + "'", str6.equals("2"));

	}

	@Test
	public void test060() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test060");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("hi!");
		java.lang.String str9 = wcApplication0.printCountInFile("19", true, false, false);
		java.lang.String str11 = wcApplication0.printWordCountInStdin("2 1 1 ");
		java.lang.String str16 = wcApplication0.printCountInFile("20", true, false, true);
		java.lang.String str18 = wcApplication0.printNewlineCountInFile("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "wc: 19: No such file\n" + "'",
				str9.equals("wc: 19: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "3" + "'", str11.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "wc: 20: No such file\n" + "'",
				str16.equals("wc: 20: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "0" + "'", str18.equals("0"));

	}

	@Test
	public void test061() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test061");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInStdin("6 3 1 ");
		java.lang.String str9 = wcApplication0.printAllCountsInFile("hi!");
		java.lang.String str11 = wcApplication0.printCharacterCountInStdin("7 3 1 ");
		java.lang.String str13 = wcApplication0.printAllCountsInStdin("6 3 1 ");
		java.lang.String str18 = wcApplication0.printCountInFile("22", true, false, true);
		java.lang.String str20 = wcApplication0.printCharacterCountInStdin("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "6" + "'", str7.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "3 1 1 " + "'", str9.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6" + "'", str11.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "6 3 1 " + "'", str13.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "wc: 22: No such file\n" + "'",
				str18.equals("wc: 22: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "0" + "'", str20.equals("0"));

	}

	@Test
	public void test062() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test062");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("0");
		java.lang.String str10 = wcApplication0.printAllCountsInStdin("7 3 1 ");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("wc: 3: No such file\n");
		java.lang.String str14 = wcApplication0.printCharacterCountInStdin("wc: 20: No such file\n");
		java.lang.String str16 = wcApplication0.printNewlineCountInFile("20");
		java.lang.String str18 = wcApplication0.printWordCountInStdin("2");
		java.lang.String str20 = wcApplication0.printAllCountsInFile("21 5 2 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "6 3 1 " + "'", str10.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "20 5 2 " + "'", str12.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "21" + "'", str14.equals("21"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "1" + "'", str18.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "7 3 1 " + "'", str20.equals("7 3 1 "));

	}

	@Test
	public void test063() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test063");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("19");
		java.lang.String str12 = wcApplication0.printCharacterCountInStdin("1 1 1 ");
		java.lang.String str14 = wcApplication0.printAllCountsInFile("6");
		java.lang.String str16 = wcApplication0.printAllCountsInFile("1");
		java.lang.String str18 = wcApplication0.printCharacterCountInFile("20 5 2 ");
		java.lang.String str20 = wcApplication0.printNewlineCountInStdin("0 0 0 ");
		java.lang.String str22 = wcApplication0.printNewlineCountInStdin("wc: : No such file\n");
		java.lang.String str24 = wcApplication0.printAllCountsInStdin("wc: 22: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6" + "'", str12.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1 1 1 " + "'", str14.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1 1 1 " + "'", str16.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "7" + "'", str18.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "1" + "'", str20.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "2" + "'", str22.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "21 5 2 " + "'", str24.equals("21 5 2 "));

	}

	@Test
	public void test064() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test064");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("1 1 1 ");
		java.lang.String str10 = wcApplication0.printCharacterCountInFile("3 1 1 ");
		java.lang.String str12 = wcApplication0.printCharacterCountInFile("0 0 0 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "6" + "'", str10.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6" + "'", str12.equals("6"));

	}

	@Test
	public void test065() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test065");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInFile("hi!");
		java.lang.String str12 = wcApplication0.printNewlineCountInFile("hi!");
		java.lang.String str14 = wcApplication0.printWordCountInStdin("wc: 2: No such file\n");
		java.lang.String str19 = wcApplication0.printCountInFile("", false, false, true);
		java.lang.String str21 = wcApplication0.printWordCountInStdin("6 3 1 ");
		java.lang.String str23 = wcApplication0.printWordCountInStdin("");
		java.lang.String str25 = wcApplication0.printCharacterCountInStdin("wc: 2: No such file\n");
		java.lang.String str27 = wcApplication0.printWordCountInStdin("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "5" + "'", str14.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "wc: : No such file\n" + "'",
				str19.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "3" + "'", str21.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "0" + "'", str23.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str25 + "' != '" + "20" + "'", str25.equals("20"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str27 + "' != '" + "0" + "'", str27.equals("0"));

	}

	@Test
	public void test066() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test066");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printWordCountInFile("");
		java.lang.String str9 = wcApplication0.printNewlineCountInStdin("wc: 1: No such file\n");
		java.lang.String str11 = wcApplication0.printCharacterCountInFile("wc: 20: No such file\n");
		java.lang.String str13 = wcApplication0.printCharacterCountInStdin("2 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "2" + "'", str9.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "21" + "'", str11.equals("21"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "6" + "'", str13.equals("6"));

	}

	@Test
	public void test067() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test067");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str9 = wcApplication0.printCountInFile("", true, false, false);
		java.lang.String str11 = wcApplication0.printAllCountsInFile("1 1 1 ");
		java.lang.String str13 = wcApplication0.printNewlineCountInStdin("hi!");
		java.lang.String str15 = wcApplication0.printNewlineCountInStdin("1");
		java.lang.String str17 = wcApplication0.printCharacterCountInFile("1");
		java.lang.String str19 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str21 = wcApplication0.printAllCountsInStdin("22");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "wc: : No such file\n" + "'",
				str9.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6 3 1 " + "'", str11.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1" + "'", str13.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "1" + "'", str15.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "1" + "'", str17.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "5" + "'", str19.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "2 1 1 " + "'", str21.equals("2 1 1 "));

	}

	@Test
	public void test068() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test068");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("wc: : No such file\n");
		java.lang.String str11 = wcApplication0.printCountInFile("", true, false, true);
		java.lang.String str13 = wcApplication0.printCharacterCountInFile("3 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "19 5 2 " + "'", str6.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "wc: : No such file\n" + "'",
				str11.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "6" + "'", str13.equals("6"));

	}

	@Test
	public void test069() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test069");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printWordCountInFile("3");
		java.lang.String str4 = wcApplication0.printCharacterCountInStdin("");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("3 1 1 ");
		java.lang.String str8 = wcApplication0.printWordCountInFile("0 0 0 ");
		java.lang.String str10 = wcApplication0.printCharacterCountInStdin("2 1 1 ");
		java.lang.String str12 = wcApplication0.printWordCountInFile("0");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "1" + "'", str2.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "6 3 1 " + "'", str6.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "6" + "'", str10.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

	}

	@Test
	public void test070() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test070");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str16 = wcApplication0.printAllCountsInStdin("6");
		java.lang.String str18 = wcApplication0.printCharacterCountInFile("19");
		java.lang.String str20 = wcApplication0.printNewlineCountInFile("1 1 1 ");
		java.lang.String str22 = wcApplication0.printNewlineCountInStdin("19 5 2 ");
		java.lang.String str24 = wcApplication0.printAllCountsInStdin("6");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "0" + "'", str14.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1 1 1 " + "'", str16.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "2" + "'", str18.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "1" + "'", str20.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "1" + "'", str22.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "1 1 1 " + "'", str24.equals("1 1 1 "));

	}

	@Test
	public void test071() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test071");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str6 = wcApplication0.printCharacterCountInFile("1");
		java.lang.String str8 = wcApplication0.printNewlineCountInFile("6 3 1 ");
		java.lang.String str10 = wcApplication0.printWordCountInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("22");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0" + "'", str10.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "2 1 1 " + "'", str12.equals("2 1 1 "));

	}

	@Test
	public void test072() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test072");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str14 = wcApplication0.printCharacterCountInFile("2 1 1 ");
		java.lang.String str16 = wcApplication0.printCharacterCountInStdin("6");
		java.lang.String str18 = wcApplication0.printWordCountInStdin("21 5 2 ");
		java.lang.String str20 = wcApplication0.printWordCountInFile("3");
		java.lang.String str22 = wcApplication0.printCharacterCountInStdin("19 5 2 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "5" + "'", str12.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "6" + "'", str14.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "3" + "'", str18.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "1" + "'", str20.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "7" + "'", str22.equals("7"));

	}

	@Test
	public void test073() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test073");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str10 = wcApplication0.printWordCountInFile("wc: 6: No such file\n");
		java.lang.String str12 = wcApplication0.printCharacterCountInFile("1");
		java.lang.String str14 = wcApplication0.printCharacterCountInFile("1 1 1 ");
		java.lang.String str16 = wcApplication0.printCharacterCountInFile("0 0 0 ");
		java.lang.String str18 = wcApplication0.printCharacterCountInFile("6 3 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "5" + "'", str6.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "0 0 0 " + "'", str8.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "5" + "'", str10.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "6" + "'", str14.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "6" + "'", str16.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "6" + "'", str18.equals("6"));

	}

	@Test
	public void test074() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test074");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInFile("");
		java.lang.String str9 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str11 = wcApplication0.printWordCountInFile("19 5 2 ");
		java.lang.String str13 = wcApplication0.printNewlineCountInStdin("wc: hi!: No such file\n");
		java.lang.String str15 = wcApplication0.printWordCountInFile("3");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0 0 0 " + "'", str7.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "3 1 1 " + "'", str9.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "3" + "'", str11.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "2" + "'", str13.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "1" + "'", str15.equals("1"));

	}

	@Test
	public void test075() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test075");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str15 = wcApplication0.printCountInFile("19", true, true, false);
		java.lang.String str17 = wcApplication0.printCharacterCountInFile("7");
		java.lang.String str19 = wcApplication0.printCharacterCountInStdin("wc: 5: No such file\n");
		java.lang.String str21 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str23 = wcApplication0.printWordCountInStdin("wc: : No such file\n");
		java.lang.String str25 = wcApplication0.printAllCountsInFile("wc: hi!: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "wc: 19: No such file\n" + "'",
				str15.equals("wc: 19: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "1" + "'", str17.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "20" + "'", str19.equals("20"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "0" + "'", str21.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "5" + "'", str23.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str25 + "' != '" + "22 5 2 " + "'", str25.equals("22 5 2 "));

	}

	@Test
	public void test076() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test076");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printWordCountInFile("");
		java.lang.String str9 = wcApplication0.printCharacterCountInStdin("6");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("5");
		java.lang.String str16 = wcApplication0.printCountInFile("0", true, false, false);
		java.lang.String str18 = wcApplication0.printNewlineCountInFile("21");
		java.lang.String str20 = wcApplication0.printAllCountsInStdin("22 5 2 ");
		java.lang.String str22 = wcApplication0.printCharacterCountInFile("wc: 0: No such file\n");
		java.lang.String str24 = wcApplication0.printAllCountsInFile("6 3 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "1" + "'", str9.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1 1 1 " + "'", str11.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "wc: 0: No such file\n" + "'",
				str16.equals("wc: 0: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "1" + "'", str18.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "7 3 1 " + "'", str20.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "20" + "'", str22.equals("20"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "6 3 1 " + "'", str24.equals("6 3 1 "));

	}

	@Test
	public void test077() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test077");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printNewlineCountInFile("7");
		java.lang.String str12 = wcApplication0.printNewlineCountInFile("wc: 7: No such file\n");
		java.lang.String str14 = wcApplication0.printAllCountsInFile("wc: 21: No such file\n");
		java.lang.String str16 = wcApplication0.printAllCountsInStdin("19 5 2 ");
		java.lang.String str18 = wcApplication0.printCharacterCountInStdin("2 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "2" + "'", str12.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "21 5 2 " + "'", str14.equals("21 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "7 3 1 " + "'", str16.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "6" + "'", str18.equals("6"));

	}

	@Test
	public void test078() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test078");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printWordCountInFile("");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("6 3 1 ");
		java.lang.String str13 = wcApplication0.printAllCountsInFile("2");
		java.lang.String str15 = wcApplication0.printWordCountInStdin("");
		java.lang.String str17 = wcApplication0.printWordCountInFile("1");
		java.lang.String str19 = wcApplication0.printAllCountsInFile("20");
		java.lang.String str21 = wcApplication0.printAllCountsInStdin("1 1 1 ");
		java.lang.String str23 = wcApplication0.printWordCountInFile("7 3 1 ");
		java.lang.String str25 = wcApplication0.printWordCountInFile("");
		java.lang.String str27 = wcApplication0.printNewlineCountInFile("wc: 1: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "0" + "'", str9.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6 3 1 " + "'", str11.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1 1 1 " + "'", str13.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "0" + "'", str15.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "1" + "'", str17.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "2 1 1 " + "'", str19.equals("2 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "6 3 1 " + "'", str21.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "3" + "'", str23.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str25 + "' != '" + "0" + "'", str25.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str27 + "' != '" + "2" + "'", str27.equals("2"));

	}

	@Test
	public void test079() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test079");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInFile("0");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("2");
		java.lang.String str8 = wcApplication0.printNewlineCountInFile("0 0 0 ");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("6");
		java.lang.String str12 = wcApplication0.printWordCountInStdin("19");
		java.lang.String str14 = wcApplication0.printCharacterCountInStdin("wc: 7: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1 1 1 " + "'", str6.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "20" + "'", str14.equals("20"));

	}

	@Test
	public void test080() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test080");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInFile("2");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("7 3 1 ");
		java.lang.String str14 = wcApplication0.printAllCountsInFile("7 3 1 ");
		java.lang.String[] str_array20 = new java.lang.String[] { "0 0 0 ", "6 3 1 ", "wc: 7: No such file\n", "21",
				"wc: 21: No such file\n" };
		java.io.InputStream inputStream21 = null;
		java.io.OutputStream outputStream22 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array20, inputStream21, outputStream22);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "6 3 1 " + "'", str14.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array20);

	}

	@Test
	public void test081() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test081");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str14 = wcApplication0.printCharacterCountInFile("2 1 1 ");
		java.lang.String str16 = wcApplication0.printCharacterCountInStdin("1");
		java.lang.String str18 = wcApplication0.printNewlineCountInFile("1 1 1 ");
		java.lang.String str20 = wcApplication0.printCharacterCountInFile("19");
		java.lang.String str22 = wcApplication0.printCharacterCountInFile("wc: 22: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "5" + "'", str12.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "6" + "'", str14.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "1" + "'", str18.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "2" + "'", str20.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "21" + "'", str22.equals("21"));

	}

	@Test
	public void test082() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test082");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str6 = wcApplication0.printCharacterCountInFile("1");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: 20: No such file\n");
		java.lang.String str10 = wcApplication0.printNewlineCountInStdin("2");
		java.lang.String str12 = wcApplication0.printNewlineCountInFile("22 5 2 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "21 5 2 " + "'", str8.equals("21 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

	}

	@Test
	public void test083() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test083");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str10 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInStdin("wc: 3: No such file\n");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("0 0 0 ");
		java.lang.String str16 = wcApplication0.printAllCountsInStdin("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "5" + "'", str6.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "0 0 0 " + "'", str8.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0" + "'", str10.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "20 5 2 " + "'", str12.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "0 0 0 " + "'", str16.equals("0 0 0 "));

	}

	@Test
	public void test084() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test084");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("1");
		java.lang.String str19 = wcApplication0.printCountInFile("", false, false, false);
		java.lang.String str21 = wcApplication0.printAllCountsInFile("wc: 19: No such file\n");
		java.lang.String str23 = wcApplication0.printNewlineCountInStdin("wc: 3: No such file\n");
		java.lang.String str25 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String[] str_array27 = new java.lang.String[] { "22 5 2 " };
		java.io.InputStream inputStream28 = null;
		java.io.OutputStream outputStream29 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array27, inputStream28, outputStream29);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "wc: : No such file\n" + "'",
				str19.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "21 5 2 " + "'", str21.equals("21 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "2" + "'", str23.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str25 + "' != '" + "1" + "'", str25.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array27);

	}

	@Test
	public void test085() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test085");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("wc: 2: No such file\n");
		java.lang.String[] str_array15 = new java.lang.String[] { "wc: 0: No such file\n", "22 5 2 ", "20 5 2 ", "20" };
		java.io.InputStream inputStream16 = null;
		java.io.OutputStream outputStream17 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array15, inputStream16, outputStream17);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "5" + "'", str6.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "0 0 0 " + "'", str8.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "20 5 2 " + "'", str10.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array15);

	}

	@Test
	public void test086() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test086");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInStdin("6 3 1 ");
		java.lang.String str9 = wcApplication0.printAllCountsInFile("hi!");
		java.lang.String str11 = wcApplication0.printCharacterCountInStdin("7 3 1 ");
		java.lang.String str13 = wcApplication0.printAllCountsInFile("20 5 2 ");
		java.lang.String[] str_array15 = new java.lang.String[] { "wc: 20: No such file\n" };
		java.io.InputStream inputStream16 = null;
		java.io.OutputStream outputStream17 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array15, inputStream16, outputStream17);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "6" + "'", str7.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "3 1 1 " + "'", str9.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6" + "'", str11.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "7 3 1 " + "'", str13.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array15);

	}

	@Test
	public void test087() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test087");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str15 = wcApplication0.printCountInFile("19", true, true, false);
		java.lang.String str17 = wcApplication0.printCharacterCountInFile("7");
		java.lang.String str19 = wcApplication0.printCharacterCountInFile("");
		java.lang.String[] str_array22 = new java.lang.String[] { "wc: 2: No such file\n", "6" };
		java.io.InputStream inputStream23 = null;
		java.io.OutputStream outputStream24 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array22, inputStream23, outputStream24);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "wc: 19: No such file\n" + "'",
				str15.equals("wc: 19: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "1" + "'", str17.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "0" + "'", str19.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array22);

	}

	@Test
	public void test088() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test088");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInFile("");
		java.lang.String str9 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str11 = wcApplication0.printNewlineCountInStdin("1");
		java.lang.String str13 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str15 = wcApplication0.printNewlineCountInFile("wc: 21: No such file\n");
		java.lang.String str17 = wcApplication0.printWordCountInStdin("wc: 22: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0 0 0 " + "'", str7.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "3 1 1 " + "'", str9.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1" + "'", str11.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "0" + "'", str13.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "2" + "'", str15.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "5" + "'", str17.equals("5"));

	}

	@Test
	public void test089() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test089");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printAllCountsInFile("0");
		java.lang.String str6 = wcApplication0.printWordCountInFile("wc: 20: No such file\n");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("5");
		java.lang.String str10 = wcApplication0.printCharacterCountInStdin("22 5 2 ");
		java.lang.String str12 = wcApplication0.printAllCountsInStdin("6 3 1 ");
		java.lang.String str14 = wcApplication0.printAllCountsInStdin("wc: 1: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "5" + "'", str6.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1 1 1 " + "'", str8.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "7" + "'", str10.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "20 5 2 " + "'", str14.equals("20 5 2 "));

	}

	@Test
	public void test090() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test090");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str10 = wcApplication0.printNewlineCountInStdin("0");
		java.lang.String str12 = wcApplication0.printWordCountInFile("20 5 2 ");
		java.lang.String str14 = wcApplication0.printCharacterCountInFile("3 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "5" + "'", str6.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "0 0 0 " + "'", str8.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "3" + "'", str12.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "6" + "'", str14.equals("6"));

	}

	@Test
	public void test091() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test091");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInFile("0");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("2");
		java.lang.String str8 = wcApplication0.printNewlineCountInFile("0 0 0 ");
		java.lang.String[] str_array13 = new java.lang.String[] { "wc: hi!: No such file\n", "hi!", "hi!", "7" };
		java.io.InputStream inputStream14 = null;
		java.io.OutputStream outputStream15 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array13, inputStream14, outputStream15);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1 1 1 " + "'", str6.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array13);

	}

	@Test
	public void test092() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test092");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printWordCountInFile("3");
		java.lang.String str4 = wcApplication0.printCharacterCountInStdin("");
		java.lang.String str6 = wcApplication0.printNewlineCountInStdin("2 1 1 ");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("3 1 1 ");
		java.lang.String str10 = wcApplication0.printWordCountInFile("19 5 2 ");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str17 = wcApplication0.printCountInFile("7", true, true, false);

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "1" + "'", str2.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "6 3 1 " + "'", str8.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "3" + "'", str10.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "wc: 7: No such file\n" + "'",
				str17.equals("wc: 7: No such file\n"));

	}

	@Test
	public void test093() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test093");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInStdin("1 1 1 ");
		java.lang.String str9 = wcApplication0.printAllCountsInStdin("1 1 1 ");
		java.lang.String str11 = wcApplication0.printWordCountInFile("5");
		java.lang.String str16 = wcApplication0.printCountInFile("6", false, true, false);
		java.lang.String str21 = wcApplication0.printCountInFile("hi!", true, false, false);
		java.lang.String str23 = wcApplication0.printCharacterCountInStdin("22");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "6 3 1 " + "'", str7.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "6 3 1 " + "'", str9.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1" + "'", str11.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "wc: 6: No such file\n" + "'",
				str16.equals("wc: 6: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "wc: hi!: No such file\n" + "'",
				str21.equals("wc: hi!: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "2" + "'", str23.equals("2"));

	}

	@Test
	public void test094() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test094");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printNewlineCountInFile("19");
		java.lang.String str14 = wcApplication0.printNewlineCountInStdin("");
		java.lang.String str19 = wcApplication0.printCountInFile("0", true, false, false);
		java.lang.String str21 = wcApplication0.printWordCountInFile("wc: 3: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "0" + "'", str14.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "wc: 0: No such file\n" + "'",
				str19.equals("wc: 0: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "5" + "'", str21.equals("5"));

	}

	@Test
	public void test095() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test095");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printWordCountInFile("");
		java.lang.String str11 = wcApplication0.printNewlineCountInStdin("19 5 2 ");
		java.lang.String str13 = wcApplication0.printCharacterCountInFile("0 0 0 ");
		java.io.InputStream inputStream14 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			java.lang.String str18 = wcApplication0.printCountInStdin(inputStream14, false, false, true);
			org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
		} catch (java.lang.NullPointerException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "0" + "'", str9.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1" + "'", str11.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "6" + "'", str13.equals("6"));

	}

	@Test
	public void test096() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test096");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str9 = wcApplication0.printCountInFile("1", false, true, true);
		java.lang.String str11 = wcApplication0.printNewlineCountInFile("2 1 1 ");
		java.lang.String str13 = wcApplication0.printCharacterCountInStdin("19");
		java.lang.String str15 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str17 = wcApplication0.printNewlineCountInFile("22 5 2 ");
		java.lang.String str19 = wcApplication0.printCharacterCountInFile("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "wc: 1: No such file\n" + "'",
				str9.equals("wc: 1: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1" + "'", str11.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "2" + "'", str13.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "19" + "'", str15.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "1" + "'", str17.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "0" + "'", str19.equals("0"));

	}

	@Test
	public void test097() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test097");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printNewlineCountInFile("19");
		java.lang.String str14 = wcApplication0.printNewlineCountInStdin("");
		java.lang.String str16 = wcApplication0.printNewlineCountInFile("0");
		java.lang.String str18 = wcApplication0.printNewlineCountInFile("wc: 6: No such file\n");
		java.lang.String str20 = wcApplication0.printNewlineCountInStdin("");
		java.lang.String str22 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str24 = wcApplication0.printCharacterCountInStdin("3");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "0" + "'", str14.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "2" + "'", str18.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "0" + "'", str20.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "3 1 1 " + "'", str22.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "1" + "'", str24.equals("1"));

	}

	@Test
	public void test098() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test098");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("1");
		java.lang.String str8 = wcApplication0.printCharacterCountInFile("1");
		java.lang.String str10 = wcApplication0.printCharacterCountInFile("3");
		java.lang.String str12 = wcApplication0.printAllCountsInStdin("20");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "2 1 1 " + "'", str12.equals("2 1 1 "));

	}

	@Test
	public void test099() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test099");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str6 = wcApplication0.printNewlineCountInStdin("19");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("2");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("22 5 2 ");
		java.lang.String str12 = wcApplication0.printWordCountInFile("wc: 3: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "3" + "'", str10.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "5" + "'", str12.equals("5"));

	}

	@Test
	public void test100() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test100");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printWordCountInFile("3");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("0 0 0 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("0");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("2");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("wc: 2: No such file\n");
		java.lang.String str12 = wcApplication0.printNewlineCountInStdin("7");
		java.lang.String str14 = wcApplication0.printWordCountInStdin("22 5 2 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "1" + "'", str2.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "20 5 2 " + "'", str10.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "3" + "'", str14.equals("3"));

	}

	@Test
	public void test101() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test101");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printWordCountInFile("3");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("0 0 0 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("0");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("2");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("wc: 2: No such file\n");
		java.lang.String str12 = wcApplication0.printCharacterCountInStdin("wc: 1: No such file\n");
		java.lang.String str14 = wcApplication0.printAllCountsInFile("7");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "1" + "'", str2.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "20 5 2 " + "'", str10.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "20" + "'", str12.equals("20"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1 1 1 " + "'", str14.equals("1 1 1 "));

	}

	@Test
	public void test102() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test102");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("1 1 1 ");
		java.lang.String str10 = wcApplication0.printNewlineCountInFile("0 0 0 ");
		java.lang.String str12 = wcApplication0.printCharacterCountInStdin("0");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("2");
		java.lang.String str16 = wcApplication0.printNewlineCountInFile("21 5 2 ");
		java.lang.String str18 = wcApplication0.printCharacterCountInFile("21");
		java.lang.String str20 = wcApplication0.printNewlineCountInStdin("2 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "6 3 1 " + "'", str8.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "2" + "'", str18.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "1" + "'", str20.equals("1"));

	}

	@Test
	public void test103() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test103");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str9 = wcApplication0.printCharacterCountInStdin("19");
		java.lang.String str11 = wcApplication0.printWordCountInStdin("20");
		java.lang.String str13 = wcApplication0.printNewlineCountInFile("wc: 5: No such file\n");
		java.lang.String str15 = wcApplication0.printNewlineCountInFile("0 0 0 ");
		java.lang.String str17 = wcApplication0.printAllCountsInStdin("wc: 7: No such file\n");
		java.lang.String str19 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str21 = wcApplication0.printWordCountInFile("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "3 1 1 " + "'", str7.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "2" + "'", str9.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "1" + "'", str11.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "2" + "'", str13.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "1" + "'", str15.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "20 5 2 " + "'", str17.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "3 1 1 " + "'", str19.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "0" + "'", str21.equals("0"));

	}

	@Test
	public void test104() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test104");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str9 = wcApplication0.printCountInFile("", true, false, false);
		java.lang.String str11 = wcApplication0.printAllCountsInFile("1 1 1 ");
		java.lang.String str13 = wcApplication0.printNewlineCountInStdin("hi!");
		java.lang.String str15 = wcApplication0.printNewlineCountInStdin("1");
		java.lang.String str17 = wcApplication0.printAllCountsInFile("wc: 5: No such file\n");
		java.lang.String str19 = wcApplication0.printNewlineCountInFile("3 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "wc: : No such file\n" + "'",
				str9.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6 3 1 " + "'", str11.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1" + "'", str13.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "1" + "'", str15.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "20 5 2 " + "'", str17.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "1" + "'", str19.equals("1"));

	}

	@Test
	public void test105() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test105");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str6 = wcApplication0.printCharacterCountInFile("0");
		java.lang.String str8 = wcApplication0.printNewlineCountInFile("5");
		java.lang.String str10 = wcApplication0.printNewlineCountInFile("6");
		java.lang.String str12 = wcApplication0.printAllCountsInStdin("wc: 1: No such file\n");
		java.lang.String str14 = wcApplication0.printWordCountInFile("1");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "20 5 2 " + "'", str12.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

	}

	@Test
	public void test106() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test106");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInFile("0");
		java.lang.String str6 = wcApplication0.printAllCountsInFile("2");
		java.lang.String str8 = wcApplication0.printNewlineCountInStdin("0 0 0 ");
		java.lang.String str10 = wcApplication0.printCharacterCountInFile("22");
		java.lang.String str12 = wcApplication0.printCharacterCountInFile("19");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1 1 1 " + "'", str6.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "2" + "'", str10.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "2" + "'", str12.equals("2"));

	}

	@Test
	public void test107() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test107");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str8 = wcApplication0.printCharacterCountInStdin("wc: 0: No such file\n");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("wc: 7: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1 1 1 " + "'", str4.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "5" + "'", str6.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "20" + "'", str8.equals("20"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "5" + "'", str10.equals("5"));

	}

	@Test
	public void test108() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test108");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printNewlineCountInStdin("wc: 1: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "2" + "'", str10.equals("2"));

	}

	@Test
	public void test109() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test109");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str9 = wcApplication0.printCharacterCountInStdin("1 1 1 ");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("19 5 2 ");
		java.lang.String str13 = wcApplication0.printWordCountInFile("");
		java.lang.String str15 = wcApplication0.printAllCountsInStdin("0 0 0 ");
		java.lang.String str20 = wcApplication0.printCountInFile("hi!", true, true, false);
		java.lang.String str22 = wcApplication0.printAllCountsInStdin("wc: 3: No such file\n");
		java.lang.String str24 = wcApplication0.printNewlineCountInFile("21");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "3 1 1 " + "'", str7.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "6" + "'", str9.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "7 3 1 " + "'", str11.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "0" + "'", str13.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "6 3 1 " + "'", str15.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "wc: hi!: No such file\n" + "'",
				str20.equals("wc: hi!: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "20 5 2 " + "'", str22.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "1" + "'", str24.equals("1"));

	}

	@Test
	public void test110() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test110");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printNewlineCountInFile("7");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("wc: : No such file\n");
		java.lang.String str14 = wcApplication0.printCharacterCountInStdin("");
		java.lang.String str16 = wcApplication0.printCharacterCountInFile("19 5 2 ");
		java.lang.String str18 = wcApplication0.printWordCountInFile("5");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "19 5 2 " + "'", str12.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "0" + "'", str14.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "7" + "'", str16.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "1" + "'", str18.equals("1"));

	}

	@Test
	public void test111() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test111");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("19");
		java.lang.String str12 = wcApplication0.printWordCountInFile("1");
		java.lang.String str17 = wcApplication0.printCountInFile("6", true, true, true);
		java.lang.String str19 = wcApplication0.printNewlineCountInFile("wc: 1: No such file\n");
		java.lang.String str21 = wcApplication0.printCharacterCountInStdin("");
		java.lang.String str23 = wcApplication0.printAllCountsInFile("");
		java.lang.String str25 = wcApplication0.printAllCountsInStdin("wc: 0: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "wc: 6: No such file\n" + "'",
				str17.equals("wc: 6: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "2" + "'", str19.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "0" + "'", str21.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "0 0 0 " + "'", str23.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str25 + "' != '" + "20 5 2 " + "'", str25.equals("20 5 2 "));

	}

	@Test
	public void test112() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test112");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printAllCountsInFile("wc: 5: No such file\n");
		java.lang.String str6 = wcApplication0.printCharacterCountInFile("1");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("wc: : No such file\n");
		java.lang.String[] str_array11 = new java.lang.String[] { "wc: 1: No such file\n", "wc: 7: No such file\n" };
		java.io.InputStream inputStream12 = null;
		java.io.OutputStream outputStream13 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array11, inputStream12, outputStream13);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "20 5 2 " + "'", str4.equals("20 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "5" + "'", str8.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array11);

	}

	@Test
	public void test113() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test113");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str9 = wcApplication0.printCharacterCountInStdin("1 1 1 ");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("19 5 2 ");
		java.lang.String str13 = wcApplication0.printWordCountInFile("");
		java.lang.String str15 = wcApplication0.printAllCountsInStdin("0 0 0 ");
		java.lang.String str20 = wcApplication0.printCountInFile("hi!", true, true, false);
		java.lang.String str22 = wcApplication0.printWordCountInStdin("7 3 1 ");
		java.lang.String str24 = wcApplication0.printNewlineCountInStdin("22 5 2 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "3 1 1 " + "'", str7.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "6" + "'", str9.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "7 3 1 " + "'", str11.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "0" + "'", str13.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "6 3 1 " + "'", str15.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "wc: hi!: No such file\n" + "'",
				str20.equals("wc: hi!: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "3" + "'", str22.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "1" + "'", str24.equals("1"));

	}

	@Test
	public void test114() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test114");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("1");
		java.lang.String str19 = wcApplication0.printCountInFile("", false, false, false);
		java.lang.String str21 = wcApplication0.printAllCountsInFile("wc: 19: No such file\n");
		java.lang.String str23 = wcApplication0.printNewlineCountInStdin("wc: 3: No such file\n");
		java.lang.String str25 = wcApplication0.printNewlineCountInStdin("20");
		java.lang.String str27 = wcApplication0.printNewlineCountInFile("21");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "wc: : No such file\n" + "'",
				str19.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "21 5 2 " + "'", str21.equals("21 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "2" + "'", str23.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str25 + "' != '" + "1" + "'", str25.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str27 + "' != '" + "1" + "'", str27.equals("1"));

	}

	@Test
	public void test115() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test115");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInStdin("6 3 1 ");
		java.lang.String str9 = wcApplication0.printAllCountsInFile("hi!");
		java.lang.String str11 = wcApplication0.printCharacterCountInStdin("7 3 1 ");
		java.lang.String str13 = wcApplication0.printAllCountsInFile("20 5 2 ");
		java.lang.String str15 = wcApplication0.printWordCountInFile("2");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "6" + "'", str7.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "3 1 1 " + "'", str9.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6" + "'", str11.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "7 3 1 " + "'", str13.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "1" + "'", str15.equals("1"));

	}

	@Test
	public void test116() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test116");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("hi!");
		java.lang.String str6 = wcApplication0.printNewlineCountInFile("1 1 1 ");
		java.lang.String str8 = wcApplication0.printWordCountInFile("1 1 1 ");
		java.lang.String str10 = wcApplication0.printNewlineCountInFile("19");
		java.lang.String str12 = wcApplication0.printWordCountInStdin("wc: 20: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "5" + "'", str12.equals("5"));

	}

	@Test
	public void test117() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test117");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("19");
		java.lang.String str12 = wcApplication0.printCharacterCountInStdin("1 1 1 ");
		java.lang.String str14 = wcApplication0.printAllCountsInFile("6");
		java.lang.String str16 = wcApplication0.printAllCountsInFile("1");
		java.lang.String str18 = wcApplication0.printCharacterCountInFile("7 3 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6" + "'", str12.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1 1 1 " + "'", str14.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1 1 1 " + "'", str16.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "6" + "'", str18.equals("6"));

	}

	@Test
	public void test118() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test118");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("19");
		java.lang.String str12 = wcApplication0.printAllCountsInStdin("wc: 21: No such file\n");
		java.lang.String str14 = wcApplication0.printCharacterCountInStdin("");
		java.lang.String str16 = wcApplication0.printCharacterCountInFile("2");
		java.lang.String str18 = wcApplication0.printWordCountInFile("wc: 6: No such file\n");
		java.lang.String str20 = wcApplication0.printCharacterCountInStdin("0 0 0 ");
		java.lang.String str22 = wcApplication0.printWordCountInFile("wc: 19: No such file\n");
		java.lang.String str24 = wcApplication0.printCharacterCountInFile("wc: 3: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "21 5 2 " + "'", str12.equals("21 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "0" + "'", str14.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "5" + "'", str18.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "6" + "'", str20.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "5" + "'", str22.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "20" + "'", str24.equals("20"));

	}

	@Test
	public void test119() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test119");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("hi!");
		java.lang.String str6 = wcApplication0.printNewlineCountInFile("1 1 1 ");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("1 1 1 ");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("5");
		java.lang.String str12 = wcApplication0.printAllCountsInStdin("wc: 0: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "6 3 1 " + "'", str8.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "20 5 2 " + "'", str12.equals("20 5 2 "));

	}

	@Test
	public void test120() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test120");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printWordCountInStdin("wc: 1: No such file\n");
		java.lang.String str14 = wcApplication0.printCharacterCountInFile("0 0 0 ");
		java.lang.String str16 = wcApplication0.printWordCountInStdin("21 5 2 ");
		java.lang.String str21 = wcApplication0.printCountInFile("19", false, false, false);
		java.lang.String str23 = wcApplication0.printNewlineCountInStdin("20");
		java.lang.String str28 = wcApplication0.printCountInFile("hi!", true, true, false);

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "5" + "'", str12.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "6" + "'", str14.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "3" + "'", str16.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "wc: 19: No such file\n" + "'",
				str21.equals("wc: 19: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "1" + "'", str23.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str28 + "' != '" + "wc: hi!: No such file\n" + "'",
				str28.equals("wc: hi!: No such file\n"));

	}

	@Test
	public void test121() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test121");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("19");
		java.lang.String str12 = wcApplication0.printAllCountsInStdin("wc: 21: No such file\n");
		java.lang.String str14 = wcApplication0.printCharacterCountInStdin("");
		java.lang.String str16 = wcApplication0.printCharacterCountInFile("2");
		java.lang.String str18 = wcApplication0.printWordCountInFile("wc: 6: No such file\n");
		java.lang.String str20 = wcApplication0.printCharacterCountInStdin("0 0 0 ");
		java.lang.String str22 = wcApplication0.printAllCountsInFile("");
		java.lang.String str24 = wcApplication0.printCharacterCountInStdin("1");
		java.lang.String str26 = wcApplication0.printWordCountInStdin("2 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "21 5 2 " + "'", str12.equals("21 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "0" + "'", str14.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1" + "'", str16.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "5" + "'", str18.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "6" + "'", str20.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "0 0 0 " + "'", str22.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "1" + "'", str24.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str26 + "' != '" + "3" + "'", str26.equals("3"));

	}

	@Test
	public void test122() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test122");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("1");
		java.lang.String str8 = wcApplication0.printCharacterCountInFile("1");
		java.lang.String str10 = wcApplication0.printNewlineCountInStdin("1");
		java.lang.String str15 = wcApplication0.printCountInFile("21", false, true, false);
		java.lang.String str17 = wcApplication0.printAllCountsInStdin("20 5 2 ");
		java.lang.String str19 = wcApplication0.printNewlineCountInFile("0 0 0 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "1" + "'", str8.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "wc: 21: No such file\n" + "'",
				str15.equals("wc: 21: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "7 3 1 " + "'", str17.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "1" + "'", str19.equals("1"));

	}

	@Test
	public void test123() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test123");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInFile("1 1 1 ");
		java.lang.String str10 = wcApplication0.printNewlineCountInFile("0 0 0 ");
		java.lang.String str12 = wcApplication0.printCharacterCountInStdin("0");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("2");
		java.lang.String str16 = wcApplication0.printAllCountsInStdin("1");
		java.lang.String str18 = wcApplication0.printCharacterCountInFile("wc: 1: No such file\n");
		java.lang.String str20 = wcApplication0.printNewlineCountInStdin("");
		java.io.InputStream inputStream21 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			java.lang.String str25 = wcApplication0.printCountInStdin(inputStream21, true, false, true);
			org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
		} catch (java.lang.NullPointerException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "6 3 1 " + "'", str8.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "1 1 1 " + "'", str16.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "20" + "'", str18.equals("20"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "0" + "'", str20.equals("0"));

	}

	@Test
	public void test124() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test124");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printWordCountInFile("");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("6 3 1 ");
		java.lang.String str13 = wcApplication0.printWordCountInStdin("1");
		java.lang.String str15 = wcApplication0.printAllCountsInStdin("2 1 1 ");
		java.lang.String str20 = wcApplication0.printCountInFile("20", false, true, false);
		java.lang.String str22 = wcApplication0.printCharacterCountInStdin("wc: 0: No such file\n");
		java.lang.String str24 = wcApplication0.printWordCountInStdin("wc: 0: No such file\n");
		java.lang.String str26 = wcApplication0.printCharacterCountInFile("wc: 6: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "0" + "'", str9.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6 3 1 " + "'", str11.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1" + "'", str13.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "6 3 1 " + "'", str15.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "wc: 20: No such file\n" + "'",
				str20.equals("wc: 20: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "20" + "'", str22.equals("20"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str24 + "' != '" + "5" + "'", str24.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str26 + "' != '" + "20" + "'", str26.equals("20"));

	}

	@Test
	public void test125() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test125");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str12 = wcApplication0.printAllCountsInStdin("0");
		java.lang.String str14 = wcApplication0.printCharacterCountInFile("wc: 22: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "19 5 2 " + "'", str10.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1 1 1 " + "'", str12.equals("1 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "21" + "'", str14.equals("21"));

	}

	@Test
	public void test126() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test126");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printAllCountsInFile("0 0 0 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("0 0 0 ");
		java.lang.String str11 = wcApplication0.printCountInFile("", false, false, true);
		java.lang.String str13 = wcApplication0.printNewlineCountInStdin("7");
		java.lang.String str15 = wcApplication0.printWordCountInFile("19 5 2 ");
		java.lang.String str17 = wcApplication0.printCharacterCountInStdin("0 0 0 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "6 3 1 " + "'", str4.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "6" + "'", str6.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "wc: : No such file\n" + "'",
				str11.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1" + "'", str13.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "3" + "'", str15.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "6" + "'", str17.equals("6"));

	}

	@Test
	public void test127() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test127");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printWordCountInFile("");
		java.lang.String str11 = wcApplication0.printAllCountsInFile("6 3 1 ");
		java.lang.String str13 = wcApplication0.printWordCountInStdin("1");
		java.lang.String str15 = wcApplication0.printAllCountsInStdin("2 1 1 ");
		java.lang.String str20 = wcApplication0.printCountInFile("", true, false, false);
		java.lang.String str22 = wcApplication0.printWordCountInFile("1");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "0" + "'", str9.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "6 3 1 " + "'", str11.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "1" + "'", str13.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "6 3 1 " + "'", str15.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "wc: : No such file\n" + "'",
				str20.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str22 + "' != '" + "1" + "'", str22.equals("1"));

	}

	@Test
	public void test128() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test128");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("");
		java.lang.String str6 = wcApplication0.printAllCountsInStdin("");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("wc: : No such file\n");
		java.lang.String str10 = wcApplication0.printAllCountsInFile("");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("2 1 1 ");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("1");
		java.lang.String str16 = wcApplication0.printCharacterCountInStdin("3 1 1 ");
		java.lang.String str18 = wcApplication0.printWordCountInFile("6 3 1 ");
		java.lang.String str20 = wcApplication0.printAllCountsInFile("wc: 20: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "0" + "'", str4.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "0 0 0 " + "'", str6.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "19 5 2 " + "'", str8.equals("19 5 2 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "0 0 0 " + "'", str10.equals("0 0 0 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "6" + "'", str16.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "3" + "'", str18.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "21 5 2 " + "'", str20.equals("21 5 2 "));

	}

	@Test
	public void test129() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test129");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInStdin("19");
		java.lang.String str12 = wcApplication0.printWordCountInFile("1");
		java.lang.String str17 = wcApplication0.printCountInFile("6", true, true, true);
		java.lang.String str19 = wcApplication0.printNewlineCountInFile("wc: 1: No such file\n");
		java.lang.String str21 = wcApplication0.printCharacterCountInStdin("");
		java.lang.String str23 = wcApplication0.printNewlineCountInStdin("3");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "wc: 6: No such file\n" + "'",
				str17.equals("wc: 6: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "2" + "'", str19.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "0" + "'", str21.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "1" + "'", str23.equals("1"));

	}

	@Test
	public void test130() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test130");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printNewlineCountInFile("hi!");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("3 1 1 ");
		java.lang.String str8 = wcApplication0.printNewlineCountInFile("wc: 7: No such file\n");
		java.lang.String str10 = wcApplication0.printCharacterCountInStdin("1 1 1 ");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "6" + "'", str6.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "2" + "'", str8.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "6" + "'", str10.equals("6"));

	}

	@Test
	public void test131() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test131");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInFile("hi!");
		java.lang.String str12 = wcApplication0.printNewlineCountInFile("hi!");
		java.lang.String str14 = wcApplication0.printWordCountInFile("6 3 1 ");
		java.lang.String str16 = wcApplication0.printNewlineCountInStdin("wc: hi!: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "1" + "'", str12.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "3" + "'", str14.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "2" + "'", str16.equals("2"));

	}

	@Test
	public void test132() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test132");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("1 1 1 ");
		java.lang.String str10 = wcApplication0.printCharacterCountInFile("3 1 1 ");
		java.lang.String str12 = wcApplication0.printWordCountInFile("21 5 2 ");
		java.lang.String str14 = wcApplication0.printWordCountInStdin("2");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "6" + "'", str10.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "3" + "'", str12.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "1" + "'", str14.equals("1"));

	}

	@Test
	public void test133() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test133");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str6 = wcApplication0.printWordCountInStdin("1");
		java.lang.String[] str_array10 = new java.lang.String[] { "7 3 1 ", "wc: 21: No such file\n", "hi!" };
		java.io.InputStream inputStream11 = null;
		java.io.OutputStream outputStream12 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			wcApplication0.run(str_array10, inputStream11, outputStream12);
			org.junit.Assert.fail("Expected exception of type java.nio.file.InvalidPathException");
		} catch (java.nio.file.InvalidPathException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertNotNull(str_array10);

	}

	@Test
	public void test134() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test134");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("3 1 1 ");
		java.lang.String str6 = wcApplication0.printCharacterCountInStdin("2");
		java.lang.String str8 = wcApplication0.printWordCountInStdin("19 5 2 ");
		java.lang.String str10 = wcApplication0.printWordCountInFile("2");
		java.lang.String str12 = wcApplication0.printAllCountsInFile("7 3 1 ");
		java.lang.String str17 = wcApplication0.printCountInFile("21", true, false, false);
		java.lang.String str19 = wcApplication0.printNewlineCountInStdin("0");
		java.lang.String str21 = wcApplication0.printWordCountInStdin("5");
		java.lang.String str23 = wcApplication0.printNewlineCountInFile("21");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "19" + "'", str2.equals("19"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "3" + "'", str4.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "3" + "'", str8.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "6 3 1 " + "'", str12.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "wc: 21: No such file\n" + "'",
				str17.equals("wc: 21: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "1" + "'", str19.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "1" + "'", str21.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str23 + "' != '" + "1" + "'", str23.equals("1"));

	}

	@Test
	public void test135() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test135");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printAllCountsInFile("19");
		java.lang.String str11 = wcApplication0.printCharacterCountInFile("19");
		java.lang.String str13 = wcApplication0.printCharacterCountInStdin("hi!");
		java.lang.String str15 = wcApplication0.printNewlineCountInFile("6");
		java.lang.String str17 = wcApplication0.printNewlineCountInFile("wc: hi!: No such file\n");
		java.lang.String str19 = wcApplication0.printCharacterCountInFile("20 5 2 ");
		java.lang.String str21 = wcApplication0.printNewlineCountInFile("2");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "2 1 1 " + "'", str9.equals("2 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "2" + "'", str11.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "3" + "'", str13.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str15 + "' != '" + "1" + "'", str15.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str17 + "' != '" + "2" + "'", str17.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str19 + "' != '" + "7" + "'", str19.equals("7"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str21 + "' != '" + "1" + "'", str21.equals("1"));

	}

	@Test
	public void test136() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test136");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str9 = wcApplication0.printCharacterCountInStdin("19");
		java.lang.String str11 = wcApplication0.printCharacterCountInStdin("wc: 5: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "3 1 1 " + "'", str7.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "2" + "'", str9.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "20" + "'", str11.equals("20"));

	}

	@Test
	public void test137() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test137");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printCharacterCountInFile("");
		java.lang.String str9 = wcApplication0.printAllCountsInFile("19");
		java.lang.String str11 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str13 = wcApplication0.printAllCountsInStdin("0 0 0 ");
		java.lang.String str18 = wcApplication0.printCountInFile("7", true, true, false);
		java.lang.String str20 = wcApplication0.printWordCountInStdin("");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "0" + "'", str7.equals("0"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "2 1 1 " + "'", str9.equals("2 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "3" + "'", str11.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str13 + "' != '" + "6 3 1 " + "'", str13.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "wc: 7: No such file\n" + "'",
				str18.equals("wc: 7: No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str20 + "' != '" + "0" + "'", str20.equals("0"));

	}

	@Test
	public void test138() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test138");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str5 = wcApplication0.printCountInFile("", false, true, true);
		java.lang.String str7 = wcApplication0.printAllCountsInStdin("hi!");
		java.lang.String str9 = wcApplication0.printCharacterCountInStdin("1 1 1 ");
		java.lang.String str11 = wcApplication0.printCharacterCountInFile("wc: : No such file\n");
		java.io.InputStream inputStream12 = null;
		// The following exception was thrown during execution in test
		// generation
		try {
			java.lang.String str16 = wcApplication0.printCountInStdin(inputStream12, false, false, false);
			org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException");
		} catch (java.lang.NullPointerException e) {
			// Expected exception.
		}

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str5 + "' != '" + "wc: : No such file\n" + "'",
				str5.equals("wc: : No such file\n"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str7 + "' != '" + "3 1 1 " + "'", str7.equals("3 1 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str9 + "' != '" + "6" + "'", str9.equals("6"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str11 + "' != '" + "19" + "'", str11.equals("19"));

	}

	@Test
	public void test139() throws Throwable {

		if (debug) {
			System.out.format("%n%s%n", "RegressionTest3.test139");
		}

		sg.edu.nus.comp.cs4218.impl.app.WcApplication wcApplication0 = new sg.edu.nus.comp.cs4218.impl.app.WcApplication();
		java.lang.String str2 = wcApplication0.printCharacterCountInFile("hi!");
		java.lang.String str4 = wcApplication0.printWordCountInStdin("hi!");
		java.lang.String str6 = wcApplication0.printCharacterCountInFile("1");
		java.lang.String str8 = wcApplication0.printAllCountsInStdin("21 5 2 ");
		java.lang.String str10 = wcApplication0.printCharacterCountInFile("6");
		java.lang.String str12 = wcApplication0.printWordCountInFile("wc: 2: No such file\n");
		java.lang.String str14 = wcApplication0.printNewlineCountInFile("wc: 21: No such file\n");
		java.lang.String str16 = wcApplication0.printAllCountsInStdin("1 1 1 ");
		java.lang.String str18 = wcApplication0.printWordCountInStdin("wc: 20: No such file\n");

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str2 + "' != '" + "3" + "'", str2.equals("3"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str4 + "' != '" + "1" + "'", str4.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str6 + "' != '" + "1" + "'", str6.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str8 + "' != '" + "7 3 1 " + "'", str8.equals("7 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str10 + "' != '" + "1" + "'", str10.equals("1"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str12 + "' != '" + "5" + "'", str12.equals("5"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str14 + "' != '" + "2" + "'", str14.equals("2"));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str16 + "' != '" + "6 3 1 " + "'", str16.equals("6 3 1 "));

		// Regression assertion (captures the current behavior of the code)
		org.junit.Assert.assertTrue("'" + str18 + "' != '" + "5" + "'", str18.equals("5"));

	}

}
