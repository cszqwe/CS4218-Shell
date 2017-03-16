package sg.edu.nus.comp.cs4218.impl.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.DateException;
import sg.edu.nus.comp.cs4218.exception.PwdException;

public class PwdApplication implements Application {

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws PwdException {
		if (stdout == null) {
			throw new PwdException("Null Pointer Exception");
		}
		
		try {
			stdout.write(Environment.currentDirectory.getBytes());
		} catch (IOException e) {
			throw new PwdException("Output stream not working");
		}
	}

}
