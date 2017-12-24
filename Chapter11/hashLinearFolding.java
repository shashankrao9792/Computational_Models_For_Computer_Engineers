import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class hashLinearFolding {

	class dataItem {
		private int iData;

		public dataItem(int i) {
			iData = i;
		}

		public int getKey() {
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
				if (hashArray[i] != null && hashArray[i].getKey() != -1) {
					count++;
				}
			}
			return count;
		}

		public hashTable(int size) {
			arraySize = size;
			hashArray = new dataItem[arraySize];
			nonItem = new dataItem(-1);
		}

		public void displayTable() {
			System.out.println("Hash Table: ");
			for (int j = 0; j < arraySize; j++) {
				if (hashArray[j] != null) {
					System.out.print("HashArray[" + j + "] = " + hashArray[j].getKey() + "  ");
				} else {
					System.out.print("** ");
				}
				if (j % 15 == 0 && j != 0) {
					System.out.println(""); // to display maximum 15 hash table elements per row
				}
			}
			System.out.println("");
		}

		public int hashFunc(int key) {
			return (key % arraySize);
		}

		/*
		 * hashFuncFolding is a hashing function written as per Homework Assignment 11.3
		 * requirements This function will calculate the hash value by considering
		 * groups of digits starting from the rightmost end Group size will differ based
		 * on size of hash array considered And no, it does not matter if array size is
		 * a multiple of 10 or not
		 */
		public int hashFuncFolding(int key) {

			int hashval = 0;
			// if(arraySize >= 10) {
			int groupSize = String.valueOf(arraySize - 1).length();
			int divNum = (int) Math.pow(10, groupSize);

			int tempkey = key;
			while (tempkey > 0) {
				hashval += tempkey % divNum;
				tempkey /= divNum;
			}

			while (hashval >= arraySize) {
				hashval %= divNum;
				if (hashval >= arraySize) {
					hashval--;
				}
			}
			// }
			// else {
			// int groupSize = String.valueOf(arraySize-1).length();
			// int divNum = (int) Math.pow(10, groupSize);
			//
			// int tempkey = key;
			// while(tempkey > 0) {
			// hashval += tempkey%divNum;
			// tempkey /= divNum;
			// }
			//
			// if(hashval > arraySize-1) {
			// hashval %= divNum;
			// }
			// }
			return hashval;
		}

		public void insert(dataItem item) {
			int key = item.getKey();
			int hashval = hashFuncFolding(key);
			System.out.println(" at index " + hashval + " of hash table");
			while ((hashArray[hashval] != null) && (hashArray[hashval].getKey() != -1)) {
				++hashval;
				hashval %= arraySize;
			}
			hashArray[hashval] = item;
		}

		/**
		 * @param key
		 * @return
		 */
		public dataItem delete(int key) {
			int hashval = hashFuncFolding(key);
			int count = 0;
			while (hashArray[hashval] != null && count <= getHashTableSize()) {
				if (hashArray[hashval].getKey() == key) {
					dataItem temp = hashArray[hashval];
					hashArray[hashval] = nonItem;
					return temp;
				}
				hashval++;
				hashval %= arraySize;
				count++;
			}
			System.out.println("Couldn't find " + key + " in hash table to delete!");
			return null;
		}

		public int find(int key) {
			int hashval = hashFuncFolding(key);
			int count = 0;
			while (hashArray[hashval] != null && count <= getHashTableSize()) {
				if (hashArray[hashval].getKey() == key) {
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
		hashLinearFolding H = new hashLinearFolding();
		hashLinearFolding.dataItem adataItem;
		int aKey, size, n, keysPerCell;

		System.out.println("Homework Assignment 11.3");
		System.out.print("Enter size of hash table: ");
		size = getInt();
		System.out.print("Enter initial number of items: ");
		n = getInt();
		keysPerCell = 1000;

		hashLinearFolding.hashTable theHT = H.new hashTable(size);

		for (int j = 0; j < n; j++) {
			aKey = (int) (java.lang.Math.random() * keysPerCell * size);
			adataItem = H.new dataItem(aKey);
			System.out.print("Inserting " + aKey);
			theHT.insert(adataItem);
		}

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
				aKey = getInt();
				adataItem = H.new dataItem(aKey);
				System.out.print("Inserting " + aKey);
				theHT.insert(adataItem);
				break;
			case 'd':
				System.out.print("Enter key value to delete: ");
				aKey = getInt();
				theHT.delete(aKey);
				break;
			case 'f':
				System.out.print("Enter key value to find: ");
				aKey = getInt();
				int foundIndex = theHT.find(aKey);
				if (foundIndex != -99) {
					System.out.println("Found " + aKey + " at index " + foundIndex + " of hash table");
				} else {
					System.out.println("Couldn't find key " + aKey + " in hash table!");
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
