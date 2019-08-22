package hw11_2;

import java.util.Random;
import java.util.Scanner;

public class Question_2 {

	public Question_2() {
		
	}
	
	public void checkArrayIndex(Scanner s) {
		int[] nums = generateRnd();
		try {
			System.out.println("Enter the array index to check: ");
			int index = Integer.parseInt(s.nextLine());
			System.out.println(nums[index]);
		} catch(ArrayIndexOutOfBoundsException nope) {
			System.out.println("That index does not exist.");
			System.out.println(nope.toString());
		}
	}
	
	public int[] generateRnd() {
		Random rnd = new Random();
		int[] tempArray = new int[5];
		for(int i = 0; i < 5 - 1; i++) {
			tempArray[i] = rnd.nextInt(100);
		}
		return tempArray;
	}

}
