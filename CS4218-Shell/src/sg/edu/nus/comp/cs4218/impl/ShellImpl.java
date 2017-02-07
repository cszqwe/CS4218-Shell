package sg.edu.nus.comp.cs4218.impl;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.Shell;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.ShellException;
import sg.edu.nus.comp.cs4218.impl.app.CatApplication;
import sg.edu.nus.comp.cs4218.impl.app.EchoApplication;
import sg.edu.nus.comp.cs4218.impl.app.HeadApplication;
import sg.edu.nus.comp.cs4218.impl.app.TailApplication;
import sg.edu.nus.comp.cs4218.impl.app.PwdApplication;
import sg.edu.nus.comp.cs4218.impl.app.CdApplication;

/**
 * A Shell is a command interpreter and forms the backbone of the entire
 * program. Its responsibility is to interpret commands that the user type and
 * to run programs that the user specify in her command lines.
 * 
 * <p>
 * <b>Command format:</b>
 * <code>&lt;Pipe&gt; | &lt;Sequence&gt; | &lt;Call&gt;</code>
 * </p>
 */

public class ShellImpl implements Shell {

	public static final String EXP_INVALID_APP = "Invalid app.";
	public static final String EXP_SYNTAX = "Invalid syntax encountered.";
	public static final String EXP_REDIR_PIPE = "File output redirection and "
			+ "pipe operator cannot be used side by side.";
	public static final String EXP_SAME_REDIR = "Input redirection file same "
			+ "as output redirection file.";
	public static final String EXP_STDOUT = "Error writing to stdout.";
	public static final String EXP_NOT_SUPPORTED = " not supported yet";

	/**
	 * Searches for and processes the commands enclosed by back quotes for
	 * command substitution.If no back quotes are found, the argsArray from the
	 * input is returned unchanged. If back quotes are found, the back quotes
	 * and its enclosed commands substituted with the output from processing the
	 * commands enclosed in the back quotes.
	 * 
	 * @param argsArray
	 *            String array of the individual commands.
	 * 
	 * @return String array with the back quotes command processed.
	 * 
	 * @throws AbstractApplicationException
	 *             If an exception happens while processing the content in the
	 *             back quotes.
	 * @throws ShellException
	 *             If an exception happens while processing the content in the
	 *             back quotes.
	 */
	public static String[] processBQ(String... argsArray)
			throws AbstractApplicationException, ShellException {
		// echo "this is space `echo "nbsp"`"
		// echo "this is space `echo "nbsp"` and `echo "2nd space"`"
		// Back quoted: any char except \n,`
		String[] resultArr = new String[argsArray.length];
		System.arraycopy(argsArray, 0, resultArr, 0, argsArray.length);
		String patternBQ = "`([^\\n`]*)`";
		Pattern patternBQp = Pattern.compile(patternBQ);

		for (int i = 0; i < argsArray.length; i++) {
			Matcher matcherBQ = patternBQp.matcher(argsArray[i]);
			if (matcherBQ.find()) {// found backquoted
				String bqStr = matcherBQ.group(1);
				// cmdVector.add(bqStr.trim());
				// process back quote
				// System.out.println("backquote" + bqStr);
				OutputStream bqOutputStream = new ByteArrayOutputStream();
				ShellImpl shell = new ShellImpl();
				//shell.parseAndEvaluate(bqStr, bqOutputStream);

				ByteArrayOutputStream outByte = (ByteArrayOutputStream) bqOutputStream;
				byte[] byteArray = outByte.toByteArray();
				String bqResult = new String(byteArray).replace("\n", "")
						.replace("\r", "");

				// replace substring of back quote with result
				String replacedStr = argsArray[i].replace("`" + bqStr + "`",
						bqResult);
				resultArr[i] = replacedStr;
			}
		}
		return resultArr;
	}

	/**
	 * Static method to run the application as specified by the application
	 * command keyword and arguments.
	 * 
	 * @param app
	 *            String containing the keyword that specifies what application
	 *            to run.
	 * @param args
	 *            String array containing the arguments to pass to the
	 *            applications for running.
	 * @param inputStream
	 *            InputputStream for the application to get arguments from, if
	 *            needed.
	 * @param outputStream
	 *            OutputStream for the application to print its output to.
	 * 
	 * @throws AbstractApplicationException
	 *             If an exception happens while running any of the
	 *             application(s).
	 * @throws ShellException
	 *             If an unsupported or invalid application command is detected.
	 */
	public static void runApp(String app, String[] argsArray,
			InputStream inputStream, OutputStream outputStream)
			throws AbstractApplicationException, ShellException {
		Application absApp = null;
		if (("cat").equals(app)) {// cat [FILE]...
			absApp = new CatApplication();
		} else if (("echo").equals(app)) {// echo [args]...
			absApp = new EchoApplication();
		} else if (("head").equals(app)) {// head [OPTIONS] [FILE]
			absApp = new HeadApplication();
		} else if (("tail").equals(app)) {// tail [OPTIONS] [FILE]
			absApp = new TailApplication();
		} else if (("cd").equals(app)) {
			absApp = new CdApplication();
		} else if (("pwd").equals(app)) {
			absApp = new PwdApplication();
		} else { // invalid command
			throw new ShellException(app + ": " + EXP_INVALID_APP);
		}
		absApp.run(argsArray, inputStream, outputStream);
	}

