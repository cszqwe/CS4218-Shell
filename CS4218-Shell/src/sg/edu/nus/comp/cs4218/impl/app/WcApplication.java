package sg.edu.nus.comp.cs4218.impl.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.Wc;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.OutputstreamNotValidException;
import sg.edu.nus.comp.cs4218.exception.WcException;

public class WcApplication implements Wc {

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
		ArrayList<String> files = new ArrayList<>();
		boolean chars = false;
		boolean words = false;
		boolean lines = false;
		String output = "";
		
		if (args == null) {
			if (stdin == null || stdout == null) {
				throw new WcException("Null Pointer Exception");
			}
		}
		if (args.length == 0) {
			// read from stdin, count all
		} else {
			for (String arg : args) {
				if (arg.charAt(0) == '-') { // indicates option
					for (int i = 1; i < arg.length(); i++) {
						if (arg.charAt(i) == 'm') {
							chars = true;
						} else if (arg.charAt(i) == 'w') {
							words = true;
						} else if (arg.charAt(i) == 'l') {
							lines = true;
						} // else ignore
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
				// read from stdin
			}
		}
		try {
			stdout.write(output.getBytes());
		} catch (IOException e) {
			throw new OutputstreamNotValidException("Output stream not working");
		}

	}
	
	public String printCountInFile(String file, boolean chars, boolean words, boolean lines) {
		String line = "";
		Path filePath = Paths.get(Environment.currentDirectory).resolve(file);
		
		if (Files.exists(filePath) && Files.isReadable(filePath) && !Files.isDirectory(filePath)) {
			try {
				String contents = new String(Files.readAllBytes(filePath));
				contents = contents.replaceAll("\\r", "").concat("\r");
				// count only /n at newlines
				
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

	@Override
	public String printCharacterCountInFile(String args) {
		if (args.equals("\r")) return "0";
		return Integer.toString(args.length());
	}

	@Override
	public String printWordCountInFile(String args) {
		String trim = args.trim();
		if (trim.isEmpty()) return "0";
	    return Integer.toString(trim.split("\\s+").length);
	}

	@Override
	public String printNewlineCountInFile(String args) {
		if (args.equals("\r")) return "0";
		return Integer.toString(args.split("\n", -1).length);
	}

	@Override
	public String printAllCountsInFile(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printCharacterCountInStdin(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printWordCountInStdin(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printNewlineCountInStdin(String args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String printAllCountsInStdin(String args) {
		// TODO Auto-generated method stub
		return null;
	}

}
