package sg.edu.nus.comp.cs4218.impl.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.Sed;
import sg.edu.nus.comp.cs4218.exception.SedException;

public class SedApplication implements Sed {
	InputStream stdin = System.in;
	OutputStream stdout;

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws SedException {
		this.stdin = stdin;
		this.stdout = stdout;
		String output = "";

		if (args == null) {
			throw new SedException("Null Pointer Exception");
		}
		String wholeCommand = "sed ";
		for (int i = 0; i < args.length; i++) {
			wholeCommand = wholeCommand + args[i] + " ";
		}
		if (args[0].length() < 5) {
			throw new SedException("Wrong usage");
		}
		if (args[0].charAt(0) != 's') {
			throw new SedException("Wrong usage");
		}
		args[0] = args[0].substring(1);
		String splitSymbol = "" + args[0].charAt(0);
		String[] strs = args[0].split(Pattern.quote(splitSymbol));

		boolean isGlobal = false;
		int sybolNumber = 0;
		for (int i = 0; i < args[0].length(); i++) {
			if (args[0].charAt(i) == splitSymbol.charAt(0)) {
				sybolNumber++;
			}
		}
		if (strs.length == 2 && sybolNumber == 3) {
			String[] newstrs = new String[3];
			newstrs[0] = strs[0];
			newstrs[1] = strs[1];
			newstrs[2] = "";
			strs = newstrs;
		}
		if (strs.length != 3 && strs.length != 4) {
			throw new SedException("Wrong usage");
		}
		if (strs[1].equals("")) {
			throw new SedException("Expression cannot be empty");
		}
		if (strs.length == 4 && strs[3].equals("g")) {
			isGlobal = true;
		}
		if (strs.length == 4 && !strs[3].equals("g")) {
			throw new SedException("Incorrect usage, last parameter should be g");
		}
		String originalExp = strs[1];
		String newExp = strs[2];
		boolean validReg = true;
		try {
			Pattern.compile(originalExp);
		} catch (Exception e) {
			validReg = false;
		}

		if (!validReg) {

			throw new SedException(replaceSubstringWithInvalidRegex(wholeCommand));
		}

		// If there is a file
		if (args.length >= 2) {
			// If there is a /g.
			if (isGlobal) {
				output = replaceAllSubstringsInFile(wholeCommand);
			} else {
				output = replaceFirstSubStringInFile(wholeCommand);
			}
		} else {
			if (stdin == null) {
				throw new SedException("No valid input stream");
			}
			if (isGlobal) {
				output = replaceAllSubstringsInStdin(wholeCommand);
			} else {
				output = replaceFirstSubStringFromStdin(wholeCommand);
			}
		}

		try {
			stdout.write(output.getBytes());
		} catch (Exception e) {
			throw new SedException("Output stream not working");
		}

	}

	/**
	 * @params args Arguments from input, with app name
	 * 
	 * @return The regex get from the args.
	 * 
	 */
	public String getReg(String args) {
		args = args.substring(5);
		return args.split(Pattern.quote(args.charAt(0) + ""))[1];
	}

	/**
	 * @params args Arguments from input, with app name
	 * 
	 * @return The replacement get from the args.
	 * 
	 */
	public String getReplacement(String args) {
		args = args.substring(5);
		return args.split(Pattern.quote(args.charAt(0) + ""))[2];
	}

	/**
	 * @params args Arguments from input, with app name
	 * 
	 * @return The content stdin.
	 * @throws SedException
	 * 
	 */
	public String getContentFromStdin(String args) throws SedException {
		BufferedReader in = new BufferedReader(new InputStreamReader(stdin));
		StringBuilder str = new StringBuilder();
		String line = null;
		boolean first = true;
		try {
			while ((line = in.readLine()) != null) {
				if (first) {
					str.append(line);
					first = false;
				} else {
					str.append("\n" + line);
				}
			}
		} catch (Exception e) {
			throw new SedException("Stdin is not readable");
		}
		return str.toString();
	}

	/**
	 * @params args Arguments from input, with app name
	 * 
	 * @return The content get from the file.
	 * 
	 */
	public String getContentFromFile(String args) throws SedException {
		String file = args.trim().split(" ")[2];
		Path filePath = Paths.get(Environment.currentDirectory).resolve(file);
		String contents;
		try {
			contents = new String(Files.readAllBytes(filePath));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new SedException("File is not readable");
		}

		return contents;
	}

	@Override
	public String replaceFirstSubStringInFile(String args) {
		String regex = getReg(args);
		String replacement = getReplacement(args);
		String contents;
		try {
			contents = getContentFromFile(args);
		} catch (Exception e) {
			return (e.getMessage());
		}
		String[] alllines = contents.split(Pattern.quote("\n"));
		contents = "";
		for (String str : alllines) {
			contents += str.replaceFirst(regex, replacement);
			contents += "\n";
		}
		return contents;
	}

	@Override
	public String replaceAllSubstringsInFile(String args) {
		String regex = getReg(args);
		String replacement = getReplacement(args);
		String contents;
		try {
			contents = getContentFromFile(args);
		} catch (Exception e) {
			return (e.getMessage());
		}
		contents = contents.replaceAll(regex, replacement);
		return contents;
	}

	/**
	 * Notice the Stdin need to be set before using this function
	 */
	@Override
	public String replaceFirstSubStringFromStdin(String args) {
		String regex = getReg(args);
		String replacement = getReplacement(args);
		String contents;
		try {
			contents = getContentFromStdin(args);
		} catch (Exception e) {
			return (e.getMessage());
		}
		String[] alllines = contents.split(Pattern.quote("\n"));
		contents = "";
		for (String str : alllines) {
			contents += str.replaceFirst(regex, replacement);
			contents += "\n";
		}
		return contents;
	}

	/**
	 * Notice the Stdin need to be set before using this function
	 */
	@Override
	public String replaceAllSubstringsInStdin(String args) {
		String regex = getReg(args);
		String replacement = getReplacement(args);
		String contents;
		try {
			contents = getContentFromStdin(args);
		} catch (Exception e) {
			return (e.getMessage());
		}
		contents = contents.replaceAll(regex, replacement);
		return contents;
	}

	/*
	 * return a error message to indicate the invalid replacement problem.
	 */
	@Override
	public String replaceSubstringWithInvalidReplacement(String args) {
		return "The command " + args + " has invalid Replacement";
	}

	/*
	 * return a error message to indicate the invalid regex problem.
	 */
	@Override
	public String replaceSubstringWithInvalidRegex(String args) {
		return "The command " + args + " has invalid Regex";
	}

}
