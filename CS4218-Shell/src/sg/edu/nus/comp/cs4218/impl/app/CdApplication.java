package sg.edu.nus.comp.cs4218.impl.app;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.IOException;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;

public class CdApplication implements Application {

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws AbstractApplicationException {
		if (args.length > 0 || args == null) { // does not support going to home directory when no args are provided
			String path = Environment.currentDirectory + '\\' + args[0];
			// if there are more arguments, they are ignored
			File f = new File(path);
			if (f.exists() && f.isDirectory()) {
				try {
					//To simplify the path if the path contains ".." 
					Environment.currentDirectory = f.getCanonicalPath();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				//This one would be printed to the system.out instead of stdout as CD does not expect any output.
				System.out.println("Directory not found");
			}
		}
	}
}
