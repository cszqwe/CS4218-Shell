package sg.edu.nus.comp.cs4218.app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.app.WcApplication;

public class WcTest {
	static WcApplication wcApp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		wcApp = new WcApplication();
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

	@Test
	public void testCharCountInFile() {
		
	}

}
