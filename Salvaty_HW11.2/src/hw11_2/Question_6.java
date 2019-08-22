package hw11_2;

public class Question_6 {

	public Question_6() {
		
	}
	
	public void strCounter(String words) {
		int vowels = 0, wordCount = 0, upper = 0, lower = 0, digits = 0;
		String[] wordArray = words.split(" ");		
		
		for(int i = 0; i < words.length(); i++) {
			char ch = words.charAt(i);
			if(ch >= 'A' && ch <= 'Z') {
				upper++;
			}else if(ch >= 'a' && ch <= 'z') {
				lower++;
			} else if(ch >= '0' && ch <= '9') {
				digits++;
			}			
		}
		for(int y = 0; y < wordArray.length; y++) {
			wordCount++;
		}
		words = words.toLowerCase();
		for(int z = 0; z < words.length(); z++) {
			if(words.charAt(z) == 'a' || words.charAt(z) == 'e' || words.charAt(z) == 'i' || words.charAt(z) == 'o' || words.charAt(z) == 'u'){
				vowels++;
			}
		}
		System.out.println("Vowels : " + vowels);
		System.out.println("Upper case letters : " + upper);
		System.out.println("Lower case letters : " + lower);
		System.out.println("Digits : " + digits);
		System.out.println("Word Count : " + wordCount);
	}

}
