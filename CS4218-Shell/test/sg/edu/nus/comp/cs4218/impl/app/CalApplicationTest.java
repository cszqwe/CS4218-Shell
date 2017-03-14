package sg.edu.nus.comp.cs4218.impl.app;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

import org.junit.Test;
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
		os = new ByteArrayOutputStream();
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
	
	
	// just testing
	@Test
	public void testing() {
		System.out.println(dateFormat.format(date));
	}
	
	
	
	// Cal -m month year
	@Test
	public void testCalMondayFirstWithMonthAndYear() {
		
	}
	
	// Cal month year
	@Test
	public void testCalSundayFirstWithMonthAndYear() {
		
	}
	
	// Cal -m year
	@Test
	public void testCalMondayFirstWithYearOnly() {
		
	}
	
	// Cal year
	@Test
	public void testCalSundayFirstWithYearOnly() {
		
	}
	
	// Cal -m
	@Test
	public void testCalMondayFirst() {
		
	}
	
	
	// Cal
	@Test
	public void testCalSundayFirst() {
		
	}
	
	
}

