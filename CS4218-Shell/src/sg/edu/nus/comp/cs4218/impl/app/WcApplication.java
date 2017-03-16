package sg.edu.nus.comp.cs4218.impl.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.Wc;
import sg.edu.nus.comp.cs4218.exception.WcException;

public class WcApplication implements Wc {
	
	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws WcException {
		ArrayList<String> files = new ArrayList<>(); //The list of all files
		boolean chars = false;
		boolean words = false;
		boolean lines = false;
		String output = "";
		
		if (args == null) {
			if (stdin == null || stdout == null) {
				throw new WcException("Null Pointer Exception");
			}
		}
		for (String arg : args) {
			if (arg.charAt(0) == '-') { // indicates option
				char invalidOption = 0;
				for (int i = 1; i < arg.length(); i++) {
					if (arg.charAt(i) == 'm') {
						chars = true;
					} else if (arg.charAt(i) == 'w') {
						words = true;
					} else if (arg.charAt(i) == 'l') {
						lines = true;
					} else {
						invalidOption = arg.charAt(i);
						throw new WcException("invalid option -- '" + invalidOption + "'");
					}
				}
			} else {
				files.add(arg); // first add, later check for validity
			}
		}
		if (!lines && !words && !chars) { // no options selected: set to default count all
			chars = true;
			words = true;
			lines = true;
		}
		if (files.size() > 0) {
			// read from file(s), even if stdin is provided
			for (String file : files) {
				output = output.concat(printCountInFile(file, chars, words, lines));
			}
		} else {
			if (stdin != null) {
				// read from stdin
				output = output.concat(printCountInStdin(stdin));
			} else {
				throw new WcException("Null Pointer Exception");
			}
		}
		try {
			stdout.write(output.getBytes());
		} catch (IOException e) {
			throw new WcException("Output stream not working");
		}

	}
	
	/**
	 * @params file chars words lines
	 * 		file is the file name to read, the rest three are all boolean values indicating the options
	 * 
	 * @return
	 * 		A string with required information, followed by the file name.
	 * 
	 */
	public String printCountInFile(String file, boolean chars, boolean words, boolean lines) {
		String line = "";
		Path filePath = Paths.get(Environment.currentDirectory).resolve(file);
		
		if (Files.exists(filePath) && Files.isReadable(filePath) && !Files.isDirectory(filePath)) {
			try {
				String contents = new String(Files.readAllBytes(filePath));
				
				if (chars) line = line.concat(printCharacterCountInFile(contents) + " ");
				if (words) line = line.concat(printWordCountInFile(contents) + " ");
				if (lines) line = line.concat(printNewlineCountInFile(contents) + " ");
				
				line = line.concat(file);
			} catch (Exception e) {
				return "wc: " + file + "Could not read file" + "\n";
			}
		} else {
			return "wc: " + file + ": No such file" + "\n";
		}
		
		return line + "\n";
	}
	
	/**
	 * @params stdin

	 * The given input stream
	 * @return
		A string which represents the content read from the stdin.
	 * 
	 */
	public static String readInputStreamToString(InputStream stdin) {      
         BufferedReader in = new BufferedReader(new InputStreamReader(stdin));
         StringBuilder str = new StringBuilder();      
         String line = null; 
         boolean first = true;
        try {
        	while ((line = in.readLine()) != null) {
        		if (!first)
        			str.append("\n" + line);      
        		else {
        			str.append(line);
        			first = false;
        		}
        	}      
         } catch (IOException e) {      
             e.printStackTrace();      
         } finally {      
            try {      
                 in.close();      
             } catch (IOException e) {      
                 e.printStackTrace();      
             }      
         }
        return str.toString();
     }
	
	
	public String printCountInStdin(InputStream stdin) {
		String line = "";
		String contents = readInputStreamToString(stdin);
		line = line.concat(printCharacterCountInStdin(contents) + " ");
		line = line.concat(printWordCountInStdin(contents) + " ");
		line = line.concat(printNewlineCountInStdin(contents) + " ");
		return line + "\n";
	}

	/**
	 * @params args
	 * 		args here is different from the defination from the interface, it means the content read from the input only.
	 * 
	 * @return
	 * 		The count of character of the args
	 */
	@Override
	public String printCharacterCountInFile(String args) {
		args = args.replace("\r\n", "\n");
		return Integer.toString(args.length());
	}

	/**
	 * @params args
	 * 		args here is different from the defination from the interface, it means the content read from the input only.
	 * 
	 * @return
	 * 		The count of words of the args
	 */
	@Override
	public String printWordCountInFile(String args) {
		String trim = args.trim();
		if (trim.isEmpty()) return "0";
	    return Integer.toString(trim.split("\\s+").length);
	}

	/**
	 * @params args
	 * 		args here is different from the defination from the interface, it means the content read from the input only.
	 * 
	 * @return
	 * 		The count of newlines of the args
	 */
	@Override
	public String printNewlineCountInFile(String args) {
		if (args.equals("")) return "0";
		return Integer.toString(args.split("\n", -1).length);
	}

	/**
	 * @params args
	 * 		args here is different from the defination from the interface, it means the content read from the input only.
	 * 
	 * @return
	 * 		The count of character, word and newline of the args
	 */
	@Override
	public String printAllCountsInFile(String args) {
		String line = "";
		line = line.concat(printCharacterCountInFile(args) + " ");
		line = line.concat(printWordCountInFile(args) + " ");
		line = line.concat(printNewlineCountInFile(args) + " ");
		return line;
	}

	/**
	 * @params args
	 * 		args here is different from the defination from the interface, it means the content read from the input only.
	 * 
	 * @return
	 * 		The count of character of the args
	 */
	@Override
	public String printCharacterCountInStdin(String args) {
		return Integer.toString(args.length());
	}

	/**
	 * @params args
	 * 		args here is different from the defination from the interface, it means the content read from the input only.
	 * 
	 * @return
	 * 		The count of words of the args
	 */
	@Override
	public String printWordCountInStdin(String args) {
		String trim = args.trim();
		if (trim.isEmpty()) return "0";
	    return Integer.toString(trim.split("\\s+").length);
	}

	/**
	 * @params args
	 * 		args here is different from the defination from the interface, it means the content read from the input only.
	 * 
	 * @return
	 * 		The count of newlines of the args
	 */
	@Override
	public String printNewlineCountInStdin(String args) {
		if (args.equals("")) return "0";
		return Integer.toString(args.split("\n", -1).length);
	}

	/**
	 * @params args
	 * 		args here is different from the defination from the interface, it means the content read from the input only.
	 * 
	 * @return
	 * 		The count of character, word and newline of the args
	 */
	@Override
	public String printAllCountsInStdin(String args) {
		String line = "";
		line = line.concat(printCharacterCountInFile(args) + " ");
		line = line.concat(printWordCountInFile(args) + " ");
		line = line.concat(printNewlineCountInFile(args) + " ");
		return line;
	}

}
