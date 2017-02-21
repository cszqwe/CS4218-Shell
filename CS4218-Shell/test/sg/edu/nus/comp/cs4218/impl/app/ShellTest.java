package sg.edu.nus.comp.cs4218.impl.app;
import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.exception.ShellException;

public class ShellTest {
	static ShellImpl shell;
	static OutputStream os;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shell = new ShellImpl();
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

	/**
	@Test
	public void testParseAndEvaluate() {
		String cmdline = "";
		
	}
	**/

}
