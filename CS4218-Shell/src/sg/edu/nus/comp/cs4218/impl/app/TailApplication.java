package sg.edu.nus.comp.cs4218.impl.app;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.InputstreamNotValidException;
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
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			
			int currLineCount = 0;
			String sCurrentLine;
			
			try {
				while ((sCurrentLine = br.readLine()) != null) {
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
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			int lineCount = getFileLineCounts(path);
			int currLineCount = 0;
			int firstWantedLine = lineCount - numOfLines;
			String sCurrentLine;
			
			try {
				while ((sCurrentLine = br.readLine()) != null) {
					if (currLineCount >= firstWantedLine){
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
		cmdArgs = args;
		path = currentDir + "\\" + args[0];
		
		// If there are command line args, read from it
		if (cmdArgs.length > 0) {
			// command: head -n 15 test.txt
			if (cmdArgs.length == 3) {
				try{
					numOfLines = Integer.parseInt(cmdArgs[1]);
				}catch (Exception e){
					AbstractApplicationException err =  new InputstreamNotValidException("The second parameter should be a number");
					throw err;
				}
				fileName = cmdArgs[2];
				path = Environment.currentDirectory + "\\" + fileName;
			} 
			// command: head test.txt
			else if (cmdArgs.length == 1) {
				numOfLines = 10;
				fileName = cmdArgs[0];
				path = Environment.currentDirectory + "\\" + fileName;
			} else{
				AbstractApplicationException e =  new InputstreamNotValidException("Invalid usage of head");
				throw e;
			}
			isFileReadable = checkIfFileIsReadable(path);
			if (isFileReadable) {	
				readFile(stdout);
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
