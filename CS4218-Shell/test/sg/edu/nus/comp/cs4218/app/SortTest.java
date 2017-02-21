package sg.edu.nus.comp.cs4218.app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.app.SortApplication;

public class SortTest {
	
	static SortApplication sortApp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sortApp = new SortApplication();
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

	
	// file1.txt contents:
	//  burger
	//  bumper
	//  bandage
	//  car
	//  arms
	@Test
	public void testSortStringsSimple() {
		String input = "sort file1.txt";
		String expected = "arms\nbandage\nbumper\nburger\ncar\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}
	
	// file2.txt contents:
	//  XP
	//  WINDOW
	//  VISTA
	//  
	@Test
	public void testSortStringsCapital() {
		String input = "sort file2.txt";
		String expected = "arms\nbandage\nbumper\nburger\ncar\n";
		assertEquals(expected, sortApp.sortStringsSimple(input));
	}

}
