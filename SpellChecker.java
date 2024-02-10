
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);

	}

	public static String tail(String str) {
		if (str.length() == 1) {
			str = "";
		} else {
		str = str.substring(1, str.length());
		}
		return str;
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word2.length() == 0){
			return(word1.length());
		}
		else if (word1.length() == 0){
			return(word2.length());
		}
		else if (word1.charAt(0) == word2.charAt(0)){
			return (levenshtein(tail(word1), tail(word2)));
		} else {
			return(1 + (Math.min(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))), levenshtein(tail(word1), tail(word2)))));
		}

	
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		while (!in.isEmpty()) {
			for (int i = 0; i < 3000; i++){
				dictionary[i] = in.readLine();
			}

		}
		return dictionary;
	}


	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int cur = threshold + 1;
		String closestoWord = ""; 
		String s = "";
		for (int i = 0; i < dictionary.length; i++) {
			// #feedback - in order to avoid calling to levenshtein twice (as recursive functions can be inefficient), it is better to assign the result to a variable and then use the variable only.
			if(levenshtein(word, dictionary[i]) < cur) {
				cur = levenshtein(word, dictionary[i]);
				closestoWord = dictionary[i]; 
			}
		}

		if (cur <= threshold) {
			s = closestoWord;
		} else {
			s = word;
		}

		return s;
	}

}
