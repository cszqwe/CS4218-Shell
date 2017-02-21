package sg.edu.nus.comp.cs4218.impl.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sg.edu.nus.comp.cs4218.Environment;
import sg.edu.nus.comp.cs4218.app.Sed;
import sg.edu.nus.comp.cs4218.exception.AbstractApplicationException;
import sg.edu.nus.comp.cs4218.exception.OutputstreamNotValidException;
import sg.edu.nus.comp.cs4218.exception.SedException;

public class SedApplication implements Sed{
	InputStream stdin = System.in;
	OutputStream stdout;
	@Override
	public void run(String[] args, InputStream stdin, OutputStream stdout) throws SedException, OutputstreamNotValidException {
		ArrayList<String> files = new ArrayList<>();
		this.stdin = stdin;
		this.stdout = stdout;
		String output = "";
		
		if (args == null) {
			if (stdin == null || stdout == null) {
				throw new SedException("Null Pointer Exception");
			}
		}
		String wholeCommand = "sed ";
		for (int i = 0; i< args.length; i++){
			wholeCommand = wholeCommand + args[i] + " ";
		}
		if (args[0].charAt(0) != 's') throw new SedException("Wrong usage");
		String splitSymbol = ""+args[0].charAt(1);
		String[] strs = args[0].split(splitSymbol);
		boolean isGlobal = false;
		if (strs.length != 3 && strs.length != 4){
			throw new SedException("Wrong usage");
		}
		if (strs.length == 4 && strs[3].equals("g")){
			isGlobal = true;
		}
		String originalExp = strs[1];
		String newExp = strs[2];
		boolean validReg = true;
		boolean validNew = true;
		try{
			Pattern.compile(originalExp);
		}catch (Exception e){
			validReg = false;
		}
		try{
			Pattern.compile(newExp);
		}catch (Exception e){
			validNew = false;
		}
	
		if (!validReg){
			replaceSubstringWithInvalidRegex(wholeCommand);
		}
		
		if (!validNew){
			replaceSubstringWithInvalidReplacement(wholeCommand);
		}

		if (args.length == 2){
			if (isGlobal){
				output = replaceAllSubstringsInFile(wholeCommand);
			}else{
				output = replaceFirstSubStringInFile(wholeCommand);	
			}
		}else{
			if (isGlobal){
				output = replaceAllSubstringsInStdin(wholeCommand);
			}else{
				output = replaceFirstSubStringFromStdin(wholeCommand);	
			}
		}
		
		try {
			stdout.write(output.getBytes());
		} catch (IOException e) {
			throw new OutputstreamNotValidException("Output stream not working");
		}

	}

	public String getReg(String args){
		String[] allArgs = args.trim().split(" ");
		return allArgs[1].split(allArgs[1].charAt(1)+"")[1];
	}
	
	public String getReplacement(String args){
		String[] allArgs = args.trim().split(" ");
		return allArgs[1].split(allArgs[1].charAt(1)+"")[2];
	}
	
	public String getContentFromStdin(String args){
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
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
           try {      
                in.close();      
            } catch (IOException e) {      
                e.printStackTrace();      
            }      
        }
       return str.toString();
	}
	
	public String getContentFromFile(String args) throws SedException{
		String file = args.trim().split(" ")[2];
		Path filePath = Paths.get(Environment.currentDirectory).resolve(file);
		String contents;
		if (Files.exists(filePath) && Files.isReadable(filePath) && !Files.isDirectory(filePath)) {
				try {
					contents = new String(Files.readAllBytes(filePath));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new SedException("File is not readable");
				}
			
		} else {
			throw new SedException("No such file");
		}
		return contents;
	}
	@Override
	public String replaceFirstSubStringInFile(String args) {
		String regex = getReg(args);
		String replacement = getReplacement(args);
		String contents;
		try{
			contents = getContentFromFile(args);
		}catch (Exception e){
			return (e.getMessage());
		}
		contents = contents.replaceFirst(regex, replacement);
		return contents;
	}

	@Override
	public String replaceAllSubstringsInFile(String args) {
		String regex = getReg(args);
		String replacement = getReplacement(args);
		String contents;
		try{
			contents = getContentFromFile(args);
		}catch (Exception e){
			return (e.getMessage());
		}
		contents = contents.replaceAll(regex, replacement);
		return contents;
	}

	@Override
	public String replaceFirstSubStringFromStdin(String args) {
		String regex = getReg(args);
		String replacement = getReplacement(args);
		String contents;
		try{
			contents = getContentFromStdin(args);
		}catch (Exception e){
			return (e.getMessage());
		}
		contents = contents.replaceFirst(regex, replacement);
		return contents;
	}

	@Override
	public String replaceAllSubstringsInStdin(String args) {
		String regex = getReg(args);
		String replacement = getReplacement(args);
		String contents;
		try{
			contents = getContentFromStdin(args);
		}catch (Exception e){
			return (e.getMessage());
		}
		contents = contents.replaceAll(regex, replacement);
		return contents;
	}

	@Override
	public String replaceSubstringWithInvalidReplacement(String args) {
		return "The command " + args + " has invalid Replacement";
	}

	@Override
	public String replaceSubstringWithInvalidRegex(String args) {
		return "The command " + args + " has invalid Regex";
	}

}
