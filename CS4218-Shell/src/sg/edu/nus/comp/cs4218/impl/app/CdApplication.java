package sg.edu.nus.comp.cs4218.impl.app;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;

public class CdApplication implements Application {

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
		String path = Environment.currentDirectory + '\\' + args[0];
		File f = new File(path);
		if (f.exists() && f.isDirectory()) {
			Environment.currentDirectory = path;
		} else {
			System.out.println("Directory not found");
		}
		
	}

}
