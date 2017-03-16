package sg.edu.nus.comp.cs4218.impl.app;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import sg.edu.nus.comp.cs4218.impl.ShellImpl;
import sg.edu.nus.comp.cs4218.impl.app.WcApplication;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.exception.WcException;

public class WcApplicationTest {
	
	static WcApplication wcApp;
	static InputStream is;
	static OutputStream os;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		wcApp = new WcApplication();
		os = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		os = new ByteArrayOutputStream();
	}


	@Test
	//Notice all the interfaces in Wc has been slightly changed. The args does not mean the command anymore, but the content from the input
	public void testPrintCharacterCountInFile() {
		String args = "line 1\nline 2\nline 3\nline 4";
		String answer = "";
		try {
			answer = wcApp.printCharacterCountInFile(args);
		} catch (Exception e) {
			
		}
		assertEquals("27", answer);
	}

	@Test
	//Notice all the interfaces in Wc has been slightly changed. The args does not mean the command anymore, but the content from the input
	public void testPrintWordCountInFile() {
		String args = "line 1\nline 2\nline 3\nline 4";
		String answer = "";
		try {
			answer = wcApp.printWordCountInFile(args);
		} catch (Exception e) {
			
		}
		assertEquals("8", answer);
	}

	@Test
	//Notice all the interfaces in Wc has been slightly changed. The args does not mean the command anymore, but the content from the input
	public void testPrintNewlineCountInFile() {
		String args = "line 1\nline 2\nline 3\nline 4";
		String answer = "";
		try {
			answer = wcApp.printNewlineCountInFile(args);
		} catch (Exception e) {
			
		}
		assertEquals("4", answer);
	}
	
	@Test
	//Notice all the interfaces in Wc has been slightly changed. The args does not mean the command anymore, but the content from the input
	public void testPrintCharacterCountInStdin() {
		String args = "line 1\nline 2\nline 3\nline 4";
		String answer = "";
		try {
			answer = wcApp.printCharacterCountInStdin(args);
		} catch (Exception e) {
			
		}
		assertEquals("27", answer);
	}

	@Test
	//Notice all the interfaces in Wc has been slightly changed. The args does not mean the command anymore, but the content from the input
	public void testPrintWordCountInStdin() {
		String args = "line 1\nline 2\nline 3\nline 4";
		String answer = "";
		try {
			answer = wcApp.printWordCountInStdin(args);
		} catch (Exception e) {
			
		}
		assertEquals("8", answer);
	}

	@Test
	//Notice all the interfaces in Wc has been slightly changed. The args does not mean the command anymore, but the content from the input
	public void testPrintNewlineCountInStdin() {
		String args = "line 1\nline 2\nline 3\nline 4";
		String answer = "";
		try {
			answer = wcApp.printNewlineCountInStdin(args);
		} catch (Exception e) {
			
		}
		assertEquals("4", answer);
	}
	
	@Test
	//Notice all the interfaces in Wc has been slightly changed. The args does not mean the command anymore, but the content from the input
	public void testPrintAllCountsInFile() {
		String args = "line 1\nline 2\nline 3\nline 4";
		String answer = "";
		try {
			answer = wcApp.printAllCountsInFile(args);
		} catch (Exception e) {
			
		}
		assertEquals("27 8 4 ", answer);
	}
	
	@Test
	//Notice all the interfaces in Wc has been slightly changed. The args does not mean the command anymore, but the content from the input
	public void testPrintAllCountsInStdin() {
		String args = "line 1\nline 2\nline 3\nline 4";
		String answer = "";
		try {
			answer = wcApp.printAllCountsInStdin(args);
		} catch (Exception e) {
			
		}
		assertEquals("27 8 4 ", answer);
	}
	
	@Test
	public void testWc() {
		String[] args = {"fileTest.txt"};
		String expected = "947 181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcEmptyFile() {
		String[] args = {"fileTestEmpty.txt"};
		String expected = "0 0 0 fileTestEmpty.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcOnlyNewlines() {
		String[] args = {"fileTest only newlines.txt"};
		String expected = "4 0 5 fileTest only newlines.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcOnlySpaces() {
		String[] args = {"fileTest_onlyspaces.txt"};
		String expected = "5 0 1 fileTest_onlyspaces.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcCountChars() {
		String[] args = {"-m", "fileTest.txt"};
		String expected = "947 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcCountWords() {
		String[] args = {"-w", "fileTest.txt"};
		String expected = "181 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcCountLines() {
		String[] args = {"-l", "fileTest.txt"};
		String expected = "32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcCountCharsWords() {
		String[] args = {"-m", "-w", "fileTest.txt"};
		String expected = "947 181 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcCountWordsLines() {
		String[] args = {"-w", "-l", "fileTest.txt"};
		String expected = "181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcCountCharsLines() {
		String[] args = {"-m", "-l", "fileTest.txt"};
		String expected = "947 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcCountWordsChars() {
		String[] args = {"-w", "-m", "fileTest.txt"};
		String expected = "947 181 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcCountLinesWords() {
		String[] args = {"-l", "-w", "fileTest.txt"};
		String expected = "181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcCountLinesChars() {
		String[] args = {"-l", "-m", "fileTest.txt"};
		String expected = "947 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcAllOptions() {
		String[] args = {"-m", "-w", "-l", "fileTest.txt"};
		String expected = "947 181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcAllOptionsDiffOrder_W_L_M() {
		String[] args = {"-w", "-l", "-m", "fileTest.txt"};
		String expected = "947 181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcAllOptionsDiffOrder_L_M_W() {
		String[] args = {"-l", "-m", "-w", "fileTest.txt"};
		String expected = "947 181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	public void testWcAllOptionsDiffOrder_L_W_M() {
		String[] args = {"-l", "-w", "-m", "fileTest.txt"};
		String expected = "947 181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcOptionsOverlapping_M_M_L() {
		String[] args = {"-m", "-m", "-l", "fileTest.txt"};
		String expected = "947 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcOptionsOverlapping_W_L_W() {
		String[] args = {"-w", "-l", "-w", "fileTest.txt"};
		String expected = "181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcOptionsOverlapping_W_W_W_W() {
		String[] args = {"-w", "-w", "-w", "-w", "fileTest.txt"};
		String expected = "181 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcMulticharOptions_MW() {
		String[] args = {"-mw", "fileTest.txt"};
		String expected = "947 181 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcMultiCharOptions_WL() {
		String[] args = {"-wl", "fileTest.txt"};
		String expected = "181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcMultiCharOptions_ML() {
		String[] args = {"-ml", "fileTest.txt"};
		String expected = "947 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcMultiCharOptions_LM() {
		String[] args = {"-lm", "fileTest.txt"};
		String expected = "947 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcMultiCharOptions_WWW() {
		String[] args = {"-www", "fileTest.txt"};
		String expected = "181 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcMultiCharOptions_MWL() {
		String[] args = {"-mwl", "fileTest.txt"};
		String expected = "947 181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcMultiCharOptions_WLM() {
		String[] args = {"-wlm", "fileTest.txt"};
		String expected = "947 181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcMulticharOptionsOverlapping_M_WM() {
		String[] args = {"-m", "-wm", "fileTest.txt"};
		String expected = "947 181 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcMulticharOptionsOverlapping_ML_MM() {
		String[] args = {"-ml", "-mm", "-l", "fileTest.txt"};
		String expected = "947 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}

	@Test
	public void testWcMulticharOptionsOverlapping_WWM_MLLM_W_WL() {
		String[] args = {"-wwm", "-mllm", "-w", "-wl", "fileTest.txt"};
		String expected = "947 181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcMultipleFiles() {
		String[] args = {"fileTest.txt", "test2.txt"};
		String expected = "947 181 32 fileTest.txt\n86 24 12 test2.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcOptions_M_MultipleFiles() {
		String[] args = {"-m", "fileTest.txt", "test2.txt"};
		String expected = "947 fileTest.txt\n86 test2.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcOptions_ML_MultipleFiles() {
		String[] args = {"-ml", "fileTest.txt", "test.txt"};
		String expected = "947 32 fileTest.txt\n27 4 test.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcOptions_L_WL_MultipleFiles() {
		String[] args = {"-l", "-wl", "test2.txt", "fileTest.txt"};
		String expected = "24 12 test2.txt\n181 32 fileTest.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcInvalidFile() {
		String[] args = {"asdf.txt"};
		String expected = "wc: asdf.txt: No such file\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcMultipleFilesInvalid() {
		String[] args = {"asdf.txt", "querty.txt", "montypython"};
		String expected = "wc: asdf.txt: No such file\nwc: querty.txt: No such file\nwc: montypython: No such file\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcMultipleFilesValidInvalidValid() {
		String[] args = {"fileTest.txt", "m", "fileTest_onlyspaces.txt"};
		String expected = "947 181 32 fileTest.txt\nwc: m: No such file\n5 0 1 fileTest_onlyspaces.txt\n";
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			
		}
		assertEquals(expected, os.toString());
	}
	
	@Test
	public void testWcOptionInvalid() {
		String[] args = {"-s", "fileTest.txt"};
		String expected = "wc: invalid option -- 's'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcMultipleOptionsInvalidValid() {
		String[] args = {"-a", "-m", "fileTest.txt"};
		String expected = "wc: invalid option -- 'a'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcMultipleOptionsValidInvalid() {
		String[] args = {"-m", "-t", "fileTest.txt"};
		String expected = "wc: invalid option -- 't'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcMultipleOptionsMixed() {
		String[] args = {"-m", "-a", "-s", "-m", "fileTest.txt"};
		String expected = "wc: invalid option -- 'a'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcMulticharOptionInvalid() {
		String[] args = {"-asd", "fileTest.txt"};
		String expected = "wc: invalid option -- 'a'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcMulticharOptionsInvalid_MAW_LST() {
		String[] args = {"-maw", "-lst", "fileTest.txt"};
		String expected = "wc: invalid option -- 'a'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcMulticharOptionsInvalid_ML_LW_TASD() {
		String[] args = {"-ml", "-lw", "-tasd", "fileTest.txt"};
		String expected = "wc: invalid option -- 't'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcInvalidOptionsMultipleFiles() {
		String[] args = {"-a", "fileTest.txt", "test2.txt"};
		String expected = "wc: invalid option -- 'a'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcInvalidOptions_LAM_MultipleFiles() {
		String[] args = {"-lam", "fileTest.txt", "test2.txt", "test.txt"};
		String expected = "wc: invalid option -- 'a'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcInvalidOptionInvalidFile() {
		String[] args = {"-x", "test2"};
		String expected = "wc: invalid option -- 'x'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcInvalidOptions_LM_T_M_ValidFile() {
		String[] args = {"-lm", "-t", "m", "test.txt"};
		String expected = "wc: invalid option -- 't'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcInvalidOptionFilesValidInvalid() {
		String[] args = {"-z", "testfile", "fileTest.txt"};
		String expected = "wc: invalid option -- 'z'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testWcInvalidOptions_M_WL_R_FilesValidInvalid() {
		String[] args = {"-m", "-wl", "-r", "fileTest.txt", "wrongtest.txt"};
		String expected = "wc: invalid option -- 'r'";
		Exception exp = new Exception();
		try {
			wcApp.run(args, is, os);
		} catch (Exception e) {
			exp = e;
		}
		assertEquals(expected, exp.getMessage());
	}
	
	@Test
	public void testOverAllFromFile() throws AbstractApplicationException, ShellException {
		os = new ByteArrayOutputStream();
		ShellImpl shell = new ShellImpl();
		String args = "wc test.txt";
		String expected = "27 8 4 test.txt\n";
		shell.parseAndEvaluate(args,os);
		assertEquals(expected, os.toString());	
	}

}
