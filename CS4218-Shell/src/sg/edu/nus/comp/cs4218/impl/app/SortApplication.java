package sg.edu.nus.comp.cs4218.impl.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// import CharGroup.Type;

import sg.edu.nus.comp.cs4218.Environment;

import sg.edu.nus.comp.cs4218.app.Sort;
import sg.edu.nus.comp.cs4218.exception.SortException;

public class SortApplication implements Sort {
	
	InputStream stdin;
	
	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws SortException {
		this.stdin = stdin;
		String output = "";
		boolean isNumericSort = false;
		ArrayList<String> lines = new ArrayList<>();
		boolean isSimpleFound = false;
		boolean isCapitalFound = false;
		boolean isNumbersFound = false;
		boolean isSpecialFound = false;
		
		if (args == null) {
			if (stdin == null || stdout == null) {
				throw new SortException("Null Pointer Exception");
			}
		}
		
		String wholeCommand = "sort ";
		if (args != null){
			for (int i = 0; i< args.length; i++){
				// System.out.println(args[i]);
				wholeCommand = wholeCommand + args[i] + " ";
			}		
			if (args[0].charAt(0) == '-') {
				for (int i = 1; i < args[0].length(); i++) {
					if (args[0].charAt(i) == 'n') {
						isNumericSort = true;
					} else {
						throw new SortException("invalid option -- '" + args[0].charAt(i) + "'");
					}
				}
			}
		}		
		if (isNumericSort) {
			lines = getFilesContents(new ArrayList<String>(Arrays.asList(Arrays.copyOfRange(args, 1, args.length))));
		} else {
			lines = getFilesContents(new ArrayList<String>(Arrays.asList(args)));
		}
		
		for (String line : lines) {
			for (int i = 0; i < line.length(); i++) {
				char c = line.charAt(i);
				if (Character.isLowerCase(c)) isSimpleFound = true;
				else if (Character.isUpperCase(c)) isCapitalFound = true;
				else if (Character.isDigit(c)) isNumbersFound = true;
				else isSpecialFound = true;
			}
		}
		
		String cmd = "sort";
		for (String arg : args) {
			if (arg.contains(" ")) {
				cmd = cmd.concat(" \"" + arg + "\"");
			} else {
				cmd = cmd.concat(" " + arg);
			}
		}
		
		// invoke appropriate method
		if (isSimpleFound && !isCapitalFound && !isNumbersFound && !isSpecialFound) {
			output = sortStringsSimple(cmd);
		} else if (!isSimpleFound && isCapitalFound && !isNumbersFound && !isSpecialFound) {
			output = sortStringsCapital(cmd);
		} else if (!isSimpleFound && !isCapitalFound && isNumbersFound && !isSpecialFound) {
			output = sortNumbers(cmd);
		} else if (!isSimpleFound && !isCapitalFound && !isNumbersFound && isSpecialFound) {
			output = sortSpecialChars(cmd);
		} else if (isSimpleFound && isCapitalFound && !isNumbersFound && !isSpecialFound) {
			output = sortSimpleCapital(cmd);
		} else if (isSimpleFound && !isCapitalFound && isNumbersFound && !isSpecialFound) {
			output = sortSimpleNumbers(cmd);
		} else if (isSimpleFound && !isCapitalFound && !isNumbersFound && isSpecialFound) {
			output = sortSimpleSpecialChars(cmd);
		} else if (!isSimpleFound && isCapitalFound && isNumbersFound && !isSpecialFound) {
			output = sortCapitalNumbers(cmd);
		} else if (!isSimpleFound && isCapitalFound && !isNumbersFound && isSpecialFound) {
			output = sortCapitalSpecialChars(cmd);
		} else if (!isSimpleFound && !isCapitalFound && isNumbersFound && isSpecialFound) {
			output = sortNumbersSpecialChars(cmd);
		} else if (isSimpleFound && isCapitalFound && isNumbersFound && !isSpecialFound) {
			output = sortSimpleCapitalNumber(cmd);
		} else if (isSimpleFound && isCapitalFound && !isNumbersFound && isSpecialFound) {
			output = sortSimpleCapitalSpecialChars(cmd);
		} else if (isSimpleFound && !isCapitalFound && isNumbersFound && isSpecialFound) {
			output = sortSimpleNumbersSpecialChars(cmd);
		} else if (!isSimpleFound && isCapitalFound && isNumbersFound && isSpecialFound) {
			output = sortCapitalNumbersSpecialChars(cmd);
		} else {
			output = sortAll(cmd);
		}
		
		// for now, print all lines without sorting
		// do TDD
		//for (String line : lines) {
			//output = output.concat(line + "\n");
		//}
		
		try {
			stdout.write(output.getBytes());
		} catch (Exception e) {
			throw new SortException("Sort: Output stream not working");
		}

	}
	
