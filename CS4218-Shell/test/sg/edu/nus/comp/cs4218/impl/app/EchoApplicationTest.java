package sg.edu.nus.comp.cs4218.impl.app;
import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.app.EchoApplication;
import sg.edu.nus.comp.cs4218.exception.EchoException;

public class EchoApplicationTest {
	static EchoApplication echoApp;
	static OutputStream os;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		echoApp = new EchoApplication();
		os = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		os = new ByteArrayOutputStream();
	}

	@Test
	public void testEcho() {
		String args[] = {"test", "values", "args"};
		try {
			echoApp.run(args, null, os);
			assertEquals("test values args\n", os.toString());
		} catch (EchoException e) {
			
		}
	}
	
	@Test
	public void testEchoNoArgs() {
		try {
			echoApp.run(null, null, os);
		} catch (EchoException e) {
			assertEquals("echo: Null arguments", e.getMessage());
		}
	}

	@Test
	public void testEchoNoStdout() {
		String args[] = {"test"};
		try {
			echoApp.run(args, null, null);
		} catch (EchoException e) {
			assertEquals("echo: OutputStream not provided", e.getMessage());
		}
	}

	@Test
	public void testEchoEmptyArgs() {
		String args[] = {};
		try {
			echoApp.run(args, null, os);
			assertEquals("\n\n", os.toString());
		} catch (EchoException e) {
			
		}
	}

	@Test
	public void testEchoSingleArg() {
		String args[] = {"test"};
		try {
			echoApp.run(args, null, os);
			assertEquals("test\n", os.toString());
		} catch (EchoException e) {
			
		}
	}

}
