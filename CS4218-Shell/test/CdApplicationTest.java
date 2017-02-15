import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.impl.app.CdApplication;

public class CdApplicationTest {
	public static final String CURRENTDIR = "Workspace\\CS4218-Shell";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InputStream is = null;
		OutputStream os = null;
		CdApplication CdApp = new CdApplication();
		Environment.currentDirectory = CURRENTDIR;
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
	public void testCd() {
		// how to fool the test into thinking that a file exists at given path?
	}

}
