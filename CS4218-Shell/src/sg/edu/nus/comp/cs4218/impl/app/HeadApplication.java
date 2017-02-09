package sg.edu.nus.comp.cs4218.impl.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class HeadApplication implements Application {
	public static int numOfLines;
	public static String path;
	public static String fileName;
	
	public String[] cmdArgs = ShellImpl.cmdArgs;

	public static void readFile() {
		// Read file
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			
			int currLineCount = 0;
			String sCurrentLine;

			try {
				while ((sCurrentLine = br.readLine()) != null) {
					if (currLineCount != numOfLines) {
						System.out.println(sCurrentLine);
						currLineCount++;
					} else {
						break;
					}
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
	 * @params args
	 * 		Arguments from input, without the app name
	 * 
	 * 
	 * 
	 */
	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
		// TODO Auto-generated method stub
		String currentDir = Environment.currentDirectory;
		boolean isFileReadable = false;
		
		path = currentDir + "\\" + args[0];
		
		// If there are command line args, read from it
		if (cmdArgs.length > 0) {
			// command: head -n 15 test.txt
			if (cmdArgs.length == 4) {
				numOfLines = Integer.parseInt(cmdArgs[2]);
				fileName = cmdArgs[3];
				path = Environment.currentDirectory + "\\" + fileName;
			} 
			// command: head test.txt
			else if (cmdArgs.length == 2) {
				numOfLines = 10;
				fileName = cmdArgs[1];
				path = Environment.currentDirectory + "\\" + fileName;
			} 
			// command: head
			// Read file from stdin
			else if (cmdArgs.length == 1) {
				numOfLines = 10;
				// Else read stdin
				
				// stdin empty, throw exception
				if (args == null || args.length == 0) {
					if (stdin == null || stdout == null) {
						throw new CatException("Null Pointer Exception");
					}
					try {
						int intCount;
						while ((intCount = stdin.read()) != -1) {
							stdout.write(intCount);
						}
					} catch (Exception exIO) {
						throw new CatException("Exception Caught");
					}
				}
				// stdin not empty
				else {
					if (args.length != 0) {
						path = Environment.currentDirectory + "\\" + args[0];
					}	
				}
			}
			isFileReadable = checkIfFileIsReadable(path);
			if (isFileReadable) {	
				readFile();
			}
		} 
		// TODO: read from stdin
		else {
			
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
	boolean checkIfFileIsReadable(String filePath) throws CatException {
		try {
			FileReader fr = new FileReader(filePath);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("OH NO");
		return false;
	}
}
