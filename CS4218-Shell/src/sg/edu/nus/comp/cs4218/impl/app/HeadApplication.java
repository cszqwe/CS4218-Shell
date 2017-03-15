package sg.edu.nus.comp.cs4218.impl.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CatException;
import sg.edu.nus.comp.cs4218.exception.HeadException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

public class HeadApplication implements Application {
	public static int numOfLines;
	public static String path;
	public static String fileName;
	
	public String[] cmdArgs = ShellImpl.cmdArgs;

	public static void readFile(OutputStream stdout) {
		// Read file
		try {
			FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);
			
			int currLineCount = 0;
			String sCurrentLine;
			
			try {
				while ((sCurrentLine = br.readLine()) != null) {
					if (currLineCount != numOfLines) {
						stdout.write(sCurrentLine.getBytes());
						stdout.write("\n".getBytes());
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

	public static void readFromStdin(InputStream stdin, OutputStream stdout) throws HeadException {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(stdin)); 
			String strCurrent;
			ArrayList<String> answers = new ArrayList<String>();
			answers.clear();
			int cnt = numOfLines;
			while ((strCurrent = in.readLine()) != null) {
				
				//if (strCurrent.equals("end")) break;  //This statement is used for testing only.
				if (cnt == 0){
					break;
				}else{
					cnt--;
				}
				answers.add(strCurrent);
				
			}
			for (int i = 0; i < answers.size(); i++){
				stdout.write(answers.get(i).getBytes());
				stdout.write("\n".getBytes());
				
			}
		
		} catch (Exception exIO) {
			throw new HeadException("Exception Caught");
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
		
		// If there are command line args, read from it
		if (cmdArgs.length > 0) {
			path = currentDir + "\\" + args[0];
			// command: head -n 15 test.txt
			if (cmdArgs.length == 3) {
				try{
					numOfLines = Integer.parseInt(cmdArgs[1]);
				}catch (Exception e){
					AbstractApplicationException err =  new HeadException("Head: The second parameter should be a number");
					throw err;
				}
				fileName = cmdArgs[2];
				path = Environment.currentDirectory + "\\" + fileName;
				isFileReadable = checkIfFileIsReadable(path);
				if (!isFileReadable) throw new HeadException("File not found");

			} 
			// command: head test.txt
			else if (cmdArgs.length == 1) {
				numOfLines = 10;
				fileName = cmdArgs[0];
				path = Environment.currentDirectory + "\\" + fileName;
				isFileReadable = checkIfFileIsReadable(path);
				if (!isFileReadable) throw new HeadException("File not found");

			} else if(cmdArgs.length == 2){ 
				
				try{
					numOfLines = Integer.parseInt(cmdArgs[1]);
					isFileReadable = false;

				}catch (Exception e){
					AbstractApplicationException err =  new HeadException("The second parameter should be a number");
					throw err;
				}
			}else{
				AbstractApplicationException e =  new HeadException("Invalid usage of head");
				throw e;
			}
		} // TODO: read from stdin
		else {
			numOfLines = 10;
			isFileReadable = false;
		}
		if (isFileReadable) {	
			readFile(stdout);
		}else{
			readFromStdin(stdin,stdout);
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
	boolean checkIfFileIsReadable(String filePath) throws HeadException {
		try {
			FileReader fr = new FileReader(filePath);
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			return false;
		} 
		// System.out.println("OH NO");
	}
}
