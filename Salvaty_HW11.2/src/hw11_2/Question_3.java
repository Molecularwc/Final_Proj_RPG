package hw11_2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.Random;
import java.util.Scanner;

public class Question_3 {

	public Question_3() {
	}

	public void arrayToFile(Scanner s) throws FileAlreadyExistsException {
		System.out.println("Enter a file name: ");
		String directory = System.getProperty("user.home");  
		String fileName = String.format("%s.txt", s.nextLine());
		String absolutePath = directory + File.separator + fileName;
		File tmpFile = new File(absolutePath);
		boolean exists = tmpFile.exists();
		int[] arrayOfNum = generateRnd();
		if(exists) {
			throw new FileAlreadyExistsException(absolutePath);
		}
		else {
			try(PrintWriter pWrite = new PrintWriter(absolutePath)) {
				pWrite.println("Richard Salvaty");
				for(int i = 0; i < arrayOfNum.length; i++) {
					pWrite.println(arrayOfNum[i]);
				}				
			} catch (IOException e) {
				System.out.println("Something broke. Good job!");
				System.out.println(e.toString());
			}
		}
	}

	public int[] generateRnd() {
		Random rnd = new Random();
		int[] tempArray = new int[10];
		for (int i = 0; i < 10 - 1; i++) {
			tempArray[i] = rnd.nextInt(100);
		}
		return tempArray;
	}
}