	public ParseRes parseCmd(String cmd) {
		ArrayList<String> filenames = new ArrayList<>();
		boolean isNumericSort = false;
		
		ArrayList<String> matchList = new ArrayList<>();
		Pattern regex = Pattern.compile("[^\\s\"']+|\"([^\"]*)\"|'([^']*)'");
		Matcher regexMatcher = regex.matcher(cmd);
		while (regexMatcher.find()) {
		    if (regexMatcher.group(1) != null) {
		        // Add double-quoted string without the quotes
		        matchList.add(regexMatcher.group(1));
		    } else if (regexMatcher.group(2) != null) {
		        // Add single-quoted string without the quotes
		        matchList.add(regexMatcher.group(2));
		    } else {
		        // Add unquoted word
		        matchList.add(regexMatcher.group());
		    }
		}
		
		String[] args = matchList.toArray(new String[matchList.size()]);
		if (args[1].equals("-n")) isNumericSort = true;
		
		int i = 1;
		if (isNumericSort) i = 2;
		
		for (; i < args.length; i++) {
			filenames.add(args[i]);
		}
		
		ParseRes res = new ParseRes(filenames, isNumericSort);
		return res;
	}
	
	public ArrayList<String> getFilesContents(ArrayList<String> filenames) throws SortException {
		ArrayList<String> lines = new ArrayList<>();
		
		for (String filename : filenames) {
			Path filePath = Paths.get(Environment.currentDirectory).resolve(filename);
			if (Files.exists(filePath) && Files.isReadable(filePath) && !Files.isDirectory(filePath)) {
				try {
					String contents = new String(Files.readAllBytes(filePath));
					lines.addAll(new ArrayList<String>(Arrays.asList(contents.replaceAll("\r\n", "\n").split("\n"))));
				} catch (Exception e) {
					throw new SortException("sort: " + filename + ": Could not read file");
				}
			} else {
				throw new SortException("cannot read: " + filename + ": No such file");
			}
		}
		
		return lines;
	}
	
	public String sort(String toSort) {
		ParseRes res = parseCmd(toSort);
		ArrayList<String> files = res.filenames;
		boolean isNumericSort = res.isNumericSort;
		ArrayList<ArrayList<StrObj>> objLsts = new ArrayList<>();
		ArrayList<String> lines = new ArrayList<>();
		try {
			lines = getFilesContents(files);
		} catch (SortException e) {
			// not supposed to happen since run() does the file validation
			e.printStackTrace();
		}
		
		for (String line : lines) {
			objLsts.add(convertStringToStrObjLst(line, isNumericSort));
		}
		
		ArrayList<String> sortedLines = sortLines(objLsts);
		String output = "";
		for (String line : sortedLines) {
			output = output.concat(line + "\n");
		}
		
		return output;
	}

