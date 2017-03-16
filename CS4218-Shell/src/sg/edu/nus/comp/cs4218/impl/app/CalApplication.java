package sg.edu.nus.comp.cs4218.impl.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import sg.edu.nus.comp.cs4218.app.Cal;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CalException;

public class CalApplication implements Cal {
	/*
	 * Assumption: Month is entered as number instead of word
	 * e.g. (6 instead of june)
	 * 
	 */
	static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static Date date = new Date();
	String currentDate = dateFormat.format(date).split(" ")[0];
	String[] currentDateSplit = currentDate.split("/");
	String currentYear = currentDateSplit[0];
	String currentMonth = currentDateSplit[1];
	
	String[] monthIntToString = {
			null, "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December"
	};
	
	// 0 to 6
	HashMap<String, Integer> firstDayOfMonthPositionMondayFirst = new HashMap<String, Integer>();
	HashMap<String, Integer> firstDayOfMonthPositionSundayFirst = new HashMap<String, Integer>();	
	
	final int ROW_MAX_LENGTH = 20;
	
	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws CalException {
		firstDayOfMonthPositionMondayFirst.put("Monday", 0);
		firstDayOfMonthPositionMondayFirst.put("Tuesday", 1);
		firstDayOfMonthPositionMondayFirst.put("Wednesday", 2);
		firstDayOfMonthPositionMondayFirst.put("Thursday", 3);
		firstDayOfMonthPositionMondayFirst.put("Friday", 4);
		firstDayOfMonthPositionMondayFirst.put("Saturday", 5);
		firstDayOfMonthPositionMondayFirst.put("Sunday", 6);
		
		firstDayOfMonthPositionSundayFirst.put("Sunday", 0);
		firstDayOfMonthPositionSundayFirst.put("Monday", 1);
		firstDayOfMonthPositionSundayFirst.put("Tuesday", 2);
		firstDayOfMonthPositionSundayFirst.put("Wednesday", 3);
		firstDayOfMonthPositionSundayFirst.put("Thursday", 4);
		firstDayOfMonthPositionSundayFirst.put("Friday", 5);
		firstDayOfMonthPositionSundayFirst.put("Saturday", 6);
		
		// cal
		if (args.length == 0) {
			try {
				stdout.write(printCal("").getBytes());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new CalException(e.getMessage());
			}			
		} else if (args.length == 1) {
			// cal -m	
			if (args[0].equals("-m")) {
				try {
					stdout.write(printCalWithMondayFirst("").getBytes());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new CalException(e.getMessage());
				}
			} else {
				// cal year
				try {
					// Catch illegal year argument
					Integer.parseInt(args[0]);
					stdout.write(printCalForYear(args[0]).getBytes());
				} catch (Exception e) {
					throw new CalException("Usage: cal -m or cal <year>");
				}
			}
		} else if (args.length == 2) {
			// cal -m year
			if (args[0].equals("-m")) {
				try {
					// Catch illegal year argument
					Integer.parseInt(args[1]);
					stdout.write(printCalForYearMondayFirst(args[1]).getBytes());
				} catch (Exception e){
					throw new CalException("Usage: cal -m or cal <year>");
				}
			} else {
				// cal month year
				try {
					int monthInt = Integer.parseInt(args[0]); 

					if (monthInt > 12 || monthInt < 1) {
						throw new CalException("month must be between 1 and 12");
					} else {
						try {
							Integer.parseInt(args[1]); 
							stdout.write(printCalForMonthYear(args[0], args[1]).getBytes());
						} catch (Exception e) {
							throw new CalException("Usage: cal -m or cal <year>");
						} 
					}
				} catch (NumberFormatException e) {
					throw new CalException("Usage: cal -m or cal <year>");
				} 	
			}
		} else if (args.length == 3) {
			// cal -m month year
			if (!args[0].equals("-m")) {
				throw new CalException("First argument must be '-m'");
			}
			try {
				int monthInt = Integer.parseInt(args[1]);
				if (monthInt > 12 || monthInt < 1) {
					throw new CalException("month must be between 1 and 12");
				} else {
					try {
						Integer.parseInt(args[2]);
						stdout.write(printCalForMonthYearMondayFirst(args[1], args[2]).getBytes());
					} catch (Exception e) {
						throw new CalException("Usage: cal -m <month> <year>");
					} 
				}
			} catch (NumberFormatException e) {
				throw new CalException("month must be between 1 and 12");
			} 
		} else {
			throw new CalException("Cal should have between 1 to 3 arguments");
		}
	}
	
	public String getFirstLine(String month, String year) {
		int currentMonthInt = Integer.parseInt(month);
		String firstLine;
		if (year.equals("")) {
			firstLine = monthIntToString[currentMonthInt];
		} else {
			firstLine = monthIntToString[currentMonthInt] + " " + year;
		}
		
		return firstLine;
	}
	
