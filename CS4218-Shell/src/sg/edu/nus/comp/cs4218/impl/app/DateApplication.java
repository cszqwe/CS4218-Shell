package sg.edu.nus.comp.cs4218.impl.app;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import sg.edu.nus.comp.cs4218.app.Date;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;

public class DateApplication implements Date {

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
		System.out.println(printCurrentDate(null));

	}

	@Override
	public String printCurrentDate(String args) {
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", java.util.Locale.US);
		return sdf.format(new java.util.Date());
	}

}
