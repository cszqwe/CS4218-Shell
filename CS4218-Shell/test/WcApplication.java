

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.OutputstreamNotValidException;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.WcException;

public class WcApplication implements Application {
	
	public int countLines(String contents) {
		if (contents.equals("\r")) return 0;
		return contents.split("\n", -1).length;
	}
	
	public int countWords(String contents) {
		String trim = contents.trim();
		if (trim.isEmpty()) return 0;
	    return trim.split("\\s+").length;
	}
	
	public int countChars(String contents) {
		if (contents.equals("\r")) return 0;
		return contents.length(); // something wrong here
	}
	
	public String wc(String filename, String contents, boolean lines, boolean words, boolean chars) {
		String output = "";
		if (lines) output = output.concat(Integer.toString(countLines(contents)) + " ");
		if (words) output = output.concat(Integer.toString(countWords(contents)) + " ");
		if (chars) output = output.concat(Integer.toString(countChars(contents)) + " ");
		if (filename == null) {
			output = output.substring(0, output.length()-1);
		} else {
			output = output.concat(filename);
		}
		return output;
	}

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws WcException, AbstractApplicationException {
		ArrayList<String> files = new ArrayList<>();
		boolean lines = false;
		boolean words = false;
		boolean chars = false;
		String output = "";
		// always print in the order: no. of newlines, no. of words, no. of chars 
		if (args == null || args.length == 0) {
			if (stdin == null || stdout == null) {
				throw new WcException("Null Pointer Exception");
			}
			// read from default, count all values
		} else {
			// read args, first identify options
			for (String arg : args) {
				if (arg.equals("-l")) {
					lines = true;
				} else if (arg.equals("-w")) {
					words = true;
				} else if (arg.equals("-m")) {
					chars = true;
				} else {
					files.add(arg); // first add, later check for validity
				}
			}
			if (!lines && !words && !chars) { // no options selected: set to default count all
				lines = true;
				words = true;
				chars = true;
			}
			if (files.size() == 0) {
				// read from stdin
			} else {
				for (String file : files) {
					Path filePath = Paths.get(Environment.currentDirectory).resolve(file);
					if (Files.exists(filePath) && Files.isReadable(filePath) && !Files.isDirectory(filePath)) {
						try {
							String contents = new String(Files.readAllBytes(filePath));
							contents = contents.replaceAll("\\r", "").concat("\r");
							// count only /n at newlines
							output = output.concat(wc(file, contents, lines, words, chars) + "\n");
						} catch (Exception e) {
							output = output.concat("wc: " + file + "Could not read file" + "\n");
						}
					} else {
						output = output.concat("wc: " + file + ": No such file" + "\n");
					}
				}
			}
		}
		try {
			stdout.write(output.getBytes());
		} catch (IOException e) {
			throw new OutputstreamNotValidException("Output stream not work");
		}
	}

}