	@Override
	public String sortStringsSimple(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortStringsCapital(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortNumbers(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortSpecialChars(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortSimpleCapital(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortSimpleNumbers(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortSimpleSpecialChars(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortCapitalNumbers(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortCapitalSpecialChars(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortNumbersSpecialChars(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortSimpleCapitalNumber(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortSimpleCapitalSpecialChars(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortSimpleNumbersSpecialChars(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortCapitalNumbersSpecialChars(String toSort) {
		return sort(toSort);
	}

	@Override
	public String sortAll(String toSort) {
		return sort(toSort);
	}
	
	public StrObj.Type getType(char c) {
		if (Character.isLowerCase(c)) return StrObj.Type.SIMPLE;
		if (Character.isUpperCase(c)) return StrObj.Type.CAPITAL;
		if (Character.isDigit(c)) return StrObj.Type.NUMBERS;
		return StrObj.Type.SPECIAL;
	}
	
	public ArrayList<StrObj> convertStringToStrObjLst(String line, boolean isNumericSort) {
		ArrayList<StrObj> objLst = new ArrayList<>();
		
		if (line.equals("")) {
			StrObj mtObj = new StrObj(StrObj.Type.SPECIAL, "");
			objLst.add(mtObj);
			return objLst;
		}
		
		boolean isProcessingFirstNums = Character.isDigit(line.charAt(0)); // true if line starts with digit, false otherwise
		String firstNums = "";
		
		for (int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			StrObj.Type cType = getType(c);
			
			if (isNumericSort && isProcessingFirstNums) {
				if (cType == StrObj.Type.NUMBERS) {
					firstNums = firstNums.concat(Character.toString(c));
					if (i == line.length()-1) { // line ends with first number
						StrObj group = new StrObj(StrObj.Type.NUMBERS, firstNums);
						objLst.add(group);
					}
					continue;
				} else {
					isProcessingFirstNums = false;
					StrObj group = new StrObj(StrObj.Type.NUMBERS, firstNums);
					objLst.add(group);
				}
			}
			
			StrObj group = new StrObj(cType, Character.toString(c));
			objLst.add(group);
			
		}
		
		return objLst;
	}
	
	public int compareStrObjs(StrObj a, StrObj b) {
		if (a.type == StrObj.Type.NUMBERS && b.type == StrObj.Type.NUMBERS) {
			// if both are numbers, compare their numeric values
			int aNum = Integer.parseInt(a.contents);
			int bNum = Integer.parseInt(b.contents);
			
			if (aNum > bNum) return 1;
			else if (aNum == bNum) return 0;
			else return -1;
			// no need to worry about sorting numerically or not: already taken care in string splitting stage
		} else if (a.type == b.type) {
			// if the chars are of same type, just compare using the ASCII values
			int aChar = -1;
			int bChar = -1; // default values, set in case the content in empty string
			if (a.contents.length() > 0) aChar = (int) a.contents.charAt(0);
			if (b.contents.length() > 0) bChar = (int) b.contents.charAt(0);
			// the content strings contain single chars anyway, so charAt(0) is okay
			// except when contents are numbers, which has already been taken care of above
			
			if (aChar > bChar) return 1;
			else if (aChar == bChar) return 0;
			else return -1;
		} else {
			// special char < number < capital < simple
			int aType = a.type.getNumval();
			int bType = b.type.getNumval();
			
			if (aType > bType) return 1;
			else if (aType == bType) return 0;
			else return -1;
		}
	}
	
	public int compareLines(ArrayList<StrObj> lst1, ArrayList<StrObj> lst2) {
		
		for (int i = 0; i < Math.max(lst1.size(), lst2.size()); i++) {
			StrObj obj1 = lst1.get(i);
			StrObj obj2 = lst2.get(i);
			int comparison = compareStrObjs(obj1, obj2);
			
			if (comparison == 1) { // lst1 comes after lst2
				return 1;
			} else if (comparison == -1) { // lst1 comes before lst2
				return -1;
			} else { // comparison == 0
				if (i == lst1.size()-1 && i == lst2.size()-1) {
					// reached end of both strings, still identical
					// lst1 and lst2 are identical
					return 0;
				} else if (i == lst1.size()-1) {
					// identical up to ith obj, lst1 ends first -> lst1 comes before lst2
					return -1;
				} else if (i == lst2.size()-1) {
					return 1;
				} else {
					continue;
				}
			}
		}
		return 1; // should not reach this line, but it has to be included
		// this line will not be covered by tests
	}
	
	public ArrayList<String> sortLines(ArrayList<ArrayList<StrObj>> objLsts) {
		ArrayList<ArrayList<StrObj>> sortedLsts = new ArrayList<>();
		ArrayList<String> sortedLines = new ArrayList<>();
		
		for (ArrayList<StrObj> line : objLsts) {
			// for each obj arrLst, iterate through the already-sorted list and position it appropriately
			int size = sortedLsts.size();
			for (int i = 0; i <= size; i++) { // compare with each objLst in sorted
				if (i == sortedLsts.size()) {
					sortedLsts.add(i, line); // add to end of sorted list
				} else {
					ArrayList<StrObj> other = sortedLsts.get(i);
					int comparison = compareLines(line, other);
					if (comparison == 1) { // line should come after other: continue comparing with next line 
						continue;
					} else { // stop and place line before other
						sortedLsts.add(i, line);
						break;
					}
				}
			}
		}
		
		for (ArrayList<StrObj> cgl : sortedLsts) {
			String line = "";
			for (StrObj cg : cgl) {
				line = line.concat(cg.contents);
			}
			sortedLines.add(line);
		}
		
		return sortedLines;
	}

}

class StrObj {
	public enum Type {
		SIMPLE(3), 
		CAPITAL(2), 
		NUMBERS(1), 
		SPECIAL(0);
		
		private int numval;
		
		Type(int numval) {
			this.numval = numval;
		}
		
		public int getNumval() {
			return this.numval;
		}
	}
	
	public Type type;
	public String contents;
	
	public StrObj(Type type, String contents) {
		this.type = type;
		this.contents = contents;
	}

	/*
	public boolean isThisAfterOther(CharGroup other) {
		
		return false;
	}
	*/

}

class ParseRes {
	public ArrayList<String> filenames;
	public boolean isNumericSort;
	
	public ParseRes(ArrayList<String> filenames, boolean isNumericSort) {
		this.filenames = new ArrayList<String>(filenames);
		this.isNumericSort = isNumericSort;
	}
}