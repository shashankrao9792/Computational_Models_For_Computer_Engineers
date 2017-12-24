import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class hashLinearStrings {

	class dataItem {
		private String iData;

		public dataItem(String i) {
			iData = i;
		}

		public String getKey() {
			return iData;
		}
	}

	class hashTable {
		private dataItem[] hashArray;
		private int arraySize;
		private dataItem nonItem;

		public int getHashTableSize() {
			int count = 0;
			for (int i = 0; i < arraySize; i++) {
				if (hashArray[i] != null && !(hashArray[i].getKey().equals("-1"))) {
					count++;
				}
			}
			return count;
		}

		public hashTable(int size) {
			arraySize = size;
			hashArray = new dataItem[arraySize];
			nonItem = new dataItem("-1");
		}

		public void displayTable() {
			System.out.print("Hash Table: ");
			for (int j = 0; j < arraySize; j++) {
				System.out.print("\"");
				if (hashArray[j] != null) {
					System.out.print(hashArray[j].getKey());
				} else {
					System.out.print("**");
				}
				System.out.print("\" ");
			}
			System.out.println("");
		}

		/*
		 * hashFuncForString is a hashing function written as per Homework Assignment
		 * 11.2 requirements This function will hash a String key to its corresponding
		 * int value it considers that the input String key will only contain lowercase
		 * letters, and not contain uppercase letters, spaces or any other special
		 * characters
		 */
		public int hashFuncForString(String key) {
			int hashval = 0;
			for (int j = 0; j < key.length(); j++) {
				int letter = key.charAt(j) - 96;
				hashval = (hashval * 26 + letter) % arraySize;
			}
			return hashval;
		}

		public void insert(dataItem item) {
			String key = item.getKey();
			int hashval = hashFuncForString(key);
			System.out.println("Hashed value of new string which got inserted is: " + hashval);
			while ((hashArray[hashval] != null) && !(hashArray[hashval].getKey().equals("-1"))) {
				++hashval;
				hashval %= arraySize;
			}
			hashArray[hashval] = item;
		}

		/**
		 * @param key
		 * @return
		 */
		public dataItem delete(String key) {
			int hashval = hashFuncForString(key);
			int count = 0;
			while (hashArray[hashval] != null && count <= getHashTableSize()) {
				if (hashArray[hashval].getKey().equals(key)) {
					dataItem temp = hashArray[hashval];
					hashArray[hashval] = nonItem;
					return temp;
				}
				hashval++;
				hashval %= arraySize;
				count++;
			}
			System.out.println("Couldn't find \"" + key + "\" in hash table to delete!");
			return null;
		}

		public int find(String key) {
			int hashval = hashFuncForString(key);
			int count = 0;
			while (hashArray[hashval] != null && count <= getHashTableSize()) {
				if (hashArray[hashval].getKey().equals(key)) {
					return hashval;
				}
				hashval++;
				hashval %= arraySize;
				count++;
			}
			return -99;
		}
	}

	public static int getInt() throws IOException {
		String s = getString();
		return Integer.parseInt(s);
	}

	public static char getChar() throws IOException {
		String s = getString();
		return s.charAt(0);
	}

	public static String getString() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		hashLinearStrings H = new hashLinearStrings();
		hashLinearStrings.dataItem adataItem;
		int size;
		String aKey;
		System.out.println("Homework Assignment 11.2");
		System.out.print("Enter size of hash table: ");
		size = getInt();

		hashLinearStrings.hashTable theHT = H.new hashTable(size);

		while (true) {
			System.out.print("Enter first character of show(s), insert(i), delete(d), find(f), or quit(q): ");
			char choice = getChar();
			switch (choice) {
			case 's':
				theHT.displayTable();
				break;
			case 'i':
				System.out.println("Current Hash Table size: " + theHT.getHashTableSize());
				if (theHT.getHashTableSize() >= size) {
					System.out.println("Hash Table full!!");
					break;
				}
				System.out.print("Enter key value to insert: ");
				aKey = getString();
				adataItem = H.new dataItem(aKey);
				theHT.insert(adataItem);
				break;
			case 'd':
				System.out.print("Enter key value to delete: ");
				aKey = getString();
				theHT.delete(aKey);
				break;
			case 'f':
				System.out.print("Enter key value to find: ");
				aKey = getString();
				int foundIndex = theHT.find(aKey);
				if (foundIndex != -99) {
					System.out.println("Found \"" + aKey + "\" at index " + foundIndex + " of hash table");
				} else {
					System.out.println("Couldn't find key \"" + aKey + "\" in hash table!");
				}
				break;
			case 'q':
				System.out.println("Exiting!!");
				return;
			default:
				System.out.println("Invalid Entry");
			}
		}
	}
}
