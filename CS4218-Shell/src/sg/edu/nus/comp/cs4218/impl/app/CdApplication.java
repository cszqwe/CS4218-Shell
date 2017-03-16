package sg.edu.nus.comp.cs4218.impl.app;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.IOException;

import sg.edu.nus.comp.cs4218.Application;
import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.exception.CdException;

public class CdApplication implements Application {

	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws CdException {
		if (args != null && args.length > 0) { // does not support going to home directory when no args are provided: ignore command
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
				try {
					stdout.write("Directory not found".getBytes());
				} catch (Exception e) {
					throw new CdException("Output stream not working");
				}
			}
		}
	}
}
