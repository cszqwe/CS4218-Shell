package sg.edu.nus.comp.cs4218.impl.app;


import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTestSedAppliaction {

  public static boolean debug = false;

  @Test
  public void test01() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test01"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str2 = sedApplication0.replaceFirstSubStringInFile("");
      org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException");
    } catch (java.lang.StringIndexOutOfBoundsException e) {
      // Expected exception.
    }

  }

  @Test
  public void test02() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test02"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str2 = sedApplication0.replaceAllSubstringsInFile("hi!");
      org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException");
    } catch (java.lang.StringIndexOutOfBoundsException e) {
      // Expected exception.
    }

  }

  @Test
  public void test03() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test03"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str2 = sedApplication0.replaceAllSubstringsInFile("");
      org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException");
    } catch (java.lang.StringIndexOutOfBoundsException e) {
      // Expected exception.
    }

  }

  @Test
  public void test04() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test04"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str2 = sedApplication0.getContentFromFile("hi!");
      org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }

  }

  @Test
  public void test05() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test05"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str2 = sedApplication0.getReplacement("The command hi! has invalid Regex");
      org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }

  }

  @Test
  public void test06() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test06"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceSubstringWithInvalidRegex("hi!");
    java.lang.String str4 = sedApplication0.replaceSubstringWithInvalidReplacement("The command hi! has invalid Regex");
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str6 = sedApplication0.replaceFirstSubStringInFile("hi!");
      org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException");
    } catch (java.lang.StringIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "The command hi! has invalid Regex"+ "'", str2.equals("The command hi! has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str4 + "' != '" + "The command The command hi! has invalid Regex has invalid Replacement"+ "'", str4.equals("The command The command hi! has invalid Regex has invalid Replacement"));

  }

  @Test
  public void test07() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test07"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str2 = sedApplication0.replaceAllSubstringsInStdin("The command hi! has invalid Regex");
      org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }

  }

  @Test
  public void test08() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test08"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceSubstringWithInvalidRegex("hi!");
    java.lang.String str4 = sedApplication0.replaceSubstringWithInvalidReplacement("The command hi! has invalid Regex");
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str6 = sedApplication0.getReplacement("hi!");
      org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException");
    } catch (java.lang.StringIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "The command hi! has invalid Regex"+ "'", str2.equals("The command hi! has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str4 + "' != '" + "The command The command hi! has invalid Regex has invalid Replacement"+ "'", str4.equals("The command The command hi! has invalid Regex has invalid Replacement"));

  }

  @Test
  public void test09() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test09"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceSubstringWithInvalidRegex("");
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str4 = sedApplication0.replaceFirstSubStringInFile("");
      org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException");
    } catch (java.lang.StringIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "The command  has invalid Regex"+ "'", str2.equals("The command  has invalid Regex"));

  }

  @Test
  public void test10() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test10"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceSubstringWithInvalidRegex("hi!");
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str4 = sedApplication0.getReplacement("The command hi! has invalid Regex");
      org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "The command hi! has invalid Regex"+ "'", str2.equals("The command hi! has invalid Regex"));

  }

  @Test
  public void test11() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test11"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceAllSubstringsInFile("The command The command hi! has invalid Regex has invalid Replacement");
    java.lang.String[] str_array9 = new java.lang.String[] { "sed: File is not readable", "", "The command hi! has invalid Regex", "The command hi! has invalid Regex", "sed: File is not readable", "The command  has invalid Regex" };
    java.io.InputStream inputStream10 = null;
    java.io.OutputStream outputStream11 = null;
    // The following exception was thrown during execution in test generation
    try {
    sedApplication0.run(str_array9, inputStream10, outputStream11);
      org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.SedException");
    } catch (sg.edu.nus.comp.cs4218.exception.SedException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "sed: File is not readable"+ "'", str2.equals("sed: File is not readable"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertNotNull(str_array9);

  }

  @Test
  public void test12() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test12"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceSubstringWithInvalidRegex("hi!");
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str4 = sedApplication0.getContentFromFile("hi!");
      org.junit.Assert.fail("Expected exception of type java.lang.ArrayIndexOutOfBoundsException");
    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "The command hi! has invalid Regex"+ "'", str2.equals("The command hi! has invalid Regex"));

  }

  @Test
  public void test13() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test13"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceSubstringWithInvalidRegex("hi!");
    java.lang.String str4 = sedApplication0.getReg("The command  has invalid Regex");
    java.lang.String str6 = sedApplication0.replaceSubstringWithInvalidReplacement("The command The command hi! has invalid Regex has invalid Replacement");
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "The command hi! has invalid Regex"+ "'", str2.equals("The command hi! has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str4 + "' != '" + "mmand  has invalid Regex"+ "'", str4.equals("mmand  has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str6 + "' != '" + "The command The command The command hi! has invalid Regex has invalid Replacement has invalid Replacement"+ "'", str6.equals("The command The command The command hi! has invalid Regex has invalid Replacement has invalid Replacement"));

  }

  @Test
  public void test14() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test14"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceSubstringWithInvalidRegex("hi!");
    java.lang.String str4 = sedApplication0.replaceSubstringWithInvalidReplacement("The command hi! has invalid Regex");
    java.lang.String str6 = sedApplication0.getReg("mmand hi! has invalid Regex");
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str8 = sedApplication0.replaceAllSubstringsInFile("");
      org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException");
    } catch (java.lang.StringIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "The command hi! has invalid Regex"+ "'", str2.equals("The command hi! has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str4 + "' != '" + "The command The command hi! has invalid Regex has invalid Replacement"+ "'", str4.equals("The command The command hi! has invalid Regex has invalid Replacement"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str6 + "' != '" + "hi!"+ "'", str6.equals("hi!"));

  }

  @Test
  public void test15() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test15"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceSubstringWithInvalidRegex("hi!");
    java.lang.String str4 = sedApplication0.getReg("The command  has invalid Regex");
    java.lang.String str6 = sedApplication0.getReg("The command hi! has invalid Regex");
    java.lang.String str8 = sedApplication0.replaceFirstSubStringInFile("mmand  has invalid Regex");
    java.lang.String str10 = sedApplication0.replaceSubstringWithInvalidReplacement("The command The command The command hi! has invalid Regex has invalid Replacement has invalid Replacement");
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "The command hi! has invalid Regex"+ "'", str2.equals("The command hi! has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str4 + "' != '" + "mmand  has invalid Regex"+ "'", str4.equals("mmand  has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str6 + "' != '" + "mmand hi! has invalid Regex"+ "'", str6.equals("mmand hi! has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str8 + "' != '" + "sed: File is not readable"+ "'", str8.equals("sed: File is not readable"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str10 + "' != '" + "The command The command The command The command hi! has invalid Regex has invalid Replacement has invalid Replacement has invalid Replacement"+ "'", str10.equals("The command The command The command The command hi! has invalid Regex has invalid Replacement has invalid Replacement has invalid Replacement"));

  }

  @Test
  public void test16() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test16"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceSubstringWithInvalidRegex("");
    java.lang.String str4 = sedApplication0.replaceSubstringWithInvalidReplacement("");
    // The following exception was thrown during execution in test generation
    try {
    java.lang.String str6 = sedApplication0.replaceFirstSubStringInFile("");
      org.junit.Assert.fail("Expected exception of type java.lang.StringIndexOutOfBoundsException");
    } catch (java.lang.StringIndexOutOfBoundsException e) {
      // Expected exception.
    }
    
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "The command  has invalid Regex"+ "'", str2.equals("The command  has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str4 + "' != '" + "The command  has invalid Replacement"+ "'", str4.equals("The command  has invalid Replacement"));

  }

  @Test
  public void test17() throws Throwable {

    if (debug) { System.out.format("%n%s%n","RegressionTest0.test17"); }

    sg.edu.nus.comp.cs4218.impl.app.SedApplication sedApplication0 = new sg.edu.nus.comp.cs4218.impl.app.SedApplication();
    java.lang.String str2 = sedApplication0.replaceSubstringWithInvalidRegex("hi!");
    java.lang.String str4 = sedApplication0.getReg("The command  has invalid Regex");
    java.lang.String str6 = sedApplication0.getReg("The command hi! has invalid Regex");
    java.lang.String str8 = sedApplication0.replaceSubstringWithInvalidReplacement("The command  has invalid Regex");
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str2 + "' != '" + "The command hi! has invalid Regex"+ "'", str2.equals("The command hi! has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str4 + "' != '" + "mmand  has invalid Regex"+ "'", str4.equals("mmand  has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str6 + "' != '" + "mmand hi! has invalid Regex"+ "'", str6.equals("mmand hi! has invalid Regex"));
    
    // Regression assertion (captures the current behavior of the code)
    org.junit.Assert.assertTrue("'" + str8 + "' != '" + "The command The command  has invalid Regex has invalid Replacement"+ "'", str8.equals("The command The command  has invalid Regex has invalid Replacement"));

  }

}
