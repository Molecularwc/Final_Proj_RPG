package scan;

import java.util.Scanner;

public class Scan {
	Scanner s;
	
	public Scan() {
		s = new Scanner(System.in);
	}
	
	public String ScanString(String message) {
		System.out.println(message);
		return s.nextLine();
	}
	
	public String ScanStrings() {
		return s.nextLine();
	}
	
	public boolean ScanBool(String message) {
		System.out.println(message);
		return s.nextBoolean();
	}
	
	public int ScanInt(String message) {
		System.out.println(message);
		return Integer.parseInt(s.nextLine());
	}
	
	public double ScanDouble(String message) {
		System.out.println(message);
		return Double.parseDouble(s.nextLine());
	}
	
	public long ScanLong(String message) {
		System.out.println(message);
		return Long.parseLong(s.nextLine());
	}
}
