package sg.edu.nus.comp.cs4218.app;

import sg.edu.nus.comp.cs4218.Application;

public interface Sed extends Application {

	/**
	 * Returns string containing lines with the first matched substring replaced
	 * in file
	 * @param args String containing command and arguments
	 */
	public String replaceFirstSubStringInFile(String args);

	/**
	 * Returns string containing lines with all matched substring replaced in
	 * file
	 * @param args String containing command and arguments
	 */
	public String replaceAllSubstringsInFile(String args);

	/**
	 * Returns string containing lines with first matched substring replaced in
	 * Stdin
	 * @param args String containing command and arguments
	 */
	public String replaceFirstSubStringFromStdin(String args);

	/**
	 * Returns string containing lines with all matched substring replaced in
	 * Stdin
	 * @param args String containing command and arguments
	 */
	public String replaceAllSubstringsInStdin(String args);

	/**
	 * Returns string containing lines when invalid replacement string is
	 * provided
	 * @param args String containing command and arguments
	 */
	public String replaceSubstringWithInvalidReplacement(String args);

	/**
	 * Returns string containing lines when invalid regex is provided
	 * @param args String containing command and arguments
	 */
	public String replaceSubstringWithInvalidRegex(String args);

}
