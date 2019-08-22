package hw11_2;

import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;

public class HW11_2 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Question_1 q1 = new Question_1();
		Question_2 q2 = new Question_2();
		Question_3 q3 =  new Question_3();
		Question_4 q4 = new Question_4();
		Question_5 q5 = new Question_5();
		Question_6 q6 = new Question_6();
		
		q1.checkInts(s);
		q2.checkArrayIndex(s);
		try {
			q3.arrayToFile(s);
		} catch (FileAlreadyExistsException e) {
			System.out.println(e.toString());
		}
		q4.textFileWrite();
		System.out.println(q5.readTextFile());
		q6.strCounter(q5.readTextFile());
	}

}
