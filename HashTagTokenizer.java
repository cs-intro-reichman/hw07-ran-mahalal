

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);

	
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

	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i = 0; i < 3000; i++){
			if (word.equals(dictionary[i])) {
				return true;
			}
		}
			
		return false;	
		
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		// #feedback - you should assign to a new variable.
		hashtag.toLowerCase();
		
        for (int i = 1; i <= N; i++) {
			if (existInDictionary(hashtag.substring(0, i), dictionary)) {
				System.out.println(hashtag.substring(0, i));
				breakHashTag(hashtag.substring(i , N), dictionary);
				return;
				
			}
		
        }
    }

}