	public String indentFirstLine(String line) {
		String result = "";
		int lineLength = line.length();
		int padding = (ROW_MAX_LENGTH - lineLength) / 2;
		boolean extraPadding = false;
		
		// Need an extra space at the end for odd number of characters
		if ((ROW_MAX_LENGTH - lineLength) % 2 != 0) {
			extraPadding = true;
		}
		for (int i = 0; i < padding; i++) {
			result += " ";
		}
		result += line;
		for (int i = 0; i < padding; i++) {
			result += " ";
		}
		if (extraPadding) {
			result += " ";
		}
		return result;
	}
	
	public int getMaxDaysInMonth(int month, int year) {
	    Calendar cal = Calendar.getInstance();
	    // Note: 0-based months
	    cal.set(year, month, 1);
	    return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public String populateCalendarDays(int startPos, int maxDays) {
		String result = "";
		int current = 1;
		int remainingSpaces = ROW_MAX_LENGTH - (startPos * 3);
		// Go to starting position
		for (int i = 0; i < startPos * 3; i++) {
			result += " ";
		}
		for (int i = 1; i <= maxDays; i++) {
			result += i;
			if (i == maxDays) {
				// Fill the up the last row with spaces
				remainingSpaces -= 2;
				for (int j = 0; j < remainingSpaces; j++) {
					result += " ";
				}
				break;
			}
			if (i < 10) {
				remainingSpaces -= 1;
				// last entry of the row
				if (remainingSpaces == 1) {
					result += " \n";
					remainingSpaces = ROW_MAX_LENGTH;
				} else {
					result += "  ";
					remainingSpaces -= 2;
				}
			} else {
				remainingSpaces -= 2;
				// last entry of the row
				if (remainingSpaces == 0) {
					result += "\n";
					remainingSpaces = ROW_MAX_LENGTH;
				} else {
					result += " ";
					remainingSpaces -= 1;
				}
			}
		}
		return result;
	}
	
	/**
	 * Print the calendar of the current month
	 * @param args String containing command and arguments to print the calendar of the current month
	 */
	@Override
	public String printCal(String args) {
		// firstLine e.g. December 2009
		String result = "";
		String firstLine = "";
		
		firstLine = getFirstLine(currentMonth, currentYear);
		firstLine = indentFirstLine(firstLine) + "\n";
		
		result = firstLine;
		result += "Su Mo Tu We Th Fr Sa\n";
		
		Calendar cal = Calendar.getInstance();  
		// cal.set(Calendar.MONTH, 0);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    Date calDate = cal.getTime();
	    // System.out.println(calDate);
	    
	    // Get first day of the month
	    DateFormat sdf = new SimpleDateFormat("EEEE");
	    String firstDayOfMonth = sdf.format(calDate);
	    // System.out.println("first day of month: " + firstDayOfMonth);
	    
	    // Get the starting position
	    int startingPos = cal.get(Calendar.DAY_OF_WEEK) - 1;
	    if (startingPos < 0) startingPos += 7;
	    // System.out.println("starting pos: " + startingPos);
	    
	    int maxDaysInCurrentMonth = getMaxDaysInMonth(
	    		Integer.parseInt(currentMonth)-1, Integer.parseInt(currentYear));
	    // System.out.println("max days in current month: " + maxDaysInCurrentMonth);
	    
	    result += populateCalendarDays(startingPos, maxDaysInCurrentMonth);
	    
		return result;
	}

	/**
	 * Returns the string to print the calendar of the current month with Monday
	 * as the first day of the week
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalWithMondayFirst(String args) {
		// firstLine e.g. December 2009
		String result = "";
		String firstLine = "";
		
		firstLine = getFirstLine(currentMonth, currentYear);
		firstLine = indentFirstLine(firstLine) + "\n";
		
		result = firstLine;
		result += "Mo Tu We Th Fr Sa Su\n";
		
		Calendar cal = Calendar.getInstance();  
		// cal.set(Calendar.MONTH, 0);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    Date calDate = cal.getTime();
	    // System.out.println(calDate);
	    
	    // Get first day of the month
	    DateFormat sdf = new SimpleDateFormat("EEEE");
	    String firstDayOfMonth = sdf.format(calDate);
	    // System.out.println("first day of month: " + firstDayOfMonth);
	    
	    // Get the starting position
	    int startingPos = cal.get(Calendar.DAY_OF_WEEK) - 2;
	    if (startingPos < 0) startingPos += 7;

	    int maxDaysInCurrentMonth = getMaxDaysInMonth(
	    		Integer.parseInt(currentMonth)-1, Integer.parseInt(currentYear));
	    
	    result += populateCalendarDays(startingPos, maxDaysInCurrentMonth);
	    
		return result;
	}

	/**
	 * Returns the string to print the calendar for specified month and year
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForMonthYear(String month, String year) {
		// firstLine e.g. December 2009
		String result = "";
		String firstLine = "";
		
		firstLine = getFirstLine(month, year);
		firstLine = indentFirstLine(firstLine) + "\n";
		
		result = firstLine;
		result += "Su Mo Tu We Th Fr Sa\n";
		
		Calendar cal = Calendar.getInstance(); 
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
	    cal.set(Calendar.DAY_OF_MONTH, 1);
	    Date calDate = cal.getTime();
	    // System.out.println(calDate);
	    
	    // Get first day of the month
	    DateFormat sdf = new SimpleDateFormat("EEEE");
	    String firstDayOfMonth = sdf.format(calDate);
	    // System.out.println("first day of month: " + firstDayOfMonth);
	    
	    // Get the starting position
	    int startingPos = cal.get(Calendar.DAY_OF_WEEK) - 1;
	    if (startingPos < 0) startingPos += 7;
	    // System.out.println("starting pos: " + startingPos);
	    
	    int maxDaysInCurrentMonth = getMaxDaysInMonth(
	    		Integer.parseInt(month)-1, Integer.parseInt(year));
	    // System.out.println("max days in current month: " + maxDaysInCurrentMonth);
	    
	    result += populateCalendarDays(startingPos, maxDaysInCurrentMonth);
	    
		return result;
	}
	
	public String processResultArray(String[][] arrayResult) {
		// Build the result row by row
		String result = "";
		// String january = arrayResult[0][0];
		boolean padLastRow = false;

		
		for (int i = 0; i < 4; i++) {
			String[] colOneSplit = arrayResult[i][0].split("\\r?\\n");
			String[] colTwoSplit = arrayResult[i][1].split("\\r?\\n");
			String[] colThreeSplit = arrayResult[i][2].split("\\r?\\n");
			int colOneLength = colOneSplit.length;
			int colTwoLength = colTwoSplit.length;
			int colThreeLength = colThreeSplit.length;
			// System.out.println(colOneLength + " " + colTwoLength + " " + colThreeLength);
			
			int minColLength = Math.min(Math.min(colOneLength, colTwoLength), colThreeLength);
			
			// Fill up last row with extra spaces
			if (colOneLength != colTwoLength || colTwoLength != colThreeLength) {
				padLastRow = true;
			} else {
				padLastRow = false;
			}
			
			for (int j = 0; j < minColLength; j++) {
				result += colOneSplit[j] + "  " + colTwoSplit[j] + "  " +
							colThreeSplit[j];
				// Dont add newline for last entry row
				if (i+1 != 4 || j+1 != minColLength || padLastRow) {
					result += "\n";
				}
					
			}
			if (padLastRow) {
				// Identify the longest column
				// Assume the difference btw column heights is at most 1
				if ((minColLength+1) == colOneLength) {
					// System.out.println("PAD FIRST COLUMN");
					result += colOneSplit[minColLength];
					// Pad with 44 spaces
					for (int k = 0; k < 44; k++) {
						result += " ";
					}
					// Dont add newline for last entry row
					if (i+1 != 4) {
						result += "\n";
					}
				} else if ((minColLength+1) == colTwoLength) {
					// System.out.println("PAD SECOND COLUMN");
					// Pad with 22 spaces
					for (int k = 0; k < 22; k++) {
						result += " ";
					}
					result += colTwoSplit[minColLength];
					
					// Pad with 22 spaces
					for (int k = 0; k < 22; k++) {
						result += " ";
					}
					// Dont add newline for last entry row
					if (i+1 != 4) {
						result += "\n";
					}
				} else if ((minColLength+1) == colThreeLength) {
					// System.out.println("PAD THIRD COLUMN");
					// Pad with 44 spaces
					for (int k = 0; k < 44; k++) {
						result += " ";
					}
					result += colThreeSplit[minColLength];
					// Dont add newline for last entry row
					if (i+1 != 4) {
						result += "\n";
					}
				}
			}
			if (i+1 == 4) {
				result += "\n";
			}
			else if (i+1 != 4) {
				result += "\n\n";
			}
		}
		/*
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.println(arrayResult[i][j]);
			}
		}
		*/
		return result;
		// return null;
	}

