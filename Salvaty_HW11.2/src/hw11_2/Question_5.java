package hw11_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Question_5 {

	public Question_5() {
		// TODO Auto-generated constructor stub
	}
	
	public String readTextFile() {
		String directory = System.getProperty("user.home");  
		String fileName = "text.txt";
		String absolutePath = directory + File.separator + fileName;
		String words = null;
		try(BufferedReader bRead = new BufferedReader(new FileReader(absolutePath))){
			words = bRead.readLine();
		}catch(FileNotFoundException ex) {
			System.out.println(absolutePath + " does not exist.");
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return words;
	}

}
