package sg.edu.nus.comp.cs4218.impl.app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.HeadException;
import sg.edu.nus.comp.cs4218.exception.TailException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class TailApplication implements Application {
	public static int numOfLines;
	public static String path;
	public static String fileName;

	public String[] cmdArgs = ShellImpl.cmdArgs;

	public static int getFileLineCounts(String filename) {
		int cnt = 0;
		// Read file
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			int currLineCount = 0;
			String sCurrentLine;

			try {
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					cnt++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}

	public static void readFile(OutputStream stdout) {
		// Read file
		try {
			FileReader fileReader = new FileReader(path);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int lineCount = getFileLineCounts(path);
			int currLineCount = 0;
			int firstWantedLine = lineCount - numOfLines;
			String sCurrentLine;

			try {
				while ((sCurrentLine = bufferedReader.readLine()) != null) {
					if (currLineCount >= firstWantedLine) {
						stdout.write(sCurrentLine.getBytes());
						stdout.write("\n".getBytes());
					}
					currLineCount++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @params args Arguments from input, without the app name
	 * 
	 * 
	 * 
	 */
	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
		// TODO Auto-generated method stub
		String currentDir = Environment.currentDirectory;
		boolean isFileReadable = false;
		cmdArgs = args;

		// If there are command line args, read from it
		if (cmdArgs.length > 0) {
			path = currentDir + "\\" + args[0];
			// command: head -n 15 test.txt
			if (cmdArgs.length == 3) {
				try {
					numOfLines = Integer.parseInt(cmdArgs[1]);
				} catch (Exception e) {
					AbstractApplicationException err = new TailException("The second parameter should be a number");
					throw err;
				}
				fileName = cmdArgs[2];
				path = Environment.currentDirectory + "\\" + fileName;
				isFileReadable = checkIfFileIsReadable(path);
				if (!isFileReadable) {
					throw new HeadException("File not found");
				}

			}
			// command: head test.txt
			else if (cmdArgs.length == 1) {
				numOfLines = 10;
				fileName = cmdArgs[0];
				path = Environment.currentDirectory + "\\" + fileName;
				isFileReadable = checkIfFileIsReadable(path);
				if (!isFileReadable) {
					throw new HeadException("File not found");
				}

			} else if (cmdArgs.length == 2) {

				try {
					numOfLines = Integer.parseInt(cmdArgs[1]);
					isFileReadable = false;

				} catch (Exception e) {
					AbstractApplicationException err = new TailException("The second parameter should be a number");
					throw err;
				}
			} else {
				AbstractApplicationException err = new TailException("Invalid usage of head");
				throw err;
			}
		} // TODO: read from stdin
		else {
			numOfLines = 10;
			isFileReadable = false;
		}
		if (isFileReadable) {
			readFile(stdout);
		} else {
			readFromStdin(stdin, stdout);
		}

	}

	public static void readFromStdin(InputStream stdin, OutputStream stdout) throws HeadException {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stdin));
			String strCurrent;
			ArrayList<String> answers = new ArrayList<String>();
			answers.clear();
			int cnt = numOfLines;
			while ((strCurrent = bufferedReader.readLine()) != null) {

				// if (strCurrent.equals("end")) break; //This statement is used
				// for testing only.
				answers.add(strCurrent);
				if (cnt == 0) {
					answers.remove(0);
				} else {
					cnt--;
				}
			}
			for (int i = 0; i < answers.size(); i++) {
				stdout.write(answers.get(i).getBytes());
				stdout.write("\n".getBytes());

			}

		} catch (Exception exIO) {
			throw new HeadException("Exception Caught");
		}
	}

	/**
	 * Checks if a file is readable.
	 * 
	 * @param filePath
	 *            The path to the file
	 * @return True if the file is readable.
	 * @throws CatException
	 *             If the file is not readable
	 */
	boolean checkIfFileIsReadable(String filePath) throws TailException {
		try {
			FileReader fileReader = new FileReader(filePath);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new TailException("File not found");
		}
	}
}
