package sg.edu.nus.comp.cs4218.impl.app;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.app.DateApplication;

public class DateApplicationTest {
	static DateApplication dateApp;
	static OutputStream os;
	static ByteArrayOutputStream print;
	static PrintStream origOut;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dateApp = new DateApplication();
		os = new ByteArrayOutputStream();
		print = new ByteArrayOutputStream();
		origOut = System.out;
		System.setOut(new PrintStream(print));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	    System.setOut(origOut); // reset System to output to original PrintStream
	}

	@Test
	public void testDateBasic() {
		Date date = java.util.Calendar.getInstance().getTime();  
	    String expected = date.toString() + "\n\n";
	    String[] args = {};
	    
	    try {
	    	dateApp.run(args, null, os);
	    } catch (Exception e) {
	    	
	    }
	    assertEquals(expected.trim(), print.toString().trim());
	    
	}
	
	@Test
	public void testDateArgs() {
		Date date = java.util.Calendar.getInstance().getTime();  
	    String expected = date.toString() + "\n\n";
	    String[] args = {"arg1", "arg2"};
	    
	    try {
	    	dateApp.run(args, null, os);
	    } catch (Exception e) {
	    	
	    }
	    assertEquals(expected.trim(), print.toString().trim());
	    // Date does not use args
	}
	
	@Test
	public void testDateNullArgs() {
		Date date = java.util.Calendar.getInstance().getTime();  
	    String expected = date.toString() + "\n\n";
	    String[] args = null;
	    
	    try {
	    	dateApp.run(args, null, os);
	    } catch (Exception e) {
	    	
	    }
	    assertEquals(expected.trim(), print.toString().trim());
	    // Same reason as above
	}
	
	@Test
	public void testDateNullStdout() {
		Date date = java.util.Calendar.getInstance().getTime();  
	    String expected = date.toString() + "\n\n";
	    String[] args = {};
	    
	    try {
	    	dateApp.run(args, null, null);
	    } catch (Exception e) {
	    	
	    }
	    assertEquals(expected.trim(), print.toString().trim());
	    // Date does not use stdout to display its result: as project description says "print" the current time and date, Date uses System.out
	}

}
