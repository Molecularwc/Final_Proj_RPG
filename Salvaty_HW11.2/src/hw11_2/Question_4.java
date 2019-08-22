package hw11_2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Question_4 {

	public Question_4() {
	}
	
	public void textFileWrite() {
		String directory = System.getProperty("user.home");  
		String fileName = "text.txt";
		String absolutePath = directory + File.separator + fileName;
		try(PrintWriter pWrite = new PrintWriter(absolutePath)){
			pWrite.println("All men (and women) are created equal!");
		} catch (IOException ex) {
			
		}
	}

}
