package sg.edu.nus.comp.cs4218.impl.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sg.edu.nus.comp.cs4218.app.Grep;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.GrepException;

public class GrepApplication implements Grep {
	String pattern = "";

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
		ArrayList<String> files = new ArrayList<>(); // The list of all files
		String output = "";
		String ans;
		boolean validPattern;
		if (args == null || args.length == 0) {
			throw new GrepException("No pattern found");
		}
		if (args.length == 1 && stdin == null) {
			throw new GrepException("No Input Stream found");
		}
		pattern = args[0];
		try {
			Pattern.compile(pattern);
			validPattern = true;
		} catch (Exception e) {
			validPattern = false;
		}

		for (int i = 1; i < args.length; i++) {
			files.add(args[i]); // first add, later check for validity
		}
		if (files.size() > 0) {
			if (validPattern) {
				// read from file(s), even if stdin is provided
				for (String file : files) {
					String content;
					try {
						content = getContentFromFile(file);
						if (files.size() == 1) {
							ans = grepFromOneFile(content);
						} else {
							ans = grepFromMultipleFiles(content);
						}
						if (!ans.equals(""))
							stdout.write((ans + "\n").getBytes());
					} catch (Exception e) {
						try {
							stdout.write(e.getMessage().getBytes());
						} catch (Exception ee) {
							throw new GrepException("Invalid output stream");
						}
					}

				}
			} else {
				ans = grepInvalidPatternInFile(output);
				throw new GrepException(ans);
			}
		} else {
			// read from stdin
			if (validPattern) {
				output = output.concat(getContentFromStdin(stdin));
				ans = grepFromStdin(output);
			} else {
				ans = grepInvalidPatternInStdin(output);
				throw new GrepException(ans);
			}
			try {
				if (!ans.equals(""))
					stdout.write((ans + "\n").getBytes());
			} catch (Exception e) {
				throw new GrepException("Invalid output stream");
			}
		}

	}

	/**
	 * @params stdin
	 * 
	 *         The given input stream
	 * @return A string which represents the content read from the stdin.
	 * 
	 */

	/**
	 * @params file chars words lines file is the file name to read, the rest
	 *         three are all boolean values indicating the options
	 * 
	 * @return A string with required information, followed by the file name.
	 * @throws GrepException
	 * 
	 */
	public String getContentFromFile(String filename) throws GrepException {
		String xmlString;
		byte[] strBuffer = null;
		int flen = 0;
		File xmlfile = new File(filename);
		InputStream in;
		try {
			in = new FileInputStream(xmlfile);
			flen = (int) xmlfile.length();
			strBuffer = new byte[flen];
			in.read(strBuffer, 0, flen);
			in.close();
		} catch (Exception e) {
			throw new GrepException(filename + ": No such file\n");
		}
		xmlString = new String(strBuffer);
		return xmlString.replaceAll("\r\n", "\n");
	}

	/**
	 * @params stdin
	 * 
	 *         The given input stream
	 * @return A string which represents the content read from the stdin.
	 * 
	 */
	public static String getContentFromStdin(InputStream stdin) throws GrepException {
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
		} catch (Exception e) {
			throw new GrepException("Invalid input stream");
		}
		return str.toString();
	}

	public String grepFromContent(String args, String pattern) {
		args.replaceAll("\r\n", "\n");
		String[] allStmts = args.split("\n");
		ArrayList<String> strList = new ArrayList<String>();
		strList.clear();
		String ans = "";
		Pattern cre = Pattern.compile(pattern);
		for (int i = 0; i < allStmts.length; i++) {
			Matcher m = cre.matcher(allStmts[i]);
			if (m.find()) {
				strList.add(allStmts[i]);
			}
		}
		for (int i = 0; i < strList.size(); i++) {
			ans += strList.get(i);
			if (i != strList.size() - 1)
				ans += "\n";
		}
		return ans;
	}

	@Override
	public String grepFromStdin(String args) {
		return grepFromContent(args, pattern);
	}

	@Override
	public String grepFromOneFile(String args) {
		return grepFromContent(args, pattern);
	}

	@Override
	public String grepFromMultipleFiles(String args) {
		return grepFromContent(args, pattern);
	}

	@Override
	public String grepInvalidPatternInStdin(String args) {
		return "Invalid Pattern";
	}

	@Override
	public String grepInvalidPatternInFile(String args) {
		return "Invalid Pattern";
	}

}
