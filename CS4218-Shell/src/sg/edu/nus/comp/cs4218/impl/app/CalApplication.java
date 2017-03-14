package sg.edu.nus.comp.cs4218.impl.app;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import sg.edu.nus.comp.cs4218.app.Cal;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.CalException;
import sg.edu.nus.comp.cs4218.impl.ShellImpl;

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
	HashMap<String, Integer> monthStringToInt = new HashMap<String, Integer>();
	
	
	final int ROW_MAX_LENGTH = 20;
	
	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
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
			System.out.println(printCal(""));
		} else if (args.length == 1) {
			// cal -m	
			if (args[0].equals("-m")) {
				System.out.println(printCalWithMondayFirst(""));
			} else {
				// cal year
				System.out.println(printCalForYear(args[0]));
			}
		} else if (args.length == 2) {
			// cal -m year
			if (args[0].equals("-m")) {
				System.out.println(printCalForYearMondayFirst(args[1]));
			} else {
				// cal month year
				int monthInt = Integer.parseInt(args[1]); 
				if (monthInt > 12 || monthInt < 1) {
					throw new CalException("month must be between 1 and 12");
				} else {
					System.out.println(printCalForMonthYear(args[0], args[1]));
				}
				
			}
		} else if (args.length == 3) {
			// cal -m month year
			int monthInt = Integer.parseInt(args[2]);
			if (monthInt > 12 || monthInt < 1) {
				throw new CalException("month must be between 1 and 12");
			} else {
				System.out.println(printCalForMonthYearMondayFirst(args[1], args[2]));
			}
			
		} else {
			throw new CalException("Cal should have between 1 to 3 arguments");
		}
	}
	
	public String getFirstLine() {
		int currentMonthInt = Integer.parseInt(currentMonth);
		String firstLine = monthIntToString[currentMonthInt] + " " + currentYear;
		return firstLine;
	}
	
	public String indentFirstLine(String line) {
		String result = "";
		int lineLength = line.length();
		int padding = (ROW_MAX_LENGTH - lineLength) / 2;
		for (int i = 0; i < padding; i++) {
			result += " ";
		}
		result += line;
		for (int i = 0; i < padding; i++) {
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
		
		firstLine = getFirstLine();
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
	    System.out.println("first day of month: " + firstDayOfMonth);
	    
	    // Get the starting position
	    int startingPos = firstDayOfMonthPositionSundayFirst.get(firstDayOfMonth);
	    System.out.println("starting pos: " + startingPos);
	    
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
		
		firstLine = getFirstLine();
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
	    System.out.println("first day of month: " + firstDayOfMonth);
	    
	    // Get the starting position
	    int startingPos = firstDayOfMonthPositionMondayFirst.get(firstDayOfMonth);
	    System.out.println("starting pos: " + startingPos);
	    
	    int maxDaysInCurrentMonth = getMaxDaysInMonth(
	    		Integer.parseInt(currentMonth)-1, Integer.parseInt(currentYear));
	    // System.out.println("max days in current month: " + maxDaysInCurrentMonth);
	    
	    result += populateCalendarDays(startingPos, maxDaysInCurrentMonth);
	    
		return result;
	}

	/**
	 * Returns the string to print the calendar for specified month and year
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForMonthYear(String month, String year) {
		return null;
	}

	/**
	 * Returns the string to print the calendar for specified year
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForYear(String year) {
		return null;
	}

	/**
	 * Returns the string to print the calendar for specified month and year
	 * with Monday as the first day of the week
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForMonthYearMondayFirst(String month, String year) {
		return null;
	}

	/**
	 * Returns the string to print the calendar for specified year with Monday
	 * as the first day of the week
	 * @param args String containing command and arguments
	 */
	@Override
	public String printCalForYearMondayFirst(String year) {
		return null;
	}
	
	// 
}
