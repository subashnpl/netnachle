package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class Logger {
	private static int lineNumber = 1;
	private static String fileName;
	private static boolean exists = false;
	private Calendar cal;
	private FileWriter fstream;
	private BufferedWriter out;
	private static Logger logi;
	
	private Logger(String fileName) {
		this.fileName = fileName;
		createFile();
	}
	
	private void createFile(){
		try{
		    // Create file
		    fstream = new FileWriter(fileName);
		    out = new BufferedWriter(fstream);
		    out.write("Movie Recommender ver 1.01 - Log file");
		    out.newLine();
		    out.write("-------------------------------------------------------------");
		    out.newLine();
	 	}catch (Exception e){
		      System.err.println("Error: " + e.getMessage());}
	}

	public static Logger makeSingleton(String fileName){
		if (!exists) {
			logi = new Logger(fileName);
			exists = true;
			return logi;
		}
		return logi;
	}	
	
	public void log(String action){
		cal = cal.getInstance();
		String prefix = lineNumber + " [" + cal.getTime() + "]: ";
		lineNumber++;
		try {
			out.write(prefix + action);
			out.newLine();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("couldn't write to file action: " + action  );
		}
	}

	public void exit(){
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String toString(){
		return "logger writes to the file: " + fileName; 
	}
}

