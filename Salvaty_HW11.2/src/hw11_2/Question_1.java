package hw11_2;

import java.util.Scanner;

public class Question_1 {

	public Question_1() {

	}
	
	public void checkInts(Scanner s) {
		System.out.println("Enter a number (enter 999 to stop): ");
		try {
			int nums = Integer.parseInt(s.nextLine());
			while(nums != 999) {
				System.out.println("Enter another number (enter 999 to stop): ");
				nums = Integer.parseInt(s.nextLine());
			}
		} catch (Exception e) {
			System.out.println("Only whole integers are allowed. Try again.");
			System.out.println(e.toString());
		}
	}

}
