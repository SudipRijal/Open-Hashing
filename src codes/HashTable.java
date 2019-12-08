package openHashing;
import java.util.Random;
import java.util.Scanner;

public class HashTable {
	
	private String[] words = {"the", "I", "they", "she", "would", "one", "me", "could", "more", "more", "only", "woman",
								"should", "high", "ask", "put", "same", "problem","place", "system", "government", "point", "all", "national",
								"head", "book", "long", "power", "stand", "almost", "white" ,"idea", "whether", "all", "office", "party", "win", "teacher",
								"second", "process", "serve", "oh", "behind", "class", "pass", "roll", "drug", "pull", "son", "arm", "early", "building", "space", 
								"couple", "court", "industry", "quite", "wall", "open", "attention", "cause", "culture", "hundred", "place", "material", "thousand", "security",
								"officer", "goal", "plan", "reduce", "share","hot", "article", "career", "lie", "list", "left", "particularly", "attack", "election", "election", "arrive",
								"glass", "ok", "gun","truth", "rather", "design", "design", "sound", "green", "that", "tonight", "respond", "employee", "wide", "structure", "treat", "worry", "writer", 
								"dream", "somebody"};
	private String[] hashTable = new String [words.length];
	int length = words.length;
	int hashTableLength = hashTable.length;
	
	private void initialize() {
		for (int i=0; i<hashTableLength; i++) {
			hashTable[i] = "0";
		}
	}
	
	public void hash (double mu) {
		
		print(words);
		System.out.println("\n");
		
		int hashcode;
		int j;
		int count = 0;
		
		initialize();
	
		for (int i=0; i<length; i++) {
			j=0;
			double x = (double)count/(double)hashTableLength;
			
			if (x >= mu) {
				
				hashTable = new String[hashTableLength+5];
				hashTableLength = hashTable.length;
				initialize();
				i = 0;
				count = 0;
			}
			
			hashcode = getHashcode(ascii(words[i]));
			
			while(hashTable[hashcode] != "0") {
				hashcode = getHashcode(hashcode+j);
				j++;
			}
			
			hashTable[hashcode] = words[i];
			count++;
			
		}

		System.out.println("\n");
	}
	
	private int getHashcode(int value) {
		return(value % hashTableLength);
	}
	
	private int ascii(String word) {
		int value = 0;
		for(int i=0; i<word.length(); i++) {
			value = value + (int)word.charAt(i);
		}
		return value;
		
	}
	
	public int countCollisions() {
		print(hashTable);
		System.out.println("\n");
		int hashcode;
		int j;
		int count = 0;
		int random;
		
		for(int i=0; i<50; i++) {
			j=0;
			Random r = new Random();
			random = r.nextInt(length-1);
			
			hashcode = getHashcode(ascii(words[random]));
			
			while(hashTable[hashcode] != words[random]) {
				
				
				hashcode = getHashcode(hashcode+j);
				j++;
				count++;
			}
			
		}
		
		return count;
	}
	
	private void print(String[] xxx) {
		int l=xxx.length;
		for (int i=0; i<l; i++)
			System.out.println(i+" ----->"+xxx[i]);
	}
	
	public static void main (String[] args) {
		
		System.out.println("you have a list of 102 most common words in the english language. \n"
				+"please enter the load factor for the hash table");
		
		Scanner input = new Scanner(System.in);
		double mu = input.nextDouble();
		HashTable h1 = new HashTable();
		h1.hash(mu);
		System.out.println(h1.countCollisions());
		
	}
	
}