	/**
	 * Returns the string to print the calendar for specified year
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForYear(String year) {
		// firstLine e.g. December 2009
		String masterResult = "";
		String[][] masterResultArray = new String[4][3];
		String result = "";
		String firstLine = "";
		int currMonth = 1;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				result = "";
				// populate calendar for each month of the year
				firstLine = getFirstLine(String.valueOf(currMonth), "");
				firstLine = indentFirstLine(firstLine) + "\n";
				result = firstLine;
				result += "Su Mo Tu We Th Fr Sa\n";

				
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, currMonth-1);
			    cal.set(Calendar.DAY_OF_MONTH, 1);
			    Date calDate = cal.getTime();
			    // System.out.println(calDate);
			    
			    // Get first day of the month
			    DateFormat sdf = new SimpleDateFormat("EEEE");
			    String firstDayOfMonth = sdf.format(calDate);
			    // System.out.println("first day of month: " + firstDayOfMonth);
			    
			    // Get the starting position
			    int startingPos = cal.get(Calendar.DAY_OF_WEEK) - 1;
			    if (startingPos < 0) startingPos += 7;
			    // System.out.println("starting pos: " + startingPos);
			    
			    int maxDaysInCurrentMonth = getMaxDaysInMonth(
			    		currMonth-1, Integer.parseInt(currentYear));
			    // System.out.println("max days in current month: " + maxDaysInCurrentMonth);
			    
			    result += populateCalendarDays(startingPos, maxDaysInCurrentMonth);
			    
			    masterResultArray[i][j] = result;
			    // masterResult += result;
			    currMonth++;
			}
		}
		// return masterResult;
		return processResultArray(masterResultArray);
	}

	/**
	 * Returns the string to print the calendar for specified month and year
	 * with Monday as the first day of the week
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForMonthYearMondayFirst(String month, String year) {
		// firstLine e.g. December 2009
			String result = "";
			String firstLine = "";
			
			firstLine = getFirstLine(month, year);
			firstLine = indentFirstLine(firstLine) + "\n";
			
			result = firstLine;
			result += "Mo Tu We Th Fr Sa Su\n";
			
			Calendar cal = Calendar.getInstance(); 
			cal.set(Calendar.YEAR, Integer.parseInt(year));
			cal.set(Calendar.MONTH, Integer.parseInt(month)-1);
		    cal.set(Calendar.DAY_OF_MONTH, 1);
		    Date calDate = cal.getTime();
		    // System.out.println(calDate);
		    
		    // Get first day of the month
		    DateFormat sdf = new SimpleDateFormat("EEEE");
		    String firstDayOfMonth = sdf.format(calDate);
		    // System.out.println("first day of month: " + firstDayOfMonth);
		    
		    // Get the starting position

		    int startingPos = cal.get(Calendar.DAY_OF_WEEK) - 2;
		    if (startingPos < 0) startingPos += 7;
		    // System.out.println("starting pos: " + startingPos);
		    
		    int maxDaysInCurrentMonth = getMaxDaysInMonth(
		    		Integer.parseInt(month)-1, Integer.parseInt(year));
		    // System.out.println("max days in current month: " + maxDaysInCurrentMonth);
		    
		    result += populateCalendarDays(startingPos, maxDaysInCurrentMonth);
		    
			return result;
	}

	/**
	 * Returns the string to print the calendar for specified year with Monday
	 * as the first day of the week
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForYearMondayFirst(String year) {
		// firstLine e.g. December 2009
		String masterResult = "";
		String[][] masterResultArray = new String[4][3];
		String result = "";
		String firstLine = "";
		int currMonth = 1;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				result = "";
				// populate calendar for each month of the year
				firstLine = getFirstLine(String.valueOf(currMonth), "");
				firstLine = indentFirstLine(firstLine) + "\n";
				result = firstLine;
				result += "Mo Tu We Th Fr Sa Su\n";

				
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, Integer.parseInt(year));
				cal.set(Calendar.MONTH, currMonth-1);
			    cal.set(Calendar.DAY_OF_MONTH, 1);
			    Date calDate = cal.getTime();
			    // System.out.println(calDate);
			    
			    // Get first day of the month
			    DateFormat sdf = new SimpleDateFormat("EEEE");
			    String firstDayOfMonth = sdf.format(calDate);
			    // System.out.println("first day of month: " + firstDayOfMonth);
			    
			    // Get the starting position
			    int startingPos = cal.get(Calendar.DAY_OF_WEEK) - 2;
			    if (startingPos < 0) startingPos += 7;
			     
			    int maxDaysInCurrentMonth = getMaxDaysInMonth(
			    		currMonth-1, Integer.parseInt(currentYear));
			    // System.out.println("max days in current month: " + maxDaysInCurrentMonth);
			    
			    result += populateCalendarDays(startingPos, maxDaysInCurrentMonth);
			    
			    masterResultArray[i][j] = result;
			    // masterResult += result;
			    currMonth++;
			}
		}
		// return masterResult;
		return processResultArray(masterResultArray);
	}
}
