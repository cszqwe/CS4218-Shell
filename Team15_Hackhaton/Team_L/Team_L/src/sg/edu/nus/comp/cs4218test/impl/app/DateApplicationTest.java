package sg.edu.nus.comp.cs4218test.impl.app;

import org.junit.Before;
import org.junit.Test;
import sg.edu.nus.comp.cs4218.exception.DateException;
import sg.edu.nus.comp.cs4218.impl.app.DateApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class DateApplicationTest {

	private OutputStream stdout;
	private InputStream stdin;
	private static DateApplication app;
	private static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

	@Before
	public void setup() {
		String fileString = "123 456 789 aaa bbb ccc ddd" + System.lineSeparator() + System.lineSeparator()
				+ "aaa 00000";
		app = new DateApplication();
		stdin = new ByteArrayInputStream(fileString.getBytes(StandardCharsets.UTF_8));
		stdout = new ByteArrayOutputStream();
	}

	@Test
	public void testDateWithNullStdin() throws DateException {
		String[] arg = {};
		app.run(arg, null, stdout);
		Calendar cal = Calendar.getInstance();
		assertEquals(DEFAULT_DATE_FORMAT.format(cal.getTime()) + System.lineSeparator(), stdout.toString());
	}

	@Test(expected = DateException.class)
	public void testDateWithNullStdout() throws DateException {
		app.run(null, stdin, null);
	}

	@Test(expected = DateException.class)
	public void testDateWithNullStdinAndStdout() throws DateException {
		app.run(null, null, null);
	}

	@Test
	public void testDateWithCurrentTimeDate() throws DateException {
		String message = "date - test with current time and date";
		String[] arg = {};
		app.run(arg, stdin, stdout);
		Calendar cal = Calendar.getInstance();
		assertEquals(message, DEFAULT_DATE_FORMAT.format(cal.getTime()) + System.lineSeparator(), stdout.toString());
	}

	@Test
	public void testDateWithWaitOneSecond() throws DateException, InterruptedException {
		String message = "date - test with current time and wait seconds";
		String[] arg = {};
		app.run(arg, stdin, stdout);
		Thread.sleep(1000);
		Calendar cal = Calendar.getInstance();
		assertNotSame(message, DEFAULT_DATE_FORMAT.format(cal.getTime()) + System.lineSeparator(), stdout.toString());
	}

	@Test
	public void testPrintCurrentDate() throws DateException {
		String[] arg = {};
		app.run(arg, stdin, stdout);
		Calendar cal = Calendar.getInstance();
		assertEquals(DEFAULT_DATE_FORMAT.format(cal.getTime()), app.printCurrentDate(""));
	}
}