	/**
	 * Static method to creates an inputStream based on the file name or file
	 * path.
	 * 
	 * @param inputStreamS
	 *            String of file name or file path
	 * 
	 * @return InputStream of file opened
	 * 
	 * @throws ShellException
	 *             If file is not found.
	 */
	public static InputStream openInputRedir(String inputStreamS)
			throws ShellException {
		File inputFile = new File(inputStreamS);
		FileInputStream fInputStream = null;
		try {
			fInputStream = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			throw new ShellException(e.getMessage());
		}
		return fInputStream;
	}

	/**
	 * Static method to creates an outputStream based on the file name or file
	 * path.
	 * 
	 * @param onputStreamS
	 *            String of file name or file path.
	 * 
	 * @return OutputStream of file opened.
	 * 
	 * @throws ShellException
	 *             If file destination cannot be opened or inaccessible.
	 */
	public static OutputStream openOutputRedir(String outputStreamS)
			throws ShellException {
		File outputFile = new File(outputStreamS);
		FileOutputStream fOutputStream = null;
		try {
			fOutputStream = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			throw new ShellException(e.getMessage());
		}
		return fOutputStream;
	}

	/**
	 * Static method to close an inputStream.
	 * 
	 * @param inputStream
	 *            InputStream to be closed.
	 * 
	 * @throws ShellException
	 *             If inputStream cannot be closed successfully.
	 */
	public static void closeInputStream(InputStream inputStream)
			throws ShellException {
		if (inputStream != System.in) {
			try {
				inputStream.close();
			} catch (IOException e) {
				throw new ShellException(e.getMessage());
			}
		}
	}

	/**
	 * Static method to close an outputStream. If outputStream provided is
	 * System.out, it will be ignored.
	 * 
	 * @param outputStream
	 *            OutputStream to be closed.
	 * 
	 * @throws ShellException
	 *             If outputStream cannot be closed successfully.
	 */
	public static void closeOutputStream(OutputStream outputStream)
			throws ShellException {
		if (outputStream != System.out) {
			try {
				outputStream.close();
			} catch (IOException e) {
				throw new ShellException(e.getMessage());
			}
		}
	}

	/**
	 * Static method to write output of an outputStream to another outputStream,
	 * usually System.out.
	 * 
	 * @param outputStream
	 *            Source outputStream to get stream from.
	 * @param stdout
	 *            Destination outputStream to write stream to.
	 * @throws ShellException
	 *             If exception is thrown during writing.
	 */
	public static void writeToStdout(OutputStream outputStream,
			OutputStream stdout) throws ShellException {
		if (outputStream instanceof FileOutputStream) {
			return;
		}
		try {
			stdout.write(((ByteArrayOutputStream) outputStream).toByteArray());
		} catch (IOException e) {
			throw new ShellException(EXP_STDOUT);
		}
	}

	/**
	 * Static method to pipe data from an outputStream to an inputStream, for
	 * the evaluation of the Pipe Commands.
	 * 
	 * @param outputStream
	 *            Source outputStream to get stream from.
	 * 
	 * @return InputStream with data piped from the outputStream.
	 * 
	 * @throws ShellException
	 *             If exception is thrown during piping.
	 */
	public static InputStream outputStreamToInputStream(
			OutputStream outputStream) throws ShellException {
		return new ByteArrayInputStream(
				((ByteArrayOutputStream) outputStream).toByteArray());
	}

	/**
	 * Main method for the Shell Interpreter program.
	 * 
	 * @param args
	 *            List of strings arguments, unused.
	 */

	public static void main(String... args) {
		ShellImpl shell = new ShellImpl();

		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		String readLine = null;
		String currentDir;

		while (true) {
			try {
				currentDir = Environment.currentDirectory;
				System.out.print(currentDir + ">");
				readLine = bReader.readLine();
				if (readLine == null) {
					break;
				}
				if (("").equals(readLine)) {
					continue;
				}
				shell.parseAndEvaluate(readLine, System.out);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void parseAndEvaluate(String cmdline, OutputStream stdout)
			throws AbstractApplicationException, ShellException {
		// TODO figure out how to pipe data from one app's OS to another's IS
		String args[] = cmdline.split(" ");
		String app = args[0];
		
		runApp(app, args, null, stdout);
	}

	@Override
	public String pipeTwoCommands(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pipeMultipleCommands(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String pipeWithException(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String globNoPaths(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String globOneFile(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String globFilesDirectories(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String globWithException(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String redirectInput(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String redirectOutput(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String redirectInputWithNoFile(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String redirectOutputWithNoFile(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String redirectInputWithException(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String redirectOutputWithException(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String performCommandSubstitution(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String performCommandSubstitutionWithException(String args) {
		// TODO Auto-generated method stub
		return null;
	}
}
